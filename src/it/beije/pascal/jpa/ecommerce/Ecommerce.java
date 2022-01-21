package it.beije.pascal.jpa.ecommerce;

import java.util.List;
import java.util.Scanner;

import it.beije.pascal.jpa.ecommerce.bean.Product;
import it.beije.pascal.jpa.ecommerce.service.ProductService;

public class Ecommerce {

	public static void launchEcommerce() {
		// ProductService.getProducts();
		boolean continua = true;
		while (continua) {
			printMenu();
			int scelta = readChoice();
			switch (scelta) {
			case 0:
				continua = false;
				break;
			case 1:
				printProducts();
				break;

			}
		}

	}

	private static void printMenu() {
		System.out.println("\n====== ECOMMERCE =======");
		System.out.println("0: Logout");
		System.out.println("1: Stampa lista prodotti");
		System.out.println("2: Scegli prodotto da acquistare");
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
	
	private static void printProducts() {
		List<Product> products = ProductService.getProducts();
		for(Product product:products) {
			System.out.println(product);
		}
	}
}
