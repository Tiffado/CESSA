package fr.paulblouet.cessa.restlet.server;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import fr.paulblouet.cessa.restlet.model.ClassA;
import fr.paulblouet.cessa.restlet.model.ClassBNEq;

public class ClassBNEqResource extends ServerResource {
    private static volatile ClassBNEq aBNEq = ClassBNEq.createSample();

    @Get
    public ClassA retrieve() {
        return aBNEq;
    }
}