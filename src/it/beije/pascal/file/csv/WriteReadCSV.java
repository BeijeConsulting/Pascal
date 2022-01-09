package it.beije.pascal.file.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.beije.pascal.file.util.FileUtil;

public class WriteReadCSV {

	public static void main(String[] args) {
		String path1 = "/Users/ema29/javafile/csv/lettura.txt";
		String path2 = "/Users/ema29/JavaFile/csv/scrittura.txt";

		System.out.println("=========== LETTURA ===========");
		readFile(path1);
		System.out.println("\n=========== SCRITTURA ===========");
		//writeFile(path1, path2);
	}

	private static List<String> getFileContents(String path) {
		List<String> rows = new ArrayList<>();
		FileReader reader = null;

		try {
			reader = new FileReader(path);
			// Componente per lettura del file riga per riga (simile a scanner)
			BufferedReader bufferedReader = new BufferedReader(reader);
			// Controlla se c'è una riga da leggere
			while (bufferedReader.ready()) {
				// Lettura riga per riga
				String row = bufferedReader.readLine();
				rows.add(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			FileUtil.closeReader(reader);
		}
		return rows;
	}

	private static void writeFile(List<String> rows, String path) {
		FileWriter writer = null;
		StringBuilder newRow = null;
		try {
			writer = new FileWriter(path);
			for (String row : rows) {
				String[] columns = row.split(";");

				newRow = new StringBuilder(columns[1]).append('$');
				newRow.append(columns[0]).append('$');
				newRow.append(columns[2]).append('$');
				newRow.append(columns[3]).append('\n');
				writer.write(newRow.toString());
				writer.flush();
			}
			getFileContents(path);

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			FileUtil.closeWriter(writer);
		}
	}

	private static void readFile(String path) {
		List<String> list = getFileContents(path);
		for (String str : list) {
			System.out.println(str);
		}

	}

	private static void writeFile(String path1, String path2) {
		writeFile(getFileContents(path1), path2);
		readFile(path2);
	}

}
