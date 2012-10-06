/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DataAccessObjects.Sqlite;

import DataLayer.DataAccessObjects.IEmailKontaktDAO;
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
public class DataLayerSqliteTest {
    
    public DataLayerSqliteTest() {
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
     * Test of getEmailKontaktDao method, of class DataLayerSqlite.
     */
    @Test
    public void testGetEmailKontaktDao() {
        DataLayerSqlite instance = new DataLayerSqlite();
        IEmailKontaktDAO result = instance.getEmailKontaktDao();
        assertTrue(result instanceof EmailKontaktDaoSqlite);
    }
}
