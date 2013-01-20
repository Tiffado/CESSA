package fr.paulblouet.cessa.restlet.model;

import fr.paulblouet.cessa.restlet.server.Internal;

@Internal
public class ClassBInt extends ClassA {
	private int other;

	public ClassBInt() {
		super();
	}

	public int getOther() {
		return other;
	}

	public void setOther(int other) {
		this.other = other;
	}

	public static ClassBInt createSample() {
		ClassBInt aBInt = new ClassBInt();
		aBInt.setId("bint");
		return aBInt;
	}
}
