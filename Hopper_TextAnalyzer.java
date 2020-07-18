/** @author Zachary Hopper */

//Author: Zachary Hopper
//Date: 7/2/2020
//Program Name: Hopper_TextAnalyzer
//Purpose: Analyzes text from an HTML file and returns results.

package module7_TextAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javafx.util.Pair;

//Had to use Jsoup in order to parse the HTML file.
//I put the Jsoup JAR into the resources folder.
import org.jsoup.Jsoup;

/** Analyzes text from an HTML file and returns the results. */
public class Hopper_TextAnalyzer {

	/** Main method with no argument.
	 *  When Hopper_TextAnalyzerGUI is run, this method is invoked.
	 *  @return Results of overloaded main method.
	 *  @throws IOException Needed if the file to be analyzed can't be found. */
	public ArrayList<Pair<String, Integer>> main() throws IOException {

		//Invokes the overloaded main method using an argument.
		//Default is the Macbeth HTML since that's what the program was originally designed to analyze.
		//Also changed name of HTML file since last update.
		return this.main("resources/Macbeth_Entire_Play.html");
	}

	/** Overloaded main method.
	 *  Created for use with test case.
	 *  @return ArrayList object containing 20 Pair objects representing the top 20 words in the file.
	 *  Each Pair object contains a String and an Integer.
	 *  The String stores a word, the Integer stores how many times that word appeared in the file.
	 *  @param filename Name of the file to be analyzed.
	 *  If the noarg main method calls this method, it will analyze a Macbeth HTML file.
	 *  @throws IOException Needed if the file to be analyzed can't be found. */
	public ArrayList<Pair<String, Integer>> main(String filename) throws IOException {

		//Looks into resources folder for HTML file. Program throws IOException if not found.
		//On my computer, the resources folder is a child of the project folder, alongside the src and bin folders.
		//String filename = "resources/Macbeth_Entire_Play.html";
		//String filename = "resources/Test.html";
		//String filename = string;

		//HTML file turned into File variable.
		File input = new File(filename);

		//Text of the File is parsed from the HTML formatting and turned into a String.
		String text = Jsoup.parse(input, "UTF-8").text();

		//Replaces one or more punctuations followed by whitespace with a single space.
		//Otherwise, words that are immediately followed by punctuation would throw off the word count.
		//eg. "again." would be considered different from "again"
		text = text.replaceAll("\\p{Punct}+\\s", " ");

		//Replaces "'s" followed by whitespace with a single space.
		//Otherwise, possessive forms of words would throw off the word count.
		//(Possessive plurals were already taken care of the the above replaceAll.)
		//eg. "Macbeth's" would be considered different from "Macbeth"
		text = text.replaceAll("'s+\\s", " ");

		//Turns all chars in the text to their lowercase forms.
		//Otherwise, words with capital letters would throw off the word count.
		//eg. "The" would be considered different from "the"
		text = text.toLowerCase();

		//Splits up words from the String into a String array based on whitespace.
		String[] splitText = text.split(" ");

		//This variable will be used to tracked recorded words.
		ArrayList<String> wordList = new ArrayList<String>();

		//This variable will be used to track how many times a word is recorded.
		ArrayList<Integer> count = new ArrayList<Integer>();

		//Inspects each word in the array.
		for (String word : splitText) {

			//If the word has not been recorded, it is added, and its count is set to 1.
			if (!wordList.contains(word)) {
				wordList.add(word);
				count.add(1);
			}

			//If the word had been recorded, its count is incremented by 1.
			else {
				count.set(wordList.indexOf(word), (count.get(wordList.indexOf(word))+1));
			}
		}

		//This variable will be used to directly associate each word with its count.
		ArrayList<Pair<String, Integer>> pairedList = new ArrayList<Pair<String, Integer>>();

		//Adds each word and its count to the pair list.
		for (int i = 0; i < wordList.size(); i++) {
			pairedList.add(new Pair<String, Integer> (wordList.get(i), count.get(i)));
		}

		//This variable will hold the 20 words with the highest counts, as well as their associated counts.
		ArrayList<Pair<String, Integer>> top20 = new ArrayList<Pair<String, Integer>>();

		//Searches through the pair list until the highest 20 pairs have been found.
		while (top20.size() < 20) {

			//Placeholder generated for each loop.
			//String value is irrelevant, but it is important to set the Integer to 0.
			Pair<String, Integer> highest = new Pair<String, Integer>("", 0);

			//Searches through the pair list to find the word with the highest count.
			for (int i = 0; i < pairedList.size(); i++) {
				if (pairedList.get(i).getValue() > highest.getValue()) {
					highest = pairedList.get(i);
				}
			}

			//Adds the word with the highest count, and its count, to the top 20.
			top20.add(highest);

			//Removes the word and its count from the pair list so there are no repeats in the top 20.
			pairedList.remove(highest);
		}

		//Returns top20 object to caller.
		return top20;
	}
}
