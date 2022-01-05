package it.beije.pascal.rubrica;

import java.util.Comparator;

public class OrdinaCognome implements Comparator<Contatto> {

	public int compare(Contatto o1, Contatto o2) {
		
        return o1.getCognome().compareToIgnoreCase(o2.getCognome());
        
    }
	
}
