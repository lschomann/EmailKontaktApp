/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DataAccessObjects.Sqlite;

import BusinessObjects.IEmailKontakt;
import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        System.out.println("init");
        EmailKontaktDaoSqlite instance = new EmailKontaktDaoSqlite();
        instance.init();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnection method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        EmailKontaktDaoSqlite instance = new EmailKontaktDaoSqlite();
        Connection expResult = null;
        Connection result = instance.getConnection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        EmailKontaktDaoSqlite instance = new EmailKontaktDaoSqlite();
        IEmailKontakt expResult = null;
        IEmailKontakt result = instance.create();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of select method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testSelect_0args() {
        System.out.println("select");
        EmailKontaktDaoSqlite instance = new EmailKontaktDaoSqlite();
        IEmailKontakt[] expResult = null;
        IEmailKontakt[] result = instance.select();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of select method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testSelect_int() throws Exception {
        System.out.println("select");
        int id = 0;
        EmailKontaktDaoSqlite instance = new EmailKontaktDaoSqlite();
        IEmailKontakt expResult = null;
        IEmailKontakt result = instance.select(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of first method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testFirst() throws Exception {
        System.out.println("first");
        EmailKontaktDaoSqlite instance = new EmailKontaktDaoSqlite();
        IEmailKontakt expResult = null;
        IEmailKontakt result = instance.first();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of last method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testLast() throws Exception {
        System.out.println("last");
        EmailKontaktDaoSqlite instance = new EmailKontaktDaoSqlite();
        IEmailKontakt expResult = null;
        IEmailKontakt result = instance.last();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        IEmailKontakt emailKontakt = null;
        EmailKontaktDaoSqlite instance = new EmailKontaktDaoSqlite();
        instance.delete(emailKontakt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of next method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testNext() throws Exception {
        System.out.println("next");
        IEmailKontakt emailKontakt = null;
        EmailKontaktDaoSqlite instance = new EmailKontaktDaoSqlite();
        IEmailKontakt expResult = null;
        IEmailKontakt result = instance.next(emailKontakt);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of previous method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testPrevious() throws Exception {
        System.out.println("previous");
        IEmailKontakt emailKontakt = null;
        EmailKontaktDaoSqlite instance = new EmailKontaktDaoSqlite();
        IEmailKontakt expResult = null;
        IEmailKontakt result = instance.previous(emailKontakt);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class EmailKontaktDaoSqlite.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        IEmailKontakt emailKontakt = null;
        EmailKontaktDaoSqlite instance = new EmailKontaktDaoSqlite();
        instance.save(emailKontakt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
