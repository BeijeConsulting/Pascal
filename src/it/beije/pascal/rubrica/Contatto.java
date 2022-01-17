package it.beije.pascal.rubrica;

/*
CREATE TABLE `rubrica`.`contatti` (
`id` INT NOT NULL AUTO_INCREMENT,
`cognome` VARCHAR(45) NULL,
`nome` VARCHAR(45) NULL,
`telefono` VARCHAR(20) NULL,
`email` VARCHAR(100) NULL,
`note` VARCHAR(200) NULL,
PRIMARY KEY (`id`));
*/


public class Contatto {
	private int id;
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
				.append("{ cognome : ").append(this.cognome)
				.append(", nome : ").append(this.nome)
				.append(", telefono : ").append(this.telefono)
				.append(", email : ").append(this.email)
				.append(", note : ").append(this.note).append(" }");
		
		return builder.toString();
	}
	
	
}
