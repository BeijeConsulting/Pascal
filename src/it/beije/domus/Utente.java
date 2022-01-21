package it.beije.domus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "utente")
public class Utente {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="avatar_url")
	private String avatar_url;
	
	@Column(name="password")
	private String password;
	
	@Column(name="spam_check")
	private boolean spam_check;
	
	@Column(name="amministratore")
	private boolean amministratore;

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

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSpam_check() {
		return spam_check;
	}

	public void setSpam_check(boolean spam_check) {
		this.spam_check = spam_check;
	}

	public boolean isAmministratore() {
		return amministratore;
	}

	public void setAmministratore(boolean amministratore) {
		this.amministratore = amministratore;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", email=" + email + ", avatar_url=" + avatar_url + ", password=" + password
				+ ", spam_check=" + spam_check + ", amministratore=" + amministratore + "]";
	}
}
