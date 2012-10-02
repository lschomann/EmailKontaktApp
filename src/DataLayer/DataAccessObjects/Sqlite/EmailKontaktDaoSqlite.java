/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DataAccessObjects.Sqlite;

import BusinessObjects.IEmailKontakt;
import DataLayer.DataAccessObjects.IEmailKontaktDAO;
import java.sql.*;

/**
 *
 * @author lschomann
 */
public class EmailKontaktDaoSqlite implements IEmailKontaktDAO{

	public void Init(){
		String connstr = "jdbc:sqlite:kontakte_db";
		String user = "master";
		String pw = "1234";
		
		try{
			
		}
	}
	
    @Override
    public IEmailKontakt create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IEmailKontakt[] select() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IEmailKontakt select(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void first() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void last() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(IEmailKontakt emailKontakt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void next(IEmailKontakt emailKontakt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void previous(IEmailKontakt emailKontakt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(IEmailKontakt emailKontakt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
