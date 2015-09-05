package br.com.codegen.templates.angular;

import java.util.Iterator;
import java.util.List;

import br.com.codegen.templates.angular.form.CreateGenerator;
import br.com.codegen.templates.angular.form.DeleteGenerator;
import br.com.codegen.templates.angular.form.EditGenerator;
import br.com.codegen.templates.angular.form.ListGenerator;
import br.com.codegen.templates.spring.business.BusinessGenerator;
import br.com.codegen.templates.spring.business.interfaces.BusinessInterfaceGenerator;
import br.com.codegen.templates.spring.entities.EntityGenerator;
import br.com.codegen.templates.spring.repositories.RepositoryGenerator;
import br.com.codegen.templates.spring.services.ServiceGenerator;
import br.com.codegen.util.XmlUtil;
import br.com.codegen.vo.TemplateVO;

public class Generator {

	public void execute() {
		XmlUtil xmlUtil = new XmlUtil();
		List<TemplateVO> list = xmlUtil.parserXml(".\\model.xml");

		Iterator<TemplateVO> iterate = list.iterator();

		String path = "F:/upper";

		while (iterate.hasNext()) {
			TemplateVO templateVO = iterate.next();

			ListGenerator listGenerator = new ListGenerator(path);
			System.out.println(listGenerator.execute(templateVO));

			CreateGenerator createGenerator = new CreateGenerator(path);
			System.out.println(createGenerator.execute(templateVO));

			EditGenerator editGenerator = new EditGenerator(path);
			System.out.println(editGenerator.execute(templateVO));

			DeleteGenerator deleteGenerator = new DeleteGenerator(path);
			System.out.println(deleteGenerator.execute(templateVO));
		}
	}

	public static void main(String args[]) {
		new Generator().execute();
	}
}
