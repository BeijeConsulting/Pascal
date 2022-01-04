package it.beije.pascal.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class CSVmanager {

	public static void main(String[] args) throws IOException{

//		File file = new File("rubrica.csv");
//		System.out.println("esiste ? " + file.exists());
//		System.out.println("isDirectory ? " + file.isDirectory());
//
//		//uso il metodo di lettura con il Reader
//		leggiFileReader(file);
//
//		System.out.println();
//
//		//uso il metodo con il BufferedReader che mi rende un ArrayList di String che corrispondono alle righe della colonna
//		List<String> rows = new ArrayList<String>();
//		rows = ottieniRighe(file);
//		for(String s : rows) {
//			System.out.println(s);
//		}
//
//		System.out.println();
//
//		//uso il metodo con lo StringTokenizer che mi permette di spacchettare le righe ottenute col BufferedReader in base al 
//		//separatore che gli passo nel costruttore
//		List<String> campiTokenizer = new ArrayList<String>();
//		campiTokenizer = ottieniCampiTokenizer(file);
//		for(String s : campiTokenizer) {
//			System.out.println(s);
//		}
//
//		System.out.println();
//
//		//uso il metodo split() delle String per spacchettare le righe ottenute dal BufferedReader 
//		List<String> campiSplit = new ArrayList<String>();
//		campiSplit = ottieniCampiStringSplit(file);
//		for(String s : campiSplit) {
//			System.out.println(s);
//		}
//		
//		System.out.println();
//		
//		//uso la classe FileWriter per cancellare e riscrivere un certo file (ma me lo svuota e basta)!!
//		File newFile = new File("C:\\Users\\Padawan01\\OneDrive\\Desktop\\nuovo.txt");
//		System.out.println(newFile.exists());
//		
//		scriviFile(newFile);
		
		alberoDirectory("C:\\Users\\Padawan01\\OneDrive\\Desktop\\ciao");
		
		

		
		//		FileReader reader = null;
		//		FileWriter writer = null;
		//		List<String> rows = new ArrayList<String>();
		//
		//		try {
		//			reader = new FileReader(file);//"/temp/rubrica.txt"
		//			
		//			while (reader.ready()) {
		//				System.out.print((char)reader.read());
		//			}
		//			
		//			BufferedReader bufferedReader = new BufferedReader(reader);
		//			String row;
		//			while (bufferedReader.ready()) {
		//				row = bufferedReader.readLine();
		//				rows.add(row);
		//				System.out.println(row);

		//				StringTokenizer tokenizer = new StringTokenizer(row, "\t");
		//				System.out.println(tokenizer.countTokens());
		//				while (tokenizer.hasMoreElements()) {
		//					System.out.println(tokenizer.nextElement());
		//				}

		//				String[] r = row.split("\t");
		//				System.out.println("COGNOME : " + r[0]);
		//				System.out.println("NOME : " + r[1]);
		//				System.out.println("TELEFONO : " + r[2]);
		//				System.out.println("EMAIL : " + r[3]);
		//			}
		//
		//			
		//			System.out.println("rows size : " + rows.size());
		//			
		//			File newFile = new File("/temp/rubrica2.csv");
		//			System.out.println("esiste ? " + newFile.exists());
		//			
		//			writer = new FileWriter(newFile);
		//			
		//			for (String r : rows) {
		//				String[] c = r.split("\t");
		//				
		//				StringBuilder newRow = new StringBuilder(c[0]).append(';')
		//						.append(c[1]).append(';')
		//						.append(c[2]).append(';')
		//						.append(c[3]).append('\n');
		//				
		//				writer.write(newRow.toString());
		//			}
		//			writer.flush();
		//			
		//		} catch (IOException ioEx) {
		//			ioEx.printStackTrace();
		//		} finally {
		//			try {
		//				if (writer != null) {
		//					writer.close();
		//				}
		//				if (reader != null) {
		//					reader.close();
		//				}
		//			} catch (Exception fEx) {
		//				fEx.printStackTrace();
		//			}
		//		}

	}

	public static void leggiFileReader(File f) throws IOException{

		FileReader lettore = new FileReader(f);

		while(lettore.ready()) {
			System.out.print((char)lettore.read());	
		}

	}

	public static List<String> ottieniRighe(File f) throws IOException{

		FileReader lettore = new FileReader(f);
		BufferedReader lettoreRighe = new BufferedReader(lettore);
		List<String> rows = new ArrayList<String>();

		while(lettoreRighe.ready()) {
			rows.add(lettoreRighe.readLine());
		}

		return rows;
	}

	public static List<String> ottieniCampiTokenizer(File f) throws IOException{
		List<String> campo = new ArrayList<String>();
		FileReader lettore = new FileReader(f);
		BufferedReader lettoreRighe = new BufferedReader(lettore);

		while(lettoreRighe.ready()) {
			StringTokenizer tokenizer = new StringTokenizer(lettoreRighe.readLine(), "\t");
			//			System.out.println(tokenizer.countTokens());
			while (tokenizer.hasMoreElements()) {
				campo.add(tokenizer.nextElement().toString());
			}
		}

		return campo;
	}

	public static List<String> ottieniCampiStringSplit(File f) throws IOException{
		List<String> campo = new ArrayList<String>();
		FileReader lettore = new FileReader(f);
		BufferedReader lettoreRighe = new BufferedReader(lettore);
		String row;

		while(lettoreRighe.ready()) {
			row = lettoreRighe.readLine();
			String[] r = row.split("\t");
			for(String s : r) {
				campo.add(s);
			}
		}

		return campo;
	}

	public static void scriviFile(File f) throws IOException{
		FileWriter writer = new FileWriter(f);
		FileReader reader = new FileReader(f);
		BufferedReader buffered = new BufferedReader(reader);
		List<String> rows = new ArrayList<String>();
		
		while(buffered.ready()) {
			rows.add(buffered.readLine());
		}
		
		for (String r : rows) {
			String[] c = r.split("\t");

			StringBuilder newRow = new StringBuilder(c[0]).append(';')
					.append(c[1]).append(';')
					.append(c[2]).append(';')
					.append(c[3]).append('\n');

			writer.write(newRow.toString());
		}
		writer.flush();
		System.out.println("File sovrascritto :(");
	}
	
	public static void alberoDirectory(String directoryPath) throws IOException{
		File file = new File(directoryPath);
		if(file.isDirectory()) {
//			System.out.println(file.getPath());
//			System.out.println(file.getName());
//			System.out.println(file.getAbsolutePath());
//			System.out.println(file.getCanonicalPath());
			File[] a = file.listFiles();
			
			for(File f : a) {
				if(f.isFile()) {
					System.out.println(f.getName());
				}
				else if(f.isDirectory()){
					System.out.println("\t" + f.getName());
					File[] d = f.listFiles();
					for(File sotto : d) {
						if(sotto.isFile()) {
							System.out.println("\t\t" + sotto.getName());
						}
						else if(sotto.isDirectory()){
							System.out.println("\t\t\t\t" + sotto.getName());
						}
					}
				}
			}
		}
		
	}

}
