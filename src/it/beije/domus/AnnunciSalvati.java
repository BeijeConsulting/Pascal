package it.beije.domus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "annunci_salvati")
public class AnnunciSalvati {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "utente_id")
	private int utenteid;
	private String commento;
	
	@Column(name = "avatar_url")
	private String avatarurl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUtenteid() {
		return utenteid;
	}

	public void setUtenteid(int utenteid) {
		this.utenteid = utenteid;
	}

	public String getCommento() {
		return commento;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}

	public String getAvatarurl() {
		return avatarurl;
	}

	public void setAvatarurl(String avatarurl) {
		this.avatarurl = avatarurl;
	}
	

	
	
}
