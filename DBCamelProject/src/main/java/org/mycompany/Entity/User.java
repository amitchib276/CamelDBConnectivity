package org.mycompany.Entity;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "users")
public class User {

	User(){
		
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String email;

	// standard constructors / setters / getters / toString
	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}
}