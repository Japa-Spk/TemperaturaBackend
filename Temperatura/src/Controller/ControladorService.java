package Controller;

import com.google.gson.Gson;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

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

	
}
