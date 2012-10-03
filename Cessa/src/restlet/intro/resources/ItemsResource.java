package restlet.intro.resources;

import org.restlet.resource.Get;

public interface ItemsResource {
	@Get
	public String list();

}
