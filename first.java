import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

class first {  
   public static void main(String[] args) {
        BufferedReader reader;
        DocumentBuilder dBuilder;
        DocumentBuilderFactory dbFactory;
        Document doc;

        try {
            reader =  new BufferedReader(new InputStreamReader(System.in));
            File file = new File("sample.xml");
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
            
            doc.getDocumentElement().normalize();
            XPath xPath =  XPathFactory.newInstance().newXPath();
		
            System.out.print("Enter Expression : ");
            String expression=reader.readLine();

            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                System.out.print("\n<" + nNode.getNodeName() + "> ");			
                Element eElement = (Element) nNode;
                System.out.print(eElement.getTextContent());
                System.out.print(" </" + nNode.getNodeName() + "> ");			
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } finally{
            reader = null;
            dBuilder = null;
            dbFactory = null;
            doc = null;
        }
   }
}