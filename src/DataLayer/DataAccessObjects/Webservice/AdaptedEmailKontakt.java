package DataLayer.DataAccessObjects.Webservice;


/**
 * Supplies a no-arg constructor class to be used for serializing EmailKontakt 
 * instances with the {@link EmailKontaktAdapter}. 
 * 
 * @author Malte Engelhardt
 *
 */
public class AdaptedEmailKontakt {

	private int id;
	private String nachname;
	private String vorname;
	private String email;
	
	
	/**
	 * Set the id.
	 * @param id The id.
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Set the first name.
	 * @param vorname The first name.
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname ;
	}

	/**
	 * Set the last name.
	 * @param nachname The last name.
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 * Set the email.
	 * @param email The email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the last name.
	 * @return The last name.
	 */
	public String getNachname() {
		return this.nachname;
	}

	/**
	 * Get the email.
	 * @return The email.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Get the id.
	 * @return The id.
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Get the first name.
	 * @return The first name.
	 */
	public String getVorname() {
		return this.vorname;
	}

}
