package it.beije.pascal.rubrica;

import java.util.Comparator;

public class OrdinaNome implements Comparator<Contatto> {

	public int compare(Contatto o1, Contatto o2) {
		
        return o1.getNome().compareToIgnoreCase(o2.getNome());
        
    }
	
}
