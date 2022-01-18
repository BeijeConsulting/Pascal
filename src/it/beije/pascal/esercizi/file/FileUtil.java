package it.beije.pascal.esercizi.file;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

	private FileUtil() {

	}

	public static void closeReader(FileReader reader) {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeWriter(FileWriter writer) {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
