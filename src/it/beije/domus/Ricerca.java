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
import it.beije.domus.enums.Posti_auto;
import it.beije.domus.enums.Riscaldamento;
import it.beije.domus.enums.Stato_rogito;
import it.beije.domus.enums.Tipo_annuncio;
import it.beije.domus.enums.Tipo_immobile;
import it.beije.domus.enums.Tipo_mappa;
import it.beije.domus.enums.Tipo_ricerca;

@Entity
@Table(name = "ricerca")
public class Ricerca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int utente_id;
	
	private Tipo_immobile tipo_immobile;
	private Tipo_annuncio tipo_annuncio;

	private Tipo_ricerca tipo_ricerca;
	private Tipo_mappa tipo_mappa;

	private String comune;
	private String frazione;
	private double raggio;
	private int centro_id;
	private int prezzo_min;
	private int prezzo_max;
	private int mq_min;
	private int mq_max;
	private int locali_min;
	private int locali_max;

	
	// da enum a int
	private int bagni_min;
	private int piano;
	
	private boolean ascensore;
	private Posti_auto posti_auto;
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
	private boolean virtual_tour;
	
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
	public Tipo_ricerca getTipo_ricerca() {
		return tipo_ricerca;
	}
	public void setTipo_ricerca(Tipo_ricerca tipo_ricerca) {
		this.tipo_ricerca = tipo_ricerca;
	}
	public Tipo_mappa getTipo_mappa() {
		return tipo_mappa;
	}
	public void setTipo_mappa(Tipo_mappa tipo_mappa) {
		this.tipo_mappa = tipo_mappa;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public String getFrazione() {
		return frazione;
	}
	public void setFrazione(String frazione) {
		this.frazione = frazione;
	}
	public double getRaggio() {
		return raggio;
	}
	public void setRaggio(double raggio) {
		this.raggio = raggio;
	}
	public int getCentro_id() {
		return centro_id;
	}
	public void setCentro_id(int centro_id) {
		this.centro_id = centro_id;
	}
	public int getPrezzo_min() {
		return prezzo_min;
	}
	public void setPrezzo_min(int prezzo_min) {
		this.prezzo_min = prezzo_min;
	}
	public int getPrezzo_max() {
		return prezzo_max;
	}
	public void setPrezzo_max(int prezzo_max) {
		this.prezzo_max = prezzo_max;
	}
	public int getMq_min() {
		return mq_min;
	}
	public void setMq_min(int mq_min) {
		this.mq_min = mq_min;
	}
	public int getMq_max() {
		return mq_max;
	}
	public void setMq_max(int mq_max) {
		this.mq_max = mq_max;
	}
	public int getLocali_min() {
		return locali_min;
	}
	public void setLocali_min(int locali_min) {
		this.locali_min = locali_min;
	}
	public int getLocali_max() {
		return locali_max;
	}
	public void setLocali_max(int locali_max) {
		this.locali_max = locali_max;
	}
	public int getBagni_min() {
		return bagni_min;
	}
	public void setBagni_min(int bagni_min) {
		this.bagni_min = bagni_min;
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
	public Posti_auto getPosti_auto() {
		return posti_auto;
	}
	public void setPosti_auto(Posti_auto posti_auto) {
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
	public boolean isVirtual_tour() {
		return virtual_tour;
	}
	public void setVirtual_tour(boolean virtual_tour) {
		this.virtual_tour = virtual_tour;
	}
	
	
	
}
