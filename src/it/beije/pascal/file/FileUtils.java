package it.beije.pascal.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	public static void main(String[] args) {
		List<String> albero = getAlbero(new File("./"), 0);
		FileUtils.printToFile(albero);
	}
	
	static List<String> getAlbero(File root, int indents){
		List<String> tree = new ArrayList<>();
		StringBuilder strb = new StringBuilder();
		for (int i = 0; i < indents; i++) {
			strb.append('\t');
		}
		tree.add(strb.append(root.getName()).toString());
		for(File thisFile : root.listFiles()) {
			if(thisFile.isDirectory()) {
				indents++;
				tree.addAll(getAlbero(thisFile, indents));
				indents--;
			}
			else if(thisFile.isFile()) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < indents; i++) {
					sb.append("\t");
				}
				sb.append(thisFile.getName());
				tree.add(sb.toString());
			}
		}
		
		return tree;
	}

	public static List<String> extractRows(File file){
		List<String> rows = new ArrayList<>();
	
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String row;
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
				rows.add(row);
			}
			bufferedReader.close();
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		return rows;
	}

	static void printToFile(List<String> rows) {
		File newFile = new File("./treeFile.txt");
		System.out.println("esiste ? " + newFile.exists());
		FileWriter writer = null;
	
		try {
			writer = new FileWriter(newFile);
			for (String r : rows) {
				writer.write(r + "\n");
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
