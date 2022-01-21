package it.beije.domus;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import it.beije.domus.enums.AriaCondizionata;
import it.beije.domus.enums.Arredamento;
import it.beije.domus.enums.ClasseEnergetica;
import it.beije.domus.enums.Condizione;
import it.beije.domus.enums.Giardino;
import it.beije.domus.enums.PostiAuto;
import it.beije.domus.enums.Riscaldamento;
import it.beije.domus.enums.StatoRogito;
import it.beije.domus.enums.TipoAnnuncio;
import it.beije.domus.enums.TipoImmobile;
import it.beije.domus.enums.TipoMappa;
import it.beije.domus.enums.TipoRicerca;

@Entity
@Table(name = "ricerca")
public class Ricerca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "utente_id")
	private int utenteId;
	
	@Column(name = "tipo_immobile")
	private TipoImmobile tipoImmobile;
	@Column(name = "tipo_annuncio")
	private TipoAnnuncio tipoAnnuncio;
	@Column(name = "tipo_ricerca")
	private TipoRicerca tipoRicerca;
	@Column(name = "tipo_mappa")
	private TipoMappa tipoMappa;

	private String comune;
	private String frazione;
	private double raggio;
	@Column(name = "centro_id")
	private int centroId;
	@Column(name = "prezzo_min")
	private int prezzoMin;
	@Column(name = "prezzo_max")
	private int prezzoMax;
	@Column(name = "mq_min")
	private int mqMin;
	@Column(name = "mq_max")
	private int mqMax;
	@Column(name = "locali_min")
	private int localiMin;
	@Column(name = "locali_max")
	private int localiMax;

	// da enum a int
	private int bagni_min;
	private int piano;
	
	private boolean ascensore;
	private PostiAuto posti_auto;
	private boolean balcone;
	private boolean terrazzo;
	private Giardino giardino;
	private Condizione condizione;
	@Column(name = "classe_energetica")
	private ClasseEnergetica classe_energetica;
	
	private Riscaldamento riscaldamento;
	@Column(name = "aria_condizionata")
	private AriaCondizionata ariaCondizionata;
	private Arredamento arredamento;
	private boolean piscina;
	private boolean portineria;
	@Column(name = "anno_costruzione")
	private int annoCostruzione;
	@Column(name = "stato_rogito")
	private StatoRogito statoRogito;
	@Column(name = "visita_guidata")
	private boolean visitaGuidata;
	@Column(name = "virtual_tour")
	private boolean virtualTour;
	
	//ultimo_timestamp
	private Timestamp ultimoTimestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUtenteId() {
		return utenteId;
	}

	public void setUtenteId(int utenteId) {
		this.utenteId = utenteId;
	}

	public TipoImmobile getTipoImmobile() {
		return tipoImmobile;
	}

	public void setTipoImmobile(TipoImmobile tipoImmobile) {
		this.tipoImmobile = tipoImmobile;
	}

	public TipoAnnuncio getTipoAnnuncio() {
		return tipoAnnuncio;
	}

	public void setTipoAnnuncio(TipoAnnuncio tipoAnnuncio) {
		this.tipoAnnuncio = tipoAnnuncio;
	}

	public TipoRicerca getTipoRicerca() {
		return tipoRicerca;
	}

	public void setTipoRicerca(TipoRicerca tipoRicerca) {
		this.tipoRicerca = tipoRicerca;
	}

	public TipoMappa getTipoMappa() {
		return tipoMappa;
	}

	public void setTipoMappa(TipoMappa tipoMappa) {
		this.tipoMappa = tipoMappa;
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

	public int getCentroId() {
		return centroId;
	}

	public void setCentroId(int centroId) {
		this.centroId = centroId;
	}

	public int getPrezzoMin() {
		return prezzoMin;
	}

	public void setPrezzoMin(int prezzoMin) {
		this.prezzoMin = prezzoMin;
	}

	public int getPrezzoMax() {
		return prezzoMax;
	}

	public void setPrezzoMax(int prezzoMax) {
		this.prezzoMax = prezzoMax;
	}

	public int getMqMin() {
		return mqMin;
	}

	public void setMqMin(int mqMin) {
		this.mqMin = mqMin;
	}

	public int getMqMax() {
		return mqMax;
	}

	public void setMqMax(int mqMax) {
		this.mqMax = mqMax;
	}

	public int getLocaliMin() {
		return localiMin;
	}

	public void setLocaliMin(int localiMin) {
		this.localiMin = localiMin;
	}

	public int getLocaliMax() {
		return localiMax;
	}

	public void setLocaliMax(int localiMax) {
		this.localiMax = localiMax;
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

	public PostiAuto getPosti_auto() {
		return posti_auto;
	}

	public void setPosti_auto(PostiAuto posti_auto) {
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

	public ClasseEnergetica getClasse_energetica() {
		return classe_energetica;
	}

	public void setClasse_energetica(ClasseEnergetica classe_energetica) {
		this.classe_energetica = classe_energetica;
	}

	public Riscaldamento getRiscaldamento() {
		return riscaldamento;
	}

	public void setRiscaldamento(Riscaldamento riscaldamento) {
		this.riscaldamento = riscaldamento;
	}

	public AriaCondizionata getAriaCondizionata() {
		return ariaCondizionata;
	}

	public void setAriaCondizionata(AriaCondizionata ariaCondizionata) {
		this.ariaCondizionata = ariaCondizionata;
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

	public int getAnnoCostruzione() {
		return annoCostruzione;
	}

	public void setAnnoCostruzione(int annoCostruzione) {
		this.annoCostruzione = annoCostruzione;
	}

	public StatoRogito getStatoRogito() {
		return statoRogito;
	}

	public void setStatoRogito(StatoRogito statoRogito) {
		this.statoRogito = statoRogito;
	}

	public boolean isVisitaGuidata() {
		return visitaGuidata;
	}

	public void setVisitaGuidata(boolean visitaGuidata) {
		this.visitaGuidata = visitaGuidata;
	}

	public boolean isVirtualTour() {
		return virtualTour;
	}

	public void setVirtualTour(boolean virtualTour) {
		this.virtualTour = virtualTour;
	}

	public Timestamp getUltimoTimestamp() {
		return ultimoTimestamp;
	}

	public void setUltimoTimestamp(Timestamp ultimoTimestamp) {
		this.ultimoTimestamp = ultimoTimestamp;
	}
	
	
	
	
	
}
