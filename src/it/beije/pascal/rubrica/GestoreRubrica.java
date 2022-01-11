package it.beije.pascal.rubrica;

import java.util.ArrayList;
import java.util.List;

public class GestoreRubrica {

		
		public void InsertAll(List<Contatto> listcont, DBConnection db) {
			for(Contatto c: listcont) {
				db.Insert(c);
			}
		}
		
		public static void main(String[] args) throws Exception {
			XMLCSVmanager man = new XMLCSVmanager();
			DBConnection connection = new DBConnection();
			String fileXML = "C:/Users/franc/git/Pascal/rubrica.xml";
			String fileCSV = "C:/Users/franc/git/Pascal/rubrica.csv";
			String newFileXML = "C:/Users/franc/git/Pascal/rubrica2.xml";
			String newFileCSV = "C:/Users/franc/git/Pascal/rubrica2.csv";
			List<Contatto> listcontatto = new ArrayList<Contatto>();

			connection.Connection();
			try {
				listcontatto = man.loadRubricaFromXML(fileXML);
			} catch (Exception e) {
				System.out.println("Soos");
				e.printStackTrace();
			}
			man.printContatti(listcontatto);
			GestoreRubrica rub = new GestoreRubrica();
			rub.InsertAll(listcontatto, connection);
			
		}

	}

