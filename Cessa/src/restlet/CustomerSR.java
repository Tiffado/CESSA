package restlet;

import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class CustomerSR extends ServerResource {
    private static volatile Customer myCustomer = Customer.createSample();

    @Get
    public Customer retrieve() {
        return myCustomer;
    }

    @Put
    public void store(Customer customer) {
        myCustomer = customer;
    }

}
