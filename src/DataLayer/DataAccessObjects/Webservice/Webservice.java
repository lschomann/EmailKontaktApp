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
			return (new EmailKontaktBean((new EmailKontaktDaoSqlite()).select(id_)));
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
			List<EmailKontaktBean> beans = new LinkedList<EmailKontaktBean>();
			for(IEmailKontakt c: (new EmailKontaktDaoSqlite()).select(criterion)){
				beans.add(new EmailKontaktBean(c));
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
			beans = new LinkedList<EmailKontaktBean>();
			for(IEmailKontakt c: (new EmailKontaktDaoSqlite()).select()){
				beans.add(new EmailKontaktBean(c));
			}
		}
		catch(NumberFormatException e){
			// TODO: set response code to HTTP 204 - No Content
		}
		return beans;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("next/{bean}")
	public EmailKontaktBean next(@PathParam("bean") EmailKontaktBean bean){
		IEmailKontakt c = null;
		try{
			c = (new EmailKontaktDaoSqlite().next(bean.getContact()));
			return new EmailKontaktBean(c);
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
			c = (new EmailKontaktDaoSqlite().previous(bean.getContact()));
			return new EmailKontaktBean(c);
		}
		catch(NoPreviousEmailKontaktFoundException e)
		{
			// TODO: set response code to HTTP 204 - No Content
		}
		return null;
	}
}
