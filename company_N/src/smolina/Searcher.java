package smolina;

import smolina.searchtree.SearchTree;

/**
 * Реализация поиска
 *
 */
public class Searcher implements ISearcher{
	SearchTree tree;
	public static final int SIZE_LS = 12;
	
	public Searcher() {
		tree = new SearchTree();
	}

	@Override
	public void refresh(String[] classNames, long[] modificationDates) {
		for (int i = 0; i < classNames.length; i++) {
			tree.insert(classNames[i],modificationDates[i]);
		}
	}

	@Override
	public String[] guess(String start) {
		return tree.getNameClasses(start);
	}
}
