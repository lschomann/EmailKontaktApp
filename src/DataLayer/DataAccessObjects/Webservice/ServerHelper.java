package DataLayer.DataAccessObjects.Webservice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;


/**
 * Implements methods to deal with server setup and teardown chores.
 * 
 * 
 * @author Malte Engelhardt
 *
 */
public class ServerHelper {
	public static String SERVICE_URL = "http://localhost:8080/EmailKontaktVerwaltung";
	private static HttpServer server;
	
	
	/**
	 * Start a local server.
	 */
	public static void StartLocalServer(){
		server = null;
		try {
			server = HttpServerFactory.create(SERVICE_URL);
			server.start();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * Stop the local server that was started through this.StartLocalServer().
	 */
	public static void StopLocalServer(){
		if (server != null){
			server.stop( 0 );
		}
	}
	
	
	/**
	 * Check whether the server at the given URL is responding to requests.
	 * 
	 * @param url The Uniform Resource Locator the server is supposed to be reachable at.
	 * @return Whether the server at the given URL is responding to requests.
	 */
	public static Boolean serverResponds(String url){
		try{
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection conn = 
					(HttpURLConnection)(new URL(url).openConnection());
			conn.setRequestMethod("HEAD");
			int respcode = conn.getResponseCode();
			return (respcode == HttpURLConnection.HTTP_OK);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
