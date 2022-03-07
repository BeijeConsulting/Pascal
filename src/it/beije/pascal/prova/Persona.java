package it.beije.pascal.prova;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persona")
public class Persona {	
	
	public Persona(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Persona() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	
	@Override
	public String toString() {
		return "Persona [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
}
