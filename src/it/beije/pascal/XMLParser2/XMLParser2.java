package it.beije.pascal.XMLParser2;

import java.io.File;
import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.List;

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
		
//	List<DocumentNode> nodi = new ArrayList<>();
	
	String root; 
	
	//prima di tutto facciamo il metodo principale: parse
	public Document parse(String url) throws Exception {
		File f = new File(url);
		
		Document documento = new Document();
		
		FileReader fr = new FileReader(f);
		StringBuilder sb = new StringBuilder();
		
		while(fr.ready()) {
			char temp = (char) fr.read();
			sb.append(temp);
		}
		
		String testoFile = sb.toString();
		documento.setTesto(testoFile);
		
		if(testoFile.contains("<?")) { //il file contiene un'instazione, allora estraiamone le informazioni
			int inizioNumeroVersione = testoFile.indexOf("\"");
			int fineNumeroVersione = testoFile.indexOf("\"", inizioNumeroVersione + 1);
			String versione = testoFile.substring(inizioNumeroVersione + 1, fineNumeroVersione);
			System.out.println(versione); //funziona
			
			int inizioEncoding = testoFile.indexOf("\"", fineNumeroVersione + 1);
			int fineEncoding = testoFile.indexOf("\"", inizioEncoding + 1);
			String encoding = testoFile.substring(inizioEncoding + 1, fineEncoding);
			System.out.println(encoding); //funziona
			
			documento.setVersione(Double.parseDouble(versione));
			documento.setEncoding(encoding);
		}
		
		String rootElement;
		
		int aperturaPrimoTag = testoFile.indexOf("<");
		int aperturaRoot = testoFile.indexOf("<", aperturaPrimoTag + 1);
		int chiusuraRoot = testoFile.indexOf(">", aperturaRoot);
		rootElement = testoFile.substring(aperturaRoot + 1, chiusuraRoot);
		
		DocumentElement dc = new DocumentElement(); //siamo sicuri che è un elemento
		dc.setNome(rootElement);
		documento.setRoot(dc);
		System.out.println(documento.getRoot().getNome());
		
		
		
		//DETERMINIAMO IL TESTO INTERNO AL FILE AL DI FUORI DEL ROOT TAG
		
		String testoInternoAlRoot = "";
		StringBuilder testo = new StringBuilder();
		
		int i = chiusuraRoot + 1;
		
		while(!testo.toString().contains("</" + rootElement)) { 
			testo.append(testoFile.charAt(i));
			i++;
		}
		
		testo.delete(testo.length() - rootElement.length() - 2, testo.length());
		
		testoInternoAlRoot = testo.toString();
		System.out.println(testoInternoAlRoot);
		
		
		
//		for(int i = 0; i < testoFile.length(); i++) {
//			if(testoFile.charAt(i) == '<') {
//				DocumentElement elemento = new DocumentElement();
//				String nomeElemento = "";
//				int j = i;
//				while(testoFile.charAt(j) != '>') {
//					nomeElemento += testoFile.charAt(j);
//					j++;
//				}
//				elemento.setNome(nomeElemento);
//			}
//		}
		
		fr.close();
		return documento;
	}
	
	public void metodo(int index, String nomeTag, String testoIniziale) {
		
	}
	
	
//	public int counter;
//	
//	public int contaElementiFigli(int Index, String daCercare, String testo) {
//		
//		boolean esisteParolaDaCercare = testo.contains(daCercare);
//		
//		if(esisteParolaDaCercare) {	
//			counter++;
//			int indiceElementoFiglio = testo.indexOf(daCercare, Index);
//			contaElementiFigli(indiceElementoFiglio, daCercare, testo.substring(indiceElementoFiglio + daCercare.length(), testo.length()));
//		}
//		return counter / 2;
//	}
//	
//	public void getChildNodes() {
//		
//	}
	
	//RITORNA IL NOME DELL'ELEMENTO ROOT
//	public static String getRootElement(String testoDelFileCompleto) {
//		String rootElement;
//		
//		int aperturaPrimoTag = testoDelFileCompleto.indexOf("<");
//		int chiusuraPrimoTag = testoDelFileCompleto.indexOf(">");
//		int aperturaRoot = testoDelFileCompleto.indexOf("<", aperturaPrimoTag + 1);
//		int chiusuraRoot = testoDelFileCompleto.indexOf(">", aperturaRoot);
//		rootElement = testoDelFileCompleto.substring(aperturaRoot + 1, chiusuraRoot);
//		
//		return rootElement;
//	}
	
//	public static Document parse(String url) {
//		
//		try {
//			
//			
//			File f = new File("/javaFiles/xmlTest.xml");
//			FileReader fr = new FileReader(f);
//			
//			StringBuilder sb = new StringBuilder();
//			
//			while(fr.ready()) {
//				char c = (char)fr.read();
//				sb.append(c);
//			}
//			
//			fr.close();
//			
//			String testoCompleto = sb.toString();
//			
//			root = XMLParser2.getRootElement(testoCompleto);
//			
//			boolean chiusuraTagElementoRoot = sb.toString().contains("/" + root);
//			if(!chiusuraTagElementoRoot) {
//				throw new Exception("File xml mal formattato");
//			}
//			
//			
//			int indiceTagAperturaPrimoElementoFiglio = testoCompleto.indexOf("<", testoCompleto.indexOf(root));
//			int indiceTagChiusuraPrimoElementoFiglio = testoCompleto.indexOf(">", indiceTagAperturaPrimoElementoFiglio);
//			
//			
//			
//			String nomePrimoTagFiglio = testoCompleto.substring(indiceTagAperturaPrimoElementoFiglio + 1, indiceTagChiusuraPrimoElementoFiglio);
//			
//			
//			
//			
//			
//		} catch(Exception e) {
//			System.out.println("sono in eccezione");
//		}
//		
//		
//		return new Document();
//	}
	
	public static void main(String[] args) throws Exception {
		XMLParser2 x = new XMLParser2();
		x.parse("/javaFiles/test_parser1.xml");
	}
}
