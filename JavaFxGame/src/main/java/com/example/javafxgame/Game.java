package com.example.javafxgame;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class Game extends App{
    private static Scene rootScene;
    private static ProgressBar healthBar;
    private static String difficulty = "normal";
    private static Entity player;
    //THIS ALL BELOW IS A BUNCH OF TESTING ON HOW TO RETRIEVE AND SEND DATA FROM GUI TO OUR CLASSES
    public Game(Entity player) { //-sam
        this.player = player;
        runGame();
        rootScene.getStylesheets().add(getClass().getResource("gameStyles.css").toExternalForm());
    }
    public void runGame() { //-sam



    }
    public Scene getRootScene() { //sam
        return rootScene;
    }
    //LEVEL UP SCREEN

    public static void levelUpScreen() {
        GridPane grid = new GridPane();
        Stage stage = new Stage();
        stage.setTitle("Level Up");
        stage.setScene(new Scene(grid,300, 200));
        grid.setPadding(new Insets(0,0,0,10));

        Label entryLabel = new Label("LEVEL UP");
        entryLabel.setStyle("-fx-font: 20 Verdana");
        entryLabel.setTextFill(Color.BLACK);
        entryLabel.setPadding(new Insets(10, 10, 0, 0));
        Text level = new Text(player.getName() + ": " + player.getLevel() + " level");
        grid.add(level,0,1);
        grid.add(entryLabel,0,0);
        stage.show();
    }

    //testing item generation
    public static void generateItem() { //sam
        Item item = new Item("");
        GridPane grid = new GridPane();
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(grid,300, 200));
        grid.setPadding(new Insets(0,0,0,10));

        Text level = new Text("Name: "+item.getName() + " Rarity: " + item.getRarity() + " Type: " + item.getType());
        grid.add(level,0,1);
        stage.show();
    }

    public void setDifficulty(String diff) {
        this.difficulty = diff;
    }
    public String getDifficulty() {
        return this.difficulty;
    }


}
