package DataLayer.DataAccessObjects.Webservice;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.ClientResponse.Status;


import BusinessObjects.IEmailKontakt;
import DataLayer.BusinessObjects.EmailKontakt;
import DataLayer.DataAccessObjects.Sqlite.EmailKontaktDaoSqlite;
import Exceptions.NoEmailKontaktFoundException;
import Exceptions.NoNextEmailKontaktFoundException;
import Exceptions.NoPreviousEmailKontaktFoundException;


/**
 * This class represents a web service to route requests to the appropriate
 * DAO methods. It's methods can be consumed with a properly crafted request 
 * to the URLs resulting from the '@Path' annotations. Return values of the various
 * HTTP request handlers will be serialized to XML. See {@link EmailKontaktDaoWebservice} 
 * for how to consume this service.
 * 
 * @author Malte Engelhardt
 *
 */
@Path("objects")
public class Webservice {
	
	/**
	 * Test if service is alive.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("alive")
	public String alive(){
		return "hallo welt";
	}
	
	/**
	 * Get an instance of IEmailKontakt by the given *id* and return in a serializable 
	 * container instance ({@link EmailKontaktBean}). In case no instance
	 * could be found, returns null, resulting in a HTTP 204 No Content response 
	 * to the requesting client.
	 * 
	 * @param id The id to be looked up.
	 * @return Serializer container with the found IEmailKontakt instance in it.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("id/{id}")
	public EmailKontaktBean getById(@PathParam("id") String id){
		try{
			int id_ = Integer.parseInt(id);
			
			EmailKontaktBean b = new EmailKontaktBean(); 
			b.setContact((EmailKontakt)getDao().select(id_));
			return b;
		}
		catch(NoEmailKontaktFoundException e){
			return null;
		}
		catch(NumberFormatException e){
			throw new WebApplicationException(
			        Response
			          .status(Status.BAD_REQUEST)
			          .entity(e.getMessage())
			          .build()
			      );
		}
	}
	
	/**
	 * Filter all IEmailKontakt instances by the given *criterion*. See method
	 * EmailKontaktDaoSqlite.select(criterion) for details.
	 * 
	 * If no instances were found, returns HTTP 204 No Content.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("filter/{criterion}")
	public EmailKontaktBean[] filter(@PathParam("criterion") String criterion){
		List<EmailKontaktBean> beans = new LinkedList<EmailKontaktBean>();
		try{
			EmailKontaktBean b;
			for(IEmailKontakt c: (getDao()).select(criterion)){
				b = new EmailKontaktBean(); 
				b.setContact((EmailKontakt)c);
				beans.add(b);
			}
		}
		catch(NumberFormatException e){
			// pass. will result in http 204 No Content response
		}
		return beans.toArray(new EmailKontaktBean[beans.size()]);
	}
	
	/**
	 * Returns all IEmailKontakt instances. See method
	 * EmailKontaktDaoSqlite.select() for details.
	 * 
	 * If no instances were found, returns HTTP 204 No Content.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("all")
	public List<EmailKontaktBean> all(){
		List<EmailKontaktBean> beans = null;
		try{
			EmailKontaktBean b;
			beans = new LinkedList<EmailKontaktBean>();
			for(IEmailKontakt c: (getDao()).select()){
				b = new EmailKontaktBean(); 
				b.setContact((EmailKontakt)c);
				beans.add(b);
			}
		}
		catch(NumberFormatException e){
			// pass. will result in http 204 No Content response
		}
		return beans;
	}

	/**
	 * See method EmailKontaktDaoSqlite.first for details.
	 * 
	 * If no instances were found, returns HTTP 204 No Content.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("first")
	public EmailKontaktBean first(){
		IEmailKontakt c = null;
		try{
			c = (getDao().first());
			EmailKontaktBean b = new EmailKontaktBean(); 
			b.setContact((EmailKontakt)c);
			return b;
		}
		catch(NoEmailKontaktFoundException e)
		{
			// pass. will result in http 204 No Content response
		}
		return null;
	}
	
	/**
	 * See method EmailKontaktDaoSqlite.last for details.
	 * 
	 * If no instances were found, returns HTTP 204 No Content.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("last")
	public EmailKontaktBean last(){
		IEmailKontakt c = null;
		try{
			c = (getDao().last());
			EmailKontaktBean b = new EmailKontaktBean(); 
			b.setContact((EmailKontakt)c);
			return b;
		}
		catch(NoEmailKontaktFoundException e)
		{
			// pass. will result in http 204 No Content response
		}
		return null;
	}

	/**
	 * See method EmailKontaktDaoSqlite.next for details.
	 * 
	 * If no instances were found, returns HTTP 204 No Content.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("next")
	public EmailKontaktBean next(EmailKontaktBean bean){
		IEmailKontakt c = null;
		try{
			c = (getDao().next(bean.getContact()));
			EmailKontaktBean b = new EmailKontaktBean(); 
			b.setContact((EmailKontakt)c);
			return b;
		}
		catch(NoNextEmailKontaktFoundException e)
		{
			// pass. will result in http 204 No Content response
		}
		return null;
	}
	
	/**
	 * See method EmailKontaktDaoSqlite.previous for details.
	 * 
	 * If no instances were found, returns HTTP 204 No Content.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("previous")
	public EmailKontaktBean previous(EmailKontaktBean bean){
		IEmailKontakt c = null;
		try{
			c = getDao().previous(bean.getContact());
			EmailKontaktBean b = new EmailKontaktBean(); 
			b.setContact((EmailKontakt)c);
			return b;
		}
		catch(NoPreviousEmailKontaktFoundException e)
		{
			// pass. will result in http 204 No Content response
		}
		return null;
	}
	
	/**
	 * See method EmailKontaktDaoSqlite.delete for details.
	 */
	@PUT
	@Path("delete")
	@Consumes(MediaType.APPLICATION_XML)
	public void delete(EmailKontaktBean bean){
		getDao().delete(bean.getContact());
	}
	
	/**
	 * See method EmailKontaktDaoSqlite.save for details.
	 */
	@POST
	@Path("save")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public EmailKontaktBean save(EmailKontaktBean bean){
		getDao().save(bean.getContact());
		return bean;
	}
	
	private EmailKontaktDaoSqlite dao;
	
	/**
	 * Gets an initialized singleton instance of EmailKontaktDaoSqlite.
	 * @return
	 */
	private EmailKontaktDaoSqlite getDao(){
		if (dao == null){
			dao = new EmailKontaktDaoSqlite();
			try {
				dao.init();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dao;
	}
}
