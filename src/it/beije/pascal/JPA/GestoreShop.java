package it.beije.pascal.JPA;
//sito e-commerce, descrizione/scheda, dire quanti,  salvare l'ordine e order_item prima order e poi order_item, ordine non modificabile

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.pascal.rubrica.EntityManagerProvider;

public class GestoreShop {
//static EntityManager entityManager = EntityManagerProvider.getEntityManager();

	static public boolean isUser(String email, String password){
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		Query query = entityManager.createQuery("SELECT u FROM User as u");//SELECT * FROM contatti
		List<User> userlist = query.getResultList();
		for(User us: userlist) {
			if(email.equalsIgnoreCase(us.getEmail()) && password.equals(us.getPassword())) {
				entityManager.close();
				return true;
			}
		}
		entityManager.close();
		return false;

	}

	static public Product selectProdotto(String name){
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		Query query = entityManager.createQuery("SELECT p FROM Product as p where p.name = '"+ name + "'");//SELECT * FROM contatti
		Product p = (Product) query.getSingleResult();
		entityManager.close();
		return p;
	}

	
	
	static public void Insert(Object o) {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(o);
		transaction.commit();
		entityManager.close();
	}

	
	static public List<Product> getAllProducts() {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		List<Product> productlist = new ArrayList<Product>();
		Query query = entityManager.createQuery("SELECT p FROM Product as p");//SELECT * FROM tableName
		productlist = query.getResultList();
		System.out.println("--------------------Lista Prodotti--------------------");
		for(Product p: productlist) {
			System.out.println(p.getId() +". " + p.getName());
		}
		entityManager.close();
		return productlist;
	}
	
	public static void main(String[] args) {
		List<Product> prodlist = new ArrayList<Product>();
		Scanner sc = new Scanner(System.in);
		boolean accesso = false;
		boolean listapvisibile = false;
		boolean quit = false;
		String exit = null;
		String password;
		String email;
		int choice;
		
		while (!quit) {
			
			while(!accesso) {
				System.out.println("Effettua prima l'accesso");
				System.out.println("Inserisci email");
				email = sc.next();
				System.out.println("Inserisci password");
				password = sc.next();
				if(isUser(email, password)) {
					accesso = true;
					System.out.println("Accesso effettuato");
				}else System.out.println("email o password incorretti, riprova \n");
				
			}
			
			System.out.println("\n");
			System.out.println("--------Menu' principale--------");
			System.out.println("0: Visualizza intera lista prodotti");
			System.out.println("1: Aggiungi prodotti al carrello e acquista");
			//if(listapvisibile)System.out.println("2: Visualizza descrizione prodotto"); 
			System.out.println("2: Chiudi applicazione");
			System.out.println("\n");
			choice = sc.nextInt();

			switch(choice) {
			case 0:
				getAllProducts();
				break;
			case 1:

				 do{
					 System.out.print("Inserisci il nome del prodotto da acquistare: ");
					 String st = sc.next();
					 Product p = selectProdotto(st);
					 System.out.println(p.toString());
					 System.out.println("Inserisci quantita");
					 int quantity = sc.nextInt();
					 sc.nextLine();
					 if(quantity <= p.getQuantity()) {
						 //prodlist.add(p);
						 EntityManager eM = EntityManagerProvider.getEntityManager();
						 Product pr = eM.find(Product.class, p.getId());
						 EntityTransaction transaction = eM.getTransaction();
						 transaction.begin();
						 int q = pr.getQuantity();
						 p.setQuantity(q-quantity);
						 eM.persist(pr);
						 transaction.commit();
						 eM.close();
					 } else
						 System.out.println("Non abbiamo abbastanza " + p.getName());
					 System.out.println("Vuoi inserire altri prodotti?");
					 Scanner ex = new Scanner(System.in);
					 exit = ex.next();
				 }while(exit.equals("yes"));
				 System.out.println("dioporco");
				 break;
			case 2:
				quit = true;
				break;
			}
		}
	}


}
