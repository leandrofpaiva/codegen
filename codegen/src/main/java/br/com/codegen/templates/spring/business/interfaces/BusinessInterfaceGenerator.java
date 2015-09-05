package br.com.codegen.templates.spring.business.interfaces;

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
public class BusinessInterfaceGenerator {
	private VelocityEngine velocityEngine;

	private StringUtil utils;

	private final String PACKAGE_NAME = "business.interfaces";
	private final String DIR_NAME = "business\\interfaces";

	private String path;

	public BusinessInterfaceGenerator(String path) {
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
			velocityEngine.mergeTemplate(
					"templates/spring/businessinterface.vm", "utf-8", context,
					stringWriter);

			// criando o diretorio caso seja necessário
			String filename = path + "\\"
					+ templateVO.getPackageName().replaceAll("\\.", "\\\\")
					+ "\\" + DIR_NAME + "\\I" + templateVO.getClassName()
					+ "Business.java";

			File dir = new File(filename);
			if (!dir.getParentFile().exists()) {
				dir.getParentFile().mkdirs();
			}

			FileWriter fileWriter = new FileWriter(filename);
			velocityEngine.mergeTemplate(
					"templates/spring/businessinterface.vm", "utf-8", context,
					fileWriter);
			fileWriter.close();

			// Return the result
			return stringWriter.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
