/** @author Zachary Hopper */

//Author: Zachary Hopper
//Date: 7/2/2020
//Program Name: Hopper_TextAnalyzerTest
//Purpose: Tests Hopper_TextAnalyzer using a test case.

package module7_TextAnalyzer;

import static org.junit.Assert.*;

import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import javafx.util.Pair;

/** Tests Hopper_TextAnalyzer using a test case. */
public class Hopper_TextAnalyzerTest {

	//main method, which invokes class's own test method.
	/** Invokes class's own test method.
	 *  @param args Default args parameter included in main method. Not used here.
	 *  @throws IOException Needed if the file to be analyzed can't be found. */
	public static void main(String[] args) throws IOException {

		//Creates Hopper_TextAnalyzerTest object, then invokes its test method.
		Hopper_TextAnalyzerTest analyzerTest = new Hopper_TextAnalyzerTest();
		analyzerTest.test();
	}

	//test method, which uses a test case to determine if Hopper_TextAnalyzer
	//is functioning properly.
	/** Tests Hopper_TextAnalyzer against a test case to determine if it's functioning properly.
	 *  @throws IOException Needed if the file to be analyzed can't be found. */
	@Test
	public void test() throws IOException {

		//Creates ArrayList object testTop20 to contain known answers to the test case.
		//This test case is the opening of The Lego Movie, written as a play and formatted in HTML.
		//I used this video on YouTube for reference: https://www.youtube.com/watch?v=V96h4KA_qbo
		ArrayList<Pair<String, Integer>> testTop20 = new ArrayList<Pair<String, Integer>>();
		testTop20.add(new Pair<String, Integer>("the",       13));
		testTop20.add(new Pair<String, Integer>("lord",      12));
		testTop20.add(new Pair<String, Integer>("business",  12));
		testTop20.add(new Pair<String, Integer>("a",         11));
		testTop20.add(new Pair<String, Integer>("vitruvius", 11));
		testTop20.add(new Pair<String, Integer>("ha",        7));
		testTop20.add(new Pair<String, Integer>("kragle",    7));
		testTop20.add(new Pair<String, Integer>("robots",    7));
		testTop20.add(new Pair<String, Integer>("and",       6));
		testTop20.add(new Pair<String, Integer>("of",        6));
		testTop20.add(new Pair<String, Integer>("is",        4));
		testTop20.add(new Pair<String, Integer>("i",         3));
		testTop20.add(new Pair<String, Integer>("enter",     3));
		testTop20.add(new Pair<String, Integer>("ah",        3));
		testTop20.add(new Pair<String, Integer>("most",      3));
		testTop20.add(new Pair<String, Integer>("now",       3));
		testTop20.add(new Pair<String, Integer>("will",      3));
		testTop20.add(new Pair<String, Integer>("can",       3));
		testTop20.add(new Pair<String, Integer>("you",       3));
		testTop20.add(new Pair<String, Integer>("me",        3));

		//Creates Hopper_TextAnalyzer object analyzer to invoke its main method using test file.
		Hopper_TextAnalyzer analyzer = new Hopper_TextAnalyzer();

		//Compares two ArrayList objects. The first is testTop20, the ArrayList object from above.
		//The other is the ArrayList object returned by the main method of analyzer using a test file.
		//If the two objects are not equal, then the JUnit error will return the given string.
		assertEquals("Top 20 results not equal!", testTop20, analyzer.main("resources/Test.html"));
	}
}
