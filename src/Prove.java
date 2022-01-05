import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import it.beije.pascal.rubrica.Contatto;

public class Prove {
	public static void main(String[] args) {

		// sort Object type
		List<Contatto> list = new ArrayList<Contatto>();
		list.add(new Contatto("nome3", "nome3"));
		list.add(new Contatto("nome1", "nome1"));
		list.add(new Contatto("nome2", "nome2"));
		
		System.out.println("Prima ordinamento:");
		for(Contatto contatto:list) {
			System.out.println(contatto.getNome());
		}

		
		list.sort(new Comparator<Contatto>() {
			public int compare(Contatto c1, Contatto c2) {							
				return c2.getNome().compareTo(c1.getNome());
			}	
			
		});
		
		System.out.println("Dopo ordinamento:");
		for(Contatto contatto:list) {
			System.out.println(contatto.getNome());
		}
		
		
		/*
		values.sort(new Comparator<Contatto>() {
			public int compare(Contatto c1, Contatto c2) {
				return c2.getNome().compareTo(c1.getNome());
			}

		});
		*/

	}
}
