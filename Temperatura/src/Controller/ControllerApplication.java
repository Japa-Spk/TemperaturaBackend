package Controller;

import jakarta.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class ControllerApplication extends ResourceConfig {
	
	public ControllerApplication() {
		packages("Controller");
	}

}
