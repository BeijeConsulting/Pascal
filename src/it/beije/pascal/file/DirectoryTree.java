package it.beije.pascal.file;

import java.io.File;
public class DirectoryTree {

	public static String printDirectoryTree(File directory) { //unico metodo visibile
		
		//il file è una directory?
    	if (!directory.isDirectory()) { 
        	throw new IllegalArgumentException("Non hai passato una directory");
    	}
    	int indent = 0;
    	StringBuilder sb = new StringBuilder();
    	DirectoryTree.printDirectoryTree(directory, indent, sb);
    	return sb.toString();
	}

	private static void printDirectoryTree(File directory, int indent, StringBuilder sb) {
		
    	sb.append(DirectoryTree.getIndentString(indent)); 
    	sb.append(" " + directory.getName() + "/ (dir) \n");
    	File[] files = directory.listFiles();
    	for (File file : files) {
    		//se troviamo una directory la stampiamo con tutti i contenuti (ricorsione) altrimenti stampiamo il singolo file
    		if (file.isDirectory()) { 
            	DirectoryTree.printDirectoryTree(file, indent + 1, sb);
        	} else {
            	DirectoryTree.printFile(file, indent + 1, sb);
        	}
    	}
	}

	private static void printFile(File file, int indent, StringBuilder sb) {
		
    	sb.append(DirectoryTree.getIndentString(indent) + " " + file.getName() + "\n");
	}

	private static String getIndentString(int indent) {
		
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < indent; i++) {
        	sb.append("   ");
    	}
    	return sb.toString();
	}

	public static void main(String... args) {
		File dir = new File("/javaExc");
		System.out.println(DirectoryTree.printDirectoryTree(dir));
	}
}
