package DataLayer.DataAccessObjects.Webservice;

public class AdaptedEmailKontakt {

	private int id;
	private String nachname;
	private String vorname;
	private String email;
	
	
	public void setID(int id) {
		this.id = id;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname ;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNachname() {
		return this.nachname;
	}

	public String getEmail() {
		return this.email;
	}

	public int getID() {
		return this.id;
	}

	public String getVorname() {
		return this.vorname;
	}

}
