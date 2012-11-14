package DataLayer.DataAccessObjects.Webservice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class ServerHelper {
	public static String SERVICE_URL = "http://localhost:8080/EmailKontaktVerwaltung";
	private static HttpServer server;
	
	
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
	
	public static void StopLocalServer(){
		if (server != null){
			server.stop( 0 );
		}
	}
	
	
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
