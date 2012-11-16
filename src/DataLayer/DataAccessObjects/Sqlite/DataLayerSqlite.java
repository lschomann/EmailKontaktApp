
package DataLayer.DataAccessObjects.Sqlite;

import java.sql.SQLException;

import DataLayer.DataAccessObjects.IEmailKontaktDAO;
import DataLayer.IDataLayer;

/**
 * This is the DataLayerSqlite class providing the getEmailKontaktDao method for sqlite. 
 *
 * @author Lukas Schomann
 * @author Malte Engelhardt
 */
public class DataLayerSqlite implements IDataLayer{
	
 	/**
	 * This method creates a new EmaiKontaktDaoSqlite object and initialize the db.
	 */
    @Override
    public IEmailKontaktDAO getEmailKontaktDao() {
        EmailKontaktDaoSqlite dao = new EmailKontaktDaoSqlite();
        
        try{
            dao.init();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return dao;
    }
    
}
