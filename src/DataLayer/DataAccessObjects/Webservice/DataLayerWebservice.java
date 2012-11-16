
package DataLayer.DataAccessObjects.Webservice;

import DataLayer.DataAccessObjects.IEmailKontaktDAO;
import DataLayer.IDataLayer;

/**
 * This is the DataLayerWebservice object providing the getEmailKontaktDao method for webservice. 
 * 
 * @author Lukas Schomann
 * @author Malte Engelhardt
 */
public class DataLayerWebservice implements IDataLayer {

	/**
	 * This method creates a new EmaiKontaktDaoWebservice object which initializes the Webservice settings.
	 */
	@Override
	public IEmailKontaktDAO getEmailKontaktDao() {
		EmailKontaktDaoWebservice dao = new EmailKontaktDaoWebservice();

		return dao;
	}

}
