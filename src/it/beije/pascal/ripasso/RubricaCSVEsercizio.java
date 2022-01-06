package it.beije.pascal.ripasso;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RubricaCSVEsercizio {

	public static void main(String[] args) throws IOException {
		
		FileReader fr = new FileReader("rubrica.csv");
		BufferedReader br = new BufferedReader(fr);
		
		List<String> rows = new ArrayList<String>();
		List<ContattoEsercizio> contatti = new ArrayList<ContattoEsercizio>();
		String[] fields;
		ContattoEsercizio contatto = new ContattoEsercizio();
		
		//due modi per leggere il file con il read() o il readLine()
		
//		while(fr.ready()) {
//			System.out.print((char)fr.read());
//		}
		
		while(br.ready()) {
			rows.add(br.readLine());
		}
		
		for(String s : rows) {
			fields = s.split("\t");
			contatto.setCognome(fields[0]);
			contatto.setEmail(fields[3]);
			contatto.setNome(fields[1]);
			contatto.setTelefono(fields[2]);
			
			System.out.println(contatto);
			
			contatti.add(contatto);
		}
		
	}

}
