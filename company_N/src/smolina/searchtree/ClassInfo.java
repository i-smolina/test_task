package smolina.searchtree;

import java.util.Comparator;

public class ClassInfo {
	String name;
	long date;

	public ClassInfo(String s, long d) {
		name = s;
		date = d;
	}
	
	public class ClassInfoComparator implements Comparator<ClassInfo> {

		@Override
		public int compare(ClassInfo o1, ClassInfo o2) {
			// compare the dates
			if (o1.date > o2.date) return -1;
			else if (o1.date == o2.date) {
				// if the dates are equals then compare the names
				if (o1.name.compareTo(o2.name) < 0)
					return -1;
				else return 1;
			}
			return 1;
		}
	}
}
