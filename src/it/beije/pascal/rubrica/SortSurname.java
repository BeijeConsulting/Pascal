package it.beije.pascal.rubrica;

import java.util.Comparator;

public class SortSurname implements Comparator<Contatto> {
	public int compare(Contatto first, Contatto second) {
		return first.getCognome().compareToIgnoreCase(second.getCognome());
	}
}
