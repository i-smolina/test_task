package smolina.searchtree;

public class SearchTree {
	private Node root; // root of the tree

	public SearchTree() {
		root = new Node();
	}

	/**
	 * insert into the tree name of the class and date
	 * @param str
	 * @param date
	 */
	public void insert(String str, long date) {
		Node current = root;
		ClassInfo info = new ClassInfo(str, date);
		
		for (char ch : str.toCharArray()) {
			// if node have got some children
			if (current.children.size() > 0) { 
				current = current.addChildNode(ch);
				current.cache(info);
			}
			// if node havn't got any children
			else { 
				Node newNode = new Node(ch);
				current.children.add(newNode);
				current = newNode;
				current.cache(info);
			}
		}
	}
	
	public String[] getNameClasses(String str) {
		Node current = root;
		for (char ch : str.toCharArray()) {
			int i = current.findChildNode(ch);
			if (i >= 0) // if letter found
				current = current.children.get(i);
			else
				return new String[0];
		}
		String[] arr = new String[current.classNameCache.size()];
		for (int i = 0; i < current.classNameCache.size(); i++)
			arr[i] = current.classNameCache.get(i).name;
		return arr;
	}

	public void reset() {
		root = new Node();
	}
}
