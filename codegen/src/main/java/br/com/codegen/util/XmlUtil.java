package br.com.codegen.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import br.com.codegen.vo.TemplateVO;

public class XmlUtil {

    public List<TemplateVO> parserXml(String filename) {
        try {
            File file = new File(filename);
            InputStream inputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream, "UTF-8");

            InputSource source = new InputSource(reader);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(source);

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();

            XPathExpression exprClass = xpath.compile("/templates/class");

            NodeList nodesClass = (NodeList) exprClass.evaluate(document,
                    XPathConstants.NODESET);

            // criando a lista de objetos a serem gerados
            List<TemplateVO> list = new ArrayList<TemplateVO>();

            // listando todas as classes mapeadas no XML
            for (int i = 0; i < nodesClass.getLength(); i++) {
                TemplateVO templateVO = new TemplateVO();
                templateVO.setClassName(nodesClass.item(i).getAttributes()
                        .item(0).getNodeValue().toString());
                templateVO.setPackageName(nodesClass.item(i).getAttributes()
                        .item(1).getNodeValue().toString());

                System.out.println("className: " + templateVO.getClassName());
                System.out.println("  package: " + templateVO.getPackageName());

                XPathExpression expr = xpath.compile("/templates/class["
                        + (i + 1) + "]/attribute");

                // evaluate expression result on XML document
                NodeList nodes = (NodeList) expr.evaluate(document,
                        XPathConstants.NODESET);

                // Pegandos os atributos da classe e montando a lista
                ArrayList<Map<String, String>> listAttr = new ArrayList<Map<String, String>>();
                for (int j = 0; j < nodes.getLength(); j++) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("name", nodes.item(j).getAttributes().getNamedItem("name").getNodeValue().toString());
                    map.put("type", nodes.item(j).getAttributes().getNamedItem("type").getNodeValue().toString());
                    map.put("size", nodes.item(j).getAttributes().getNamedItem("size").getNodeValue().toString());
					map.put("constraint", nodes.item(j).getAttributes().getNamedItem("constraint").getNodeValue().toString());
					if (nodes.item(j).getAttributes().getNamedItem("mappedBy") != null) {
						map.put("mappedBy", nodes.item(j).getAttributes().getNamedItem("mappedBy").getNodeValue().toString());
					} else {
						map.put("mappedBy", "");
					}
                    listAttr.add(map);

                    System.out.println(map.get("name"));
                    System.out.println(map.get("type"));
                    System.out.println(map.get("size"));
					System.out.println(map.get("constraint"));
					System.out.println(map.get("mappedBy"));
                }
                
                templateVO.setList(listAttr);

                list.add(templateVO);

                System.out.println("----------------");
            }

            System.out.println();
            
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String args[]) {
        XmlUtil xmlUtil = new XmlUtil();
        xmlUtil.parserXml("C:\\Users\\ts01236\\Desktop\\workspaces\\pesca-ws\\codegen\\src\\main\\resources\\pesca-model.xml");
    }
}