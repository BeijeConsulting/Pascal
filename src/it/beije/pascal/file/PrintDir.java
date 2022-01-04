package it.beije.pascal.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrintDir {

	private static void printPath(File file) throws IOException {
		List<String> paths = new ArrayList<String>();
		File[] files = file.listFiles();
		for(File r : files) {
			if(r.isDirectory()) {
				System.out.println(r.getName() + "(dir)");
				printPath(r);
			}
			else {
			System.out.println("	" + r.getName());
			}
		}
	}
	
	public static void main(String[] args) {
		File file = new File("D:\\Documenti\\Voli");
		
		try {
			printPath(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}



}
