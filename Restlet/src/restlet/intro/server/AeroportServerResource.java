package restlet.intro.server;

import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;

import restlet.intro.model.Aeroport;
import restlet.intro.resources.AeroportResource;

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
	
	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());

		router.attachDefault(new Directory(getContext(), "war:///"));
		router.attach("/contacts/123", AeroportServerResource.class);

		return router;
	}
}


