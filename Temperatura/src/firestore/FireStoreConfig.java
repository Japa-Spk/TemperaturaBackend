package firestore;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.api.client.util.Value;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

public class FireStoreConfig {

	public Firestore inicializar() throws IOException {
		var serviceAccount = new FileInputStream("/home/spk/Descargas/temperatura-spk-firebase-adminsdk-jyrwc-c1b038a780.json");
		var credentials = GoogleCredentials.fromStream(serviceAccount);

		var options = FirestoreOptions.newBuilder()
						.setCredentials(credentials).build();

		return options.getService();
	}
}
