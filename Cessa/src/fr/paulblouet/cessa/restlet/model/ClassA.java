package fr.paulblouet.cessa.restlet.model;

public class ClassA {
	private String id;

	public ClassA() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static ClassA createSample() {
		ClassA aA = new ClassA();
		aA.setId("a");
		return aA;
	}
	
}
