package DataLayer.Settings;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

/**
 * SettingsManager is managing the settings stored in settings.xml
 * 
 * @author Lukas Schomann
 */
public class SettingsManager {

	/**
	 * Private class attribute, single instance of this class.
	 */
	private final static SettingsManager instance = new SettingsManager();
	private PersistenceSettings persistenceSettings = new PersistenceSettings();

	/**
	 * Constructor is private, forbidden to instantiate from the outside.
	 * 
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 */
	private SettingsManager() {
		try {
			this.getPersistenceType();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Static method „getInstance()“
	 * 
	 * @return Returns the single instance of the class.
	 */
	public static SettingsManager getInstance() {
		return instance;
	}

	/**
	 * This method parse the persistenceType
	 * 
	 * @return the actual in settings.xml defined {@link #persistenceSettings} as {@link String}
	 * @throws XPathExpressionException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public String getPersistenceType() throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {

		String Settings = persistenceSettings.getPersistenceType();

		return Settings;

	}
}
