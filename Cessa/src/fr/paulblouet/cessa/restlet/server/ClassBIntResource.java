package fr.paulblouet.cessa.restlet.server;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import fr.paulblouet.cessa.restlet.model.ClassA;
import fr.paulblouet.cessa.restlet.model.ClassBInt;

public class ClassBIntResource extends ServerResource {
    private static volatile ClassBInt aBInt = ClassBInt.createSample();

    @Get
    public ClassA retrieve() {
        return aBInt;
    }
}