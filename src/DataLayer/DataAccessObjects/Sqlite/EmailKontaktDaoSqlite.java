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
		try{
		    Connection conn = getConnection();
		    Statement stat = conn.createStatement();
		    stat.executeUpdate("CREATE TABLE kontakte(id integer primary key, vorname, nachname, email);");
		    PreparedStatement stmt = conn.prepareStatement("insert into kontakte values (null, ?, ?, ?);");
		    
            stmt.setString(2,"Richard");
            stmt.setString(3,"Lionheart");
            stmt.setString(4,"rich@king.co.uk");
            stmt.addBatch();
            
            stmt.execute();
		    
		    conn.commit();
		    conn.close();
		}
	}
	
	public Connection getConnection(){
		String connstr = "jdbc:sqlite:kontakte_db";
		String user = "master";
		String pw = "1234";
		
		try{
			Class.forName("org.sqlite.JDBC");
		    return DriverManager.getConnection("jdbc:sqlite:test.db");
		}
		catch(SQLException ex){
			
		}
		
	}
	
	
    @Override
    public IEmailKontakt create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IEmailKontakt[] select() {
    	ResultSet rs = stat.executeQuery("select * from people;");
    	while (rs.next())
	    {
	      System.out.println("name = " + rs.getString("name"));
	      System.out.println("job = " + rs.getString("occupation"));
	    }
	    rs.close();*/
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
