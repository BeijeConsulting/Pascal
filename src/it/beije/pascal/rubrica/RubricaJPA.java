package it.beije.pascal.rubrica;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class RubricaJPA implements DatabaseConnection{

	public RubricaJPA() {
	}

	public static void main(String[] args) {

		RubricaJPA inst = new RubricaJPA();
		inst.listAllOrderedBy("nome", false);
		inst.autoDeleteDuplicati();
		//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pascal-rubrica");
		//		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//		EntityManager entityManager = EntityManagerProvider.getEntityManager();

		//JPQL
		//		Query query = entityManager.createQuery("SELECT c FROM Contatto as c");
		//		//Query query = entityManager.createNativeQuery("SELECT * FROM contatti", Contatto.class);
		//		List<Contatto> contatti = query.getResultList();
		//		
		//		for (Contatto c : contatti) {
		//			System.out.println(c);
		//		}

		//		Contatto contatto = entityManager.find(Contatto.class, Integer.valueOf(18));
		//		System.out.println(contatto);


		//		EntityTransaction transaction = entityManager.getTransaction();
		//		transaction.begin();

		//		//INSERT
		//		Contatto newContatto = new Contatto();
		//		//newContatto.setId(30);
		//		newContatto.setCognome("Brambilla");
		//		newContatto.setNome("Mario");
		//		newContatto.setEmail("m.brambilla@beije.it");
		//		System.out.println("contatto PRE : " + newContatto);
		//		entityManager.persist(newContatto);
		//		System.out.println("contatto POST : " + newContatto);

		//		//UPDATE
		//		contatto.setCognome("Fumagalli");
		//		contatto.setTelefono("43214342");
		//		entityManager.persist(contatto);

		//		//DELETE
		//		entityManager.remove(contatto);
		//		
		//		transaction.commit();
		////		transaction.rollback();
		//		
		//		entityManager.close();


	}

	@Override
	public List<Contatto> cercaContattoNomeCognome(String nome, String cognome) {
		//TODO test
		EntityManager em = EntityManagerProvider.getEntityManager();

		Query query = em.createQuery("SELECT c FROM Contatto AS c WHERE c.nome = :nome AND c.cognome = :cognome ");
		query.setParameter("nome", nome == null ? null : nome)
		.setParameter  ("cognome", cognome == null ? null : cognome);
		List<Contatto> result = query.getResultList();
		em.close();
		return result;
	}

	@Override
	public List<Contatto> cercaContatto(Contatto contatto) {
		//FIXME null values do not return
		EntityManager em = EntityManagerProvider.getEntityManager();
		Query query = em.createQuery("Select c FROM Contatto AS c WHERE c.nome = :nome AND c.cognome = :cognome "
				+ "AND c.telefono = :telefono AND c.email = :email AND c.note = :note");
		query.setParameter("nome", contatto.getNome()).setParameter("cognome", contatto.getCognome()).setParameter("telefono", contatto.getTelefono())
		.setParameter("email", contatto.getEmail()).setParameter("note", contatto.getNote());
		
		List<Contatto> result = query.getResultList();
		em.close();
		return result;
	}

	@Override
	public void inserisciContatto(Contatto contatto) {
		EntityManager em = EntityManagerProvider.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		//FIXME
		//test
		System.out.println("contatto PRE: " + contatto);
		em.persist(contatto);
		System.out.println("contatto POST: " + contatto);

		et.commit();
		em.close();
	}

	@Override
	public void modificaContatto(int id, Contatto contatto) {
		// TODO Test
		EntityManager em = EntityManagerProvider.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		Contatto daModificare = em.find(Contatto.class, id);
		daModificare.setNome(contatto.getNome());
		daModificare.setCognome(contatto.getCognome());
		daModificare.setTelefono(contatto.getTelefono());
		daModificare.setEmail(contatto.getEmail());
		daModificare.setNote(contatto.getNote());
		em.persist(daModificare);

		et.commit();
		em.close();
	}

	@Override
	public void eliminaContatto(int id) {
		// TODO test
		EntityManager em = EntityManagerProvider.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		Contatto toRemove = em.find(Contatto.class, id);
		em.remove(toRemove);

		et.commit();
		em.close();
	}

	@Override
	public List<Contatto> listDuplicates() {
		//TODO test
		EntityManager em = EntityManagerProvider.getEntityManager();
		Query query = em.createNativeQuery("SELECT * FROM contatti GROUP BY nome, cognome HAVING COUNT(id) > 1", Contatto.class);
		List<Contatto> result = query.getResultList();
		em.close();
		return result;
	}

	@Override
	public List<Contatto> listAllOrderedBy(String column, boolean ascendent) {

		EntityManager em = EntityManagerProvider.getEntityManager();
		String sql = "SELECT * FROM contatti ORDER BY " + column + " " + (ascendent ? "ASC" : "DESC");
		Query query = em.createNativeQuery(sql, Contatto.class);
		List<Contatto> result = query.getResultList();
		em.close();
		return result; 
	}

	public void autoDeleteDuplicati() {
		EntityManager em = EntityManagerProvider.getEntityManager();
		Query query = em.createNativeQuery("SELECT * FROM contatti GROUP BY nome, cognome, telefono, email, note HAVING COUNT(id) > 1", Contatto.class);
		List<Contatto> duplicati = query.getResultList();
		query = em.createQuery("SELECT c FROM Contatto AS c WHERE c.id IN "
				+ "(SELECT id FROM Contatto AS d GROUP BY nome, cognome, telefono, email, note HAVING COUNT(*) > 1)", Contatto.class);
		List<Contatto> duplicatiOfC = query.getResultList();

		EntityTransaction et = em.getTransaction();
		et.begin();

		for(Contatto c : duplicati) {

			em.persist(c);
		}
		for(Contatto d : duplicatiOfC) {
			em.remove(d);
		}

		et.commit();
		em.close();
	}


}
