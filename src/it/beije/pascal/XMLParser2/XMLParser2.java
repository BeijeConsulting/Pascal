package it.beije.pascal.XMLParser2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class XMLParser2 {
	
	public int counter;
	
	public int contaElementiFigli(int Index, String toFind, String testo) {
		
		
		System.out.println("sono nel metodo");
		
		boolean esisteParolaDaCercare = testo.contains(toFind);
		
		System.out.println(esisteParolaDaCercare);
		
		if(esisteParolaDaCercare) {
			
			System.out.println(counter);
			counter++;
			int indiceElementoFiglio = testo.indexOf(toFind, Index);
			System.out.println(indiceElementoFiglio);
			contaElementiFigli(indiceElementoFiglio, toFind, testo.substring(indiceElementoFiglio + 1, testo.length()));
		}
		return counter ;
	}
	
	
	//RITORNA IL NOME DELL'ELEMENTO ROOT
	public static String getRootElement(String testoDelFileCompleto) {
		String rootElement;
		
		int aperturaPrimoTag = testoDelFileCompleto.indexOf("<");
		int chiusuraPrimoTag = testoDelFileCompleto.indexOf(">");
		int aperturaRoot = testoDelFileCompleto.indexOf("<", aperturaPrimoTag + 1);
		int chiusuraRoot = testoDelFileCompleto.indexOf(">", aperturaRoot);
		rootElement = testoDelFileCompleto.substring(aperturaRoot + 1, chiusuraRoot);
		
		return rootElement;
	}
	
	public static Document parse(String url) {
		
		try {
			
			
			File f = new File("/javaFiles/xmlTest.xml");
			FileReader fr = new FileReader(f);
			
			StringBuilder sb = new StringBuilder();
			
			while(fr.ready()) {
				char c = (char)fr.read();
				sb.append(c);
			}
			
			fr.close();
			
			String testoCompleto = sb.toString();
			
			System.out.println(new XMLParser2().contaElementiFigli(0, "contatto", testoCompleto)); 
			
			
//			String nomeElementoRoot = XMLParser2.getRootElement(sb.toString());
//			
//			boolean chiusuraTagElementoRoot = sb.toString().contains("/" + nomeElementoRoot);
//			if(!chiusuraTagElementoRoot) {
//				throw new Exception("File xml mal formattato");
//			}
//			
//			
//			int indiceTagAperturaPrimoElementoFiglio = testoCompleto.indexOf("<", testoCompleto.indexOf(nomeElementoRoot));
//			int indiceTagChiusuraPrimoElementoFiglio = testoCompleto.indexOf(">", indiceTagAperturaPrimoElementoFiglio);
//			String nomePrimoTagFiglio = testoCompleto.substring(indiceTagAperturaPrimoElementoFiglio + 1, indiceTagChiusuraPrimoElementoFiglio);
//			
//			System.out.println(nomePrimoTagFiglio);
//			
//			int indicePartenza = testoCompleto.indexOf(nomePrimoTagFiglio);
//			int count = 1;
//			for(int i = indicePartenza; i < sb.length(); i++) {
//				
//				
//			}
//			
//			
			
		} catch(Exception e) {
			System.out.println("sono in eccezione");
		}
		
		
		return new Document();
	}
	
	public static void main(String[] args) {
		XMLParser2.parse("/javaFiles/test_parser1");
		
	}
}
