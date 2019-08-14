package org.mycompany.Route;

import org.apache.camel.builder.RouteBuilder;
import org.mycompany.Bean.BeanTransaction;

public class TransactionRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		BeanTransaction transRoute = new BeanTransaction();

		from("direct:start").transacted().bean(transRoute, "insertUser").
		to("jdbc:myDataSource").log("insertion operation performed").
		bean(transRoute, "selectUser").to("jdbc:myDataSource").log("select operation performed");

	}

}
