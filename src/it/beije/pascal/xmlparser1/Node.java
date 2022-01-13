package it.beije.pascal.xmlparser1;

import java.util.ArrayList;

public class Node {

	private Tag root;
	private Node parent;
	private ArrayList <Node> children;
	
	public Node (Tag root) {
		
		this.root = root;
		children = new ArrayList<>();
		
	}
	
	public Node addChild(Tag child) {
		
		Node childNode = new Node(child);
		this.children.add(childNode);
		return childNode;
		
	}
	
	public Tag getRoot() {
		return root;
	}
	
	public boolean isRoot() {
		return parent == null;
	}
	
	public boolean isLeaf() {
		
		return children.size() == 0;
		
	}
	
	public int getLevel() {
		
		if(this.isRoot())
			return 0;
		else
			return parent.getLevel()+1;
		
		
	}
	
	  
}
