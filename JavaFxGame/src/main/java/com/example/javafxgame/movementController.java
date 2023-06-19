package com.example.javafxgame;


public class movementController {
    public static void moveUp(Entity player) {
        player.setY(player.getY() - 10);
    }
    public static void moveRight(Entity player) {
        player.setImage("JavaFxGame/src/main/resources/com/example/javafxgame/char_walk_right.gif");
        player.setX(player.getX() + 10);

    }
    public static void moveDown(Entity player) {
        player.setY(player.getY() + 10);

    }
    public static void moveLeft(Entity player) {
        player.setImage("JavaFxGame/src/main/resources/com/example/javafxgame/char_walk_left.gif");
        player.setX(player.getX() - 10);

    }

}
