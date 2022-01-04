package it.beije.pascal.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class CSVmanager {

	private final static String FILE_PATH = "./rubrica.csv";
	
	public static void main(String[] args) {

		File file = new File(FILE_PATH);
		System.out.println("esiste ? " + file.exists());
		System.out.println("isDirectory ? " + file.isDirectory());

		List<String> rows = FileUtils.extractRows(file);
		System.out.println("rows size : " + rows.size());
		printDataToFile(rows);

	}

	static void printDataToFile(List<String> rows) {
		File newFile = new File("./nuova_rubrica.csv");
		System.out.println("esiste ? " + newFile.exists());
		FileWriter writer = null;

		try {
			writer = new FileWriter(newFile);
			for (String r : rows) {
				String[] c = r.split("\t");

				StringBuilder newRow = new StringBuilder()
						.append('\"').append(c[0] == null ? "" : c[0]).append('\"').append(';')
						.append('\"').append(c[1] == null ? "" : c[1]).append('\"').append(';')
						.append('\"').append(c[3] == null ? "" : c[2]).append('\"').append(';')
						.append('\"').append(c[2] == null ? "" : c[3]).append('\"').append('\n');

				writer.write(newRow.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(writer != null ) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	
}
