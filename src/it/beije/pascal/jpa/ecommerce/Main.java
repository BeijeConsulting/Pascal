package it.beije.pascal.jpa.ecommerce;

import java.io.IOException;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import it.beije.pascal.jpa.ecommerce.bean.User;
import it.beije.pascal.jpa.ecommerce.service.UserService;
import it.beije.pascal.jpa.util.EcommerceEntityProvider;

public class Main {

	public static void main(String[] args) {
		boolean continua = true;
		while (continua) {			
			printMenu();
			int scelta = readChoice();
			switch (scelta) {

			case 0:
				continua = false;
				break;
			case 1:
				login();
				break;
			case 2:
				signUp();
				break;
			}
		}
	}

	private static void login() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nInserisci l'email: ");
		String email = scanner.next();
		System.out.print("\nInserisci la password: ");
		String password = scanner.next();
		UserService.login(email, password);

	}
	
	private static void signUp() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Inserisci il tuo nome: ");
		String nome = scanner.next();
		System.out.print("Inserisci il tuo cognome: ");
		String cognome = scanner.next();
		System.out.print("Inserisci email: ");
		String email = scanner.next();
		email = email.toLowerCase();
		System.out.print("Inserisci la password: ");
		String password = scanner.next();
		
		User user = new User(email,nome,cognome,password);
		UserService.addUser(user);
		}

	private static void printMenu() {
		System.out.println("\n====== MENU =======");
		System.out.println("0: Esci");
		System.out.println("1: Login");
		System.out.println("2: Registrati");
	}

	private static int readChoice() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nInserisci scelta: ");
		int scelta = scanner.nextInt();
		while (scelta < 0 || scelta > 2) {
			System.out.print("\nScelta errata,reinserire: ");
			scelta = scanner.nextInt();
		}
		return scelta;
	}

	


}
