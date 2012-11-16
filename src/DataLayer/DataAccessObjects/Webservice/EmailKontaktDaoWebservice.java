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
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import BusinessObjects.IEmailKontakt;
import DataLayer.BusinessObjects.EmailKontakt;
import DataLayer.DataAccessObjects.IEmailKontaktDAO;
import Exceptions.NoEmailKontaktFoundException;
import Exceptions.NoNextEmailKontaktFoundException;
import Exceptions.NoPreviousEmailKontaktFoundException;

/**
 * This implementation of the IEmailKontaktDAO interface provides the
 * ability to communicate with a web service. The web service will be
 * started on localhost if it can't be found. 
 *
 * @author Lukas Schomann
 * @author Malte Engelhardt
 */
public class EmailKontaktDaoWebservice implements IEmailKontaktDAO{

	private WebResource resource;
	
	
	/**
	 * Initializes the Data Access Object.
	 */
	public void init(){
		// just for testing: set up local server
		if (!ServerHelper.serverResponds(ServerHelper.SERVICE_URL + "/objects/alive")){
			ServerHelper.StartLocalServer();
		}
	}
	
	/**
	 * Takes care of any clean up action when the Data Access Object 
	 * is being garbage collected.
	 */
	protected void finalize() throws Throwable{
		ServerHelper.StopLocalServer();
		super.finalize();
	}
	
	/**
	 * Constructs a singleton instance of the jersey.api.client.WebResource class
	 * to be used for sending requests to the web service.
	 * 
	 * @return The web resource object, representing the web service accessor.
	 */
	private WebResource getResource(){
		if (resource == null){
			resource = Client.create().resource(ServerHelper.SERVICE_URL);
		}
		return resource;
	}
	
	/**
	 * Constructs a singleton instance of the jersey.api.client.AsyncWebResource class
	 * to be used for sending requests to the web service. As the name implies this is
	 * the asynchronous version of *this.getResource()*.
	 * 
	 * @return The async web resource object, representing the web service accessor.
	 */
	@SuppressWarnings("unused")
	private AsyncWebResource getAsyncResource(){ 
		return Client.create().asyncResource(ServerHelper.SERVICE_URL);
	}
	
	/**
	 * Constructs a new IEmailKontakt instance without saving it in the backend.
	 * The instance will have all it's fields initialized with default values.
	 * 
	 * @return The newly created IEmailKontakt instance.
	 */
    @Override
    public IEmailKontakt create() {
    	return new EmailKontakt(0, null, null, null); 
    }

    /**
     * Get all entries from the backend. If there are none, NoEmailKontaktFoundException
     * will be raised.
     * 
     * @return An array of IEmailKontakt instances.
     * @throws NoEmailKontaktFoundException
     */
    @Override
    public IEmailKontakt[] select() throws NoEmailKontaktFoundException {
    	List<IEmailKontakt> objs = new LinkedList<IEmailKontakt>();
    	
    	try{
    		EmailKontaktBean[] beans = getResource().path("objects").path("all")
													.accept(MediaType.APPLICATION_XML)
													.get(EmailKontaktBean[].class);
    		for(EmailKontaktBean bean: beans){
    			objs.add(bean.getContact());
    		}
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
    	return objs.toArray(new IEmailKontakt[objs.size()]);
    }

    /**
     * Get the IEmailKontakt instance with the given *id*. If there is no 
     * instance with the given id, NoEmailKontaktFoundException is raised.
     * 
     * @param id The id of the IEmailKontakt instance to be returned.
     * @return The IEmailKontakt instance with the given id.
     * @throws NoEmailKontaktFoundException
     */
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
    
    /**
     * Get an instance of all IEMailKontakt instances that satisfy the given *criterion*.
     * All string fields (vorname, nachname, email) will be checked whether they _contain_
     * *criterion*. If *criterion* can be converted to a number, the converted int value
     * will be used to compare to the id field of the instance. The lookup on the id field
     * has higher precedence than lookup on the other fields. If the result is empty,
     * NoEmailKontaktFoundException will be raised.
     * 
     * @param criterion A string to looked up on each field of the IEmailKontakt instance.
     * @return An array of IEmailKontakt instances.
     */
    @Override
    public IEmailKontakt[] select(String criterion) throws NoEmailKontaktFoundException {
    	List<IEmailKontakt> objs = new LinkedList<IEmailKontakt>();
    	
    	try{
    		EmailKontaktBean[] beans = getResource().path("objects").path("filter").path(criterion)
													.accept(MediaType.APPLICATION_XML)
													.get(EmailKontaktBean[].class);
    		for(EmailKontaktBean bean: beans){
    			objs.add(bean.getContact());
    		}
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
    	return objs.toArray(new IEmailKontakt[objs.size()]);
    }

    /**
     * Get the first entry from the backend. If there is no entry,
     * raises NoEmailKontaktFoundExcepion.
     * 
     * @return The last IEmailKontakt instance.
     * @throws NoEmailKontaktFoundException
     */
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

    /**
     * Get the last entry from the backend. If there is no entry,
     * raises NoEmailKontaktFoundExcepion.
     * 
     * @return The last IEmailKontakt instance.
     * @throws NoEmailKontaktFoundException
     */
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

    /**
     * Delete the given instance from the backend.
     */
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

    /**
     * Get the {@link IEmailKontakt} instance whose id is the next highest after
     * the given *emailKontakt* instance. If there is no next {@link IEmailKontakt}
     * {@link NoNextEmailKontaktFoundException} will be raised.
     * 
     * @return The next {@link IEmailKontakt} instance.
     * @throws {@link NoNextEmailKontaktFoundException}
     */
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


    /**
     * Get the {@link IEmailKontakt} instance whose id is the next lowest after
     * the given *emailKontakt* instance. If there is no previous {@link IEmailKontakt}
     * {@link NoPreviousEmailKontaktFoundException} will be raised.
     * 
     * @return The next {@link IEmailKontakt} instance.
     * @throws {@link NoPreviousEmailKontaktFoundException}
     */
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

    /**
     * Persist the given *emailKontakt* instance in the backend. If it was saved
     * already and has an id != 0, this results in SQL UPDATE. Otherwise, 
     * SQL INSERT and subsequent setting of the newly (auto-) created id on the 
     * instance.
     */
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
