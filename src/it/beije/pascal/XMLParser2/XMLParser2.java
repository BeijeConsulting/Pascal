package it.beije.pascal.XMLParser2;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/*
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<contatti>
	<contatto>
		<nome>Pippo</nome>
		<cognome>Pluto</cognome>
		<telefono>3331234567</telefono>
		<email>pippo@pluto.net</email>
	</contatto>
	<contatto>
		<nome>Paolino</nome>
		<cognome>Paperino</cognome>
		<telefono>00423803243423</telefono>
	</contatto>
</contatti>
*/

public class XMLParser2 {
	
	String root;
//	Document documento = new Document();
	
	//prima di tutto facciamo il metodo principale: parse
	public Document parse(String url) throws Exception {
		Document documento = new Document();
		File f = new File(url);
		
		FileReader fr = new FileReader(f);
		StringBuilder sb = new StringBuilder();
		
		while(fr.ready()) {
			char temp = (char) fr.read();
			sb.append(temp);
		}
		
		String testoFile = sb.toString().trim();
		documento.setTesto(testoFile);
		
		if(testoFile.contains("<?")) { //il file contiene un'instazione, allora estraiamone le informazioni
			int inizioNumeroVersione = testoFile.indexOf("\"");
			int fineNumeroVersione = testoFile.indexOf("\"", inizioNumeroVersione + 1);
			String versione = testoFile.substring(inizioNumeroVersione + 1, fineNumeroVersione);
//			System.out.println(versione); //funziona
			
			int inizioEncoding = testoFile.indexOf("\"", fineNumeroVersione + 1);
			int fineEncoding = testoFile.indexOf("\"", inizioEncoding + 1);
			String encoding = testoFile.substring(inizioEncoding + 1, fineEncoding);
//			System.out.println(encoding); //funziona
			
			documento.setVersione(Double.parseDouble(versione));
			documento.setEncoding(encoding);
		}
		
		//IMPOSTAZIONE ELEMENTO ROOT DEL DOCUMENTO
		String rootElement;
		
		int aperturaPrimoTag = testoFile.indexOf("<");
		int aperturaRoot = testoFile.indexOf("<", aperturaPrimoTag + 1);
		int chiusuraRoot = testoFile.indexOf(">", aperturaRoot);
		rootElement = testoFile.substring(aperturaRoot + 1, chiusuraRoot);
		
		DocumentElement elementoRoot = new DocumentElement(); //siamo sicuri che è un elemento
		elementoRoot.setNome(rootElement);
		documento.setRoot(elementoRoot);
		
		//DETERMINIAMO IL TESTO INTERNO AL FILE INTERNO AL ROOT TAG
		String testoInternoAlRoot = "";
		StringBuilder testo = new StringBuilder();
		
		int i = chiusuraRoot + 1;
		
		while(!testo.toString().contains("</" + rootElement)) { 
			testo.append(testoFile.charAt(i));
			i++;
		}
		
		testo.delete(testo.length() - rootElement.length() - 2, testo.length());
		
		testoInternoAlRoot = testo.toString();
//		System.out.println("stampa del testo interno al root" + testoInternoAlRoot);
		
		elementoRoot.setTestoInterno(testoInternoAlRoot);		
		
		//DETERMINIAMO: 1.NUMERO DI ELEMENTI FIGLI DIRETTI DEL ROOT, 2. NOME DEGLI ELEMENTI FIGLI DIRETTI, 3. CONTENUTO INTERNO AI VARI ELEMENTI FIGLI
		List<DocumentElement> listaDiElementi = new ArrayList<>();
		
		int numero = new XMLParser2().ottieniNumeroDiSottoElementi(testoInternoAlRoot);
//		System.out.println("numero di elementi contatto " + numero);
		
		String nomeElementiDaInserire = new XMLParser2().ottieniNomeTagFiglioDiretto(testoInternoAlRoot);
//		System.out.println("nome degli elementi interi: " + nomeElementiDaInserire);
		
		List<String> sottoTesto = new XMLParser2().ottieniSottotesti(testoInternoAlRoot, numero, nomeElementiDaInserire);
//		System.out.println(sottoTesto.get(1));
		
		for(int j = 0; j < numero; j++) {
			DocumentElement contatto = new DocumentElement();
			contatto.setNome(nomeElementiDaInserire);
			contatto.setTestoInterno(sottoTesto.get(j));
			contatto.settextValue(null);
			listaDiElementi.add(contatto);
		}
		documento.getRoot().setNodi(listaDiElementi);
		
//		System.out.println("stampa finale: " + listaDiElementi.get(1).getNome());
//		System.out.println("stampa finale root: " + elementoRoot.getTestoInterno())
		
		new XMLParser2().leggiElementiInterni(listaDiElementi.get(0).getTestoInterno().trim(), 0, documento);
		
		System.out.println(documento.getRoot().getNodi().get(0).getNodi().get(0).gettextValue());
		
//		elementoRoot.parseAncora(testoInternoAlRoot, 1);		
		fr.close();
		return documento;
	}
	
	public String ottieniNomeTagFiglioDiretto(String testo) throws Exception {
		String testoFile = testo;
		//NOME DEL PRIMO TAG PRESENTE NEL SOTTOTESTO
		String contatto; 
		int aperturaRoot = testoFile.indexOf("<");
		int chiusuraRoot = testoFile.indexOf(">", aperturaRoot);
		contatto = testoFile.substring(aperturaRoot + 1, chiusuraRoot);
		
		return contatto;	
	}
	
	public List<String> ottieniSottotesti(String sottoTesto, int count, String tagDaCercare) {
		String s = sottoTesto;
		
		String sottoS = s; 
		List<String> sottoTestoLista = new ArrayList<String>();
		for(int i=0; i < count; i++) {
			String s2 = sottoS.substring(sottoS.indexOf(">")+1, sottoS.indexOf("</" + tagDaCercare + ">"));
			
			sottoTestoLista.add(s2);
			
			sottoS = sottoS.substring(sottoS.indexOf("</" + tagDaCercare + ">") + (tagDaCercare.length()+ 3));
		}		
		return sottoTestoLista;
	}
	
	public int ottieniNumeroDiSottoElementi(String testo) {
		String s = testo;
		int primaApertura = s.indexOf("<");
		int primaChiusura = s.indexOf(">");
		String nome = s.substring(primaApertura + 1, primaChiusura);
		int count = 0;
		
		for(int i=s.length(); i>0 || i!=-1; i--) {
			if(s.contains(nome)) {
				count++;
				s = s.substring(s.indexOf(nome) + nome.length());
			} else {
				continue;
			}
		}
		count = count / 2;
		return count;
	}
	
	public void leggiElementiInterni(String testoInterno, int indiceContatto, Document document) {
		String daLavorare = testoInterno;
		
	
		String[] arrayDiString = daLavorare.split("\n");
		
		List<DocumentElement> elemInterni = new ArrayList<>();
		for(String s : arrayDiString) {
			DocumentElement nuovoElemento = new DocumentElement();
			
			String nomeDelTag = s.substring((s.indexOf("<") + 1), s.indexOf(">"));
			
			nuovoElemento.setNome(nomeDelTag);
			
			String valoreInterno = s.substring(s.indexOf(">") + 1, s.indexOf("</"));
			nuovoElemento.settextValue(valoreInterno);
			//<tag>valore</tag>
			elemInterni.add(nuovoElemento);
		}
//		System.out.println(document.getRoot().getNodi().get(0).getNome());	
		document.getRoot().getNodi().get(indiceContatto).setNodi(elemInterni);	
	}
	
	public static void main(String[] args) throws Exception {
		XMLParser2 x = new XMLParser2();
		x.parse("/javaFiles/test_parser1.xml");
//		x.metodo("<nome>Ciao</nome>", 1);
	}
}
