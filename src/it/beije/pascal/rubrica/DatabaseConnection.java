package it.beije.pascal.rubrica;

import java.util.List;

public interface DatabaseConnection {

	List<Contatto> cercaContattoNomeCognome(String nome, String cognome);

	List<Contatto> cercaContatto(Contatto contatto);

	void inserisciContatto(Contatto contatto);

	void modificaContatto(int id, Contatto contatto);

	void eliminaContatto(int id);

	List<Contatto> listDuplicates();

	List<Contatto> listAllOrderedBy(String column, boolean ascendent);
	
	void autoDeleteDuplicati();

}