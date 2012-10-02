/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

/**
 *
 * @author lschomann
 */
public interface IEmailKontakt {
    public int getID();
    public String getVorname();
    public String getNachname();
    public String getEmail();
    public void setVorname(String Vorname);
    public void setNachname(String Nachname);
    public void setEmail(String Email);
}
