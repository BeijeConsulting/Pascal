package it.beije.pascal.ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShopManager {
	
	public static User inputUser() {
		User temp = new User();
		System.out.print("Email: ");
		temp.setEmail(readKeyboard()); 
		System.out.print("Name: ");
		temp.setName(readKeyboard());
		System.out.print("Surname: ");
		temp.setSurname(readKeyboard());
		System.out.print("Password: ");
		temp.setPassword(readKeyboard());
		return temp;
	}
	
//	public static Product inputProduct() {
//		Product appoggio = new Product();
//		System.out.print("Name: ");
//		appoggio.setName(readKeyboard());
//		System.out.print("Description: ");
//		appoggio.setDescription(readKeyboard());
//		System.out.print("Price: ");
//		appoggio.setPrice(Double.parseDouble(readKeyboard()));
//		System.out.print("Quantity: ");
//		appoggio.setQuantity(Integer.parseInt(readKeyboard()));
//		return appoggio;
//	}
	
	public static void main(String[] args) {
		int choice = 1;
		User userLogin = null;
		Product tempProduct = null;
		String tC= null;
		List <Product> tempPList = new ArrayList<Product>();
		List<Product> tempPurchase = new ArrayList<Product>();
		
		System.out.println("-Benvenuto-");
		while(choice != 0) {
			System.out.println("Menu:");
			System.out.println("1- Login\n"  
					           + "2- Registrati\n"
			                   + "0 -Esci");
			System.out.print("Inserisci il numero della funzione da eseguire: ");
			choice = Integer.parseInt(readKeyboard());
			
			if (choice == 1) {
			    System.out.println("Inserisci i Dati");
			    System.out.print("Email: ");
			    String tempEmail = readKeyboard();
			    System.out.print("Password: ");
			    String tempPass = readKeyboard();
			    
			    userLogin = ManagerDB.searchUser(tempEmail, tempPass);
			    choice = 0;
			}
			
			if(choice == 2) {
				System.out.println("Inserisci dati utente da registrare");
				userLogin = inputUser();
				ManagerDB.addUser(userLogin);
				System.out.println("Registrazione Effettuata");
			}
			
		}
		
		System.out.println("\n");
        System.out.println("Lista dei Prodotti");
        System.out.println("\n");
        
        tempPList=ManagerDB.getProducts();
        visualProducts(tempPList);
        
        while(true){
			
			System.out.println("\n");
			System.out.println("Seleziona i prodotti da acquistare, quando hai finito, scrivi 'esci' per procedere con l'ordine");
			
			tC = readKeyboard();
			if (tC.equalsIgnoreCase("esci"))
					break;
			tempProduct = ManagerDB.searchProduct(tC);
			System.out.println("Dati del prodotto selezionato, selezionare la quantità richiesta");
			visualSelectedProduct(tempProduct);
			
			int q = Integer.parseInt(readKeyboard());
			
			while(q>tempProduct.getQuantity())
			{
				System.out.println("Quantità disponibile non sufficiente, reinserire un quantitativo");
				q = Integer.parseInt(readKeyboard());
			}
			tempProduct.setQuantity(q);
			
			tempPurchase.add(tempProduct);
			
		}
		
	}
	
	private static String readKeyboard() {
		Scanner scanner = new Scanner(System.in);
		String st = scanner.next();
		//scanner.close();
		return st;
	}

	public static void visualProducts(List<Product> products)
	{
		for(Product p : products) {
			
			System.out.print(p.getName()+ " ");
			System.out.print(p.getPrice()+ " euro  ");
			System.out.println("\n");
		}
	}
	
	public static void visualSelectedProduct(Product p) {
		System.out.println("Nome = " + p.getName() + ", Descrizione = " + p.getDescription() 
		                + ", Prezzo = " + p.getPrice() + ", Quantità = " + p.getQuantity());
	}
	
	public static void visualUsers(List<User> users)
	{
		for(User u : users) {
			System.out.println(u.toString());
		}
	}
}
