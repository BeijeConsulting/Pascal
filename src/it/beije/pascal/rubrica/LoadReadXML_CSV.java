package it.beije.pascal.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LoadReadXML_CSV {
	
	public List<Contatto> loadRubricaFromCSV(String pathFile, String separator) throws IOException {
		List<Contatto> rows = new ArrayList<Contatto>();
		
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		
		try {
			reader = new FileReader(pathFile);
			bufferedReader = new BufferedReader(reader);
			
			String row;
			Contatto contatto;
			String[] r;
			
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
			
				r = row.split(separator);
				
				contatto = new Contatto();
				contatto.setNome(r[0]);
				contatto.setCognome(r[1]);
				contatto.setTelefono(r[2]);
				contatto.setEmail(r[3]);
				
				rows.add(contatto);
			}
			
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
			throw ioEx;
			
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		
		return rows;
		
	}
	
	public List<Contatto> loadRubricaFromXML(String pathFile) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		Document document = documentBuilder.parse(pathFile);
		
		Element root = document.getDocumentElement();
		
		NodeList childNodes = root.getChildNodes();
		
		List<Contatto> prova = new ArrayList<Contatto>();
		
		for (int i = 0; i < childNodes.getLength(); i++) {
			Contatto contatto = new Contatto();
			Node node = childNodes.item(i);
			if (node instanceof Element) {
				Element el = (Element)node;
				
				List<Element> values = getChildElements(el);
				for (Element value : values) {
					switch (value.getTagName()) {
					case "nome":
						contatto.setNome(value.getTextContent());
						break;
					case "cognome":
						contatto.setCognome(value.getTextContent());
						break;
					case "telefono":
						contatto.setTelefono(value.getTextContent());
						break;
					case "email":
						contatto.setEmail(value.getTextContent());
						break;
					case "note":
						contatto.setNote(value.getTextContent());
					}
				}
				
				prova.add(contatto);
				
			}
		}
		
		return prova;
			
	}
	
	public void writeRubricaCSV(List<Contatto> contatti, String pathFile, String separator) {
		File newF = new File(pathFile);
		boolean check = controlloNuovoFile(newF);
		FileWriter writeF = setWriteFile(newF, check);
				
		try {
			for(Contatto c : contatti) {
				if(check) {
					writeF.write("NOME" + separator + "COGNOME" + separator + "TELEFONO" 
							     + separator +"EMAIL");
					writeF.write("\n" + c.getNome() + separator + c.getCognome() + separator
							     + c.getTelefono() + separator + c.getEmail());
					check = false;
				
				}else {
					if(c.toString().contains("NOME")) {
						continue; /*Se leggo da un altro file rubrica e lo aggiungo a questo
						            evito di riscrivere la prima riga, ad esempio se voglio
						            mettere tutti i contatti di rubrica1 in rubrica2*/
					}
					writeF.write("\n" + c.getNome() + separator + c.getCognome() + separator
							+ c.getTelefono() + separator + c.getEmail());
				}
			}
			writeF.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeRubricaXML(List<Contatto> contatti, String pathFile) throws Exception {
		File f = new File(pathFile);
		boolean check = controlloNuovoFile(f);
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document doc = documentBuilder.parse(pathFile);
		
		Element root = doc.getDocumentElement();
		
		
		if(check) {
			Element contattiEl = doc.createElement("contatti");
			doc.appendChild(contattiEl);
		}
		
		for(Contatto contattoLis : contatti) {
			Element contatto = doc.createElement("contatto");
			
			Element cognome = doc.createElement("cognome");
			cognome.setTextContent(contattoLis.getCognome());
			contatto.appendChild(cognome);

			Element nome = doc.createElement("nome");
			nome.setTextContent(contattoLis.getNome());
			contatto.appendChild(nome);

			Element telefono = doc.createElement("telefono");
			telefono.setTextContent(contattoLis.getTelefono());
			contatto.appendChild(telefono);

			Element email = doc.createElement("email");
			email.setTextContent(contattoLis.getEmail());
			contatto.appendChild(email);
			
			if(contattoLis.getNote()!= null) {
				Element note = doc.createElement("note");
				note.setTextContent(contattoLis.getNote());
				contatto.appendChild(note);
			}
			
			root.appendChild(contatto);

		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = null;
		
		if(check) {
			result = new StreamResult(new File(pathFile));
		}else {
			result = new StreamResult(pathFile);
		}
		

		// Output to console for testing
		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source, syso);
	}
	
	private List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<Element>();
		NodeList nodeList = element.getChildNodes();
		for (int n = 0; n < nodeList.getLength(); n++) {
			if (nodeList.item(n) instanceof Element) childElements.add((Element)nodeList.item(n));
		}
		
		return childElements;
	}
	
	private boolean controlloNuovoFile(File f) {
		boolean check = false;
		try {
			if(f.createNewFile()) {
				check = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	private FileWriter setWriteFile(File newF, boolean check) {
		FileWriter writeF = null;
		if(check) {
			try {
				writeF = new FileWriter(newF);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				writeF = new FileWriter(newF, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return writeF;
	}

}
