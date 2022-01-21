package it.beije.domus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "annunci_salvati")
public class Annunci_Salvati {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int utente_id;
	private String commento;
	private String avatar_url;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUtente_id() {
		return utente_id;
	}
	public void setUtente_id(int utente_id) {
		this.utente_id = utente_id;
	}
	public String getCommento() {
		return commento;
	}
	public void setCommento(String commento) {
		this.commento = commento;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	
	
	
}
