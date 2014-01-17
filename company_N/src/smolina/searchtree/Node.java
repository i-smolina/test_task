package smolina.searchtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import smolina.Searcher;

public class Node {
	char letter;
	List<Node> children = new ArrayList<Node>(); // list of the child nodes
	List<ClassInfo> classNameCache = new ArrayList<ClassInfo>(); 

	public Node(char ch) {
		letter = ch;
	}

	public Node() {
		letter = 0;
	}

	/**
	 * name cache
	 * @param info
	 */
	public void cache(ClassInfo info) {
		if (classNameCache.size() == 0) {
			classNameCache.add(info);
		} else {
			ClassInfo[] arr = new ClassInfo[0];
			arr = classNameCache.toArray(arr);
			int i = Arrays.binarySearch(arr, info, info.new ClassInfoComparator());
			if (i < 0) {
				classNameCache.add(Math.abs(i) - 1, info);
			}
			else {
				System.out.println("Warning: Duplicate name!");
			}
			if (classNameCache.size() > Searcher.SIZE_LS) {
				classNameCache.remove(Searcher.SIZE_LS);
			}
		}
	}
	
	/**
	 * find index of the letter in the list of children
	 * @param ch
	 * @return index 
	 */
	 int findChildNode(char ch) {
		Node[] arr = new Node[0];
		arr = children.toArray(arr);
		int i = Arrays.binarySearch(arr, new Node(ch), new NodeComparator());
		return i;
	}
	
	/**
	 * add letter to the list of children
	 * @param ch
	 * @return
	 */
	Node addChildNode(char ch) {
		int i = findChildNode(ch);
		if (i >= 0)
			return children.get(i);
		else {
			Node newNode = new Node(ch);
			children.add(Math.abs(i) - 1, newNode);
			return newNode;
		}
	}
	
	private class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			if (o1.letter < o2.letter) return -1;
			if (o1.letter == o2.letter) return 0;
			else return +1;
		}
	}
}
