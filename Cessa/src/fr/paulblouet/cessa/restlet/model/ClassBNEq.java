package fr.paulblouet.cessa.restlet.model;

public class ClassBNEq extends ClassA {
	private int other;

	public ClassBNEq() {
		super();
	}

	public int getOther() {
		return other;
	}

	public void setOther(int other) {
		this.other = other;
	}

	public static ClassBNEq createSample() {
		ClassBNEq aBNEq = new ClassBNEq();
		aBNEq.setId("bneq");
		return aBNEq;
	}

}
