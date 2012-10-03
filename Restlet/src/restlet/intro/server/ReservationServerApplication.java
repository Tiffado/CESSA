package restlet.intro.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class ReservationServerApplication extends Application {
	
	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());

		router.attach("/aeroports", AeroportServerResource.class);
		//router.attach("/aeroports/{aeroportName}", AeroportServerResource.class);

		return router;
	}

}