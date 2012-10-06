/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DataAccessObjects.Sqlite;

import BusinessObjects.IEmailKontakt;

import Exceptions.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Malte Engelhardt
 */
public class EmailKontaktDaoSqliteTest {
    
    public EmailKontaktDaoSqliteTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of init method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testInit() throws Exception {
        EmailKontaktDaoSqlite dao = new EmailKontaktDaoSqlite();
        
		if (dao.tableExists("kontakte")){
			dao.dropTable("kontakte");
		}
        assertFalse(dao.tableExists("kontakte"));
        
        dao.init();
        
        assertTrue(dao.tableExists("kontakte"));
        
    }
    
    @Test
    public void testTableExists() throws SQLException{
        EmailKontaktDaoSqlite dao = new EmailKontaktDaoSqlite();
		dao.init();
        
        Connection conn = dao.getConnection();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT name FROM 'sqlite_master' WHERE type='table' AND name='kontakte'"
            );
			stmt.execute();
			Boolean exists_sql = stmt.getResultSet().next();
			stmt.close();
			conn.close();
			Boolean exists_dao = dao.tableExists("kontakte");
            assertEquals(exists_sql, exists_dao);
        }
        catch(SQLException ex){
            ex.printStackTrace();
			fail("an sqlexception has been thrown: " + ex.getMessage());
        }
    }

    /**
     * Test of getConnection method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testGetConnection() throws Exception {
        EmailKontaktDaoSqlite instance = new EmailKontaktDaoSqlite();
		instance.init();
        Connection result = instance.getConnection();
        assertTrue(result instanceof Connection);
		result.close();
    }

    /**
     * Test of create method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testCreate() throws Exception {
        EmailKontaktDaoSqlite dao = new EmailKontaktDaoSqlite();
        dao.init();
		
		IEmailKontakt k = dao.create();
		
		assertEquals(k.getID(), 0);
		assertEquals(k.getVorname(), null);
		assertEquals(k.getNachname(), null);
		assertEquals(k.getEmail(), null);
    }

    /**
     * Test of select method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testSelect_0args() throws SQLException{
		
		System.out.println("testSelect_0args");
		
        EmailKontaktDaoSqlite dao = new EmailKontaktDaoSqlite();
		
		// remove table 'kontakte'
		try{
			if (dao.tableExists("kontakte")){
				if (!dao.dropTable("kontakte")){
					fail("Table 'kontakte' exists but could not be DROPped.");
				}
			}
			assertEquals(dao.tableExists("kontakte"), false);
		}
		catch(SQLException ex){
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		
		dao.init();
		assertTrue(dao.tableExists("kontakte"));
		
		// insert test data
		IEmailKontakt donald;
		donald = dao.create();
		donald.setVorname("Donald");
		donald.setNachname("Duck");
		donald.setEmail("donald@quackburg.com");
		dao.save(donald);
		
		IEmailKontakt daisy;
		daisy = dao.create();
		daisy.setVorname("Daisy");
		daisy.setNachname("Duck");
		daisy.setEmail("daisy@quackburg.com");
		dao.save(daisy);
		
		IEmailKontakt mickey;
		mickey = dao.create();
		mickey.setVorname("Mickey");
		mickey.setNachname("Mouse");
		mickey.setEmail("mickey@disney.com");
		dao.save(mickey);
		
		IEmailKontakt daniel;
		daniel = dao.create();
		daniel.setVorname("Daniel");
		daniel.setNachname("Düsentrieb");
		daniel.setEmail("duesentrieb@quackburg.com");
		dao.save(daniel);
		
		IEmailKontakt[] objs = dao.select();
		
		assertEquals(objs.length, 4);
		assertEquals(objs[0].getID(), donald.getID());
		assertEquals(objs[1].getID(), daisy.getID());
		assertEquals(objs[2].getID(), mickey.getID());
		assertEquals(objs[3].getID(), daniel.getID());
    }

    /**
     * Test of select method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testSelect_int() throws Exception {
		
		System.out.println("testSelect_int");
		
        EmailKontaktDaoSqlite dao = new EmailKontaktDaoSqlite();
		
		// remove table 'kontakte'
		try{
			if (dao.tableExists("kontakte")){
				if (!dao.dropTable("kontakte")){
					fail("Table 'kontakte' exists but could not be DROPped.");
				}
			}
			assertEquals(dao.tableExists("kontakte"), false);
		}
		catch(SQLException ex){
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		
		dao.init();
		
		// insert test data
		IEmailKontakt donald;
		donald = dao.create();
		donald.setVorname("Donald");
		donald.setNachname("Duck");
		donald.setEmail("donald@quackburg.com");
		dao.save(donald);
		
		IEmailKontakt daisy;
		daisy = dao.create();
		daisy.setVorname("Daisy");
		daisy.setNachname("Duck");
		daisy.setEmail("daisy@quackburg.com");
		dao.save(daisy);
		
		IEmailKontakt mickey;
		mickey = dao.create();
		mickey.setVorname("Mickey");
		mickey.setNachname("Mouse");
		mickey.setEmail("mickey@disney.com");
		dao.save(mickey);
		
		IEmailKontakt daniel;
		daniel = dao.create();
		daniel.setVorname("Daniel");
		daniel.setNachname("Düsentrieb");
		daniel.setEmail("duesentrieb@quackburg.com");
		dao.save(daniel);
		
		IEmailKontakt obj = dao.select(2);
		
		assertEquals(obj.getID(), daisy.getID());
		assertEquals(obj.getVorname(), "Daisy");
    }

    /**
     * Test of first method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testFirst() throws Exception {
		
		System.out.println("testFirst");
		
        EmailKontaktDaoSqlite dao = new EmailKontaktDaoSqlite();
		
		// remove table 'kontakte'
		try{
			if (dao.tableExists("kontakte")){
				if (!dao.dropTable("kontakte")){
					fail("Table 'kontakte' exists but could not be DROPped.");
				}
			}
			assertEquals(dao.tableExists("kontakte"), false);
		}
		catch(SQLException ex){
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		
		dao.init();
		
		try{
			dao.first();
		}
		catch(NoEmailKontaktFoundException ex){
			// expected;
		}
		
		// insert test data
		IEmailKontakt donald;
		donald = dao.create();
		donald.setVorname("Donald");
		donald.setNachname("Duck");
		donald.setEmail("donald@quackburg.com");
		dao.save(donald);
		
		IEmailKontakt daisy;
		daisy = dao.create();
		daisy.setVorname("Daisy");
		daisy.setNachname("Duck");
		daisy.setEmail("daisy@quackburg.com");
		dao.save(daisy);
		
		IEmailKontakt mickey;
		mickey = dao.create();
		mickey.setVorname("Mickey");
		mickey.setNachname("Mouse");
		mickey.setEmail("mickey@disney.com");
		dao.save(mickey);
		
		IEmailKontakt daniel;
		daniel = dao.create();
		daniel.setVorname("Daniel");
		daniel.setNachname("Düsentrieb");
		daniel.setEmail("duesentrieb@quackburg.com");
		dao.save(daniel);
		
		IEmailKontakt obj = dao.first();
		
		assertEquals(obj.getID(), donald.getID());
		assertEquals(obj.getVorname(), "Donald");
    }

    /**
     * Test of last method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testLast() throws Exception {
		
		System.out.println("testLast");
		
		
		EmailKontaktDaoSqlite dao = new EmailKontaktDaoSqlite();
		
		// remove table 'kontakte'
		try{
			if (dao.tableExists("kontakte")){
				if (!dao.dropTable("kontakte")){
					fail("Table 'kontakte' exists but could not be DROPped.");
				}
			}
			assertEquals(dao.tableExists("kontakte"), false);
		}
		catch(SQLException ex){
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		
		dao.init();
		
		try{
			dao.last();
		}
		catch(NoEmailKontaktFoundException ex){
			// expected;
		}		
		
		// insert test data
		IEmailKontakt donald;
		donald = dao.create();
		donald.setVorname("Donald");
		donald.setNachname("Duck");
		donald.setEmail("donald@quackburg.com");
		dao.save(donald);
		
		IEmailKontakt daisy;
		daisy = dao.create();
		daisy.setVorname("Daisy");
		daisy.setNachname("Duck");
		daisy.setEmail("daisy@quackburg.com");
		dao.save(daisy);
		
		IEmailKontakt mickey;
		mickey = dao.create();
		mickey.setVorname("Mickey");
		mickey.setNachname("Mouse");
		mickey.setEmail("mickey@disney.com");
		dao.save(mickey);
		
		IEmailKontakt daniel;
		daniel = dao.create();
		daniel.setVorname("Daniel");
		daniel.setNachname("Düsentrieb");
		daniel.setEmail("duesentrieb@quackburg.com");
		dao.save(daniel);
		
		IEmailKontakt obj = dao.last();
		
		assertEquals(obj.getID(), daniel.getID());
		assertEquals(obj.getVorname(), "Daniel");
    }

    /**
     * Test of delete method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testDelete() throws Exception {
        EmailKontaktDaoSqlite dao = new EmailKontaktDaoSqlite();
		dao.init();
		IEmailKontakt k = dao.create();
		
		k.setVorname("Helmut");
		k.setNachname("Hobel");
		k.setEmail("hobel@deutsche-telekom.de");
		dao.save(k);
		
		int id = k.getID();
		
		dao.delete(k);
		
		try{
			dao.select(id);
		}
		catch(NoEmailKontaktFoundException ex){
			// expected
		}
    }

    /**
     * Test of next method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testNext() throws Exception {
		
		System.out.println("testNext");
		
        EmailKontaktDaoSqlite dao = new EmailKontaktDaoSqlite();
		
		// remove table 'kontakte'
		try{
			if (dao.tableExists("kontakte")){
				if (!dao.dropTable("kontakte")){
					fail("Table 'kontakte' exists but could not be DROPped.");
				}
			}
			assertEquals(dao.tableExists("kontakte"), false);
		}
		catch(SQLException ex){
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		
		dao.init();
		
		// insert test data
		IEmailKontakt donald;
		donald = dao.create();
		donald.setVorname("Donald");
		donald.setNachname("Duck");
		donald.setEmail("donald@quackburg.com");
		dao.save(donald);
		
		IEmailKontakt daisy;
		daisy = dao.create();
		daisy.setVorname("Daisy");
		daisy.setNachname("Duck");
		daisy.setEmail("daisy@quackburg.com");
		dao.save(daisy);
		
		IEmailKontakt mickey;
		mickey = dao.create();
		mickey.setVorname("Mickey");
		mickey.setNachname("Mouse");
		mickey.setEmail("mickey@disney.com");
		dao.save(mickey);
		
		IEmailKontakt daniel;
		daniel = dao.create();
		daniel.setVorname("Daniel");
		daniel.setNachname("Düsentrieb");
		daniel.setEmail("duesentrieb@quackburg.com");
		dao.save(daniel);
		
		IEmailKontakt obj = dao.next(mickey);
		
		assertEquals(obj.getID(), daniel.getID());
		assertEquals(obj.getVorname(), "Daniel");
		
		try{
			dao.next(daniel);
		}
		catch(NoNextEmailKontaktFoundException ex){
			// expected;
		}
    }

    /**
     * Test of previous method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testPrevious() throws Exception {
		
		System.out.println("testPrevious");
		
        EmailKontaktDaoSqlite dao = new EmailKontaktDaoSqlite();
		
		// remove table 'kontakte'
		try{
			if (dao.tableExists("kontakte")){
				if (!dao.dropTable("kontakte")){
					fail("Table 'kontakte' exists but could not be DROPped.");
				}
			}
			assertEquals(dao.tableExists("kontakte"), false);
		}
		catch(SQLException ex){
			ex.printStackTrace();
			fail(ex.getMessage());
		}
		
		dao.init();
		
		// insert test data
		IEmailKontakt donald;
		donald = dao.create();
		donald.setVorname("Donald");
		donald.setNachname("Duck");
		donald.setEmail("donald@quackburg.com");
		dao.save(donald);
		
		IEmailKontakt daisy;
		daisy = dao.create();
		daisy.setVorname("Daisy");
		daisy.setNachname("Duck");
		daisy.setEmail("daisy@quackburg.com");
		dao.save(daisy);
		
		IEmailKontakt mickey;
		mickey = dao.create();
		mickey.setVorname("Mickey");
		mickey.setNachname("Mouse");
		mickey.setEmail("mickey@disney.com");
		dao.save(mickey);
		
		IEmailKontakt daniel;
		daniel = dao.create();
		daniel.setVorname("Daniel");
		daniel.setNachname("Düsentrieb");
		daniel.setEmail("duesentrieb@quackburg.com");
		dao.save(daniel);
		
		IEmailKontakt obj = dao.previous(mickey);
		
		assertEquals(obj.getID(), daisy.getID());
		assertEquals(obj.getVorname(), "Daisy");
		
		try{
			dao.previous(donald);
		}
		catch(NoPreviousEmailKontaktFoundException ex){
			// expected;
		}
    }

    /**
     * Test of save method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testSave() {
		
		System.out.println("testSave");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
			
		try{
			EmailKontaktDaoSqlite dao = new EmailKontaktDaoSqlite();
			
			// remove table 'kontakte'
			try{
				if (dao.tableExists("kontakte")){
					if (!dao.dropTable("kontakte")){
						fail("Table 'kontakte' exists but could not be DROPped.");
					}
				}
				assertEquals(dao.tableExists("kontakte"), false);
			}
			catch(Exception ex){
				ex.printStackTrace();
				fail(ex.getMessage());
			}
			
			assertFalse(dao.tableExists("kontakte"));

			dao.init();
			
			assertTrue(dao.tableExists("kontake"));
			
			conn = dao.getConnection();

			stmt = conn.prepareStatement(
				"SELECT COUNT(*), vorname FROM kontakte;"
			);
			stmt.execute();

			// how many rows are in the db already?
			rs = stmt.getResultSet();
			int count = (rs.next() ? rs.getInt(1) : 0);
			
			System.out.println("in testSave: vorname=" + rs.getString("vorname"));
			
			rs.close();
			
			assertEquals(0, count);

			IEmailKontakt k = dao.create();

			// set some values on email kontakt. N.B. the ID will be set 
			// automatically by the DAO object.
			k.setVorname("Herman");
			k.setNachname("Toothrot");
			k.setEmail("herman.toothrot@lucasarts.com");
			dao.save(k);

			// get last row
			stmt = conn.prepareStatement(
				"SELECT COUNT(id), id, vorname, nachname, email FROM kontakte ORDER BY id DESC LIMIT 1"
			);
			rs = stmt.getResultSet();

			assertTrue(rs.next());
			assertEquals(count + 1, rs.getInt(1));
			assertNotSame(rs.getInt("id"), count);
			assertEquals(rs.getString("vorname"), "Herman");
			assertEquals(rs.getString("nachname"), "Toothrot");
			assertEquals(rs.getString("email"), "herman.toothrot@lucasarts.com");
			assertTrue(k.getID() != 0);
		}
		catch(Exception ex){
			ex.printStackTrace();
			fail(ex.getMessage());
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
    }
}
