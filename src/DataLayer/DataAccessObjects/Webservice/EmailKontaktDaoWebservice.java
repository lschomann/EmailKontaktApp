/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DataAccessObjects.Webservice;

import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import BusinessObjects.IEmailKontakt;
import DataLayer.BusinessObjects.EmailKontakt;
import DataLayer.DataAccessObjects.IEmailKontaktDAO;

/**
 *
 * @author Lukas Schomann
 */
public class EmailKontaktDaoWebservice implements IEmailKontaktDAO{

	private WebResource resource;
	
	
	public void init(){
		// just for testing: set up local server
		if (!ServerHelper.serverResponds(ServerHelper.SERVICE_URL + "/objects/alive")){
			ServerHelper.StartLocalServer();
		}
	}
	
	protected void finalize() throws Throwable{
		ServerHelper.StopLocalServer();
		super.finalize();
	}
	
	private WebResource getResource(){
		if (resource == null){
			resource = Client.create().resource(ServerHelper.SERVICE_URL);
		}
		return resource;
	}
	
    @Override
    public IEmailKontakt create() {
    	return new EmailKontakt(0, null, null, null); 
    }

    @Override
    public IEmailKontakt[] select() {
    	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IEmailKontakt select(int id) {
    	return getResource().path("objects").path("id").path(String.valueOf(id))
    							.accept(MediaType.APPLICATION_XML)
    							.get(EmailKontaktBean.class).getContact();
    }
    
    @Override
    public IEmailKontakt[] select(String criterion){
    	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IEmailKontakt first() {
    	IEmailKontakt c = getResource().path("objects").path("first")
										.accept(MediaType.APPLICATION_XML)
										.get(EmailKontaktBean.class).getContact();
    	return c;
    }

    @Override
    public IEmailKontakt last() {
    	return getResource().path("objects").path("last")
		.accept(MediaType.APPLICATION_XML)
		.get(EmailKontaktBean.class).getContact();
    }

    @Override
    public void delete(IEmailKontakt emailKontakt) {
    	EmailKontaktBean b = new EmailKontaktBean();
    	b.setContact(emailKontakt);
    	getResource().path("objects").path("delete")
        	 		 .type(MediaType.APPLICATION_XML)
        	 		 .delete(EmailKontaktBean.class, b);
    }

    @Override
    public IEmailKontakt next(IEmailKontakt emailKontakt) {
    	EmailKontaktBean b = new EmailKontaktBean();
    	b.setContact(emailKontakt);
    	return getResource().path("objects").path("next")
        			 		 .type(MediaType.APPLICATION_XML)
        			 		 .post(EmailKontaktBean.class, b).getContact();
    }

    @Override
    public IEmailKontakt previous(IEmailKontakt emailKontakt) {
    	EmailKontaktBean b = new EmailKontaktBean();
    	b.setContact(emailKontakt);
    	return getResource().path("objects").path("previous")
        			 		 .type(MediaType.APPLICATION_XML)
        			 		 .post(EmailKontaktBean.class, b).getContact();
    }

    @Override
    public void save(IEmailKontakt emailKontakt) {
    	EmailKontaktBean b = new EmailKontaktBean();
    	b.setContact(emailKontakt);
    	getResource().path("objects").path("save")
        	 		 .type(MediaType.APPLICATION_XML)
        	 		 .post(EmailKontaktBean.class, b);
    }
    
}
