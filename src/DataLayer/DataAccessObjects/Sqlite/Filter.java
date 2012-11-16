/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DataAccessObjects.Sqlite;

/**
 * This class encapsulates filter information useful for constructing the WHERE
 * clause of an SQL statement. 
 * 
 * @author Malte Engelhardt
 */
public class Filter {
	
	private static String FILTERS_RE = "<=|>=|<|>|=|!=|LIKE|startswith|endswith|contains";
	
	private String key;
	private Object val;
	
	
	/**
	 * The constructor. Usage: new Filter("id >=", 10);
	 * 
	 * @param key The name of the table field plus any operators, like 
	 * <,<=, => >, =, !=, startswith, endswith, contains, LIKE. Note that 
	 * if you don't pass in an operator explicitly, '=' (equal) is assumed.
	 * @param val The value, the right hand side of the operator.
	 */
	public Filter(String key, Object val){
		this.key = key;
		this.val = val;
	}
	
	
	/**
	 * Return the key as passed in in the constructor.
	 */
	public String getKey() { 
		return key; 
	}
	
	/**
	 * Get the value adapted for insertion in the SQL statement.
	 */
	public Object getProcessedValue(){
		String p = getOperator().toLowerCase();
		
		if (p.equals("startswith")){
			return getRawValue().toString() + "%";
		}
		else if (p.equals("endswith")){
			return "%" + getRawValue().toString();
		}
		else if (p.equals("contains")){
			return "%" + getRawValue().toString() + "%";
		}
		
		return getRawValue();
	}
	
	/**
	 * Get the raw value as passed in in the constructor.
	 * @return
	 */
	public Object getRawValue() { 
		return val; 
	}
	
	/**
	 * Get the column name stripped off any operators.
	 * @return
	 */
	public String getColumnName() { 
		return key.replaceAll(FILTERS_RE, "").trim();
	}
	
	/**
	 * Get the operator. Note that if you pass in no operator, '=' (equal) is assumed.
	 * @return
	 */
	public String getOperator() { 
		String p = getKey().replaceAll(getColumnName(), "").trim();
		
		// default filter when only column name is supplied
		p = (p.equals("") ? "=" : p);
		
		return p;
	}
}

