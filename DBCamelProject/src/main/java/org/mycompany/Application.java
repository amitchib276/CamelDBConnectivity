/**
 *  Copyright 2005-2018 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package org.mycompany;

import javax.sql.DataSource;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.commons.dbcp2.BasicDataSource;
import org.mycompany.Entity.User;
import org.mycompany.Repo.UserRepository;
import org.mycompany.Route.TableInsertionRoute;
import org.mycompany.Route.TransactionRoute;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

/**
 * A spring-boot application that includes a Camel route builder to setup the
 * Camel routes
 */
@SpringBootApplication
@ImportResource({ "classpath:spring/camel-context.xml" })
public class Application {

	// must have a main method spring-boot can run
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);

		String url = "jdbc:h2:mem:testdb";

		DataSource dataSource = setupDataSource(url);

		SimpleRegistry registry = new SimpleRegistry();

		registry.put("myDataSource", dataSource);

		CamelContext context = new DefaultCamelContext(registry);
//		//Table reading route
		// context.addRoutes(new TableInsertionRoute());

		// Transaction Handling
		context.addRoutes(new TransactionRoute());

		context.start();
//		
//
		ProducerTemplate producerTemplate = context.createProducerTemplate();
//		
//		
	//	producerTemplate.requestBody("direct:insert", "Connecting DB");
		producerTemplate.requestBody("direct:start", "Connecting DB");

//        
		Thread.sleep(10000);

	}

	@Bean
	public CommandLineRunner run(UserRepository userRepository) throws Exception {
		return (String[] args) -> {
			User user1 = new User("John", "john@domain.com");
			User user2 = new User("Julie", "julie@domain.com");
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.findAll().forEach(user -> System.out.println(user));
		};
	}

	private static DataSource setupDataSource(String connectURI) {

		BasicDataSource ds = new BasicDataSource();

		ds.setUsername("sa");

		ds.setDriverClassName("org.h2.Driver");

		ds.setPassword("");

		ds.setUrl(connectURI);

		return ds;

	}

}
