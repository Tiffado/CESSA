/*
 * Copyright 2012 Lionel Bécuwe, Paul Blouët, Fabien Lamarque, Frédéric Lucas
 *
 * This file is part of aca-cessa.
 *
 * aca-cessa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * aca-cessa is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with aca-cessa.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.paulblouet.cessa.restlet.client;

import org.restlet.resource.ClientResource;

import fr.paulblouet.cessa.restlet.model.ClassA;
import fr.paulblouet.cessa.restlet.model.Customer;

public class TestClient {
	public static final String SERVER = "http://localhost:8182/";

    public static void main(String[] args) {
    	test("A");
    	test("BEq");
    	test("BNEq");
    	test("BInt");
    }
    
    private static void test(String clazz) {
    	ClientResource cr = new ClientResource(SERVER + "A");
    	ClassA v = /*ClassA.createSample();
    	cr.put(v);
    	v = */cr.get(ClassA.class);
    	System.out.println(v.getId());
    }
    
    @SuppressWarnings("unused")
	private static void testCustomer() {

        ClientResource cr = new ClientResource(SERVER + "customer");

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
