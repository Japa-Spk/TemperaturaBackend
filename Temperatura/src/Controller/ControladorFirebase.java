package Controller;

import model.Ciudade;
import model.Pais;
import model.Registro;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import java.util.Date;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import firestore.FireStoreConfig;

public class ControladorFirebase {

	static Firestore db = null;
	public static void inicializarDB() throws IOException {
		System.out.println("Inicializando Base de Datos");
		FireStoreConfig startConfig = new FireStoreConfig();
		db = startConfig.inicializar();
	}
	
	private static ApiFuture<WriteResult> crearDocumento(String coleccion, String documento, Map<String, Object> data) {
		System.out.println("Crear Registro a coleccion "+coleccion+" con documento "+documento+" con objeto");
		DocumentReference docRef = db.collection(coleccion).document(documento);
		ApiFuture<WriteResult> result = docRef.set(data);
		try {
			System.out.println("Tiempo de actualizacion->"+result.get().getUpdateTime());
			return result;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	private static List<QueryDocumentSnapshot> traerDocumentos(String coleccion) throws Exception{
		
		ApiFuture<QuerySnapshot> query = db.collection(coleccion).get();
		List<QueryDocumentSnapshot> documents = query.get().getDocuments();		
		return documents;	
	}
	

	private static ApiFuture<QuerySnapshot> ejecQuery(Query lQuery) throws Exception{
		ApiFuture<QuerySnapshot> query = lQuery.get();		
		return query;	
	}
	
	
	
	public void crearPais(String Nombre, String Descripcion) {
		Map<String, Object> data = new HashMap<>();
		data.put("Nombre", Nombre);
		data.put("Descripcion", Descripcion);
		crearDocumento("Paises",Nombre, data);
	}
	
	
	public static  ApiFuture<WriteResult> crearRegistro(Long Id, Date Fecha, String Hora, Integer Humedad, Integer Id_ciudad, Integer Temperatura) {
		Map<String, Object> data = new HashMap<>();
		data.put("Id", Id);
		data.put("Fecha", new Timestamp(Fecha.getTime()));
		data.put("Hora", Hora);
		data.put("Humedad", Humedad);
		data.put("Id_ciudad", Id_ciudad);
		data.put("Temperatura", Temperatura);
		return crearDocumento("Registros",Id+"", data);
	}
	
	
	public static List<Pais> getPaises() throws Exception{
		List<Pais> listaPaises = new ArrayList<>();
		List<QueryDocumentSnapshot> ListaPaises =  traerDocumentos("Paises");
		CollectionReference ciudades = db.collection("Ciudades");
		for(QueryDocumentSnapshot document: ListaPaises) {
			List<Ciudade> listaCiudades = new ArrayList<>();
		    Pais pais = new Pais();
		    pais.setId(document.getLong("Id").intValue());
		    pais.setNombre(document.getString("Nombre"));
		    pais.setDescripcion(document.getString("Descripcion"));
		    //TRAE CIUDADES DEL PAIS
			System.out.println("Traer Ciudades de pais");
		    Query queryC = ciudades.whereEqualTo("Pais_id", document.getLong("Id").intValue());
		    ApiFuture<QuerySnapshot> queryexeC = ejecQuery(queryC);
		    for(DocumentSnapshot documentCiudad: queryexeC.get().getDocuments()) {
		    	System.out.println("Query de Ciudad ID->"+documentCiudad.getLong("Id").intValue());
		    	Ciudade ciudad = new Ciudade();
			    ciudad.setId(documentCiudad.getLong("Id").intValue());
			    ciudad.setNombre(documentCiudad.getString("Nombre"));
			    listaCiudades.add(ciudad);
		    }
		    pais.setCiudades(listaCiudades);
		    listaPaises.add(pais);
		}
		return listaPaises;
	}
	
	public static List<Ciudade> getCiudades() throws Exception{
		List<Ciudade> listaCiudades = new ArrayList<>();
		List<QueryDocumentSnapshot> ListaCiudades =  traerDocumentos("Ciudades");
		
		CollectionReference cPais = db.collection("Paises");	
		CollectionReference registros = db.collection("Registros");
	
		for(QueryDocumentSnapshot document: ListaCiudades) {
			//TRAER EL PAIS DE LA CIUDAD RECORRIDA
			System.out.println("Query de Pais ID: "+document.getLong("Pais_id").intValue());
			List<Registro> listaRegistros = new ArrayList<>();
			
		    Query query = cPais.whereEqualTo("Id", document.getLong("Pais_id").intValue());
		    ApiFuture<QuerySnapshot> queryexe = ejecQuery(query);
		    Pais pais = new Pais();
		    for(DocumentSnapshot documentPais: queryexe.get().getDocuments()) {
		    	System.out.println("Query de Pais Nombre de pais encontrado "+documentPais.getString("Nombre"));
			    pais.setId(documentPais.getLong("Id").intValue());
			    pais.setNombre(documentPais.getString("Nombre"));
			    pais.setDescripcion(documentPais.getString("Descripcion"));
		    }
		  //FIN TRAER EL PAIS DE LA CIUDAD RECORRIDA
		    
		  //TRAER REGISTROS DE LA CIUDAD RECORRIDA
		  
			System.out.println("Traer Registros de Ciudad");
		    Query queryC = registros.whereEqualTo("Id_ciudad", document.getLong("Id").intValue());
		    ApiFuture<QuerySnapshot> queryexeR = ejecQuery(queryC);
		    for(DocumentSnapshot documentRegistro: queryexeR.get().getDocuments()) {
		    	System.out.println("Query de Registro ID->"+documentRegistro.getLong("Id").intValue());
		    	Registro registro = new Registro();
		    	registro.setId(documentRegistro.getLong("Id").intValue());
			    registro.setTemperatura(documentRegistro.getLong("Temperatura").intValue());
			    registro.setHumedad(documentRegistro.getLong("Humedad").intValue());
			    registro.setFecha(new Date(documentRegistro.getTimestamp("Fecha").getSeconds()*1000));
			    registro.setHora(documentRegistro.getString("Hora"));
			    listaRegistros.add(registro);
		    }
		    
		  //FIN TRAER REGISTROS DE LA CIUDAD RECORRIDA
		    
		  //ARMAR CIUDAD
		    Ciudade ciudad = new Ciudade();
		    ciudad.setId(document.getLong("Id").intValue());
		    ciudad.setNombre(document.getString("Nombre"));
		    ciudad.setDescripcion(document.getString("Descripcion"));
		    ciudad.setImg(document.getString("Img"));
		    ciudad.setPais(pais);
		    ciudad.setRegistros(listaRegistros);
		    listaCiudades.add(ciudad);
		}
		return listaCiudades;
	}

	
	
	
}
