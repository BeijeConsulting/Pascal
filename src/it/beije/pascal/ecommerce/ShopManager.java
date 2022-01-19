package it.beije.pascal.ecommerce;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// import it.beije.pascal.rubrica.Contatto;

public class ShopManager {
	
	public static void main(String[] args) throws Exception {
		
		int choice=1;
		String tC= null;
		User userLogin = null;
//		Product tempProduct = null;
		
		User tempUser = null;
		Product tempProduct = null;
		
		List <Product> tempPList = new ArrayList<Product>();
//		List <User> tempUList = new ArrayList<User>();
		
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
			    
			    if(userLogin == null) {
			    	
			    	System.out.println("Utente o Password errati");
			    	choice = 1; 
			    }
			    else
			    	choice = 0;
			}
			
			if(choice == 2) {
				System.out.println("Inserisci dati utente da registrare");
				userLogin = inputUser();
				ManagerDB.addUser(userLogin);
				System.out.println("Registrazione Effettuata");
			}
			
			if(choice == 0) {
				System.out.println("Arrivederci!");
				System.exit(0);
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
				
		/*
		while(choice !=0) {
			
			System.out.println("\n");
			System.out.println("		Welcome to the ShopManager		");
			System.out.println("\n");
			System.out.println("Press 0 to close the program");
			System.out.println("Press 1 to add a new User");
			System.out.println("Press 2 to add a new Product");
			System.out.println("Press 3 to to view the product list");
			
			String s = readKeyboard();
			
			choice = Integer.parseInt(s);
			
			switch(choice) {
			
			case 0:
				
				System.out.println("\n");
				System.out.println("		Goodbye!		");
				System.exit(0);
				
			case 1:
				
				tempUser=new User();
				
				System.out.println("\n");
				System.out.println("Please, insert the email");
				tempUser.setEmail(readKeyboard());
				System.out.println("Please, insert the name");
				tempUser.setName(readKeyboard());
				System.out.println("Please, insert the surname");
				tempUser.setSurname(readKeyboard());
				System.out.println("Please, insert the password");
				tempUser.setPassword(readKeyboard());
				
				ManagerDB.addUser(tempUser);
				
				break;
				
			case 2:
				
				tempProduct=new Product();
				
				System.out.println("\n");
				System.out.println("Please, insert the product name");
				tempProduct.setName(readKeyboard());
				System.out.println("Please, insert the description");
				tempProduct.setDescription(readKeyboard());
				System.out.println("Please, insert the price (double)");
				tempProduct.setPrice(Double.parseDouble(readKeyboard()));
				System.out.println("Please, insert the quantity");
				tempProduct.setQuantity(Integer.parseInt(readKeyboard()));
				
				ManagerDB.addProduct(tempProduct);
				
				break;
				
			case 3:
				
				System.out.println("\n");
				System.out.println("Product List");
				
				tempPList=ManagerDB.getProducts();
				visualProducts(tempPList);
				break;
				
			case 4:
				
				
				
				//tempUser=ManagerDB.searchUser();
				break;
			}
		}
		*/
	}
	
	// UTILITY
	

	public static String readKeyboard() {

		Scanner scanner = new Scanner(System.in);
		String st = scanner.nextLine();
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
	
	public static void visualUsers(List<User> users)
	{
		
		
		for(User u : users) {
			
			System.out.println(u.toString());
			
		}
		
	}
	
	public static User inputUser() {
		User appoggio = new User();
		System.out.print("Email: ");
		appoggio.setEmail(readKeyboard());
		System.out.print("Name: ");
		appoggio.setName(readKeyboard());
		System.out.print("Surname: ");
		appoggio.setSurname(readKeyboard());
		System.out.print("Password: ");
		appoggio.setPassword(readKeyboard());
		return appoggio;
	}
	
	public static void visualSelectedProduct(Product p) {
		
		System.out.println("Nome = " + p.getName() + ", Descrizione = " + p.getDescription()
		                + ", Prezzo = " + p.getPrice() + ", Quantità = " + p.getQuantity());
	}

}
