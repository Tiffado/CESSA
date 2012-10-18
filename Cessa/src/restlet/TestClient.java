package restlet;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class TestClient {

    /**
     * @param args
     * @throws ResourceException
     */
    public static void main(String[] args) throws Exception {

        ClientResource cr = new ClientResource("http://localhost:8182/customer");

        // Retrieve a representation
        Customer customer = cr.get(Customer.class);
        System.out.println(customer);

        // Update the target resource
        customer.setName("John");
        cr.put(customer);

        // Retrieve the updated version
        customer = cr.get(Customer.class);
        System.out.println(customer);
    }

}
