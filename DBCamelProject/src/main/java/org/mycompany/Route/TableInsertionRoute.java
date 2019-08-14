package org.mycompany.Route;

import org.apache.camel.builder.RouteBuilder;
import org.mycompany.Processor.InsertProcessor;

public class TableInsertionRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
         // From SQL retrieval from inbuilt DB
	//	from("direct:insert").log("Inserted new User").to("sql:select * from users").log("Data received is ${body}");
		
		//With jdbc datasource 
		from("direct:insert").log("Inserted new User").process(new InsertProcessor() )
		.to("jdbc:myDataSource").log("operation performed");	
		


	}

}
