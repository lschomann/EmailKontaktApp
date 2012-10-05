/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.Settings;

import java.io.IOException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

/**
 *
 * @author lschomann
 */
public class PersistenceSettings {
    
    private String type;

    public String getPersistenceType() throws ParserConfigurationException, SAXException, 
    IOException, XPathExpressionException{
    	
    	DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
    	domFactory.setNamespaceAware(true);
    	DocumentBuilder docBuilder = domFactory.newDocumentBuilder();
    	Document doc = docBuilder.parse("settings.xml");
    	
    	XPathFactory xpFactory = XPathFactory.newInstance();
    	XPath xpath = xpFactory.newXPath();
    	XPathExpression xpExpr = xpath.compile("//settings[option=)
        return type;
    }
    public void setPersistenceType(String type){
        
    }
    
}
