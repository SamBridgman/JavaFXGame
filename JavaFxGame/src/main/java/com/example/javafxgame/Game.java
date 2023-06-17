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
import javafx.stage.WindowEvent;

public class Game extends App implements Runnable {
    private static Scene rootScene;
    private static ProgressBar healthBar;
    private static String difficulty = "normal";
    private static Entity player;
    private static mainView mainView;
    private Thread thread;
    private boolean running = false;
    public Game(Entity player) { //-sam
        this.player = player;
        Stage stage = new Stage();
        mainView = new mainView(this.player);
        Scene scene = new Scene(mainView,820,640);
        stage.setScene(scene);
        stage.show();
        mainView.draw();
        startGame();
        System.out.println("Game is running: " + running);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                running = false;
                System.exit(1);
                //stopGame();
            }
        });
        //rootScene.getStylesheets().add(getClass().getResource("gameStyles.css").toExternalForm());
    }


    public void run() { //-sam

        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        // boolean condition keeping the game running
        while (running) {


            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1) {
                //methods that needs to be updated are put here
                //testScreen();
                if(player.getHealth() > 1 ) {
                    mainView.update();
                    player.update();
                }
                else {
                    player.setHealth(10);
                }


                updates++;
                delta--;
            }
            //FPS counter
            frames++;
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + "Ticks, Fps" + frames);
                updates = 0;
                frames = 0;
            }
        }

    }
    public synchronized void startGame() {
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
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
