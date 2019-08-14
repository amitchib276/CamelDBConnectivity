package org.mycompany.Bean;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;


public class BeanTransaction {

	public void insertUser(Exchange exchange) {

		String input = (String) exchange.getIn().getBody();

		System.out.println("Input to be persisted : " + input);

		String insertQuery = "insert INTO Users values ( 331231,'abc@com','Amit')";

		System.out.println("Insert Query is : " + insertQuery);

		exchange.getIn().setBody(insertQuery);
	}

	public void selectUser(Exchange exchange) {

		String input = (String) exchange.getIn().getBody();

		System.out.println("Input to be persisted : " + input);

		String selectQuery = "select * from  Users where id='331231";

		System.out.println("Select Query : " + selectQuery);

		exchange.getIn().setBody(selectQuery);
	}

}
