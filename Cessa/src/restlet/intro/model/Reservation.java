package restlet.intro.model;

import java.util.Collection;

import util.Factory;

public class Reservation {
	private Collection<Vol> vols;

	public Reservation() {
		super();
		this.vols = Factory.createCollection();
	}

	public Collection<Vol> getVols() {
		return vols;
	}

	public void setVols(Collection<Vol> vols) {
		this.vols = vols;
	}

}
