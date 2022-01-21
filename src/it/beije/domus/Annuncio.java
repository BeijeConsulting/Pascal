package it.beije.domus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import it.beije.domus.enums.Aria_condizionata;
import it.beije.domus.enums.Arredamento;
import it.beije.domus.enums.Classe_energetica;
import it.beije.domus.enums.Condizione;
import it.beije.domus.enums.Giardino;
import it.beije.domus.enums.Riscaldamento;
import it.beije.domus.enums.Stato_rogito;
import it.beije.domus.enums.Tipo_annuncio;
import it.beije.domus.enums.Tipo_immobile;

@Entity
@Table(name = "annuncio")
public class Annuncio {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int venditori_id;
	private int annuncio_multiplo_id;
	private int piantina_id;
	private int indirizzo_id;
	
	private Tipo_immobile tipo_immobile;
	private Tipo_annuncio tipo_annuncio;
	private int prezzo;
	private int mq;
	private int locali;
	private int bagni;
	private int tot_piani;
	private int piano;
	private boolean ascensore;
	private int posti_auto;
	private boolean balcone;
	private boolean terrazzo;
	private Giardino giardino;
	private Condizione condizione;
	private Classe_energetica classe_energetica;
	private Riscaldamento riscaldamento;
	private Aria_condizionata aria_condizionata;
	private Arredamento arredamento;
	private boolean piscina;
	private boolean portineria;
	private int anno_costruzione;
	private Stato_rogito stato_rogito;
	private boolean visita_guidata;
	private String descrizione_lunga;
	private boolean virtual_tour;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVenditori_id() {
		return venditori_id;
	}
	public void setVenditori_id(int venditori_id) {
		this.venditori_id = venditori_id;
	}
	public int getAnnuncio_multiplo_id() {
		return annuncio_multiplo_id;
	}
	public void setAnnuncio_multiplo_id(int annuncio_multiplo_id) {
		this.annuncio_multiplo_id = annuncio_multiplo_id;
	}
	public int getPiantina_id() {
		return piantina_id;
	}
	public void setPiantina_id(int piantina_id) {
		this.piantina_id = piantina_id;
	}
	public Tipo_immobile getTipo_immobile() {
		return tipo_immobile;
	}
	public void setTipo_immobile(Tipo_immobile tipo_immobile) {
		this.tipo_immobile = tipo_immobile;
	}
	public Tipo_annuncio getTipo_annuncio() {
		return tipo_annuncio;
	}
	public void setTipo_annuncio(Tipo_annuncio tipo_annuncio) {
		this.tipo_annuncio = tipo_annuncio;
	}
	public int getIndirizzo_id() {
		return indirizzo_id;
	}
	public void setIndirizzo_id(int indirizzo_id) {
		this.indirizzo_id = indirizzo_id;
	}
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	public int getMq() {
		return mq;
	}
	public void setMq(int mq) {
		this.mq = mq;
	}
	public int getLocali() {
		return locali;
	}
	public void setLocali(int locali) {
		this.locali = locali;
	}
	public int getBagni() {
		return bagni;
	}
	public void setBagni(int bagni) {
		this.bagni = bagni;
	}
	public int getTot_piani() {
		return tot_piani;
	}
	public void setTot_piani(int tot_piani) {
		this.tot_piani = tot_piani;
	}
	public int getPiano() {
		return piano;
	}
	public void setPiano(int piano) {
		this.piano = piano;
	}
	public boolean isAscensore() {
		return ascensore;
	}
	public void setAscensore(boolean ascensore) {
		this.ascensore = ascensore;
	}
	public int getPosti_auto() {
		return posti_auto;
	}
	public void setPosti_auto(int posti_auto) {
		this.posti_auto = posti_auto;
	}
	public boolean isBalcone() {
		return balcone;
	}
	public void setBalcone(boolean balcone) {
		this.balcone = balcone;
	}
	public boolean isTerrazzo() {
		return terrazzo;
	}
	public void setTerrazzo(boolean terrazzo) {
		this.terrazzo = terrazzo;
	}
	public Giardino getGiardino() {
		return giardino;
	}
	public void setGiardino(Giardino giardino) {
		this.giardino = giardino;
	}
	public Condizione getCondizione() {
		return condizione;
	}
	public void setCondizione(Condizione condizione) {
		this.condizione = condizione;
	}
	public Classe_energetica getClasse_energetica() {
		return classe_energetica;
	}
	public void setClasse_energetica(Classe_energetica classe_energetica) {
		this.classe_energetica = classe_energetica;
	}
	public Riscaldamento getRiscaldamento() {
		return riscaldamento;
	}
	public void setRiscaldamento(Riscaldamento riscaldamento) {
		this.riscaldamento = riscaldamento;
	}
	public Aria_condizionata getAria_condizionata() {
		return aria_condizionata;
	}
	public void setAria_condizionata(Aria_condizionata aria_condizionata) {
		this.aria_condizionata = aria_condizionata;
	}
	public Arredamento getArredamento() {
		return arredamento;
	}
	public void setArredamento(Arredamento arredamento) {
		this.arredamento = arredamento;
	}
	public boolean isPiscina() {
		return piscina;
	}
	public void setPiscina(boolean piscina) {
		this.piscina = piscina;
	}
	public boolean isPortineria() {
		return portineria;
	}
	public void setPortineria(boolean portineria) {
		this.portineria = portineria;
	}
	public int getAnno_costruzione() {
		return anno_costruzione;
	}
	public void setAnno_costruzione(int anno_costruzione) {
		this.anno_costruzione = anno_costruzione;
	}
	public Stato_rogito getStato_rogito() {
		return stato_rogito;
	}
	public void setStato_rogito(Stato_rogito stato_rogito) {
		this.stato_rogito = stato_rogito;
	}
	public boolean isVisita_guidata() {
		return visita_guidata;
	}
	public void setVisita_guidata(boolean visita_guidata) {
		this.visita_guidata = visita_guidata;
	}
	public String getDescrizione_lunga() {
		return descrizione_lunga;
	}
	public void setDescrizione_lunga(String descrizione_lunga) {
		this.descrizione_lunga = descrizione_lunga;
	}
	public boolean isVirtual_tour() {
		return virtual_tour;
	}
	public void setVirtual_tour(boolean virtual_tour) {
		this.virtual_tour = virtual_tour;
	}
	
	
	
	
	
	
	
}
