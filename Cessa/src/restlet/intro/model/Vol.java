package restlet.intro.model;

import java.util.Date;

public class Vol {
	private Aeroport origine;
	private Aeroport destination;
	private Date depart;
	private Integer nb_places;
	
	public Vol(Aeroport origine, Aeroport destination, Date depart,
			Integer nb_places) {
		super();
		this.origine = origine;
		this.destination = destination;
		this.depart = depart;
		this.nb_places = nb_places;
	}
	
	public Aeroport getOrigine() {
		return origine;
	}
	public void setOrigine(Aeroport origine) {
		this.origine = origine;
	}
	public Aeroport getDestination() {
		return destination;
	}
	public void setDestination(Aeroport destination) {
		this.destination = destination;
	}
	public Date getDepart() {
		return depart;
	}
	public void setDepart(Date depart) {
		this.depart = depart;
	}
	public Integer getNb_places() {
		return nb_places;
	}
	public void setNb_places(Integer nb_places) {
		this.nb_places = nb_places;
	}

}
