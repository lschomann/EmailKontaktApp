package DataLayer.DataAccessObjects.Webservice;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import BusinessObjects.IEmailKontakt;
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
			b.setContact(getDao().select(id_));
			return b;
		}
		catch(NoEmailKontaktFoundException e){
			return null;
		}
		catch(NumberFormatException e){
			return null;
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("filter/{criterion}")
	public List<EmailKontaktBean> filter(@PathParam("criterion") String criterion){
		try{
			EmailKontaktBean b;
			List<EmailKontaktBean> beans = new LinkedList<EmailKontaktBean>();
			for(IEmailKontakt c: (getDao()).select(criterion)){
				b = new EmailKontaktBean(); 
				b.setContact(c);
				beans.add(b);
			}
			return beans;
		}
		catch(NumberFormatException e){
			// TODO: set response HTTP 204 - No Content
			return null;
		}
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
				b.setContact(c);
				beans.add(b);
			}
		}
		catch(NumberFormatException e){
			// TODO: set response code to HTTP 204 - No Content
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
			b.setContact(c);
			return b;
		}
		catch(NoEmailKontaktFoundException e)
		{
			// TODO: set response code to HTTP 204 - No Content
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
			b.setContact(c);
			return b;
		}
		catch(NoEmailKontaktFoundException e)
		{
			// TODO: set response code to HTTP 204 - No Content
		}
		return null;
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("next/{bean}")
	public EmailKontaktBean next(@PathParam("bean") EmailKontaktBean bean){
		IEmailKontakt c = null;
		try{
			c = (getDao().next(bean.getContact()));
			EmailKontaktBean b = new EmailKontaktBean(); 
			b.setContact(c);
			return b;
		}
		catch(NoNextEmailKontaktFoundException e)
		{
			// TODO: set response code to HTTP 204 - No Content
		}
		return null;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("previous/{bean}")
	public EmailKontaktBean previous(@PathParam("bean") EmailKontaktBean bean){
		IEmailKontakt c = null;
		try{
			c = getDao().previous(bean.getContact());
			EmailKontaktBean b = new EmailKontaktBean(); 
			b.setContact(c);
			return b;
		}
		catch(NoPreviousEmailKontaktFoundException e)
		{
			// TODO: set response code to HTTP 204 - No Content
		}
		return null;
	}
	
	@DELETE
	@Path("delete/{bean}")
	public void delete(@PathParam("bean") EmailKontaktBean bean){
		getDao().delete(bean.getContact());
	}
	
	@POST
	@Path("save/{bean}")
	public void save(@PathParam("bean") EmailKontaktBean bean){
		getDao().save(bean.getContact());
	}
	
	private EmailKontaktDaoSqlite dao;
	private EmailKontaktDaoSqlite getDao(){
		if (dao == null){
			dao = new EmailKontaktDaoSqlite();
		}
		return dao;
	}
}
