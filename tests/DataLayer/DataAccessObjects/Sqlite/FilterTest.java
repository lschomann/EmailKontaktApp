/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DataAccessObjects.Sqlite;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author malte
 */
public class FilterTest {
    
    public FilterTest() {
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
     * Filter.getKey() is a no-op.
     */
    @Test
    public void testGetKey() {
        Filter f;
        f = new Filter("id >", 10);
        assertEquals(f.getKey(), "id >");
        
        // anything can be put inside, so be aware that it can fuck up your 
        // program if you use it insensibly.
        String s = "    hello world...";
        f = new Filter(s, new Object());
        assertEquals(f.getKey(), s);
        
    }

    /**
     * Filter.getProcessedValue() returns the value that was supplied to the 
     * Filter constructor, processed according to the supplied predicate in 
     * the key (see constructor for Filter).
     */
    @Test
    public void testGetProcessedValue() {
        Filter f; 
        
        f = new Filter("name contains", "obi");
        assertEquals(f.getProcessedValue(), "%obi%");
        
        f = new Filter("name startswith", "Rob");
        assertEquals(f.getProcessedValue(), "Rob%");
        
        f = new Filter("name endswith", "bin");
        assertEquals(f.getProcessedValue(), "%bin");
        
        // all other filter choices (=, !=, >=, <=, >, <, LIKE) are no-ops.
        f = new Filter("id >=", 10);
        assertEquals(f.getProcessedValue(), 10);
        
        f = new Filter("id <=", 10);
        assertEquals(f.getProcessedValue(), 10);
        
        f = new Filter("id =", 10);
        assertEquals(f.getProcessedValue(), 10);
        
        f = new Filter("id <", 10);
        assertEquals(f.getProcessedValue(), 10);
        
        f = new Filter("id >", 10);
        assertEquals(f.getProcessedValue(), 10);
        
        f = new Filter("id LIKE", 10);
        assertEquals(f.getProcessedValue(), 10);
        
        f = new Filter("id !=", 10);
        assertEquals(f.getProcessedValue(), 10);
    }

    /**
     * Filter.getRawValue() is a no-op.
     */
    @Test
    public void testGetRawValue() {
        Filter f;
        
        f = new Filter("id >=", 1.1111);
        assertEquals(f.getRawValue(), 1.1111);
        
        Object foo = new Object();
        f = new Filter("bla", foo);
        assertEquals(f.getRawValue(), foo);
    }

    /**
     * Filter.getColumnName() returns the name of the db column specified in
     * the filter's predicate, usable as parameter value in the SQL generation.
     */
    @Test
    public void testGetColumnName() {
        Filter f;
        
        f = new Filter("id <", 10);
        assertEquals(f.getColumnName(), "id");
        
        f = new Filter(" name contains ", 10);
        assertEquals(f.getColumnName(), "name");
        
        // note the trimmed leading and trailing white space
        f = new Filter(" hello world__  \t", 10);
        assertEquals(f.getColumnName(), "hello world__");
    }

    /**
     * Filter.getPredicate() returns only the operator portion
     */
    @Test
    public void testGetOperator() {
        Filter f;
        
        f = new Filter("name contains", "obi");
        assertEquals(f.getOperator(), "contains");
        
        f = new Filter("name startswith", "Rob");
        assertEquals(f.getOperator(), "startswith");
        
        f = new Filter("name endswith", "bin");
        assertEquals(f.getOperator(), "endswith");
        
        // all other filter choices (=, !=, >=, <=, >, <, LIKE) are no-ops.
        f = new Filter("id >=", 10);
        assertEquals(f.getOperator(), ">=");
        
        f = new Filter("id <=", 10);
        assertEquals(f.getOperator(), "<=");
        
        f = new Filter("id =", 10);
        assertEquals(f.getOperator(), "=");
        
        f = new Filter("id <", 10);
        assertEquals(f.getOperator(), "<");
        
        f = new Filter("id >", 10);
        assertEquals(f.getOperator(), ">");
        
        f = new Filter("id LIKE", 10);
        assertEquals(f.getOperator(), "LIKE");
        
        f = new Filter("id !=", 10);
        assertEquals(f.getOperator(), "!=");
        
        // N.B. if there's no explicit operator, '=' (equals) is assumed.
        f = new Filter("id", 10);
        assertEquals(f.getOperator(), "=");
    }
}
