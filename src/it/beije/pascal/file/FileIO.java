package it.beije.pascal.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {
	
	FileReader fileReader;
	StringBuilder sb;
	ArrayList<String> res = new ArrayList<>();
	
	public String[] readFromFile(File f) {
		
		try {
		sb = new StringBuilder();
		fileReader = new FileReader(f);
		
			while(fileReader.ready()) {
				char i = (char)fileReader.read(); 
				sb.append(i);
				if(i == 32) {
					res.add(sb.toString());
					sb.delete(0, sb.length());
				}
			}
			
			res.add(sb.toString());
			
		} catch(IOException e) 
		{
			e.printStackTrace();
		}
		
		String[] trueRes = new String[res.size()];
		trueRes = res.toArray(trueRes);
		
		return trueRes;
	}
	
	public static void describeFile(File file) {
		System.out.println("Il file esiste? " + file.exists());
		System.out.println("Nome: " + file.getName());
		System.out.println("Path: " + file.getPath());
		System.out.println("Path assoluto: " + file.getAbsolutePath());
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("/javaFiles/FileOne.txt");
		System.out.println(file.exists()); 
		System.out.println(file.canExecute());
		System.out.println(file.canRead());
		System.out.println(file.canWrite());
		System.out.println(file.getFreeSpace());
		System.out.println(file.isDirectory());
		System.out.println(file.isFile());
		System.out.println(file.toURI());
		
		
		FileIO.describeFile(file);
		
		File newFile = new File("/javaFiles/FileTwo.txt"); 
		newFile.createNewFile();
		newFile.delete();
		
		FileWriter fw = new FileWriter(newFile);
		fw.write("Hello");
		
		BufferedWriter bw = new BufferedWriter(fw);
		String s = "scrittura nel file";
		bw.write(s);
		System.out.println(newFile.exists());
		
		
		bw.close();
		
		FileReader fr = new FileReader(file); 
		
		BufferedReader br = new BufferedReader(fr); 
		
		System.out.println(br.readLine());
		
		br.close();
	
		FileIO fio = new FileIO();
		String[] arr;
		arr = fio.readFromFile(newFile);
		for(String str : arr) {
			System.out.println(str);
		}
	}
}
