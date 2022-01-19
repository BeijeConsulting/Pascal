package it.beije.pascal.jpa.rubrica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import it.beije.pascal.bean.Contatto;
import it.beije.pascal.jpa.util.EntityManagerProvider;

public class RubricaMenu {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		boolean continua = true;
		while (continua) {
			writeMenu();
			int scelta = readChoice();
			switch (scelta) {

			case 0:
				continua = false;
				break;
			case 1:
				// backup();
				break;
			case 2:
				printContacts();
				break;
			case 3:
				findContact(1);
				break;
			case 4:
				addContact();
				break;
			case 5:
				updateContact(177);
				break;
			case 6:
				deleteContact(1111);
				break;
			case 7:
				findDuplicates();
				break;
			}
		}
	}

	private static void writeMenu() {
		System.out.println("\n==== MENU ====");
		System.out.println("0: esci");
		System.out.println("1: ripristina database");
		System.out.println("2: stampa lista contatti (Nome crescente)");
		System.out.println("3: cerca contatto");
		System.out.println("4: inserisci contatto");
		System.out.println("5: modifica contatto");
		System.out.println("6: cancella contatto");
		System.out.println("7: trova contatti duplicati");
		System.out.println("8: unisci contatti duplicati\n");
	}

	private static int readChoice() {
		System.out.print("Inserisci scelta: ");
		int scelta = scanner.nextInt();
		while (scelta < 0 || scelta > 8) {
			System.out.print("\nScelta errata,reinserire: ");
			scelta = scanner.nextInt();
		}
		return scelta;
	}

	public static void printContacts() {
		EntityManager entityManger = EntityManagerProvider.getEntityManager();
		String jpql = "SELECT c FROM Contatto AS c";
		Query query = entityManger.createQuery(jpql);
		List<Contatto> contatti = query.getResultList();

		for (Contatto contatto : contatti) {
			System.out.println(contatto);
		}
		entityManger.close();

	}

	private static void findContact(int id) {
		String jpql = "SELECT c FROM Contatto AS c WHERE id = :id";
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);

		try {
			Contatto contatto = (Contatto) query.getSingleResult();
			System.out.println(contatto);

		} catch (NoResultException e) {
			System.out.println("Contatto non trovato");
		}
		entityManager.close();

	}

	private static void addContact() {
		Contatto contatto = new Contatto("nuovo", "nuovo", "nuovo", "nuovo", "nuovo");
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		entityManager.persist(contatto);
		transaction.commit();
		entityManager.close();
	}

	private static void updateContact(int id) {
		String jpql = "SELECT c FROM Contatto AS c WHERE id = :id";
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		Contatto contatto = null;
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		transaction.begin();

		try {
			contatto = (Contatto) query.getSingleResult();
			contatto.setCognome("Modificato");
			contatto.setNome("Modificato");
			contatto.setTelefono("Modificato");
			contatto.setEmail("Modificato");
			contatto.setNote("Modificato");

			entityManager.persist(contatto);
			transaction.commit();

		} catch (NoResultException e) {
			System.out.println("Contatto non trovato");
		}
		entityManager.close();
	}

	private static void findDuplicates() {
		String jpql = "SELECT c.cognome,c.nome,c.telefono,c.email,c.note FROM Contatto AS c GROUP BY c.cognome,c.nome,c.telefono,c.email,c.note HAVING count(c.email) > 1";
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		Query query = entityManager.createQuery(jpql);
		List<Contatto> contatti = query.getResultList();

		for (Contatto contatto : contatti) {
			System.out.println(contatto);
		}
		entityManager.close();

	}

	private static void deleteContact(int id) {
		String jpql = "	SELECT c FROM Contatto AS c WHERE id = :id";
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		Contatto contatto = null;
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		transaction.begin();

		try {
			contatto = (Contatto) query.getSingleResult();
			entityManager.remove(contatto);
			transaction.commit();

		} catch (NoResultException e) {
			System.out.println("Contatto non trovato");
		}

		entityManager.close();
	}

}
