package Controller;
import firestore.FireStoreConfig;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.PaisDAO;
import model.Ciudade;
import model.Pais;
import model.Registro;

public class Controlador {
	public static int WINS,LOSSES, TIES;
	public static List<model.Pais> paises;

	
	public static List<Pais> Paises() {
		List<Pais> listaPaises = new ArrayList<>();
	    Pais p1 = new Pais();
	    p1.setId(2);
	    p1.setNombre("Panama");
	    p1.setDescripcion("Panama pais del puerto maritimo y turista");
	    
	    Pais p2 = new Pais();
	    p2.setId(3);
	    p2.setNombre("España");
	    p2.setDescripcion("Pais de futbol y culturas antiguas, perteneciente al continente mas antiguo.");
		
	    listaPaises.add(p1);
	    listaPaises.add(p2);
	  
	    return listaPaises;
	    
	    
	}
	
	
	public static List<Ciudade> Ciudades() {
		List<Ciudade> listaCiudades = new ArrayList<>();
	    Pais p1 = new Pais();
	    p1.setId(1);
	    p1.setNombre("Colombia");
	    p1.setDescripcion("Pais ubicado en el tropico, que tiene costas sobre los oceanos pacifico y atlantico");
	    	    
	    Ciudade c1 = new Ciudade();
	    c1.setId(2);
	    c1.setPais(p1);
	    c1.setNombre("Bogota");
	    c1.setDescripcion("Capital y ciudad mas grande de colombia, tiene alrededor de 8 millones de habitantes.");
	    c1.setRegistros(Registros());
	    
	    Ciudade c2 = new Ciudade();
	    c2.setId(3);
	    c2.setPais(p1);
	    c2.setNombre("Medellin");
	    c2.setDescripcion("Medellín está ubicada en el noroccidente de Colombia sobre la coordillera central con una altitud media de 1495 m.s.n.m. Ubicada en el Valle del Aburrá, está rodeada de montañas y es conocida como la “Ciudad de la eterna primavera” por su clima que, durante todo el año, promedia los 24°C.");
	    c2.setRegistros(Registros());
	    
	    listaCiudades.add(c1);
	    listaCiudades.add(c2);
	  
	    return listaCiudades;
	    
	}
	
	public static List<Registro> Registros() {
		List<Registro> listaRegistros = new ArrayList<>();
	    Registro r1 = new Registro();
	    r1.setId(1);
	    r1.setTemperatura(33);
	    r1.setHumedad(60);
	    r1.setFecha(new Date());
	    r1.setHora("10:20 AM");
	    Registro r2 = new Registro();
	    r2.setId(2);
	    r2.setTemperatura(33);
	    r2.setHumedad(60);
	    r2.setFecha(new Date());
	    r2.setHora("10:59 AM");
	    listaRegistros.add(r1);
	    listaRegistros.add(r2);
	    
	    return listaRegistros;	    
	    
	}

	
	
	
	
	
}
