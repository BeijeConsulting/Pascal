package it.beije.pascal.file;

import java.io.File;
import java.io.IOException;

public class Tree {
	private String path;

	Tree(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void printPosition() {
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory()) {
			for (File file : dir.listFiles()) {
				if (file.isDirectory()) {
					System.out.print("directory:");
				} else {
					System.out.print("     file:");
				}
				try {
					System.out.println(file.getCanonicalPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void RecursivePrint(File[] arr, int index, int level) {
		if (index == arr.length) {
			return;
		}

		for (int i = 0; i < level; i++)
			System.out.print("\t");

		if (arr[index].isFile())
			System.out.println(arr[index].getName());

		else if (arr[index].isDirectory()) {
			System.out.println(arr[index].getName() + "(dir)");

			RecursivePrint(arr[index].listFiles(), 0, level + 1);
		}
		
		RecursivePrint(arr, ++index, level);
	}

}
