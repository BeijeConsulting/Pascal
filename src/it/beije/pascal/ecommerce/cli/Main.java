package it.beije.pascal.ecommerce.cli;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import javax.persistence.NoResultException;

import it.beije.pascal.ecommerce.OrderUtil;
import it.beije.pascal.ecommerce.ProductUtil;
import it.beije.pascal.ecommerce.UserUtil;
import it.beije.pascal.ecommerce.bean.Order;
import it.beije.pascal.ecommerce.bean.OrderItem;
import it.beije.pascal.ecommerce.bean.Product;
import it.beije.pascal.ecommerce.bean.User;

//Main class for managing the cli ui
public class Main {
	
	public static final String MENU_STRING = "----MENU----"
			+ "0. Esci"
			+ "1. Cerca prodotto"
			+ "2. "
			+ "3. ";
	
	static Scanner sc = new Scanner(System.in);
	static User loggedUser = null;
	static Order currentOrder;
	
	public static void main(String[] args) {
		System.out.println("Benvenuti in ecommerce!\n");
		login();
		init();
		int scelta = -1;
		while(scelta != 0 ) {
			System.out.println(MENU_STRING);
			scelta = sc.nextInt();
			handleScelta(scelta);
		}
		
		
	}
	
	private static void init() {
		currentOrder = new Order(0, LocalDateTime.now(), 0, loggedUser.getId());
	}

	private static void login() {
		System.out.println("Login: ");
		System.out.print("email: ");
		String email = sc.nextLine();
		System.out.println("password: ");
		String pass = sc.nextLine();
		try {
			loggedUser = UserUtil.login(email, pass);
		} catch(NoResultException e) {
			System.out.println("Email o password non validi");
			login();
		}
	}

	private static void handleScelta(int scelta) {
		switch(scelta) {
		case 0: return;
		case 1: cercaProdotto();break;
		}
	}

	private static void cercaProdotto() {
		System.out.println("----Cerca prodotto----\n");
		System.out.print("Nome prodotto: ");
		String nomeProd =sc.nextLine();
		List<Product> results = null;
		try {
		results = ProductUtil.search(nomeProd);
		}catch(NoResultException e) {
			System.out.println("Nessun risultato");
			cercaProdotto();
		}

		Product chosenProd = chooseProductFromList(results);
		printProductCard(chosenProd);
		
		System.out.print("Vuoi acquistarlo? 1. si  2. no : ");
		int choice = sc.nextInt();
		sc.nextLine();
		if(choice == 1 ) {
			menuAcquista(chosenProd);
		}
	}

	private static Product chooseProductFromList(List<Product> results) {
		int choice =0;
		System.out.println("Risultati:");
		for (int i = 0; i < results.size(); i++) {
			System.out.println(""+ (i + 1) + ": " +results.get(i) );
		}
		System.out.print("Visualizza elemento (0 = exit) : ");
		choice = sc.nextInt()-1;
		sc.nextLine();
		
		Product chosenProd = results.get(choice);
		return chosenProd;
	}

	private static void printProductCard(Product chosenProd) {
		System.out.println(String.format("Prodotto: %s \n  id: %s "
				+ "\n  descrizione	: %s"
				+ "\n  prezzo 		: %f"
				+ "\n  quantita		: %d",
				chosenProd.getName(),
				chosenProd.getDescription(),
				chosenProd.getPrice(),
				chosenProd.getQuantity()));
	}

	private static void menuAcquista(Product chosenProd) {
		System.out.print("In stock: "+ chosenProd.getQuantity()+"\nQuanti ne vuoi acquistare? : ");
		int amount = sc.nextInt();
		sc.nextLine();
		try {
			OrderUtil.addToOrder(currentOrder, new OrderItem(currentOrder, chosenProd, amount));
		}
	}
	
	
}
