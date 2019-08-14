package org.mycompany.Processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class InsertProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String input = (String) exchange.getIn().getBody();

		System.out.println("Input to be persisted : " + input);

		String insertQuery = "insert INTO Users values ( 331231,'abc@com','Amit')";

		System.out.println("Insert Query is : " + insertQuery);

		exchange.getIn().setBody(insertQuery);

	}

}
