package com.example.javafxgame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Location {
    private String name;
    private Entity[] enemies;
    private String desc;
    private NPC[] npcs;
    int mapTileNum[][];
    private boolean safeZone;

    public void location(String name,String desc,boolean safeZone) {
        this.name = name;
        this.desc = desc;
        this.safeZone = safeZone;
    }
    public void loadMap(String mapPath, int map) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(mapPath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int column = 0;
            int row = 0;

            while (column < mainView.canvas.getHeight() && row < mainView.canvas.getWidth()) {
                String line = bufferedReader.readLine();

                while (column < mainView.canvas.getHeight()) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[column]);
                    mapTileNum[column][row] = num;
                    column++;
                }
                if (column == mainView.canvas.getHeight()) {
                    column = 0;
                    row++;
                }
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //getters
    public String getName() {
        return this.name;
    }
    public String getDesc() {
        return this.desc;
    }
    public Entity[] getEnemies() {
        return this.enemies;
    }
    public NPC[] getNPCs() {
        return this.npcs;
    }
    public boolean getSafezone() {
        return this.safeZone;
    }
    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setEnemies(Entity[] enemies) {
        this.enemies = enemies;
    }
    public void setNPCs(NPC[] NPCs) {
        this.npcs = NPCs;
    }
}
