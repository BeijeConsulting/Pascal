package it.beije.pascal.file.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.beije.pascal.bean.Contatto;
import it.beije.pascal.file.util.FileUtil;

public class EsercizioCSV {
	public static void main(String[] args) {
		String path = "/Users/ema29/JavaFile/lettura.txt";
		String path2 = "/Users/ema29/JavaFile/scrittura.txt";
		String separatore = ";";

		printContacts();
		scriviContatti(path2, getContactList(path, separatore, false));

	}

	private static void printContacts() {
		String path = "/Users/ema29/JavaFile/lettura.txt";
		String separatore = ";";
		List<Contatto> contatti = getContactList(path, separatore, true);
		for (Contatto contatto : contatti) {
			System.out.println(contatto);
		}
	}

	public static List<Contatto> getContactList(String path, String separatore, boolean controlBoolean) {

		List<Contatto> contatti = new ArrayList<Contatto>();
		FileReader reader = null;
		try {
			reader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(reader);
			while (bufferedReader.ready()) {
				String row = bufferedReader.readLine();
				String columns[] = row.split(";");
				Contatto contatto = new Contatto();
				if (!controlBoolean) {
					contatto.setNome(columns[0].substring(1, columns[0].length() - 1));
					contatto.setCognome(columns[1].substring(1, columns[1].length() - 1));
					contatto.setTelefono(columns[2].substring(1, columns[2].length() - 1));
					contatto.setEmail(columns[3].substring(1, columns[3].length() - 1));
					contatto.setNote(columns[4].substring(1, columns[4].length() - 1));
					contatti.add(contatto);
				} else {
					contatto.setNome(columns[0]);
					contatto.setCognome(columns[1]);
					contatto.setTelefono(columns[2]);
					contatto.setEmail(columns[3]);
					contatto.setNote(columns[4]);
					contatti.add(contatto);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			FileUtil.closeReader(reader);
		}
		return contatti;
	}

	private static void scriviContatti(String path, List<Contatto> contatti) {
		StringBuilder builder = new StringBuilder();
		FileWriter writer = null;
		for (Contatto contatto : contatti) {
			builder.append(contatto).append("\n");
		}
		try {
			writer = new FileWriter(path);
			writer.write(builder.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			FileUtil.closeWriter(writer);
		}

	}
	// Il risultato si vede dal file(per ora)

}
