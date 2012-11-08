package DataLayer.Settings;

import java.io.IOException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;



/**
 * Get and Set the Persistence Setting Type
 *
 * @author Lukas Schomann
 */
public class PersistenceSettings {
    
    private String type;

    
    /**
     * This function parses the settings XML file for the currently set persistence type
     * 
     * @return	type Returns the actual persistence type value 
     * @throws XPathExpressionException 
     * @throws ParserConfigurationException 
     * @throws IOException 
     * @throws SAXException 
     * @throws FileNotFoundException 
     */
    public String getPersistenceType() throws ParserConfigurationException, SAXException, 
    IOException, XPathExpressionException  {
    	
    	DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse("settings.xml");

        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        XPathExpression expr 
         = xpath.compile("//settings/option/text()");

        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
       
        type = nodes.item(0).getNodeValue(); 
       

        return type;
    }
    
    /**
     * This function writes the new chosen persistence type to the settings XML file
     * 
     * @param type Sets the new Value
     * @throws FileNotFoundException 
     * @throws XPathExpressionException 
     */
    public void setPersistenceType(String type) throws ParserConfigurationException, SAXException, 
    IOException, XPathExpressionException{
    	
    	DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse("settings.xml");

        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        XPathExpression expr 
         = xpath.compile("//settings/option/text()");
        
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
       
        nodes.item(0).setNodeValue(type);
    }
    
}
