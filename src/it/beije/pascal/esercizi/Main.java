package it.beije.pascal.esercizi;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		IOBeanUtil io = new IOBeanUtil();
		io.loadRubricaFromXML("rubrica.xml");
	}

}
