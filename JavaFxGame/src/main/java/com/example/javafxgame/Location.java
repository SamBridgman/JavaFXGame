package com.example.javafxgame;

public class Location {
    private String name;
    private Entity[] enemies;
    private String desc;
    private NPC[] npcs;
    private boolean safeZone;

    public void location(String name,String desc,boolean safeZone) {
        this.name = name;
        this.desc = desc;
        this.safeZone = safeZone;
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
