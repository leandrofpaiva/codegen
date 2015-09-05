package br.com.codegen.templates.spring;

import java.util.Iterator;
import java.util.List;

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
		List<TemplateVO> list = xmlUtil
				.parserXml(".\\model.xml");

		Iterator<TemplateVO> iterate = list.iterator();

		String path = ".\\src\\main\\java";

		while (iterate.hasNext()) {
			TemplateVO templateVO = iterate.next();

			BusinessGenerator businessGenerator = new BusinessGenerator(path);
			System.out.println(businessGenerator.execute(templateVO));

			BusinessInterfaceGenerator businessInterfaceGenerator = new BusinessInterfaceGenerator(
					path);
			System.out.println(businessInterfaceGenerator.execute(templateVO));

			EntityGenerator entityGenerator = new EntityGenerator(path);
			System.out.println(entityGenerator.execute(templateVO));

			RepositoryGenerator repositoryGenerator = new RepositoryGenerator(
					path);
			System.out.println(repositoryGenerator.execute(templateVO));

			ServiceGenerator serviceGenerator = new ServiceGenerator(path);
			System.out.println(serviceGenerator.execute(templateVO));
		}
	}

	public static void main(String args[]) {
		new Generator().execute();
	}
}
