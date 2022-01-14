package it.beije.pascal.xmlparser1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser1 {
	public static Node node;

	public static void main(String[] args) {
		List<String> rows = readFile();
		StringBuilder str = new StringBuilder();
		for (String row : rows) {
			str.append(row);
		}
		Node nodo = test2(str.toString(), null);
		System.out.println(nodo.isRoot());
		System.out.println(nodo.isLeaf());
		System.out.println(nodo.getLevel());
		System.out.println(nodo.getRoot());
		// System.out.println(nodo.getParent());
		System.out.println(nodo.getChildren());

	}

	private static List<String> readFile() {
		List<String> rows = new ArrayList<>();
		FileReader reader = null;
		try {
			reader = new FileReader("C:/Users/ema29/javafile/xml/challenge.xml"); //da sistemare path corretto
			BufferedReader bufferedReader = new BufferedReader(reader);
			while (bufferedReader.ready()) {
				String row = bufferedReader.readLine();
				rows.add(row);
			}
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return rows;
	}

//	private static void test() {
//		List<Tag> tags = new ArrayList<Tag>();
//		List<String> rows = readFile();
//		StringBuilder sb = new StringBuilder();
//		OUTER: for (String row : rows) {
//			for (int i = 0; i < row.length(); i++) {
//				if (row.charAt(i) == '<') {
//					for (int j = i + 1; row.charAt(j) != '>'; j++) {
//						if (row.charAt(j) != '/') {
//							sb.append(row.charAt(j));
//						}
//						i = j;
//					}
//					Tag tag = new Tag();
//					tag.setName(sb.toString());
//					tags.add(tag);
//					sb.delete(0, sb.length());
//				}
//			}
//		}
//	}

	private static List<Tag> getTagList() {
		List<String> rows = readFile();
		List<Tag> tags = new ArrayList<Tag>();
		StringBuilder sb = new StringBuilder();

		for (String row : rows) {
			for (int i = 0; i < row.length(); i++) {
				if (row.charAt(i) == '<') {
					LOOP: for (int j = i + 1; row.charAt(j) != '>'; j++) {
						// if (row.charAt(j) != '/') {
						sb.append(row.charAt(j));
						i = j;
						// }
					}
					Tag tag = new Tag();
					tag.setName(sb.toString());
					tags.add(tag);
					// node = new Node(tag);
					sb.delete(0, sb.length());
				}
			}
		}
		return tags;
	}

//	private static void createTree(List<Tag> tags) {
//		Node node = null;
//		for (int i = 0; i < tags.size(); i++) {
//			node = new Node(tags.get(i));
//
//			if (i > 0) {
//				if (tags.get(i).getName().charAt(0) != '/') {
//					//node.addChild(tags.get(i));
//				}
//			}
//		}
//	}

//	private static Node test(String str, Node father) {
//		StringBuilder sb = new StringBuilder();
//
//		OUTER: for (int i = 0; i < str.length(); i++) {
//			if (str.charAt(i) == '<') {
//				for (int j = i + 1; str.charAt(j) != '>'; j++) {
//					if (str.charAt(j) != '/') {
//						sb.append(str.charAt(j));
//						i = j;
//					}
//					else {
//						for (int k = j + 1; str.charAt(k) != '>'; k++) {
//							i = k;
//							sb.delete(0, sb.length());
//							if(str.charAt(k +1) == '>') {
//								continue OUTER;
//							}
//						}
//						
//					}
//					else {
//						sb.delete(0, sb.length());
//						test(str.substring(i + 2), father);
//					}
//
//				}
//
//				Tag tag = new Tag();
//				tag.setName(sb.toString());
//				sb.delete(0, sb.length());
//				System.out.println(tag.getName());
//				if (father != null) {
//					//node = father.addChild(tag);
//					if (i < str.length()) {
//						System.out.println(str);						
//						// System.out.println();
//						test(str.substring(i + 2), node);
//						System.out.println("Padre: " + node.getRoot().getName());
//						System.out.println("Nome tag: " + node.getParent().getRoot().getName());
//						System.out.println("Level: " + node.getLevel());
//						System.out.println("--------------------");
//
//						// 10 9 11
//
//					} else {
//						System.out.println("Fuori indice p");
//					}
//
//				} else {
//					if (i < str.length()) {
//						node = new Node(tag);
//						// System.out.println(str);
//						// System.out.println();
//						test(str.substring(i + 2), node);
//						System.out.println("Figlio: " + node.getRoot().getName());
//						System.out.println("Nome tag: " + node.getParent().getRoot().getName());
//						System.out.println("Level: " + node.getLevel());
//						System.out.println("--------------------");
//
//					} else {
//						System.out.println("Fuori indice f");
//					}
//				}
//			}
//
//		}
//		return node;
//	}

	private static Node test2(String row, Node father) {
		StringBuilder sb = new StringBuilder();
		LOOP: for (int i = 0; i < row.length(); i++) {
			if (row.charAt(i) == '<') {
				for (int j = i + 1; row.charAt(j) != '>'; j++) {
					if (row.charAt(j) != '/') {
						sb.append(row.charAt(j));
						i = j;
					} else {
						sb.delete(0, sb.length());
						continue LOOP;
					}
				}
				Tag tag = new Tag();
				tag.setName(sb.toString());
				sb.delete(0, sb.length());
				node = new Node(tag);

				if (father != null) {
					father.addChild(node);
					System.out.println("Padre: " + father.getRoot().getName());
					System.out.println("------------------");
					System.out.println("Nodo: " + node.getRoot().getName());
					System.out.println("------------------");
					test2(row.substring(i + 1), node);

				} else {
					System.out.println("Nodo else: " + node.getRoot().getName());
					System.out.println("------------------");
					test2(row.substring(i + 1), node);
				}
			}
		}
		return node;
	}
}
