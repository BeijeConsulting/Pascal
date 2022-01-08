package it.beije.pascal.rubrica;


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
	
	public String toString() {
		StringBuilder builder = new StringBuilder()
				.append(" cognome : ").append(this.cognome + "\n")
				.append(" nome : ").append(this.nome + "\n")
				.append(" telefono : ").append(this.telefono + "\n")
				.append(" email : ").append(this.email + "\n")
				.append(" note : ").append(this.note + "\n");
		
		return builder.toString();
	}
	
}
