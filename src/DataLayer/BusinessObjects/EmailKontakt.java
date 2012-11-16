
package DataLayer.BusinessObjects;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import DataLayer.DataAccessObjects.Webservice.EmailKontaktAdapter;


/**
 * This class handles the EmailKontakt
 *
 * @author Lukas Schomann
 * @author Malte Engelhardt
 */
@XmlJavaTypeAdapter(EmailKontaktAdapter.class)
public class EmailKontakt implements BusinessObjects.IEmailKontakt{
	
    private int id;
    private String vorname, nachname, email;
    
    /**
     * This method creates an {@link #EmailKontakt(int, String, String, String) } object that contains all information that are given to it by Strings.
     * 
     * @param id
     * @param vorname
     * @param nachname
     * @param email
     */
    public EmailKontakt(int id, String vorname, String nachname, String email){
    	setID(id);
    	setVorname(vorname);
    	setNachname(nachname);
    	setEmail(email);
    }
    
    /**
     * Get the {@link #id}
     * 
     * @return id
     */
    public int getID() {
       return id;
    }
    
    /**
     * Get the {@link #vorname}
     * 
     * @return vorname
     */
    public String getVorname() {
        return  vorname;
    }
    
    /**
     * Get the {@link #nachname}
     * 
     * @return nachname
     */
    public String getNachname() {
        return nachname;
    }
    
    /**
     * Get the {@link #email}
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * {@link #id} setter method
     * 
     * @param id
     */
    public void setID(int id){
        this.id = id;
    }
    
    /**
     * {@link #vorname} setter method
     * 
     * @param vorname
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    
    /**
     * {@link #nachname} setter method
     * 
     * @param nachname
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
    
    
    /**
     * {@link #email} setter method
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
