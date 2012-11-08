
package DataLayer;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import DataLayer.Settings.SettingsManager;


/**
 *
 *
 * @author Lukas Schomann
 */
public final class DataLayerManager {
    
	/** Private class attribute, single instance of this class.
	 */
    private final static DataLayerManager instance = new DataLayerManager();
    private IDataLayer dataLayer;
    
    /** Constructor is private, forbidden to instantiate from the outside. 
     */
    private DataLayerManager(){
        this.getDataLayer();
        
        
    }
    
    /** Static method „getInstance()“ 
     * 
     * @return Returns the only instance of the class.
     */
    public static DataLayerManager getInstance(){
        return instance;
    }
    
    /** Fetch the DataLayer from SettingsManager 
     * 
     * @return 
     */
    public IDataLayer getDataLayer(){
    	SettingsManager _instance = SettingsManager.getInstance();
    	
    	try {
			System.out.println(_instance.getPersistenceType());
		} catch (XPathExpressionException | ParserConfigurationException
				| SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return this.dataLayer;
    }
}
