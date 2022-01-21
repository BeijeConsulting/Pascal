package it.beije.domus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "annuncio_multiplo")
public class Annuncio_Multiplo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int annuncio_id;
	private int contenuto_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAnnuncio_id() {
		return annuncio_id;
	}
	public void setAnnuncio_id(int annuncio_id) {
		this.annuncio_id = annuncio_id;
	}
	public int getContenuto_id() {
		return contenuto_id;
	}
	public void setContenuto_id(int contenuto_id) {
		this.contenuto_id = contenuto_id;
	}
	
	
}
