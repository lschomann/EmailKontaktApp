/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DataAccessObjects.Webservice;

import DataLayer.DataAccessObjects.IEmailKontaktDAO;
import DataLayer.IDataLayer;

/**
 * 
 * @author lschomann
 */
public class DataLayerWebservice implements IDataLayer {

	@Override
	public IEmailKontaktDAO getEmailKontaktDao() {
		EmailKontaktDaoWebservice dao = new EmailKontaktDaoWebservice();

		dao.init();

		return dao;
	}

}
