package it.beije.pascal.esercizi.database.e_commerce;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	@Column(name = "email")
	String email;
	@Column(name = "name")
	String name;
	@Column(name = "surname")
	String surname;
	@Column(name = "password")
	String password;

	public User() {
		super();
	}

	public User(String email, String name, String surname, String password) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder().append("{ id : ").append(this.id).append(", email : ")
				.append(this.email).append(", name : ").append(this.name).append(", surname : ").append(this.surname)
				.append(", password : ").append(this.password).append(" }");

		return builder.toString();
	}

}
