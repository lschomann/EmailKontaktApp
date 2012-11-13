package DataLayer.DataAccessObjects.Webservice;

import javax.xml.bind.annotation.XmlRootElement;

import BusinessObjects.IEmailKontakt;


@XmlRootElement
public class EmailKontaktBean {
	private IEmailKontakt c;
	
	
	public EmailKontaktBean(IEmailKontakt c){
		this.c = c;
	}
	
	public IEmailKontakt getContact(){
		return c;
	}
	
	public void setContact(IEmailKontakt c){
		this.c = c;
	}
}
