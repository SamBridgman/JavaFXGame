package com.example.javafxgame;

import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class mainView extends VBox {
    private ImageView character;
    protected Canvas canvas;
    private Entity player;
    private Label money;
    private Button addMoney;
    public ProgressBar healthBar;

    public mainView(Entity player) {
        this.healthBar = new ProgressBar(Double.valueOf(player.getHealth()) / 10);
        this.player = player;
        this.canvas = new Canvas();
        StackPane stackPane = new StackPane();
        stackPane.prefHeightProperty().bind(this.heightProperty());



        money = new Label(Integer.toString(player.getMoney()));
        healthBar.setId("healthBar");
        addMoney = new Button("add 10");
        addMoney.setOnAction(e-> {
            player.setMoney(player.getMoney() + 10);
            System.out.println("moneyyy");
        });

        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case W:
                        System.out.println("Player: " + player.getY());
                        System.out.println("view: " + stackPane.getHeight());
                        if(player.getY() < ((stackPane.getHeight() / 2) - (stackPane.getHeight())) + 30) {
                            System.out.println("Out of bounds");
                            Location location = new Location();
                            System.out.println("setting location");
                            draw("UP");
                            Game.curLocation = "Up";
                            Game.changeLocation = true;
                        }
                        else {
                            movementController.moveUp(player);
                            player.getImage().setTranslateY(player.getY());
                        }

                        break;
                    case D:
                        movementController.moveRight(player);
                        player.getImage().setTranslateX(player.getX());
                        break;
                    case A:
                        movementController.moveLeft(player);
                        player.getImage().setTranslateX(player.getX());
                        break;
                    case S:
                        System.out.println("Player: " + player.getY());
                        System.out.println("view: " + stackPane.getHeight());
                        if(player.getY() > ((stackPane.getHeight())  / 2) - 60) {

                            System.out.println("Out of bounds");
                        }
                        else {
                            movementController.moveDown(player);
                            player.getImage().setTranslateY(player.getY());
                        }


                        break;

                }
            }
        });

        stackPane.getChildren().addAll(this.canvas,player.getImage(),money,addMoney,healthBar);
        this.getChildren().addAll(stackPane);
        this.getStylesheets().add(getClass().getResource("gameStyles.css").toExternalForm());
    }

    public void draw(String location) {
        if(location.equals("Start")) {
            GraphicsContext g = this.canvas.getGraphicsContext2D();
            g.setFill(Color.GREEN);
            g.fillRect(0,0,100,100);
        }
        else if(location.equals("Up")) {
            GraphicsContext g = this.canvas.getGraphicsContext2D();
            g.setFill(Color.BLACK);
            g.fillRect(0,0,100,100);
        }
    }
    public void update() {
        Platform.runLater(() -> { //for whatever reason, thread crashes if platform run later not here
            healthBar.setProgress(Double.valueOf(player.getHealth()) / 10);
            money.setText(Integer.toString(player.getMoney()));

        });

    }
}
