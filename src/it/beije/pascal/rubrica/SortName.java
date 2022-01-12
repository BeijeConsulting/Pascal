package it.beije.pascal.rubrica;

import java.util.Comparator;

public class SortName implements Comparator<Contatto> {
	public int compare(Contatto first, Contatto second) {
		return first.getNome().compareToIgnoreCase(second.getNome());
	}
}