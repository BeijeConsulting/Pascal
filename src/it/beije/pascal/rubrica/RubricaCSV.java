package it.beije.pascal.rubrica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.beije.pascal.util.FileUtil;

/*
	non devono scrivere array di stringhe ma un bin di contatti
	
	-metodo lettura: 
	booleano che dica se i campi da leggere abbiano gli apici "" true nel caso o false altrimenti
	
	
	ordine campi non dato per scontato (se non se ne trova uno salta)
*/

public class RubricaCSV {
	public static void main(String[] args) {
		String path = "/Users/ema29/JavaFile/lettura.txt";
		String path2 = "/Users/ema29/JavaFile/scrittura.txt";
		String separatore = ";";
		scriviContatti(path2, leggiContatti(path, separatore, false));

	}

	public static List<Contatto> leggiContatti(String path, String separatore, boolean controlBoolean) {

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
		}finally {
			FileUtil.closeWriter(writer);
		}
		

	}
	
	

}
