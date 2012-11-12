
package DataLayer.Settings;


import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

/**
 *
 * @author Lukas Schomann
 */
public class SettingsManager {
	
	/** Private class attribute, single instance of this class.
	 */
    private final static SettingsManager instance = new SettingsManager();
    private PersistenceSettings persistenceSettings = new PersistenceSettings();
    
    /** Constructor is private, forbidden to instantiate from the outside. 
     * @throws IOException 
     * @throws SAXException 
     * @throws ParserConfigurationException 
     * @throws XPathExpressionException 
     */
    private SettingsManager() {
        try {
			this.getPersistenceType();
		} catch (XPathExpressionException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        catch (ParserConfigurationException e)
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
        catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        catch(SAXException e)
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
    }
    
    
    private PersistenceSettings readPersistenceSettings(){
        return persistenceSettings;
    }
    
    /** Static method „getInstance()“ 
     * 
     * @return Returns the single instance of the class.
     */
    public static SettingsManager getInstance(){
        return instance;
    }
    

    public String getPersistenceType() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
    	
    	String Settings = persistenceSettings.getPersistenceType();

		return Settings;
                
    }
}
