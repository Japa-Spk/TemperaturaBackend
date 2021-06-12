package Controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONObject;
import org.threeten.bp.ZoneId;

import com.google.gson.Gson;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;




@Path("/")
public class ControladorService {
	
	@GET
	@Path("/prueba/wins")
	@Produces("text/plain")
	public int getWins() {
		return Controlador.WINS;
	}

	@POST
	@Path("/prueba/wins")
	@Produces("text/plain")
	public int increaseWins() {
		return Controlador.WINS++;
	}
	
	@GET
	@Path("/prueba")
	@Produces("application/json")
	public String getSWins() {
		String msj = "Resultado Total PRueba";
		return msj;
	}
	
	
	@POST
	@Path("/prueba/paises")
	@Produces("application/json")
	public String Paises() {
		String json = new Gson().toJson(Controlador.Paises());
		return json;
	}

	@POST
	@Path("/prueba/ciudades")
	@Produces("application/json")
	public String Ciudades() {
		String json = new Gson().toJson(Controlador.Ciudades());
		return json;
	}
	
	
	@POST
	@Path("/prueba/registros")
	@Produces("application/json")
	public String Registros() {
		String json = new Gson().toJson(Controlador.Registros());
		return json;
	}
	
	
	
	
	@POST
	@Path("/paises")
	@Produces("application/json")
	public String FPaises() throws Exception {
		String json = new Gson().toJson(ControladorFirebase.getPaises());
		return json;
	}
	
	@POST
	@Path("/ciudades")
	@Produces("application/json")
	public String FCiudades() throws Exception {
		String json = new Gson().toJson(ControladorFirebase.getCiudades());
		return json;
	}
	
	@POST
	@Path("/createciudad")
	@Produces("application/json")
	public String setCiudad(String datos) throws Exception {
		System.out.println(datos);
		//String json = new Gson().toJson(ControladorFirebase.getCiudades());
		return datos;
	}
	
	
	@POST
	@Path("/createregistro")
	@Produces("application/json")
	public String setRegistro(String datos) throws Exception {
		System.out.println(datos);
		JSONObject obj = new JSONObject(datos);
		System.out.println(obj.get("fecha"));
		System.out.println(obj.getLong("id"));
		Timestamp ts = new Timestamp(obj.getLong("fecha"));
		Date fecha = new Date(ts.getTime());
		ControladorFirebase.crearRegistro(obj.getLong("id"),fecha, obj.getString("hora"), obj.getInt("humedad"), obj.getInt("id_ciudad"), obj.getInt("temperatura"));
		return datos;
	}
	
	
	
	@POST
	@Path("/getCiudadAPI")
	@Produces("application/json")
	public String getCiudad(String datos) throws Exception {
		System.out.println(datos);
		//String json = new Gson().toJson(ControladorFirebase.getCiudades());
        String uri = "http://api.openweathermap.org/data/2.5/weather?q=Espinal&APPID=8790a97dd9c578e899caef563d5b2cd4";
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(uri);
         
        String response = target.request()
                    .accept(MediaType.APPLICATION_JSON)
                    .get(String.class);
         
        System.out.println(response);
		return datos;
	}
	
	

	
}
