/** @author Zachary Hopper */

//Author: Zachary Hopper
//Date: 7/2/2020
//Program Name: Hopper_TextAnalyzerGUI
//Purpose: Displays the output of Hopper_TextAnalyzer in a GUI using JavaFX.

package module7_TextAnalyzer;

//These imported classes are shared with Hopper_TextAnalyzer, used here to handle
//interaction with that class and its output.
import java.io.IOException;
import java.util.ArrayList;
import javafx.util.Pair;

//These imported classes and packages are used to create the GUI.
import javafx.application.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** Displays the output of Hopper_TextAnalyzer in a GUI using JavaFX. */
public class Hopper_TextAnalyzerGUI extends Application {

	/** Default start method used by JavaFX to create and modify the stage.
	 *  @param stage Stage object used to display output.
	 *  @throws IOException Needed if the file to be analyzed can't be found. */
	@Override
	public void start(Stage stage) throws IOException {

		//Creates a button that will appear in the first scene that
		//the user will see.
		Button button = new Button();
		button.setText("Start Text Analyzer");

		//Creates StackPane object layout and adds button to it.
		StackPane layout = new StackPane();
		layout.getChildren().add(button);

		//Creates Scene object startScene to display layout.
		//Also specifies size of startScene.
		Scene startScene = new Scene(layout, 200, 100);

		//Sets title of stage to "Start".
		stage.setTitle("Start");

		//Sets stage's scene to startScene.
		stage.setScene(startScene);

		//Launches stage.
		stage.show();

		//Sets button to call Hopper_TextAnalyzer and display its output
		//when pressed.
		button.setOnAction(showTop20 -> {

			//try used here to catch Hopper_TextAnalyzer's thrown IOException.
			try {

				//Calls the Hopper_TextAnalyzer class and stores its output.
				//null used as args argument since that argument isn't important.
				Hopper_TextAnalyzer analyzer = new Hopper_TextAnalyzer();
				ArrayList<Pair<String, Integer>> top20 = analyzer.main();

				//Text displayed at the top of the results screen.
				Text resultsText = new Text("Top 20 words in text by count:\n");

				//Creates TextFlow object resultsFlow to display results in results screen,
				//adds offsets, and adds resultsText to resultsFlow.
				TextFlow resultsFlow = new TextFlow();
				resultsFlow.setLayoutX(10);
				resultsFlow.setLayoutY(10);
				resultsFlow.getChildren().add(resultsText);

				//For each of the pairs of data returned from Hopper_TextAnalyzer,
				//displays the information within that pair.
				for (Pair<String, Integer> top : top20) {

					//Begins with count rather than word to make lining up the columns
					//created with tab easier. Object begins with new line to give
					//whitespace in-between lines.
					Text countText = new Text("\nCount: ");

					//Count variable made blue to make it stand out more as a variable
					//rather than as hardcoded text.
					Text valueText = new Text("" + top.getValue());
					valueText.setFill(Color.BLUE);

					//Inserts tab to make the two variables easier to read while
					//remaining on the same line.
					Text wordText = new Text("\tWord: \"");

					//Word variable made red to make it stand out more as a variable
					//rather than as hardcoded text.
					Text keyText = new Text("" + top.getKey());
					keyText.setFill(Color.RED);

					//Ending quote for word variable.
					Text endText = new Text("\"");

					//Adds all created Text objects to resultsFlow.
					resultsFlow.getChildren().addAll(countText, valueText, wordText, keyText, endText);
				}

				//Creates Group object resultsGroup to display resultsFlow.
				Group resultsGroup = new Group(resultsFlow);

				//Creates Scene object resultsScene to display resultsGroup.
				//Also specifies size of resultsScene.
				Scene resultsScene = new Scene(resultsGroup, 250, 375);

				//Sets title of stage to "Results" when it displays the results.
				stage.setTitle("Results");

				//Sets stage's scene to resultsScene.
				stage.setScene(resultsScene);

			}

			//Catches the IOException thrown by Hopper_TextAnalyzer.
			//Since the file is hardcoded, this is filled out
			//mostly for the sake of completion.
			catch (IOException e) {

				//Creates Text object errorText used to display error message.
				Text errorText = new Text("IOException occured. Can't find file.");

				//Creates TextFlow object errorFlow to display errorText.
				TextFlow errorFlow = new TextFlow();

				//Adds offsets to errorFlow.
				errorFlow.setLayoutX(10);
				errorFlow.setLayoutY(10);

				//Creates Group object errorGroup to display errorFlow.
				Group errorGroup = new Group(errorFlow);

				//Adds errorText to errorFlow.
				errorFlow.getChildren().add(errorText);

				//Creates Scene object errorScene using errorGroup.
				//Also specifies size of errorScene.
				Scene errorScene = new Scene(errorGroup, 225, 50);

				//Sets title of stage to "Error".
				stage.setTitle("Error");

				//Sets stage's scene to errorScene.
				stage.setScene(errorScene);
			}
		});
	}

	/** Launches the start method.
	 *  @param args Default args parameter included in main method. Not used here. */
	public static void main(String[] args) {
		launch(args);
	}
}
