package it.beije.pascal.rubrica;

import java.io.IOException;
import java.util.Scanner;

public class Gestore_Rubrica {

	public static void main(String[] args) {
		
		System.out.println("Benvenuto nella Rubrica");
		System.out.println("Premere 1 per visualizzare la lista dei contatti ordinata per nome");
		System.out.println("Premere 2 per visualizzare la lista dei contatti ordinata per cognome");
		System.out.println("Premere 3 per cercare un contatto");
		System.out.println("Premere 4 per inserire un nuovo contatto");
		System.out.println("Premere 5 per modificare un contatto esistente");
		System.out.println("Premere 6 per cancellare un contatto esistente");
		System.out.println("Premere 7 per trovare eventuali contatti duplicati");
		System.out.println("Premere 8 per unire eventuali contatti duplicati");
		System.out.println("Premere 0 per chiudere");
		
		String s = readKeyboard();
		
		int scelta = Integer.parseInt(s);
		
		switch(scelta){
		
		
		case 0: 
			break;
		case 1:
			System.out.println("Sei in caso 1");
			break;
		case 2:
			System.out.println("Sei in caso 2");
			break;
		case 3:
			System.out.println("Sei in caso 3");
			break;
		case 4:
			System.out.println("Sei in caso 4");
			break;
		case 5:
			System.out.println("Sei in caso 5");
			break;
		case 6:
			System.out.println("Sei in caso 6");
			break;
		case 7:
			System.out.println("Sei in caso 7");
			break;
		case 8:
			System.out.println("Sei in caso 8");
			break;
			
		
		}
		
	}
	
	public static String readKeyboard() {
		
		Scanner scanner = new Scanner(System.in);
		String st = scanner.next();
		scanner.close();
		
		return st;
	}
	
}
