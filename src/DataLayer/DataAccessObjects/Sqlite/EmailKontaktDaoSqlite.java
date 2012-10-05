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


/**
 *
 * @author lschomann, Malte Engelhardt
 */
public class EmailKontaktDaoSqlite implements IEmailKontaktDAO{

	public void Init() throws SQLException{

		PreparedStatement stmt;
		Connection conn = null;
		try{
		    conn = getConnection();
		    
			stmt = conn.prepareStatement("CREATE TABLE kontakte(id integer primary key, vorname, nachname, email);");
			stmt.execute();
			
		    stmt = conn.prepareStatement("INSERT INTO kontakte VALUES (null, ?, ?, ?);");
		    
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
	
	private IEmailKontakt[] selectBase(AbstractMap.SimpleEntry<String, Object> param){
		LinkedList<AbstractMap.SimpleEntry<String, Object>> params = 
			new LinkedList<AbstractMap.SimpleEntry<String, Object>>();
		params.add(param);
		return selectBase((AbstractMap.SimpleEntry[]) params.toArray(), "", 0);
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
    private IEmailKontakt[] selectBase(AbstractMap.SimpleEntry<String, Object>[] params
										, String orderBy
										, int limit){
		
		PreparedStatement stmt;
		ResultSet rs;
    	
		LinkedList<IEmailKontakt> objs = new LinkedList<IEmailKontakt>();
		try {
			String where = getWhereString(params);
			stmt = getConnection().prepareStatement(
				"SELECT id, vorname, nachname, email FROM kontakte" 
				+ (where.equals("") ? "" : " WHERE " + where)
				+ (orderBy.equals("") ? "" : " ORDER BY " + orderBy)
				+ (limit == 0 ? "" : " LIMIT " + Integer.toString(limit))
			);
			for(int i = 0; i < params.length; i++){
				stmt.setObject(i + 1, params[i]);
			}
            
			rs = stmt.executeQuery();
			while (rs.next()){
	    		objs.add(new EmailKontakt(rs.getInt("id"), rs.getString("vorname"), 
	    							rs.getString("nachname"), rs.getString("email")));
	    	}
		    rs.close();
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return (IEmailKontakt[]) objs.toArray();
    }
    
    private String getWhereString(AbstractMap.SimpleEntry<String, Object>[] params){
        if (params == null){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
        
        int i = 0;
        for(AbstractMap.SimpleEntry<String, Object> e: params){
            sb.append(e.getKey() + "=:" + e.getKey());
            if (i < params.length - 1){
                sb.append(" AND ");
            }
			i++;
        }
        return sb.toString();
    }
	
	@Override
	public IEmailKontakt[] select(){
		return selectBase(null, "", 0);
	}

    @Override
    public IEmailKontakt select(int id) throws NoEmailKontaktFoundException{		
    	
		
		IEmailKontakt[] objs = selectBase(
			new AbstractMap.SimpleEntry<String, Object>("id", new Integer(id))
		);
	    
        if (objs.length < 1){
            throw new NoEmailKontaktFoundException();
        }
		return objs[0];
    }
	
    @Override
    public IEmailKontakt first() throws NoEmailKontaktFoundException {
		throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IEmailKontakt last() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(IEmailKontakt emailKontakt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IEmailKontakt next(IEmailKontakt emailKontakt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IEmailKontakt previous(IEmailKontakt emailKontakt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(IEmailKontakt emailKontakt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
