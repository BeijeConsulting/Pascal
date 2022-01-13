package it.beije.pascal.XMLParser2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser2 {
	
	static String root; //FATTO
	List<DocumentElement> elementi = new ArrayList<DocumentElement>();
	
	
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
	public void getChildNodes() {
		
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
			
			root = XMLParser2.getRootElement(testoCompleto);
			
			boolean chiusuraTagElementoRoot = sb.toString().contains("/" + root);
			if(!chiusuraTagElementoRoot) {
				throw new Exception("File xml mal formattato");
			}
			
			
			int indiceTagAperturaPrimoElementoFiglio = testoCompleto.indexOf("<", testoCompleto.indexOf(root));
			int indiceTagChiusuraPrimoElementoFiglio = testoCompleto.indexOf(">", indiceTagAperturaPrimoElementoFiglio);
			
			
			
			String nomePrimoTagFiglio = testoCompleto.substring(indiceTagAperturaPrimoElementoFiglio + 1, indiceTagChiusuraPrimoElementoFiglio);
			
			
			
			
			
		} catch(Exception e) {
			System.out.println("sono in eccezione");
		}
		
		
		return new Document();
	}
	
	public static void main(String[] args) throws Exception {
//		XMLParser2.parse("/javaFiles/test_parser1");
	}
}
