package fr.paulblouet.cessa.restlet.server;

import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import fr.paulblouet.cessa.restlet.model.ClassA;

public class ClassAResource extends ServerResource {
    private static volatile ClassA aA = ClassA.createSample();

    @Get
    public ClassA retrieve() {
        return aA;
    }

    @Put
    public void store(ClassA newA) {
        aA = newA;
    }

}
