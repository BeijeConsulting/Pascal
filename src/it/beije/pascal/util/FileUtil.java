package it.beije.pascal.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	public static void closeReader(FileReader reader) {
		if(reader != null) {
			try {
				reader.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
	}
	
	public static void closeWriter(FileWriter writer) {
		if(writer != null) {
			try {
				writer.close();
			} catch (IOException e) {			
				e.printStackTrace();
			}
		}
	}
	
}