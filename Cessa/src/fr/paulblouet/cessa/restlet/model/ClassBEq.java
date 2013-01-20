package fr.paulblouet.cessa.restlet.model;

public class ClassBEq extends ClassA {

	public ClassBEq() {
		super();
	}

	public static ClassBEq createSample() {
		ClassBEq aBEq = new ClassBEq();
		aBEq.setId("beq");
		return aBEq;
	}

}
