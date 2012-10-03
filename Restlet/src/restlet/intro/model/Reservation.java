package restlet.intro.model;

import java.util.Collection;

import restlet.intro.util.Factory;

public class Reservation {
	private Collection<Vol> vols;

	public Reservation() {
		super();
		this.vols = Factory.newCollection();
	}

	public Collection<Vol> getVols() {
		return vols;
	}

	public void setVols(Collection<Vol> vols) {
		this.vols = vols;
	}

}