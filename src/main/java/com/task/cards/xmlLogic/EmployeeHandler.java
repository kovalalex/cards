package com.task.cards.xmlLogic;


import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;


import org.xml.sax.SAXException;
/*
* Обязательные поля: Имя, Фамилия, Отчество, Должность
* Списки email и телефонов необязательны
* format.xsd в package upload, использум при проверке
* */

public class EmployeeHandler  {


    /**
     * Метод валидации XML с помощью XML и XSD файлов.
     *
     * @param xml XML File
     * @param xsd  XSD File
     * @return boolean, valid or not
     */
    public static boolean validateXMLByXSD(File xml, File xsd) {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(xsd)
                    .newValidator()
                    .validate(new StreamSource(xml));
        } catch (Exception e) {

            return false;
        }
        return true;
    }

    /**
     * Парсим XML с помощью DOM.
     *
     * @param xmlFile XML File
     *
     * @return arrayList
     *
     */

    public static ArrayList<Employee> makeTableFromXML (File xmlFile) throws SAXException, IOException, ParserConfigurationException{
        ArrayList<Employee> employees = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("user");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String uid = elem.getAttribute("id");

                Node node1 = elem.getElementsByTagName("firstname").item(0);
                String fname = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("lastname").item(0);
                String lname = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("secondname").item(0);
                String sname = node3.getTextContent();

                Node node4 = elem.getElementsByTagName("position").item(0);
                String position = node4.getTextContent();

                Employee newEmployee = new Employee(fname,lname,sname,position);

                NodeList emailNodeList = elem.getElementsByTagName("email");
                ArrayList<String> emailList = getArrayListFromNodelList(emailNodeList);
                newEmployee.setEmailList(emailList);

                NodeList phoneNodeList = elem.getElementsByTagName("phone");
                ArrayList<String> phoneList = getArrayListFromNodelList(phoneNodeList);
                newEmployee.setPhoneList(phoneList);


                employees.add(newEmployee);
            }
        }

        return employees;

    }

    public static ArrayList<String> getArrayListFromNodelList(NodeList nodeList){
        ArrayList<String> result = new ArrayList<>();
        for (int j = 0; j <= nodeList.getLength(); j++) {
            Node node = nodeList.item(j);
            if (node!=null && node.getNodeType() == Node.ELEMENT_NODE) {
                result.add(node.getTextContent());
            }
        }
        return result;
    }
}
