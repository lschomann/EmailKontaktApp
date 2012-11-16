
package BusinessObjects;

/**
 * Interface for EmailKontakt methods
 *
 * @author Lukas Schomann
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
