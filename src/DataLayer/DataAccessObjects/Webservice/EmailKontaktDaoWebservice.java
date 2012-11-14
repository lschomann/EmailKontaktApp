/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DataAccessObjects.Webservice;

import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.AsyncWebResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import BusinessObjects.IEmailKontakt;
import DataLayer.BusinessObjects.EmailKontakt;
import DataLayer.DataAccessObjects.IEmailKontaktDAO;
import Exceptions.NoEmailKontaktFoundException;
import Exceptions.NoNextEmailKontaktFoundException;
import Exceptions.NoPreviousEmailKontaktFoundException;

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
	
	private AsyncWebResource getAsyncResource(){ 
		return Client.create().asyncResource(ServerHelper.SERVICE_URL);
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
    public IEmailKontakt select(int id) throws NoEmailKontaktFoundException {
    	IEmailKontakt c = null;
    	try{
    		c = getResource().path("objects").path("id").path(String.valueOf(id))
											.accept(MediaType.APPLICATION_XML)
											.get(EmailKontaktBean.class).getContact();
    	}
    	catch(UniformInterfaceException e){
    		int status = e.getResponse().getStatus();
    		if (status == 404){
    			throw new NoEmailKontaktFoundException();
    		} 
    		else if (status == 400){
    			// handle http Bad Request
    			throw new NoEmailKontaktFoundException();
    		} 
    		else if (status == 204){
    			// handle http No Content
    			throw new NoEmailKontaktFoundException();
    		}
    	}
    	return c;
    }
    
    @Override
    public IEmailKontakt[] select(String criterion){
    	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IEmailKontakt first() throws NoEmailKontaktFoundException {
    	IEmailKontakt c = null;
    	try{
	    	c = getResource().path("objects").path("first")
											.accept(MediaType.APPLICATION_XML)
											.get(EmailKontaktBean.class).getContact();
    	}
    	catch(UniformInterfaceException e){
    		int status = e.getResponse().getStatus();
    		if (status == 404){
    			throw new NoEmailKontaktFoundException();
    		}
    		else if (status == 204){
    			throw new NoEmailKontaktFoundException();
    		}
    	}
    	return c;
    }

    @Override
    public IEmailKontakt last() throws NoEmailKontaktFoundException {
    	IEmailKontakt c = null;
    	try{
    		c = getResource().path("objects").path("last")
					.accept(MediaType.APPLICATION_XML)
					.get(EmailKontaktBean.class).getContact();
    	}
    	catch(UniformInterfaceException e){
    		int status = e.getResponse().getStatus();
    		if (status == 404){
    			throw new NoEmailKontaktFoundException();
    		}
    		else if (status == 204){
    			throw new NoEmailKontaktFoundException();
    		}
    	}
    	return c;
    }

    @Override
    public void delete(IEmailKontakt emailKontakt) {
    	try{
	    	EmailKontaktBean b = new EmailKontaktBean();
	    	b.setContact((EmailKontakt)emailKontakt);
	    	
	    	getResource().path("objects").path("delete")
	        	 		 .type(MediaType.APPLICATION_XML)
	        	 		 .put(EmailKontaktBean.class, b);
    	}
    	catch(UniformInterfaceException e){
    		int status = e.getResponse().getStatus();
    		if (status == 404){
    			// handle Not Found
    		}
    		else if (status == 500){
    			// do something on error
    		}
    	}
    }

    @Override
    public IEmailKontakt next(IEmailKontakt emailKontakt) throws NoNextEmailKontaktFoundException{
    	IEmailKontakt c = null;
    	try{
	    	EmailKontaktBean b = new EmailKontaktBean();
	    	b.setContact((EmailKontakt)emailKontakt);
	    	
	    	c = getResource().path("objects").path("next")
							 .type(MediaType.APPLICATION_XML)
							 .post(EmailKontaktBean.class, b).getContact();
    	}
    	catch(UniformInterfaceException e){
    		int status = e.getResponse().getStatus();
    		if (status == 404){
    			throw new NoNextEmailKontaktFoundException();
    		}
    		else if (status == 204){
    			throw new NoNextEmailKontaktFoundException();
    		}
    	}
    	return c;
    }

    @Override
    public IEmailKontakt previous(IEmailKontakt emailKontakt) throws NoPreviousEmailKontaktFoundException {
    	IEmailKontakt c = null;
    	try{
	    	EmailKontaktBean b = new EmailKontaktBean();
	    	b.setContact((EmailKontakt)emailKontakt);
	    	
	    	c = getResource().path("objects").path("previous")
	        			 		 .type(MediaType.APPLICATION_XML)
	        			 		 .post(EmailKontaktBean.class, b).getContact();
    	}
    	catch(UniformInterfaceException e){
    		int status = e.getResponse().getStatus();
    		if (status == 404){
    			throw new NoPreviousEmailKontaktFoundException();
    		}
    		else if (status == 204){
    			throw new NoPreviousEmailKontaktFoundException();
    		}
    	}
    	return c;
    }

    @Override
    public void save(IEmailKontakt emailKontakt) {
    	try{
	    	EmailKontaktBean b = new EmailKontaktBean();
	    	b.setContact((EmailKontakt)emailKontakt);
	    	b = getResource().path("objects").path("save")
	        	 		 .type(MediaType.APPLICATION_XML)
	        	 		 .post(EmailKontaktBean.class, b);
	    	((EmailKontakt)emailKontakt).setID(b.getContact().getID());
    	}
    	catch(UniformInterfaceException e){
    		int status = e.getResponse().getStatus();
    		if (status == 404){
    			// handle Not Found
    		}
    		else if (status == 500){
    			// handle Internal Server Error
    		}
    	}
    }
    
}
