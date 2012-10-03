package restlet.intro.server;

import java.util.Collection;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import restlet.intro.model.Aeroport;
import restlet.intro.resources.ItemsResource;
import util.Factory;

public class AeroportsServerResource extends ServerResource implements ItemsResource {

	private static Aeroport paris = new Aeroport("Paris");
	private static Aeroport nantes = new Aeroport("Nantes");
	private static Collection<Aeroport> all = Factory.createCollection();

	//TODO delete when client is created
	public static void init() {
		all.add(paris);
		all.add(nantes);
	}
	
	@Override
	@Get
	public String list() {
		StringBuffer buf = new StringBuffer();
		for (Aeroport a: all) {
			buf.append(a.getNom());
			buf.append('\n');
		}
		return buf.toString();
	}
}
