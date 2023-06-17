package com.example.javafxgame;

import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class mainView extends VBox {
    private Canvas canvas;
    private Entity player;
    private Label money;
    private Button addMoney;
    public ProgressBar healthBar;

    public mainView(Entity player) {
        this.healthBar = new ProgressBar(Double.valueOf(player.getHealth()) / 10);
        this.player = player;
        this.canvas = new Canvas(400,400);
        money = new Label(Integer.toString(player.getMoney()));
        healthBar.setId("healthBar");
        addMoney = new Button("add 10");
        addMoney.setOnAction(e-> {
            player.setMoney(player.getMoney() + 10);
            System.out.println("moneyyy");
        });
        //healthBar.getStylesheets().add(getClass().getResource("gameStyles.css").toExternalForm());
        this.getChildren().addAll(healthBar,money,addMoney,this.canvas);
        this.getStylesheets().add(getClass().getResource("gameStyles.css").toExternalForm());
    }
    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setFill(Color.ALICEBLUE);
        g.fillRect(0,0,400,400);
    }
    public void update() {
        Platform.runLater(() -> { //for whatever reason, thread crashes if platform run later not here
            healthBar.setProgress(Double.valueOf(player.getHealth()) / 10);
            money.setText(Integer.toString(player.getMoney()));

        });

    }
}
