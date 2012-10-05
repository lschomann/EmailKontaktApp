/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DataAccessObjects.Sqlite;

import BusinessObjects.IEmailKontakt;
import DataLayer.BusinessObjects.EmailKontakt;
import DataLayer.DataAccessObjects.IEmailKontaktDAO;
import Exceptions.*;

import java.sql.*;
import java.util.LinkedList;

/**
 *
 * @author lschomann
 */
public class EmailKontaktDaoSqlite implements IEmailKontaktDAO{

	public void Init() throws SQLException{
		
		Connection conn = null;
		try{
		    conn = getConnection();
		    Statement stat = conn.createStatement();
		    stat.executeUpdate("CREATE TABLE kontakte(id integer primary key, vorname, nachname, email);");
		    PreparedStatement stmt = conn.prepareStatement("insert into kontakte values (null, ?, ?, ?);");
		    
            stmt.setString(2,"Richard");
            stmt.setString(3,"Lionheart");
            stmt.setString(4,"rich@king.co.uk");
            stmt.addBatch();
            
            stmt.execute();
		    
		    conn.commit();
		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
		finally{
			conn.close();
		}
	}
	
	public Connection getConnection(){
		String connstr = "jdbc:sqlite:kontakte_db";
		String user = "master";
		String pw = "1234";
		
		Connection conn = null;
		
		try{
			Class.forName("org.sqlite.JDBC");
		    conn = DriverManager.getConnection(connstr, user, pw);
		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
		return conn;
	}
	
	
    @Override
    public IEmailKontakt create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IEmailKontakt[] select() {
    	Statement stmt;
    	ResultSet rs;
    	
    	LinkedList<IEmailKontakt> kontakte = new LinkedList<IEmailKontakt>();
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery("SELECT * FROM kontakte;");
			while (rs.next()){
	    		kontakte.add(new EmailKontakt(rs.getInt("id"), rs.getString("vorname"), 
	    							rs.getString("nachname"), rs.getString("email")));
	    	}
		    rs.close();
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (IEmailKontakt[]) kontakte.toArray();
    }

    @Override
    public IEmailKontakt select(int id) throws NoEmailKontaktFoundException{
    	Statement stmt;
    	ResultSet rs;
    	
    	LinkedList<IEmailKontakt> kontakte = new LinkedList<IEmailKontakt>();
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery("SELECT * FROM kontakte WHERE id = " + Integer.toString(id));
			while (rs.next()){
	    		kontakte.add(new EmailKontakt(rs.getInt("id"), rs.getString("vorname"), 
	    							rs.getString("nachname"), rs.getString("email")));
	    	}
		    rs.close();
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (kontakte.size() < 1){
            throw new Exceptions.NoEmailKontaktFoundException();
        }
		return kontakte.getFirst();
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
