package it.beije.pascal.jdbc.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.beije.pascal.file.util.FileUtil;
import it.beije.pascal.jdbc.util.DBUtil;
import it.beije.pascal.jdbc.util.DataSource;
import it.beije.pascal.rubrica.Contatto;

public class GestoreRubrica {

	public static void main(String[] args) {
		// importFromCSV();
		// exportFromDatabaseToCsv();

		//exportFromDatabaseToXml();
		// fromListToXML(getContactsFromDatabase());
		//getListFromXML();
		
		getListFromCSV("/Users/ema29/javafile/jdbc/rubrica.csv");
	}

	private static void importFromCSV() {
		List<Contatto> contatti = getListFromCSV("/Users/ema29/javafile/jdbc/rubrica.csv");
		addContacts(contatti);
	}

	private static void exportFromDatabaseToCsv() {
		fromListToCSV(getContactsFromDatabase(), "$");
	}
	
	private static void exportFromDatabaseToXml() {
		fromListToXML(getContactsFromDatabase());
		
	}

	public static List<Contatto> getListFromCSV(String path) {

		BufferedReader buffer = null;
		String riga = null;
		List<Contatto> contatti = new ArrayList<>();

		try {

			buffer = new BufferedReader(new FileReader(path));

			while (buffer.ready()) {
				riga = buffer.readLine();
				String[] colonne = new String[4];
				colonne = riga.split(";");
				Contatto contatto = new Contatto();

				switch (colonne.length) {					
				case 1:
					contatto.setCognome(colonne[0]);
					contatti.add(contatto);
					break;
				case 2:
					contatto.setCognome(colonne[0]);
					contatto.setNome(colonne[1]);
					contatti.add(contatto);
					break;
				case 3:
					contatto.setCognome(colonne[0]);
					contatto.setNome(colonne[1]);
					contatto.setEmail(colonne[2]);
					contatti.add(contatto);
					break;

				case 4:
					contatto.setCognome(colonne[0]);
					contatto.setNome(colonne[1]);
					contatto.setEmail(colonne[2]);
					contatto.setTelefono(colonne[3]);
					contatti.add(contatto);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return contatti;
	}

	private static void addContacts(List<Contatto> contatti) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sql = "INSERT INTO contatti(cognome,nome,email,telefono) VALUES(?,?,?,?)";
			System.err.println(sql);
			connection = DataSource.getInstance().getConnection();

			for (Contatto contatto : contatti) {
				statement = connection.prepareStatement(sql);
				statement.setString(1, contatto.getCognome());
				statement.setString(2, contatto.getNome());
				statement.setString(3, contatto.getEmail());
				statement.setString(4, contatto.getTelefono());
				statement.executeUpdate();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
	}

	private static List<Contatto> getContactsFromDatabase() {
		List<Contatto> contatti = new ArrayList<Contatto>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			String sql = "SELECT * FROM contatti";
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Contatto contatto = new Contatto();
				contatto.setCognome(resultSet.getString("cognome"));
				contatto.setNome(resultSet.getString("nome"));
				contatto.setTelefono(resultSet.getString("telefono"));
				contatto.setEmail(resultSet.getString("email"));
				contatti.add(contatto);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.close(statement);
			DBUtil.close(connection);
		}

		return contatti;

	}

	private static void fromListToCSV(List<Contatto> contatti, String separatore) {
		FileWriter writer = null;
		try {
			writer = new FileWriter("/Users/ema29/javafile/jdbc/rubrica.txt");
			for (Contatto contatto : contatti) {
				writer.write(contatto.getCognome() + separatore);
				writer.write(contatto.getNome() + separatore);
				writer.write(contatto.getTelefono() + separatore);
				writer.write(contatto.getEmail() + "");
				writer.write("\n");
			}
			writer.flush();

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			FileUtil.closeWriter(writer);
		}

	}

	private static void fromListToXML(List<Contatto> contatti) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			Element rubrica = document.createElement("rubrica");
			document.appendChild(rubrica);

			for (Contatto c : contatti) {
				Element contatto = document.createElement("contatto");
				rubrica.appendChild(contatto);

				Element cognome = document.createElement("cognome");
				cognome.setTextContent(c.getCognome());
				contatto.appendChild(cognome);

				Element nome = document.createElement("nome");
				nome.setTextContent(c.getNome());
				contatto.appendChild(nome);

				Element telefono = document.createElement("telefono");
				telefono.setTextContent(c.getTelefono());
				contatto.appendChild(telefono);

				Element email = document.createElement("email");
				email.setTextContent(c.getEmail());
				contatto.appendChild(email);

			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File("/Users/ema29/javafile/jdbc/xml/rubrica.xml"));
			transformer.transform(source, result);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();

		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static List<Contatto> getListFromXML() {
		List<Contatto> contacts = new ArrayList<Contatto>();
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse("/Users/ema29/javafile/jdbc/xml/rubrica.xml");			
			Element rubrica = document.getDocumentElement();
			System.out.println("root : " + rubrica.getTagName());

			// NodeList contatti = root.getElementsByTagName("contatto");

			NodeList contatti = rubrica.getChildNodes();

			for (int i = 0; i < 2; i++) {
				Node contatto = contatti.item(i);
				if (contatto instanceof Element) { // provare a rimuovere
					Element element = (Element) contatto;

					List<Element> contactList = getChildElements(element);
					Contatto c = new Contatto();

					for (Element contact : contactList) {
						// System.out.println(contact.getTagName() + " : " + contact.getTextContent());
						switch (contact.getTagName()) {
						case "cognome":
							c.setCognome(contact.getTextContent());
							break;
						case "nome":
							c.setNome(contact.getTextContent());
							break;
						case "telefono":
							c.setTelefono(contact.getTextContent());
							break;
						case "email":
							c.setEmail(contact.getTextContent());
							break;
						}

					}
					
					contacts.add(c);
				}

			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();

		} catch (SAXException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return contacts;
	}

	public static List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<Element>();
		NodeList childNode = element.getChildNodes();
		for (int n = 0; n < childNode.getLength(); n++) {
			if (childNode.item(n) instanceof Element)
				childElements.add((Element) childNode.item(n));
		}
		return childElements;
	}

}
