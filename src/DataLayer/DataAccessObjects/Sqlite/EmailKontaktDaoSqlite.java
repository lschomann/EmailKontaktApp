/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DataAccessObjects.Sqlite;

import BusinessObjects.IEmailKontakt;
import DataLayer.BusinessObjects.EmailKontakt;
import DataLayer.DataAccessObjects.IEmailKontaktDAO;
import Exceptions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

import java.util.LinkedList;
import java.util.AbstractMap;
import java.util.HashMap;

import DataLayer.DataAccessObjects.Sqlite.Filter;


/**
 *
 * @author lschomann
 * @author Malte Engelhardt
 */
public class EmailKontaktDaoSqlite implements IEmailKontaktDAO{

	private Boolean isInitialized = false;
	
	
	public void init() throws SQLException{		
		if (isInitialized){
			return;
		}
		
		PreparedStatement stmt;
		Connection conn = null;
        
		try{
		    conn = getConnection();
            
            if (!tableExists("kontakte")){

				stmt = conn.prepareStatement("CREATE TABLE kontakte(id integer primary key, vorname, nachname, email);");
				stmt.execute();

            }
			isInitialized = true;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			conn.close();
		}
	}
	
	public Boolean dropTable(String name) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("DROP TABLE " + name);
			stmt.executeUpdate();
			return true;
		}
		catch(SQLException ex){
			throw ex;
		}
		finally{
			conn.close();
		}
	}
    
    /**
     * 
     * @author Malte Engelhardt
     * @param name The name of the db table whose existence is to be checked.
     * @return Whether the table with the supplied name exists in the db.
     * 
     */
    public Boolean tableExists(String name) throws SQLException{
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT name FROM sqlite_master WHERE type='table' AND name='kontakte';"
        );
        stmt.execute();

		boolean exists = stmt.getResultSet().next();
		conn.close();
		
		System.out.println("testSelect_0args: conn status=" + Boolean.toString(conn.isClosed()));
		
        return exists;
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
			ex.printStackTrace();
		}
		return conn;
	}
	
	/**
     * 
     * Creates an instance of a class implementing IEmailKontakt.
     * 
     * @author Malte Engelhardt
     * @return 
     * 
     */
    @Override
    public IEmailKontakt create() {
        return new EmailKontakt(0, null, null, null);
    }
		
	/**
	 * 
	 * @author Malte Engelhardt
	 * @param params
	 * @param orderBy
	 * @param limit
	 * @return Array of objects that implement IEmailKontakt.
	 * 
	 */
    private IEmailKontakt[] selectBase(Filter[] filters
										, String orderBy
										, int limit){
		
		Connection conn = null;
		PreparedStatement stmt;
		ResultSet rs = null;
    	
		LinkedList<IEmailKontakt> objs = new LinkedList<IEmailKontakt>();
		
		try {
			String where = getWhereString(filters);
			conn = getConnection();
			stmt = conn.prepareStatement(
				"SELECT id, vorname, nachname, email FROM kontakte" 
				+ (where.equals("") ? "" : " WHERE " + where)
				+ (orderBy.equals("") ? "" : " ORDER BY " + orderBy)
				+ (limit == 0 ? "" : " LIMIT " + Integer.toString(limit))
			);
			for(int i = 0; i < filters.length; i++){
				stmt.setObject(i + 1, filters[i].getProcessedValue());
			}
            
			rs = stmt.executeQuery();
			while (rs.next()){
	    		objs.add(new EmailKontakt(rs.getInt("id"), rs.getString("vorname"), 
	    							rs.getString("nachname"), rs.getString("email")));
	    	}
		    rs.close();
	    	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			try{
				rs.close();
				conn.close();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
        return objs.toArray(new IEmailKontakt[objs.size()]);
    }
    
    private String getWhereString(Filter[] filters){
        if (filters  == null){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		
        int i = 0;
        for(Filter filter: filters){
			sb.append("%s %s :%s".format(filter.getColumnName()
											, filter.getOperator()
											, filter.getColumnName()));
            if (i < filters.length - 1){
                sb.append(" AND ");
            }
			i++;
        }
        return sb.toString();
    }
	
	private Filter[] packFilter(Filter filter){
		LinkedList<Filter> filters = 
			new LinkedList<Filter>();
		filters.add(filter);
		return filters.toArray(new Filter[filters.size()]);
	}
	
	@Override
	public IEmailKontakt[] select(){
		return selectBase(null, "", 0);
	}

    @Override
    public IEmailKontakt select(int id) throws NoEmailKontaktFoundException{		
    	
		
		IEmailKontakt[] objs = selectBase(
			packFilter(new Filter("id", new Integer(id)))
			, ""
			, 0
		);
	    
        if (objs.length < 1){
            throw new NoEmailKontaktFoundException();
        }
		return objs[0];
    }
	
    @Override
    public IEmailKontakt first() throws NoEmailKontaktFoundException {
		IEmailKontakt[] objs = selectBase(null, "id ASC", 1);
		
		if (objs.length < 1){
			throw new NoEmailKontaktFoundException();
		}
		
		return objs[0];
    }

    @Override
    public IEmailKontakt last() throws NoEmailKontaktFoundException{
        IEmailKontakt[] objs = selectBase(null, "id DESC", 1);
		
		if (objs.length < 1){
			throw new NoEmailKontaktFoundException();
		}
		
		return objs[0];
    }

    @Override
    public void delete(IEmailKontakt emailKontakt){
        Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(
				"DELETE FROM kontakte WHERE id = " + Integer.toString(emailKontakt.getID())
			);
			stmt.executeUpdate();
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		finally{
			try{
				conn.close();
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}
		}
    }

    @Override
    public IEmailKontakt next(IEmailKontakt emailKontakt) throws NoNextEmailKontaktFoundException{
        if (emailKontakt.getID() == 0){
			throw new NoNextEmailKontaktFoundException();
		}
		
		IEmailKontakt[] objs = selectBase(
			packFilter(new Filter("id >", emailKontakt.getID()))
			, "id ASC"
			, 1
		);
		
		if (objs.length < 1){
			throw new NoNextEmailKontaktFoundException();
		}
		
		return objs[0];
    }

    @Override
    public IEmailKontakt previous(IEmailKontakt emailKontakt) throws NoPreviousEmailKontaktFoundException {
		if (emailKontakt.getID() == 0){
			throw new NoPreviousEmailKontaktFoundException();
		}
				
		IEmailKontakt[] objs = selectBase(
			packFilter(new Filter("id <", emailKontakt.getID()))
			, "id DESC"
			, 1
		);
		
		if (objs.length < 1){
			throw new NoPreviousEmailKontaktFoundException();
		}
		
		return objs[0];
    }

    @Override
    public void save(IEmailKontakt emailKontakt){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet generatedKeys;
		
		try{
			conn = getConnection();
			
			// INSERT
			if (emailKontakt.getID() == 0){
				stmt = conn.prepareStatement(
					"INSERT INTO kontakte VALUES(null, ?, ?, ?)"
				);
				
				stmt.setString(1, emailKontakt.getVorname());
				stmt.setString(2, emailKontakt.getNachname());
				stmt.setString(3, emailKontakt.getEmail());
				stmt.executeUpdate();
				
				generatedKeys = stmt.getGeneratedKeys();
				generatedKeys.next();
				((EmailKontakt)emailKontakt).setID(generatedKeys.getInt(1));
			}

			// UPDATE
			else{
				stmt = conn.prepareStatement(
					"UPDATE kontakte SET vorname = ?, nachname = ?, email = ? WHERE id = ?"
				);
				stmt.setString(1, emailKontakt.getVorname());
				stmt.setString(1, emailKontakt.getNachname());
				stmt.setString(1, emailKontakt.getEmail());
				stmt.setInt(4, emailKontakt.getID());
				stmt.executeUpdate();
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		finally{
			try{
				conn.close();
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		 
    }
    
}
