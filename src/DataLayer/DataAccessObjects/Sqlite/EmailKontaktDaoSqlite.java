/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DataAccessObjects.Sqlite;

import java.util.List;

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

import DataLayer.DataAccessObjects.Sqlite.Filter;


/**
 *
 * @author Lukas Schomann
 * @author Malte Engelhardt
 */
public class EmailKontaktDaoSqlite implements IEmailKontaktDAO{

	private Boolean isInitialized = false;
	
	
	/**
	 * Initializes the Data Access Object.
	 */
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
				save(create());
            }
			isInitialized = true;
//			/*
//			 * ONLY FOR TESTING
//			 */
//			IEmailKontakt donald;
//			donald = create();
//			donald.setVorname("Donald");
//			donald.setNachname("Duck");
//			donald.setEmail("donald@quackburg.com");
//			save(donald);
//			/*
//			 * 
//			 */
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			conn.close();
		}
	}
	
	/**
	 * Takes care of any clean up action when the Data Access Object 
	 * is being garbage collected.
	 */
	protected void finalize() throws Throwable{
		super.finalize();
	}
	
	/**
	 * Drop a table by the given *name* from the Sqlite database.
	 *  
	 * @param name The name of the table to be dropped.
	 * @return Whether the operation was succesful.
	 * @throws SQLException
	 */
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
     * Check whether the table with the given *name* exists in the Sqlite database.
     * 
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
		
        return exists;
    }
	
    /**
     * Get a {@link Connection} object to talk to the Sqlite database.
     * 
     * @return The connection object.
     */
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
     * Creates an instance of a class implementing IEmailKontakt.
     * 
     * @return The newly created instance of IEmailKontakt.  
     */
    @Override
    public IEmailKontakt create() {
        return new EmailKontakt(0, null, null, null);
    }
		
	/**
	 * Get all instances of IEmailKontakt from the Sqlite database
	 * that conform to the parameters *filters*, *orderBy* and *limit*.
	 * 
	 * @param filters An array of Filter instances, to be ANDed together 
	 * 			in the SQL statement's WHERE clause.
	 * @param orderBy The field(s) to sort on. DESC and ASC can be passed as well.
	 * @param limit The limit for the result set. Pass 0 (zero) if unlimited.
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
			String where = (filters == null ? "" : getWhereString(filters));
			conn = getConnection();
			stmt = conn.prepareStatement(
				"SELECT id, vorname, nachname, email FROM kontakte" 
				+ (where.equals("") ? "" : " WHERE " + where)
				+ (orderBy.equals("") ? "" : " ORDER BY " + orderBy)
				+ (limit == 0 ? "" : " LIMIT " + Integer.toString(limit))
			);
			
			if (filters != null){
				int i = 1;
				for(Filter filter: filters){
					stmt.setObject(i, filter.getProcessedValue());
					i++;
				}
			}
            
			rs = stmt.executeQuery();
			while (rs.next()){
	    		objs.add(new EmailKontakt(rs.getInt("id"), rs.getString("vorname"), 
	    							rs.getString("nachname"), rs.getString("email")));
	    	}

	    	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			try{
				rs.close();
				conn.close();
				
				System.out.println(System.getProperty("user.dir"));
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
        return objs.toArray(new IEmailKontakt[objs.size()]);
    }
    
    /**
     * Construct a string suitable for the construction of the SQL statement's
     * WHERE clause.
     * 
     * @param filters An array of filters to be ANDed together.
     * @return The WHERE clause.
     */
    private String getWhereString(Filter[] filters){
		StringBuilder sb = new StringBuilder();
		
        int i = 0;
        for(Filter filter: filters){
			sb.append(String.format("%s %s :%s", filter.getColumnName()
												, filter.getOperator()
												, filter.getColumnName()));
            if (i < filters.length - 1){
                sb.append(" AND ");
            }
			i++;
        }
        return sb.toString();
    }
	
    /**
     * Pack one filter into an Array.
     * 
     * @param filter The Filter instance to be packed.
     * @return
     */
	private Filter[] packFilter(Filter filter){
		return new Filter[] { filter };
	}
	
	/**
	 * Return all IEmailKontakt instances from the Sqlite database.
	 * 
	 */
	@Override
	public IEmailKontakt[] select(){
		return selectBase(null, "", 0);
	}

	/**
     * Get the IEmailKontakt instance with the given *id*. If there is no 
     * instance with the given id, NoEmailKontaktFoundException is raised.
     * 
     * @param id The id of the IEmailKontakt instance to be returned.
     * @return The IEmailKontakt instance with the given id.
     * @throws NoEmailKontaktFoundException
	 */
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
    
    /**
     * Select all IEmailKontakt instances that match a given criterion.
     * All IEmailKontakt instances are checked for whether at least one of it's fields
     * _contains_ *criterion*. If it does, that IEmailKontakt instance will belong to 
     * the result set.  
     * 
     * @param criterion The string to search all IEmailKontakt entries for.
     * @return Array of matching IEmailKontakt instances.
     */
    @Override
    public IEmailKontakt[] select(String criterion) {
		IEmailKontakt[] objs = select();
		List<IEmailKontakt> remaining = new LinkedList<IEmailKontakt>();
		
		int id = -1;
		try{
			id = Integer.parseInt(criterion);
		}
		catch(NumberFormatException e){
			// pass
		}
		
		String crit = criterion.toLowerCase();
		
		for(IEmailKontakt o: objs){
			if ((new Integer(o.getID())).equals(id)){
				remaining.add(o);
				continue;
			}
			
			if ((o.getNachname() == null ? "" : o.getNachname()).toLowerCase().contains(crit) 
					|| (o.getVorname() == null ? "" : o.getVorname()).toLowerCase().contains(crit) 
					|| (o.getEmail() == null ? "" : o.getEmail()).toLowerCase().contains(crit)){
				remaining.add(o);
			}
		}
		
		return remaining.toArray(new IEmailKontakt[remaining.size()]);
    }
	
    /**
     * Get the first entry from the backend. If there is no entry,
     * raises NoEmailKontaktFoundExcepion.
     * 
     * @return The last IEmailKontakt instance.
     * @throws NoEmailKontaktFoundException
     */
    @Override
    public IEmailKontakt first() throws NoEmailKontaktFoundException {
		IEmailKontakt[] objs = selectBase(null, "id ASC", 1);
		
		if (objs.length < 1){
			throw new NoEmailKontaktFoundException();
		}
		
		return objs[0];
    }

    /**
     * Get the last entry from the backend. If there is no entry,
     * raises NoEmailKontaktFoundExcepion.
     * 
     * @return The last IEmailKontakt instance.
     * @throws NoEmailKontaktFoundException
     */
    @Override
    public IEmailKontakt last() throws NoEmailKontaktFoundException{
        IEmailKontakt[] objs = selectBase(null, "id DESC", 1);
		
		if (objs.length < 1){
			throw new NoEmailKontaktFoundException();
		}
		
		return objs[0];
    }

    /**
     * Delete the given instance from the backend.
     */
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

    /**
     * Get the {@link IEmailKontakt} instance whose id is the next highest after
     * the given *emailKontakt* instance. If there is no next {@link IEmailKontakt}
     * {@link NoNextEmailKontaktFoundException} will be raised.
     * 
     * @return The next {@link IEmailKontakt} instance.
     * @throws {@link NoNextEmailKontaktFoundException}
     */
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

    /**
     * Get the {@link IEmailKontakt} instance whose id is the next lowest after
     * the given *emailKontakt* instance. If there is no previous {@link IEmailKontakt}
     * {@link NoPreviousEmailKontaktFoundException} will be raised.
     * 
     * @return The next {@link IEmailKontakt} instance.
     * @throws {@link NoPreviousEmailKontaktFoundException}
     */
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

    /**
     * Persist the given *emailKontakt* instance in the backend. If it was saved
     * already and has an id != 0, this results in SQL UPDATE. Otherwise, 
     * SQL INSERT and subsequent setting of the newly (auto-) created id on the 
     * instance.
     */
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
				stmt.setString(2, emailKontakt.getNachname());
				stmt.setString(3, emailKontakt.getEmail());
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
