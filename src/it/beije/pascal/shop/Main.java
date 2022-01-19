package it.beije.pascal.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.pascal.file.JPAmanager;
import it.beije.pascal.rubrica.Contatto;
import it.beije.pascal.rubrica.EntityManagerProvider;

public class Main {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		int scelta = -1;

		System.out.print("email: ");
		String email = scanner.nextLine();
		System.out.print("password: ");
		String password = scanner.nextLine();

		User check = checkUser(email, password);
		if (check == null)
			System.out.println("Wrong mail or password");
		else {
			System.out.println("\n\nWELCOME " + check.getName() + " " + check.getSurname());

			do {
				do {
					menu();
					System.out.print("inserisci scelta : ");
					scelta = scanner.nextInt();
					scanner.nextLine();
				} while (scelta <= 0 || scelta > 3);

				switch (scelta) {
				case 1:
					List<Product> pro = listProd();

					for (Product product : pro) {
						System.out.println(product);
					}
					break;
				case 2:
					buyItems();
					break;
				default:
					break;
				}
			} while (scelta != 0);
		}
	}

	public static void menu() {
		System.out.println("\n#############################");
		System.out.println("1 lista di prodotti disponibili");
		System.out.println("2 compra prodotti");
		System.out.println("#############################");
	}

	public static List<Product> listProd() {

		EntityManager entityManager = JPAmanager.getEntityManager();

		Query query = entityManager.createQuery("SELECT p FROM Product as p");
		List<Product> pro = query.getResultList();

		return pro;
	}

	public static User checkUser(String email, String password) {

		EntityManager entityManager = JPAmanager.getEntityManager();

		Query query = entityManager.createQuery(
				"SELECT u FROM User as u WHERE u.email = '" + email + "' AND u.password = '" + password + "'");

		List<User> us = query.getResultList();

		if (us.size() == 0) {
			return null;
		}

		return us.get(0);
	}

	public static void buyItems() {
		String buymore = "";
		do {

			EntityManager entityManager = JPAmanager.getEntityManager();

			Query query = entityManager.createQuery("SELECT p FROM Product as p");
			List<Product> pro = query.getResultList();

			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			Product toUpdate = null;
			System.out.print("prod name: ");
			String prodName = scanner.nextLine();
			System.out.print("quantity: ");
			int quantity = scanner.nextInt();
			scanner.nextLine();

			FOR: for (Product product : pro) {
				if (product.getName().toLowerCase().equals(prodName)) {
					if (product.getQuantity() > quantity) {
						toUpdate = product;
						toUpdate.setQuantity(product.getQuantity() - quantity);
						break FOR;
					} else {
						System.out.println("there isnt enought prod");
						return;
					}
				}
			}
			if (toUpdate != null) {
				entityManager.persist(toUpdate);
				transaction.commit();
				entityManager.close();
				System.out.println("");

				System.out.print("add more item: ");
			} else {
				System.out.print("no item");
				return;
			}
			buymore = scanner.nextLine();

		} while (!buymore.toLowerCase().equals("n"));
	}

}
