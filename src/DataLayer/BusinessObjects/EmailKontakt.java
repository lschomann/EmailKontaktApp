/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.BusinessObjects;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import DataLayer.DataAccessObjects.Webservice.EmailKontaktAdapter;


/**
 *
 * @author Lukas Schomann
 * @author Malte Engelhardt
 */
@XmlJavaTypeAdapter(EmailKontaktAdapter.class)
public class EmailKontakt implements BusinessObjects.IEmailKontakt{
    private int id;
    private String vorname, nachname, email;
    
    public EmailKontakt(int id, String vorname, String nachname, String email){
    	setID(id);
    	setVorname(vorname);
    	setNachname(nachname);
    	setEmail(email);
    }
    
    public void setID(int id){
        this.id = id;
    }
    
    public int getID() {
       
       return id;
    }
    public String getVorname() {
       
        return  vorname;
    }
    public String getNachname() {
        
        return nachname;
    }
    public String getEmail() {
        
        return email;
    }
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
