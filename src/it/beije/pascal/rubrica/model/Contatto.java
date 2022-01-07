package it.beije.pascal.rubrica.model;

import java.util.List;

public class Contatto {

	private String nome;
	private String cognome;
	private String telefono;
	private String email;
	private String note;

	public Contatto() {
		super();
	}

	public Contatto(String nome, String cognome, String telefono, String email, String note) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.email = email;
		this.note = note;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean equals(Contatto c) {			
		if (nome.equals(c.nome) && cognome.equals(c.cognome) && telefono.equals(c.telefono) && email.equals(c.email)
				&& note.equals(c.note)) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ Nome: ").append(this.nome);
		builder.append(" Cognome: ").append(this.cognome);
		builder.append(" Telefono: ").append(this.telefono);
		builder.append(" Email: ").append(this.email);
		builder.append(" Note: ").append(this.note).append(" }");
		return builder.toString();
	}
	

}
