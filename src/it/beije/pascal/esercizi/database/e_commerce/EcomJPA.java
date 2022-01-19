package it.beije.pascal.esercizi.database.e_commerce;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.beije.pascal.rubrica.Contatto;

public class EcomJPA {

	public static void main(String[] args) {

		Scanner tastiera = new Scanner(System.in);
		boolean accesso = false;
		int scelta = 0;
		User userAttuale = null;

		while(true) {
			
			System.out.println("===== E-commerce-agency =====");
			System.out.println("1 - Esci");
			System.out.println("2 - Registrati");
			System.out.println("3 - Login");
			scelta = tastiera.nextInt();
			
			if(scelta == 1) {
				System.exit(0);
			}
			
			if(scelta == 2) {
				System.out.println("Ok! Per registrarti allora devi inserire le seguenti informazioni");
				System.out.println("Email");
				String email = tastiera.next();
				System.out.println("Password");
				String password = tastiera.next();
				System.out.println("Nome");
				String nome = tastiera.next();
				System.out.println("Cognome");
				String cognome = tastiera.next();
				
				creaNuovoUser(email, password, nome, cognome);
			}

			if(scelta == 3) {
				while (!accesso) {
					System.out.println("Ok! Fai la login");
					System.out.println("Inserisci la tua email");
					String email = tastiera.next();
					System.out.println("Inserisci la tua password");
					String password = tastiera.next();

					userAttuale = controlloLogin(email, password);
					if(userAttuale != null) {
						accesso = true;
					}
				}
			}
			
			if(accesso) {
				System.out.println("\nBenvenuto dentro il nostro e-commerce");
				boolean continuaAcquisti = true;
				
				double amount = 0.0;
				Order order = new Order();
				Order_item order_item;
				List<Order_item> carrello = new ArrayList<Order_item>();
				
				while(continuaAcquisti) {
					
					System.out.println("Ecco la lista dei prodotti che puoi acquistare con tutte le relative informazioni");
					mostraListaProdotti();
					
					System.out.println("Puoi scegliere il prodotto da acquistare scrivendo il numero del suo id!");
					int id = tastiera.nextInt();
					Product prodotto = selezionaProdotto(id);
					int quantità = tastiera.nextInt();
					
					order_item = new Order_item(order.getId(), prodotto.getId(), prodotto.getPrice(), quantità);
					carrello.add(order_item);
					
					System.out.println("Perfetto! " + quantità + " pezzi di " + prodotto + " sono stati aggiunti al carrello");
					System.out.println("1 - se vuoi fare altri acquisti");
					System.out.println("2 - se vuoi completare l'ordine");
					int scelta2 = tastiera.nextInt();
					if(scelta2 == 2) {
						continuaAcquisti = false;
					}
					
				}
				
				for(Order_item o : carrello) {
					amount += (o.getSell_price() * o.getQuantity());
				}
				
				order.setAmount(amount);
				order.setUser_id(userAttuale.getId());
				order.setCreation_datetime(LocalDateTime.now());
				
				eseguiOrdine(order, carrello);
				
				
				
				
				System.exit(0);
			}
			
		}
		

	}
	
	public static void eseguiOrdine(Order order, List<Order_item> carrello) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pippo");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.persist(order);
		
		for(Order_item o : carrello) {
			entityManager.persist(o);
		}
		
		transaction.commit();
		
		entityManager.close();
	}
	
	public static Product selezionaProdotto(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pippo");
		EntityManager em = emf.createEntityManager();
		
		Product prodotto = em.find(Product.class, Integer.valueOf(id));
		
		System.out.println("Ok hai scelto " + prodotto);
		System.out.println("Di questo prodotto sono disponibili " + prodotto.getQuantity() + " pezzi, quanti ne vuoi acquistare?");
		return prodotto;
	}
	
	public static void mostraListaProdotti() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pippo");
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("SELECT p FROM Product as p");
		List<Product> prodotti = query.getResultList();
		
		for(Product p : prodotti) {
			System.out.println(p);
		}
	}
	
	public static void creaNuovoUser(String email, String password, String nome, String cognome) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pippo");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		User u = new User(email, nome, cognome, password);
		
		em.persist(u);
		
		System.out.println("Ottimo adesso sei registrato!");
		
		transaction.commit();
		em.close();
	}

	public static User controlloLogin(String email, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pippo");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT u FROM User as u");
		List<User> utenti = query.getResultList();
		boolean accesso = false;

		User user = new User();
		for (User u : utenti) {
			if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
				accesso = true;
				user = u;
				System.out.println("Accesso effettuato!");
			}
		}
		if (!accesso) {
			System.out.println("Accesso negato\nUsername o password errati!");
		}
		return user;
	}

}
