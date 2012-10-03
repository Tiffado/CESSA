package restlet.intro.server;

import org.restlet.resource.ServerResource;

import restlet.intro.model.Aeroport;
import restlet.intro.resources.AeroportResource;

public class AeroportServerResource extends ServerResource implements AeroportResource {

	private static Aeroport aeroport = new Aeroport("Paris");

	public void remove() {
		aeroport = null;
	}

	public String retrieve() {
		return aeroport.getNom();
	}

	public void store(Aeroport aeroport) {
		AeroportServerResource.aeroport = aeroport;
	}
}
