package fr.paulblouet.cessa.restlet.server;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import fr.paulblouet.cessa.restlet.model.ClassA;
import fr.paulblouet.cessa.restlet.model.ClassBEq;

public class ClassBEqResource extends ServerResource {
    private static volatile ClassBEq aBEq = ClassBEq.createSample();

    @Get
    public ClassA retrieve() {
        return aBEq;
    }

}
