package restlet.intro;

import org.restlet.resource.ServerResource;

import restlet.intro.model.Aeroport;

public class AeroportServerResource extends ServerResource implements AeroportResource {

	private static volatile Aeroport aeroport = 
			new Aeroport("Paris");

	public void remove() {
		aeroport = null;
	}

	public Aeroport retrieve() {
		return aeroport;
	}

	public void store(Aeroport aeroport) {
		AeroportServerResource.aeroport = aeroport;
	}
}


