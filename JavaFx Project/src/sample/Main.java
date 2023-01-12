package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

//////////////////////////////////////////////////////
//Omercan Sabun 150119555 - Faruk Akdemir 150119012///
//////////////////////////////////////////////////////

public class Main extends Application {

    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        startMenu();
    }

    // start level
    // input :
    //          + level ,
    //          + loaded data(level save in file)
    //


    public void startLevel(int level,LoadGameData loadGameData){
        // Starts the game. Loading the game files, resuming the saved game from where it left off, and the level system are here.
        // Props and settings for the game are made here.
        Parent root = renderLevel(level,loadGameData);
        Scene scene = new Scene(root, 650, 720);
        // The scene was created in 650x720 resolution.
        scene.getStylesheets().add("style.css");
        // To add a style file to the content of the sceneWe used the getStylesheets () method. We have visually edited a button, cell, and stage by creating a style.css file.
        stage.setTitle("Level: " + level);
        // The stage name is arranged with the level found as level + integer level number.
        stage.setScene(scene);
        //The scene is set as the scene at the previously specified 650x720 resolution.
        stage.show();
        //Shows the scene.
    }
    public void startMenu(){
        // Launches the game's menu. Scene adjustment for the game menu, name determination and style editing with css file are done here.
        Parent root = renderMenu();
        // Imports the Vbox in renderMenu (). Sets it as a parent.
        Scene scene = new Scene(root, 650, 720);
        // The scene was created in 650x720 resolution.
        scene.getStylesheets().add("style.css");
        // To add a style file to the content of the scene.We used the getStylesheets () method.
        // We have visually edited a button, cell, and stage by creating a style.css file.
        stage.setTitle("Menu");
        // the stage name is set here.
        stage.setScene(scene);
        //The scene is set as the scene at the previously specified 650x720 resolution.
        stage.show();
        //Shows the scene.
    }


    public VBox renderMenu(){
        // The menu's content, design and home screen are set here.

        // main container.
        VBox vbox = new VBox();
        // VBox lays out its children in a single vertical column.
        // That's why a Vbox () named vbox is called. Insets settings,
        // gaps between buttons and main screen, alignment, menu buttons, menu articles are adjusted from here.
        vbox.setPadding(new Insets(10,10,10,10));
        // While insets for vbox are empty by default, here they are set to top-10, right-10,bottom-10,left-10.
        vbox.setSpacing(50);
        // Sets the value of the property spacing.We set it as 50 here.
        vbox.setAlignment(Pos.CENTER);
        //Since it is a menu as alignment and we think it makes sense, we set it as Pos.CENTER and centered it.

        // load saved high score.
        CellLogic.setHighScore(SaveAndLoadData.loadHighScore());
        // Our legendary and confusing CellLogic.java file finally finds its place in main.
        // Getting the highest recorded score from SaveAndLoadData.loadHighScore ()
        // Of course, as we saw on CellLogic.java it actually updates the highScore.txt when the current score reaches the highest score.

        // Menu buttons and menu texts can be found here.
        Text welcomeText = new Text("Welcome to Hit The Box Game");
        // Right here, welcome to our game! There is a Text part that greets us.
        // We used the text class here and chose our introduction text.
        Text highScoreText = new Text("High Score: " + CellLogic.getHighScore());
        // We used the text class again and in order to show the highest score,
        // we showed the highest score with the getHighScore method in our High Score: + CellLogic.java file.
        // Actually getHighScore returns to highScore. highScore is updated with the user's score.
        // Of course, if the user's score does not exceed the record previously broken by the same or another user,
        // the score cannot update the highScore. Let's say that the user's score is higher than highScore,
        // so if (CellLogic.score> CellLogic.highScore) returns correct.
        // Then highScore is updated by the score and it updates the .txt file with the saveHighScore in the SaveAndLoadData.java file.

        Button newGameBtn = new Button("New game");
        // We call the button class and update the text of the button as a new game.We set the name of the button as newGameBtn.
        Button resumeBtn = new Button("Resume game");
        // We call the button class and update the text of the button as a resume game.We set the name of the button as resumeBtn.
        Button quitBtn = new Button("Quit");
        // We call the button class and update the text of the button as a quit.We set the name of the button as quitBtn.
        Button Youtube = new Button("Youtube Trailer");
        // style.css
        welcomeText.getStyleClass().add("menu-text");
        // We set the properties and style of the menu text from the style.css file with the getStyleClass() method.
        highScoreText.getStyleClass().add("menu-score-text");
        // We set the properties and style of the menu score text from the style.css file with the getStyleClass() method.
        newGameBtn.getStyleClass().add("menu-button");
        // We set the properties and style of the menu button text from the style.css file with the getStyleClass() method.
        resumeBtn.getStyleClass().add("menu-button");
        // We set the properties and style of the menu button text from the style.css file with the getStyleClass() method.
        quitBtn.getStyleClass().add("menu-button");
        // We set the properties and style of the menu button text from the style.css file with the getStyleClass() method.
        Youtube.getStyleClass().add("easter-egg");
        // The style we set for the Youtube trailer is transferred over the style.css file, but the name is easter-egg as you can see.
        // Please browse the style.css file, thank you!

        // new button clicked.
        newGameBtn.setOnMouseClicked(event -> {
            // The new game button is activated with the left mouse button and the game is started from level 1 by reading the level1.txt file.
            startLevel(1,null);
            // As the new game button is clicked, the player's data is deleted and returned as null.
            // Therefore, if any, the player's data is deleted with the new game button,
            // because the data held on the game.txt file is reset because the player will start the new game.
            // (However, this reset takes place after the game is closed,
            // so the newly saved data of the player is updated after the player closes the game.)
            // The player data is updated with integer CurrentLevel, integer currentScore values in CellLogic.java file.
            // Every move the player makes or passes a new level is recorded.
            // The saved data is updated with the saveGame method in SaveandLoadData.java.
        });

        // quit button clicked.
        quitBtn.setOnMouseClicked(event -> {
            // When the Quit button is clicked with the left click, the stage is closed and the game ends before it starts.
            // Because the quit is in the menu, when the user starts the game, unfortunately, there is no chance to return to the menu.
            stage.close();
            // The stage closes.
        });

        Youtube.setOnMouseClicked(event -> {
            // When you left click on the Youtube Trailer button, it runs and activates the default browser on the machine, causing it to open the desired link.
            // It directs you to our youtube link from the default browser of the computer and allows you to watch our promotional video.
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=eDw201M2lkc"));
            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
            // The stage closes.
        });

        // resume button clicked.
        resumeBtn.setOnMouseClicked(event -> {
            // When the resume button is left clicked, the game.txt file is triggered and loaded.
            // Basically, the logic of this button is SaveAndLoadData.loadGame(); located in SaveAndLoadData.java; is calling the method.
            // This part is one of the hardest part because we had to create a different logic and used the HashMap class,
            // which is one of the concrete classes of Map Interface.
            // We have uploaded the data written on the game.txt file (level, clicks, the last state of the boxes) to the map by checking the rows and columns.
            // We created 3 different cases using Switch.
            // These cases contained empty, mirror and wood type. For each type, we created an array with row and column information first and then life values.

            // get data from file
            LoadGameData data = SaveAndLoadData.loadGame();
            // To load the saved game file, the variable named data is called from the SaveAndLoadData.loadGame () method.
            // The value of the data is checked, if null freezes, it outputs true and the if command executes its function and loads the user's data from data.getCurrentLevel (),
            // that is, it pulls the .getCurrentLevel () method from the LoadGameData.java file.
            if(data != null){
                System.out.println("load level: " + data.getCurrentLevel());
                // set score from data loaded.
                CellLogic.setScore(data.getCurrentScore());

                // start level loaded with saved grid data.
                startLevel(data.getCurrentLevel(),data);
            }
        });

        // add all items to container
        vbox.getChildren().addAll(welcomeText,highScoreText,newGameBtn,resumeBtn,quitBtn,Youtube);

        return vbox;
    }


    public VBox renderLevel(int level,LoadGameData loadGameData) {

        // container
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setSpacing(8);

        // top pane
        // It reflects the level in the game to the user by pulling it from the string level + integer level. Since it is a topPane,
        // the upper part of the game has been adjusted to the top, insets and style design (style.css).
        HBox topPane = new HBox();
        topPane.setPadding(new Insets(0,30,0,30));
        topPane.setSpacing(230);
        Text levelText = new Text("Level: " + level);
        levelText.getStyleClass().add("red-text");

        Text scoreText = new Text(" " + CellLogic.getScore());
        Text highScoreText = new Text("HS: " + CellLogic.getHighScore());
        scoreText.getStyleClass().add("red-text");
        highScoreText.getStyleClass().add("red-text");

        // set global variable:  scoreText, highText , level in CellLogic for use later
        CellLogic.setScoreText(scoreText);
        CellLogic.setHighScoreText(highScoreText);
        CellLogic.setCurrentLevel(level);

        topPane.setMinWidth(1280);
        topPane.setMinHeight(20);
        topPane.getChildren().addAll(levelText,scoreText,highScoreText);

        // grid pane (center pane)
        Grid centerPane;
        // if resume button clicked -> loaded grid from save file.
        if(loadGameData != null) {
            System.out.println("grid successfully loaded from save file.");
            centerPane = loadGameData.getGrid();
        }
        else{
            // load grid from level file.
            centerPane = SaveAndLoadData.loadLevel(level);
        }

        // save game if you closed window.
        stage.setOnCloseRequest(event -> {
            SaveAndLoadData.saveGame(centerPane,level,CellLogic.getScore());
        });


        // bottom pane
        HBox bottomPane = new HBox();
        Button nextLevelBtn = new Button("Next Level >> ");
        Text bottomText = new Text("--- Text --");
        CellLogic.setLogText(bottomText);
        bottomText.getStyleClass().add("red-text");

        // the space between buttons is adjusted here.
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);
        nextLevelBtn.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);

        nextLevelBtn.setVisible(false);
        CellLogic.setNextLevelBtn(nextLevelBtn);

        nextLevelBtn.setOnMouseClicked(event -> {

            startLevel(level+1,null);
        });

        // add items (text) to bottom pane.
        bottomPane.getChildren().addAll(bottomText,spacer,nextLevelBtn);


        // add 3 pane to container.
        // add topPane, centerPane, bottomPane children to vbox.
        vbox.getChildren().addAll(topPane,centerPane,bottomPane);



        return vbox;
        // returns to vbox.
    }

    public static void main(String[] args) {
        launch(args);
    }
}
