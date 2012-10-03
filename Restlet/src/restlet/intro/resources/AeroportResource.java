package restlet.intro.resources;

import org.restlet.Restlet;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import restlet.intro.model.Aeroport;

public interface AeroportResource {
	@Get
	public Aeroport retrieve();
	
	@Post
	public void store(Aeroport aeroport);
	
	@Delete
	public void remove();

	Restlet createInboundRoot();

}
