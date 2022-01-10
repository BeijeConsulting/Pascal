package it.beije.pascal.rubrica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RubricaApplication {
	
	private List<Contatto> contatti = new ArrayList<>();
	
	public void listContacts() { //vedi lista contatti, con possibilità di ordinare per nome e cognome a scelta
		for(Contatto utente : contatti) {
			System.out.println("-------------------");
			System.out.println("Nome: " + utente.getNome());
			System.out.println("Cognome: " + utente.getCognome());
			System.out.println("Telefono: " + utente.getTelefono());
			System.out.println("Email: " + utente.getEmail());
			System.out.println("Note: " + utente.getNote());
		}
	}
	 
	public void lookForContact() { //cercare un contatto
		
	}
	
	public void newContact() { //cercare un contatto
		
	}
	
	public void updateContact() { //modificare un contatto
		
	}
	
	public void deleteContact() { //cancellare un contatto
		
	}
	
	public void findAllDuplicate() { //trovare i contatti duplicati
		
	}
	
	public void mergeDuplicate() { //unire i contatti duplicati
		
	}
	
	public static void showMenu() {
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println("---RUBRICA---\n" + ldt + "\nScegli una delle seguenti operazioni:\n"
				+ "1. visualizza tutti i contatti\n"
				+ "2. cerca un contatto\n" 
				+ "3. crea un contatto\n"
				+ "4. modifica un contatto\n"
				+ "5. cancella un contatto\n"
				+ "6. trova contatti duplicati\n"
				+ "7. unisci contatti duplicati");
	}	
	
	public static void main(String[] args) {
		showMenu();
	}
	
}
