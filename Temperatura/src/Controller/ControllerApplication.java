package Controller;

import jakarta.ws.rs.ApplicationPath;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.glassfish.jersey.server.ResourceConfig;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.WriteResult;

import firestore.FireStoreConfig;

@ApplicationPath("/")
public class ControllerApplication extends ResourceConfig {
	
	public ControllerApplication() throws Exception {
		System.out.println("Metodo iniciado en packages");
		ControladorFirebase.inicializarDB();
		packages("Controller");
	}

}
