package restlet;

public class Customer {
	private String name;

	public Customer() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Customer createSample() {
		// TODO Auto-generated method stub
		Customer cr = new Customer();
		cr.setName("Jack");
		return cr;
	}
	
	public String toString () {
		return name;
	}

}
