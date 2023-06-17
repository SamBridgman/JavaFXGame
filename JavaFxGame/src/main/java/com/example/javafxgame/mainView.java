package com.example.javafxgame;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class mainView extends VBox {
    private Canvas canvas;
    private Entity player;
    public ProgressBar healthBar;

    public mainView(Entity player) {
        this.healthBar = new ProgressBar(Double.valueOf(player.getHealth()) / 10);
        this.player = player;
        this.canvas = new Canvas(400,400);
        healthBar.setId("healthBar");
        //healthBar.getStylesheets().add(getClass().getResource("gameStyles.css").toExternalForm());
        this.getChildren().addAll(healthBar,this.canvas);
        this.getStylesheets().add(getClass().getResource("gameStyles.css").toExternalForm());
    }
    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setFill(Color.ALICEBLUE);
        g.fillRect(0,0,400,400);
    }
    public void update() {
        healthBar.setProgress(Double.valueOf(player.getHealth()) / 10);
    }
}
