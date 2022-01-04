package it.beije.pascal.rubrica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RubricaCSV {

	public static void main(String[] args) throws IOException {

		String path = "./rubrica.csv";
		List<Contatto> contatti = readContatti(path, "\t");

		writeContatti("./rubrica2.csv", contatti, ";", true);

	}

	public static void writeContatti(String writePath, List<Contatto> contatti, String sep, boolean virgolette)
			throws IOException {

		FileWriter writer = null;
		BufferedWriter bufferedWriter = null;

		try {
			writer = new FileWriter(writePath);
			bufferedWriter = new BufferedWriter(writer);

			for (Contatto c : contatti) {
				StringBuilder newRow = null;
				if (virgolette) {
					newRow = new StringBuilder("\"").append(c.getCognome()).append("\"" + sep + "\"")
							.append(c.getNome()).append("\"" + sep + "\"").append(c.getEmail())
							.append("\"" + sep + "\"").append(c.getTelefono()).append("\"" + sep + "\"").append(c.getNote()).append("\"").append('\n');
				} else {
					newRow = new StringBuilder().append(c.getCognome()).append(sep).append(c.getNome()).append(sep)
							.append(c.getEmail()).append(sep).append(c.getTelefono()).append(sep).append(c.getNote()).append('\n');
				}

				writer.write(newRow.toString());
			}

			writer.flush();

		} catch (IOException ioEx) {
			ioEx.printStackTrace();
			throw ioEx;
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}

	}

	public static List<Contatto> readContatti(String path, String sep) throws IOException {
		List<Contatto> rows = new ArrayList<Contatto>();

		FileReader reader = null;
		BufferedReader bufferedReader = null;

		HashMap<String, Integer> mapping = new HashMap<String, Integer>();

		try {
			reader = new FileReader(path);
			bufferedReader = new BufferedReader(reader);

			String row;
			Contatto contatto;
			String[] r;

			boolean firstLine = true;

			mapping.put("COGNOME", -1);
			mapping.put("NOME", -1);
			mapping.put("TELEFONO", -1);
			mapping.put("EMAIL", -1);
			mapping.put("NOTE", -1);

			contatto = new Contatto();
			
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
				if (firstLine) {
					r = row.split(sep);
					for (int i = 0; i < r.length; i++) {
						if (r[i].equals("COGNOME"))
							mapping.put(r[i], i);
						else if (r[i].equals("NOME"))
							mapping.put(r[i], i);
						else if (r[i].equals("TELEFONO"))
							mapping.put(r[i], i);
						else if (r[i].equals("EMAIL"))
							mapping.put(r[i], i);
						else if (r[i].equals("NOTE"))
							mapping.put(r[i], i);
					}
					firstLine = false;
				}

				r = row.split(sep);
				if (mapping.get("COGNOME") != -1)
					contatto.setCognome(r[mapping.get("COGNOME")]);
				if (mapping.get("NOME") != -1)
					contatto.setNome(r[mapping.get("NOME")]);
				if (mapping.get("TELEFONO") != -1)
					contatto.setTelefono(r[mapping.get("TELEFONO")]);
				if (mapping.get("EMAIL") != -1)
					contatto.setEmail(r[mapping.get("EMAIL")]);
				if (mapping.get("NOTE") != -1)
					contatto.setEmail(r[mapping.get("NOTE")]);

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
}
