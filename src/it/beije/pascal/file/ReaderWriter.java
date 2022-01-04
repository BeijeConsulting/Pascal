package it.beije.pascal.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderWriter {

	public String[] readFromFile(File f) throws IOException {
		
		String[] res = null;
		StringBuilder sb = new StringBuilder();
		List<String> strArr = new ArrayList<String>();
		FileReader fr = null;
		
		try {
			
			fr = new FileReader(f);
			while(fr.ready()) {
				char c = (char)fr.read();
				sb.append(c);
				if(c == 32) { 
					strArr.add(sb.toString());
					sb.delete(0, sb.length());
				}
			}
			
			strArr.add(sb.toString()); 
			res = new String[strArr.size()];
			res = strArr.toArray(res);
			
		} catch(IOException ioEx) {
			
			ioEx.printStackTrace();
			
		} finally {
			
			fr.close();	
			
		}	
		return res;
	}
	
	public static String[] readFromFile(File f, boolean buffer) throws IOException { 
		
		String[] res = null;
		FileReader fr = null;
		BufferedReader br = null;
		List<String> strList = new ArrayList<>();
		@SuppressWarnings("unused")
		StringBuilder sb;
		
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			
			while(br.ready()) {
				String temp = br.readLine();
				sb = new StringBuilder(temp);
				
				strList.add(temp);
			}
			
			res = new String[strList.size()];
			res = strList.toArray(res);
			
		} finally {
			fr.close();
			br.close();
		}
		
		return res;
		
	}
	
	public void writeToFile(File f, String toWrite) throws IOException {
		
		BufferedWriter bw = null;
		try {
			
			String[] toRewrite = ReaderWriter.readFromFile(f, true); 
			
			FileWriter fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			
			for(String s : toRewrite) {
				bw.write(s);
				bw.write("\n");
			}
			
			bw.write(toWrite);
				
		} catch(Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			bw.close();
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("/javaFiles/FileOne.txt");
		
		ReaderWriter r = new ReaderWriter();
		r.writeToFile(file, "ciao come stai");
			
	}
}
