package DataLayer.DataAccessObjects.Webservice;

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



@Path("objects")
public class Webservice {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("alive")
	public String alive(){
		return "hallo welt";
	}
	
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
	
	@PUT
	@Path("delete")
	@Consumes(MediaType.APPLICATION_XML)
	public void delete(EmailKontaktBean bean){
		getDao().delete(bean.getContact());
	}
	
	@POST
	@Path("save")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public EmailKontaktBean save(EmailKontaktBean bean){
		getDao().save(bean.getContact());
		return bean;
	}
	
	private EmailKontaktDaoSqlite dao;
	private EmailKontaktDaoSqlite getDao(){
		if (dao == null){
			dao = new EmailKontaktDaoSqlite();
		}
		return dao;
	}
}
