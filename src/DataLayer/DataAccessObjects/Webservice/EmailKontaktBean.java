package DataLayer.DataAccessObjects.Webservice;

import javax.xml.bind.annotation.XmlRootElement;

import DataLayer.BusinessObjects.EmailKontakt;


/**
 * 
 * Enable easy serialization of the given EmailKontakt instance.
 * 
 * @author Malte Engelhardt
 *
 */
@XmlRootElement
public class EmailKontaktBean {
	private EmailKontakt c;
	
	
	/**
	 * constructs a brand new instance of EmailKontaktBean class.
	 */
	public EmailKontaktBean(){

	}
	
	/**
	 * Get the EmailKontakt of this container.
	 * @return The EmailKontakt instance of this container.
	 */
	public EmailKontakt getContact(){
		return c;
	}
	
	/**
	 * Set the EmailKontakt instance of this container.
	 * @param c The EmailKontakt instance to be set.
	 */
	public void setContact(EmailKontakt c){
		this.c = c;
	}
}
