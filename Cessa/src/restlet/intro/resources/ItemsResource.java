package restlet.intro.resources;

import org.restlet.resource.Get;

import restlet.intro.model.Aeroport;

public interface ItemsResource {
	@Get
	public Aeroport list();

}
