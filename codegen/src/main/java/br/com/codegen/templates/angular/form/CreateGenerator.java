package br.com.codegen.templates.angular.form;

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
public class CreateGenerator {
	private VelocityEngine velocityEngine;

	private StringUtil utils;

	private String path;

	public CreateGenerator(String path) {
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
			context.put("formName", templateVO.getClassName());
			context.put("formList", templateVO.getList());

			// Execute the template
			StringWriter stringWriter = new StringWriter();
			velocityEngine.mergeTemplate("templates/angular/form/create.vm", "utf-8",
					context, stringWriter);

			// criando o diretorio caso seja necessário
			String filename = path + "/webapp/" + templateVO.getClassName()
					+ "Create.html";

			File dir = new File(filename);
			if (!dir.getParentFile().exists()) {
				dir.getParentFile().mkdirs();
			}

			FileWriter fileWriter = new FileWriter(filename);
			velocityEngine.mergeTemplate("templates/angular/form/create.vm", "utf-8",
					context, fileWriter);
			fileWriter.close();

			// Return the result
			return stringWriter.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
