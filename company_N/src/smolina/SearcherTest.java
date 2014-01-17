package smolina;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * Тестовый класс Позволяет проверить, что базовые требования выполняются
 * 
 * Для запуска теста необходим JUnit 4.x
 * 
 */
public class SearcherTest {
	String[] clNames = new String[100000];
	long[] modDates = new long[100000];
	ISearcher searcher;
	
	public SearcherTest() {
		for (int i = 0; i < 100000; i++) {
			clNames[i] = "Class" + i;
			modDates[i] = 1322191916000l;
		}
		searcher = new Searcher();
		searcher.refresh(clNames, modDates);
	}

	@Test
	public void testBaseLogic() {
		String[] classNames = new String[] { "ClassA1", "ClassA2", "ClassA3",
				"ClassA4", "ClassA5", "ClassA6", "ClassA7", "ClassA8",
				"ClassA9", "ClassB", "ClassC", "ClassD1", "ClassD2" };

		long[] modificationDates = { 1322191917000l, 1322191918000l,
				1322191913000l, 1322191913000l, 1322191913000l, 1322191913000l,
				1322191913000l, 1322191913000l, 1322191913000l, 1322191914000l,
				1322191915000l, 1322191916000l, 1322191916000l };

		ISearcher searcher = new Searcher();
		searcher.refresh(classNames, modificationDates);
		String[] actualResult = searcher.guess("Class");

		String[] expectedResult = new String[] { "ClassA2", "ClassA1",
				"ClassD1", "ClassD2", "ClassC", "ClassB", "ClassA3", "ClassA4",
				"ClassA5", "ClassA6", "ClassA7", "ClassA8" };

		Assert.assertArrayEquals(expectedResult, actualResult);
	}
	
	@Test
	public void testBaseLogic2() {
		String[] classNames = new String[] { "ClassA1", "ClassA2", "ClassA3",
				"ClassA4", "ClassA5", "ClassA6", "ClassA7", "ClassA8",
				"TestClassA9", "TestClassB", "MyClassC", "MyClassD1", "YourClassD2" };

		long[] modificationDates = { 1322191917000l, 1322191918000l,
				1322191913000l, 1322191913000l, 1322191913000l, 1322191913000l,
				1322191913000l, 1322191913000l, 1322191913000l, 1322191914000l,
				1322191915000l, 1322191916000l, 1322191916000l };

		ISearcher searcher = new Searcher();
		searcher.refresh(classNames, modificationDates);
		String[] actualResult = searcher.guess("My");

		String[] expectedResult = new String[] { "MyClassD1", "MyClassC"};

		Assert.assertArrayEquals(expectedResult, actualResult);
	}

	@Test
	public void TestNotFoundClassName() {
		String[] classNames = new String[] { "ClassA1", "ClassA2", "ClassA3",
				"ClassA4", "ClassA5", "ClassA6", "ClassA7", "ClassA8",
				"ClassA9", "ClassB", "ClassC", "ClassD1", "ClassD2" };

		long[] modificationDates = { 1322191917000l, 1322191918000l,
				1322191913000l, 1322191913000l, 1322191913000l, 1322191913000l,
				1322191913000l, 1322191913000l, 1322191913000l, 1322191914000l,
				1322191915000l, 1322191916000l, 1322191916000l };

		ISearcher searcher = new Searcher();
		searcher.refresh(classNames, modificationDates);
		String[] actualResult = searcher.guess("None");

		String[] expectedResult = new String[0];
		Assert.assertArrayEquals(expectedResult, actualResult);
	}

	@Test
	public void TestNoClasses() {
		String[] classNames = new String[0];
		long[] modificationDates = new long[0];

		ISearcher searcher = new Searcher();
		searcher.refresh(classNames, modificationDates);
		String[] actualResult = searcher.guess("ClassA5");

		String[] expectedResult = new String[0];
		Assert.assertArrayEquals(expectedResult, actualResult);
	}
	
	@Test
	public void TestEmptyName() {
		String[] classNames = new String[] { "ClassA1", "ClassA2", "ClassA3",
				"ClassA4", "ClassA5", "ClassA6", "ClassA7", "ClassA8",
				"ClassA9", "ClassB", "ClassC", "ClassD1", "ClassD2" };

		long[] modificationDates = { 1322191917000l, 1322191918000l,
				1322191913000l, 1322191913000l, 1322191913000l, 1322191913000l,
				1322191913000l, 1322191913000l, 1322191913000l, 1322191914000l,
				1322191915000l, 1322191916000l, 1322191916000l };

		ISearcher searcher = new Searcher();
		searcher.refresh(classNames, modificationDates);
		String[] actualResult = searcher.guess("");

		String[] expectedResult = new String[0];

		Assert.assertArrayEquals(expectedResult, actualResult);
	}
	
	@Test(timeout = 300)
	public void TestTimeGuess() {
		String[] actualResult = searcher.guess("Class98765");
		String[] expectedResult = new String[] { "Class98765" };
		Assert.assertArrayEquals(expectedResult, actualResult);
	}
}