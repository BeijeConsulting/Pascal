package it.beije.domus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "commerciale")
public class Commerciale {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="ragione_sociale")
	private String ragioneSociale;
	
	@Column(name="nome_ref")
	private String nomeRef;
	
	@Column(name="cognome_ref")
	private String cognomeRef;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="p_iva")
	private String pIva;
	
	@Column(name="indirizzo_id")
	private int indirizzoId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getNomeRef() {
		return nomeRef;
	}

	public void setNomeRef(String nomeRef) {
		this.nomeRef = nomeRef;
	}

	public String getCognomeRef() {
		return cognomeRef;
	}

	public void setCognomeRef(String cognomeRef) {
		this.cognomeRef = cognomeRef;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPIva() {
		return pIva;
	}

	public void setPIva(String pIva) {
		this.pIva = pIva;
	}

	public int getIndirizzoId() {
		return indirizzoId;
	}

	public void setIndirizzoId(int indirizzoId) {
		this.indirizzoId = indirizzoId;
	}

	@Override
	public String toString() {
		return "Commerciale [id=" + id + ", ragione_sociale=" + ragioneSociale + ", nome_ref=" + nomeRef
				+ ", cognome_ref=" + cognomeRef + ", telefono=" + telefono + ", p_iva=" + pIva + ", indirizzo_id="
				+ indirizzoId + "]";
	}
}
