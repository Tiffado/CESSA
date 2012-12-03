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

package fr.paulblouet.cessa.restlet;

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
