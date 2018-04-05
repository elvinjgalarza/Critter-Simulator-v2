package assignment5; // cannot be in default package

import javafx.application.Application;

import java.lang.reflect.Method;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.geometry.VPos;


import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class Main extends Application{
	private static String myPackage;
	static ByteArrayOutputStream output;
	static GridPane window = new GridPane();
	static GridPane makeWindow = new GridPane();
	static GridPane statsWindow = new GridPane();
	static int steps = 0;


	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static{
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	/* create the windows that the user can use */
	public void commandsFunctionalityWindow(){
		Text scene_title = new Text("Critter Controller");
		scene_title.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 20));
		scene_title.setFill(Color.DARKORANGE);
		window.add(scene_title, 5, 0, 25, 2);
	}
	public void gameBoardWindow(){
		Stage critter_world = new Stage();
		critter_world.setTitle("World of Critters");
		ViewComponent.scaleParams();
		Scene modelScene = new Scene(makeWindow, Params.world_width*(ViewComponent.size+1),
				Params.world_height*(ViewComponent.size+1));
		critter_world.setScene(modelScene);
		critter_world.show();
	}
	public void critterStatisticsWindow(){
		Stage statsView = new Stage();
		statsView.setTitle("Critter Statistics");
		Scene statsViewScene = new Scene(statsWindow, 450, 400);
		statsView.setScene(statsViewScene);
		statsView.show();


	}


	/* Do the functionalities of each command */
	public void addCritterFunctionality(){
		Label name = new Label("Choose Critter:");
		window.add(name, 1, 4, 2, 1);
		/* Create the drop-down menu for selecting the Critters */
		ObservableList<String> options = FXCollections.observableArrayList();
		ComboBox comboBox = new ComboBox(options);
		window.add(comboBox, 5, 4, 10, 1);
		GridPane.setHalignment(comboBox, HPos.LEFT);
		GridPane.setValignment(comboBox, VPos.BOTTOM);
		/* Get the list of Critters in order to select them */
		File[] files = new File("./src/assignment5").listFiles();
		for(File file: files){
			String fileName = file.getName();
			if(file.isFile() && fileName.contains(".java")){
				try{
					String[] fName = fileName.split(".java");
					String packageName = Critter.class.getPackage().toString().split(" ")[1];
					Critter critter = (Critter) Class.forName(packageName + "." + fName[0]).newInstance();
					options.add(fName[0]);
				}
				catch(Exception exception){}
			}
		}

		Label numCritters = new Label("# of Critters:");
		window.add(numCritters, 1, 5, 4, 1);
		TextField numCritterText = new TextField();
		window.add(numCritterText, 5, 5, 10 ,1);

		Button critButton = new Button("Add Critters");
		GridPane.setHalignment(critButton, HPos.LEFT);
		GridPane.setValignment(critButton, VPos.BOTTOM);
		window.add(critButton, 15, 5);

		final Text actiontarget = new Text();
		window.add(actiontarget, 5, 6, 20, 1);
		GridPane.setHalignment(actiontarget, HPos.CENTER);
		GridPane.setValignment(actiontarget, VPos.BOTTOM);
		critButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if((comboBox.getValue() != null &&
						!comboBox.getValue().toString().isEmpty()) && (numCritterText.getText() != null && !numCritterText.getText().isEmpty())){
					String number = numCritterText.getText();
					String type = comboBox.getValue().toString();
					if(!(numCritterText.getText().matches("^[0-9]+$"))){
						actiontarget.setFill(Color.FIREBRICK);
						actiontarget.setText("Insert valid number");
					}
					else{
						try{
							actiontarget.setText("                   ");
							for(int i=0; i < Integer.parseInt(number); i+=1){
								Critter.makeCritter(type);
							}
							Critter.displayWorld();
						}catch(Exception exception){
							System.out.println("error processing");

						}
					}
				}
				else{
					actiontarget.setFill(Color.FIREBRICK);
					actiontarget.setText("Please select number and type of critters");
				}
				comboBox.getSelectionModel().clearSelection();
				numCritterText.clear();
			}
		});
	}
	public void addDisplayWorldFunctionality(){
		Button displayButton = new Button("Display World");
		GridPane.setHalignment(displayButton, HPos.RIGHT);
		GridPane.setValignment(displayButton, VPos.BOTTOM);
		window.add(displayButton, 6, 23, 1, 1);
		displayButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Critter.clearWorld();
				Critter.displayWorld();
			}
		});
	}
	public void addQuitFunctionality(){
		Button quitButton = new Button("Quit");
		GridPane.setHalignment(quitButton, HPos.RIGHT);
		GridPane.setValignment(quitButton, VPos.BOTTOM);
		window.add(quitButton, 16, 23, 1, 1);
		quitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});
	}
	public void addSeedsFunctionality(){
		Label seedsNum = new Label("Seeds:");
		window.add(seedsNum, 1, 9, 4, 1);
		TextField numSeeds = new TextField();
		window.add(numSeeds, 5, 9, 10 ,1);
		Button buttSeed = new Button("Add Seeds");
		GridPane.setHalignment(buttSeed, HPos.LEFT);
		GridPane.setValignment(buttSeed, VPos.BOTTOM);
		window.add(buttSeed, 15, 9);
		final Text actiontarget3 = new Text();
		window.add(actiontarget3, 5, 10, 20, 1);
		GridPane.setHalignment(actiontarget3, HPos.CENTER);
		GridPane.setValignment(actiontarget3, VPos.BOTTOM);
		buttSeed.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if((numSeeds.getText() != null && !numSeeds.getText().isEmpty())){
					String seedNum = numSeeds.getText();
					if(!(numSeeds.getText().matches("^[0-9]+$"))){
						actiontarget3.setFill(Color.FIREBRICK);
						actiontarget3.setText("Insert valid number");
					}
					else{
						try{
							actiontarget3.setText("                   ");
							long seedNum0 = Long.parseLong(seedNum);
							Critter.setSeed(seedNum0);
						}catch(NullPointerException|NumberFormatException exception){
							System.out.println("error processing");

						}
					}
				}
				else{
					actiontarget3.setFill(Color.FIREBRICK);
					actiontarget3.setText("Please select number of seeds");
				}
				numSeeds.clear();
			}
		});
	}
	public void addStepsFunctionality(){
		/* Create the label for the button */
		Label stepLabel = new Label("Num of Steps:");
		window.add(stepLabel, 1, 12, 4, 1);

		/* Create the button for processing a time step*/
		TextField numSteps = new TextField();
		window.add(numSteps, 5, 12, 10, 1);
		Button stepsButton = new Button("Do");
		GridPane.setHalignment(stepsButton, HPos.LEFT);
		GridPane.setValignment(stepsButton, VPos.BOTTOM);
		window.add(stepsButton, 15, 12);

		final Text actiontarget1 = new Text();
		window.add(actiontarget1, 5, 13, 20, 1);
		GridPane.setHalignment(actiontarget1, HPos.CENTER);
		GridPane.setValignment(actiontarget1, VPos.BOTTOM);

		/* Add the field indicating the number of time steps done */
		Label times = new Label("Elapsed Time: ");
		window.add(times, 1, 13, 2, 1);
		GridPane.setHalignment(times, HPos.LEFT);
		GridPane.setValignment(times, VPos.CENTER);
		/* Add counter for the number of time steps done */
		final Text numTimeSteps = new Text();
		numTimeSteps.setText(String.valueOf(steps));
		window.add(numTimeSteps, 4, 13, 1, 1);
		GridPane.setHalignment(numTimeSteps, HPos.LEFT);
		GridPane.setValignment(numTimeSteps, VPos.CENTER);

		stepsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if((numSteps.getText() != null && !numSteps.getText().isEmpty())){
					String stepNum = numSteps.getText();
					if(!(numSteps.getText().matches("^[0-9]+$"))){
						actiontarget1.setFill(Color.FIREBRICK);
						actiontarget1.setText("Insert valid number");
					}
					else{
						try{
							actiontarget1.setText("                   ");
							for(int i=0; i < Integer.parseInt(stepNum); i+=1){
								Critter.worldTimeStep();
								//Critter.clearWorld();
								Critter.displayWorld();
							}
							steps += Integer.parseInt(stepNum);
							numTimeSteps.setText("          ");
							numTimeSteps.setText(String.valueOf(steps));
						}catch(NullPointerException|NumberFormatException exception){
							System.out.println("error processing");

						}
					}
				}
				else{
					actiontarget1.setFill(Color.FIREBRICK);
					actiontarget1.setText("Please select number of steps");
				}
				numSteps.clear();
			}
		});
	}
	public void addStatsFunctionality(){
		final Text statsText = new Text();
		statsWindow.add(statsText, 5, 6, 20, 1);
		GridPane.setHalignment(statsText, HPos.CENTER);
		GridPane.setValignment(statsText, VPos.BOTTOM);

		Button statsButton = new Button("View Stats");
		GridPane.setHalignment(statsButton, HPos.LEFT);
		GridPane.setValignment(statsButton, VPos.BOTTOM);
		window.add(statsButton, 15, 6, 1, 1);
		/* Create the drop-down menu for selecting the Critters */
		ObservableList<String> options1 = FXCollections.observableArrayList();
		ComboBox comboBox1 = new ComboBox(options1);
		window.add(comboBox1, 5, 6, 10, 1);
		GridPane.setHalignment(comboBox1, HPos.LEFT);
		GridPane.setValignment(comboBox1, VPos.BOTTOM);
		/* Find corresponding stats for corresponding Critter */
		File[] files1 = new File("./src/assignment5").listFiles();
		for(File file1: files1){
			String fileName1 = file1.getName();
			if(file1.isFile() && fileName1.contains(".java")){
				try{
					String[] fName1 = fileName1.split(".java");
					String packageName1 = Critter.class.getPackage().toString().split(" ")[1];
					Critter c1 = (Critter) Class.forName(packageName1 + "." + fName1[0]).newInstance();
					options1.add(fName1[0]);
				}catch(Exception exception){
				}

			}
		}
		final Text actiontarget4 = new Text();
		window.add(actiontarget4, 0, 0, 10, 1);
		GridPane.setHalignment(actiontarget4, HPos.CENTER);
		GridPane.setValignment(actiontarget4, VPos.BOTTOM);





		/* Exception Handler for the Buttons */
		statsButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				try{
					if((comboBox1.getValue() != null &&
							!comboBox1.getValue().toString().isEmpty())){
						String type1 = comboBox1.getValue().toString();
						List<Critter> crits = null;
						crits = Critter.getInstances(type1);
						Class<?> critClass = null;
						Class<?>[] types = {List.class};
						critClass = Class.forName(myPackage + "." + type1);
						Method runStats = critClass.getMethod("runStats", types);
						runStats.invoke(critClass, crits);

						statsText.setText(output.toString());
					}
					else{
						actiontarget4.setFill(Color.FIREBRICK);
						actiontarget4.setText("Please select type of critters");
					}
					comboBox1.getSelectionModel().clearSelection();
				}catch(Exception exception){
					comboBox1.getSelectionModel().clearSelection();
				}
			}
		});
	}

	@Override //Override the start method in the Application class
	public void start(Stage primaryStage){
		try{
			// Create the primary stage and add the more stages and buttons
			primaryStage.setTitle("Create your Critter World!"); //Set the stage title
			/* create gaps between fields, buttons, etc. */
			window.setHgap(3);
			window.setVgap(5);
			/* debugging purposes */
			window.setGridLinesVisible(false);
			window.setPadding(new Insets(10, 10, 10, 10));

			// Create the controller scene where the game can be modified
			Scene scene = new Scene(window, 500, 400, Color.DARKGRAY);
			commandsFunctionalityWindow();
			// Create the stage for the board
			gameBoardWindow();
			// Create the stage for the statistics
			critterStatisticsWindow();

			/* Create the actual functionality and buttons and corresponding text */
			addCritterFunctionality();
			addStatsFunctionality();
			addStepsFunctionality();
			addSeedsFunctionality();
			addDisplayWorldFunctionality();
			addQuitFunctionality();


			/* now we're gucci, so we can show the stage and do cool stuff */
			primaryStage.setScene(scene);
			primaryStage.show();

		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public static void main(String[] args){
		/* See FAQ 5: Outputting text to the GUI instead of console */
		// create a buffer in memory to hold all the data sent to the stream
		output = new ByteArrayOutputStream();
		// add functionality to the new output stream so that we can print the data in it
		PrintStream ps = new PrintStream(output);
		// reassign the "standard" (out) output stream, so that System.out.* is caught
		System.setOut(ps);
		launch(args);
	}
}
