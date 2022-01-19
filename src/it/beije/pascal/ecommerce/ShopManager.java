package it.beije.pascal.ecommerce;

import java.util.List;
import java.util.Scanner;

public class ShopManager {
	
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
	
	public static Product inputProduct() {
		Product appoggio = new Product();
		System.out.print("Name: ");
		appoggio.setName(readKeyboard());
		System.out.print("Description: ");
		appoggio.setDescription(readKeyboard());
		System.out.print("Price: ");
		appoggio.setPrice(Double.parseDouble(readKeyboard()));
		System.out.print("Quantity: ");
		appoggio.setQuantity(Integer.parseInt(readKeyboard()));
		return appoggio;
	}
	
	public static void main(String[] args) {
		int choice = 1;
		User tempUser = null;
		Product tempProduct = null;
		while(choice != 0) {
			System.out.println("\n-Shop Manager Menu-");
			System.out.println("Menu:");
			System.out.println("1- Insert User\n"  
					           + "2- Insert Product\n"
					           + "3- Visual Users\n"
					           + "4- Visual Products\n"
					           + "5- Search User\n"
//			                   + "6 -Cancella \n"
//			                   + "7 -Trova \n"
//			                   + "8 -Unisci \n"
//			                   + "9 -Importa da CSV a DB\n"
//			                   + "10 -Importa da XML a DB\n"
//			                   + "11 -Esporta da DB a CSV\n"
//			                   + "12 -Esporta da DB a XML\n"
			                   + "0 -Esci");
			System.out.println("Inserisci il numero della funzione da eseguire");
			choice = Integer.parseInt(readKeyboard());
			
			switch(choice) {
			case 1:
				tempUser = inputUser();
				ManagerDB.addUser(tempUser);
				break;
			case 2:
				tempProduct = inputProduct();
				ManagerDB.addProduct(tempProduct);
				break;
			case 3:
				printUsers(ManagerDB.getUsers());
				break;
			case 4:
				printProducts(ManagerDB.getProducts());
				break;
			case 5:
				tempUser = inputUser();
				User searchedUser = ManagerDB.searchUser(tempUser);
				System.out.println("-Searched User-");
				printUser(searchedUser);
				break;
			case 6:
				tempProduct = inputProduct();
				Product searchedProduct = ManagerDB.searchProduct(tempProduct);
				System.out.println("-Searched Product-");
				printProduct(searchedProduct);
				break;
			default:
			case 0:
				break;
			}
		}
		
	}
	
	private static String readKeyboard() {
		Scanner scanner = new Scanner(System.in);
		String st = scanner.next();
		//scanner.close();
		return st;
	}

	private static void printUsers(List<User> users)
	{
		System.out.println("-List of Users-");
		for(User c : users) {		
			System.out.println(c.toString());
		}
	}
	
	private static void printUser(User u) {
		System.out.println(u.toString());	
	}
	
	private static void printProducts(List<Product> products)
	{
		System.out.println("-List of Products-");
		for(Product c : products) {		
			System.out.println(c.toString());
		}
	}
	
	private static void printProduct(Product p) {
		System.out.println(p.toString());	
	}

}
