package it.beije.pascal.rubrica;

import java.util.Objects;

public class Contatto {
	
	private String cognome;
	private String nome;
	private String telefono;
	private String email;
	private String note;
	
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
		
	public Contatto() {
		super();
	}
	
	public Contatto(String cognome, String nome, String telefono, String email, String note) {
		super();
		this.cognome = cognome;
		this.nome = nome;
		this.telefono = telefono;
		this.email = email;
		this.note = note;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder()
				.append("{ cognome : ").append(this.cognome)
				.append(", nome : ").append(this.nome)
				.append(", telefono : ").append(this.telefono)
				.append(", email : ").append(this.email)
				.append(", note : ").append(this.note).append(" }");
		
		return builder.toString();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cognome, email, nome, note, telefono);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contatto other = (Contatto) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(email, other.email)
				&& Objects.equals(nome, other.nome) && Objects.equals(note, other.note)
				&& Objects.equals(telefono, other.telefono);
	}
	
	
	
}