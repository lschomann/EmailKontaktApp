/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DataAccessObjects.Sqlite;

/**
 *
 * @author Malte Engelhardt
 */
public class Filter {
	
	private static String FILTERS_RE = "<=|>=|<|>|=|!=|LIKE|startswith|endswith|contains";
	
	private String key;
	private Object val;
	
	public Filter(String key, Object val){
		this.key = key;
		this.val = val;
	}
	
	
	public String getKey() { 
		return key; 
	}
	
	public Object getFilteredValue(){
		String p = getPredicate().toLowerCase();
		
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
	
	public Object getRawValue() { 
		return val; 
	}
	
	public String getColumnName() { 
		return key.replaceAll(FILTERS_RE, "").trim();
	}
	
	public String getPredicate() { 
		String p = getKey().replaceAll(getColumnName(), "").trim();
		
		// default filter when only column name is supplied
		p = (p.equals("") ? "=" : p);
		
		return p;
	}
}

