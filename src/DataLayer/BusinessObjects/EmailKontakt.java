/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.BusinessObjects;

/**
 *
 * @author lschomann
 */
public class EmailKontakt implements BusinessObjects.IEmailKontakt{
    private int id;
    private String vorname, nachname, email;
    
    public void setID(int id){
        
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
        
    }
    public void setNachname(String nachname) {
        
    }
    public void setEmail(String email) {
        
    }
}
