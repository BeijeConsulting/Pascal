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
import it.beije.domus.enums.Riscaldamento;
import it.beije.domus.enums.StatoRogito;
import it.beije.domus.enums.TipoAnnuncio;
import it.beije.domus.enums.TipoImmobile;

@Entity
@Table(name = "annuncio")
public class Annuncio {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "venditori_id")
	private int venditoriId;
	@Column(name = "annuncio_multiplo_id")
	private int annuncio_multiplo_id;
	@Column(name = "piantina_id")
	private int piantinaId;
	@Column(name = "indirizzo_id")
	private int indirizzoId;
	@Column(name = "edificio_id")
	private int edificioId;
	
	@Column(name = "tipo_immobile")
	private TipoImmobile tipoImmobile;
	@Column(name = "tipo_annuncio")
	private TipoAnnuncio tipoAnnuncio;
	private int prezzo;
	private int mq;
	private int locali;
	private int bagni;
	@Column(name = "tot_piani")
	private int totPiani;
	private int piano;
	private boolean ascensore;
	@Column(name = "posti_auto")
	private int postiAuto;
	private boolean balcone;
	private boolean terrazzo;
	private Giardino giardino;
	private Condizione condizione;
	@Column(name = "classe_energetica")
	private ClasseEnergetica classeEnergetica;
	private Riscaldamento riscaldamento;
	@Column(name = "aria_condizionata")
	private AriaCondizionata ariaCondizionata;
	private Arredamento arredamento;
	private boolean piscina;
	private boolean portineria;
	private int anno_costruzione;
	@Column(name = "stato_rogito")
	private StatoRogito statoRogito;
	@Column(name = "visita_guidata")
	private boolean visitaGuidata;
	@Column(name = "descrizione_lunga")
	private String descrizioneLunga;
	@Column(name = "virtual_tour")
	private boolean virtualTour;
	@Column(name = "create_timestamp")
	private Timestamp createTimestamp;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVenditoriId() {
		return venditoriId;
	}
	public void setVenditoriId(int venditoriId) {
		this.venditoriId = venditoriId;
	}
	public int getAnnuncio_multiplo_id() {
		return annuncio_multiplo_id;
	}
	public void setAnnuncio_multiplo_id(int annuncio_multiplo_id) {
		this.annuncio_multiplo_id = annuncio_multiplo_id;
	}
	public int getPiantinaId() {
		return piantinaId;
	}
	public void setPiantinaId(int piantinaId) {
		this.piantinaId = piantinaId;
	}
	public int getIndirizzoId() {
		return indirizzoId;
	}
	public void setIndirizzoId(int indirizzoId) {
		this.indirizzoId = indirizzoId;
	}
	public int getEdificioId() {
		return edificioId;
	}
	public void setEdificioId(int edificioId) {
		this.edificioId = edificioId;
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
	public int getTotPiani() {
		return totPiani;
	}
	public void setTotPiani(int totPiani) {
		this.totPiani = totPiani;
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
	public int getPostiAuto() {
		return postiAuto;
	}
	public void setPostiAuto(int postiAuto) {
		this.postiAuto = postiAuto;
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
	public ClasseEnergetica getClasseEnergetica() {
		return classeEnergetica;
	}
	public void setClasseEnergetica(ClasseEnergetica classeEnergetica) {
		this.classeEnergetica = classeEnergetica;
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
	public int getAnno_costruzione() {
		return anno_costruzione;
	}
	public void setAnno_costruzione(int anno_costruzione) {
		this.anno_costruzione = anno_costruzione;
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
	public String getDescrizioneLunga() {
		return descrizioneLunga;
	}
	public void setDescrizioneLunga(String descrizioneLunga) {
		this.descrizioneLunga = descrizioneLunga;
	}
	public boolean isVirtualTour() {
		return virtualTour;
	}
	public void setVirtualTour(boolean virtualTour) {
		this.virtualTour = virtualTour;
	}
	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}
	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	

	
	
	
	
}
