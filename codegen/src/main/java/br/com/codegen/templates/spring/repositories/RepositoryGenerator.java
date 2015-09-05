package br.com.codegen.templates.spring.repositories;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import br.com.codegen.util.StringUtil;
import br.com.codegen.vo.TemplateVO;

/**
 * @author javadev
 */
public class RepositoryGenerator {
	private VelocityEngine velocityEngine;

	private StringUtil utils;

	private final String PACKAGE_NAME = "repositories";

	private String path;

	public RepositoryGenerator(String path) {
		try {
			Properties properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream(
					"velocity.properties"));

			// Cria e inicializa o engine do velocity
			velocityEngine = new VelocityEngine(properties);
			utils = new StringUtil();

			this.path = path;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String execute(TemplateVO templateVO) {
		try {
			/* add that list to a VelocityContext */
			VelocityContext context = new VelocityContext();
			context.put("utils", utils);
			context.put("package", templateVO.getPackageName() + "."
					+ PACKAGE_NAME);
			context.put("packageBase", templateVO.getPackageName());
			context.put("className", templateVO.getClassName());
			context.put("list", templateVO.getList());

			// Execute the template
			StringWriter stringWriter = new StringWriter();
			velocityEngine.mergeTemplate("templates/spring/repository.vm",
					"utf-8", context, stringWriter);

			// criando o diretorio caso seja necessário
			String filename = path + "\\"
					+ templateVO.getPackageName().replaceAll("\\.", "\\\\")
					+ "\\" + PACKAGE_NAME + "\\" + templateVO.getClassName()
					+ "Repository.java";

			File dir = new File(filename);
			if (!dir.getParentFile().exists()) {
				dir.getParentFile().mkdirs();
			}

			FileWriter fileWriter = new FileWriter(filename);
			velocityEngine.mergeTemplate("templates/spring/repository.vm",
					"utf-8", context, fileWriter);
			fileWriter.close();

			// Return the result
			return stringWriter.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
