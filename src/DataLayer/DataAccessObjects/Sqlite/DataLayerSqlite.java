/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DataAccessObjects.Sqlite;

import java.sql.SQLException;

import DataLayer.DataAccessObjects.IEmailKontaktDAO;
import DataLayer.IDataLayer;

/**
 *
 * @author lschomann
 */
public class DataLayerSqlite implements IDataLayer{

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
