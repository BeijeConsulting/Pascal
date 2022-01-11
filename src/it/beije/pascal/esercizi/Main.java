package it.beije.pascal.esercizi;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import it.beije.pascal.rubrica.Contatto;

public class Main {

	public static void main(String[] args) throws Exception {
		IOBeanUtil io = new IOBeanUtil();
		List<Contatto> contatti = io.loadRubricaFromXML("rubrica.xml");
		io.writeRubricaXML(contatti, "rubrica.xml");
	}

}
