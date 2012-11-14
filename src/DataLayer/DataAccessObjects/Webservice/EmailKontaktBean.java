package DataLayer.DataAccessObjects.Webservice;

import javax.xml.bind.annotation.XmlRootElement;

import DataLayer.BusinessObjects.EmailKontakt;


@XmlRootElement
public class EmailKontaktBean {
	private EmailKontakt c;
	
	
	public EmailKontaktBean(){

	}
	
	public EmailKontakt getContact(){
		return c;
	}
	
	public void setContact(EmailKontakt c){
		this.c = c;
	}
}
