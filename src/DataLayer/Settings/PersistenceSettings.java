package DataLayer.Settings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.xml.sax.InputSource;

/**
 * Get and Set the Persistence Setting Type
 *
 * @author lschomann
 */
public class PersistenceSettings {
    
    private String type;

    
    /**
     * @return	type Returns the actual Value 
     */
    public String getPersistenceType() throws XPathExpressionException, FileNotFoundException{
    	
    	XPathFactory xpFactory = XPathFactory.newInstance();
    	XPath xpath = xpFactory.newXPath();
    	
    	Attr result = (Attr) xpath.evaluate("/settings/option/text()",new InputSource(new FileReader("settings.xml")),XPathConstants.NODE);
    	type = result.getValue();
        return type;
    }
    
    /**
     * 
     * @param type Sets the new Value
     * @throws FileNotFoundException 
     * @throws XPathExpressionException 
     */
    public void setPersistenceType(String type) throws XPathExpressionException, FileNotFoundException{
    	
    	XPathFactory xpFactory = XPathFactory.newInstance();
    	XPath xpath = xpFactory.newXPath();
    	
    	Attr result = (Attr) xpath.evaluate("/settings/option/text()",new InputSource(new FileReader("settings.xml")),XPathConstants.NODE);
    	result.setValue(type);
    }
    
}
