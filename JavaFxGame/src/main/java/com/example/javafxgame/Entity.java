package com.example.javafxgame;

public class Entity {
    /*-----Sam------*/
    protected double health;
    protected int attack;
    protected int defense;
    protected int mana;
    protected int dex;
    protected int stamina;
    protected String name;
    protected int level;
    protected float xp;
    protected int money;
    protected int attributePoints;
    protected Item[] inventory = {};
    /*-----Sam------*/

    public Entity(String name) {
        //all these are defaults
        //user will be able to add attributes in character creation
        this.attack = 2;
        this.health = 1.0;
        this.defense = 2;
        this.mana = 2;
        this.dex = 2;
        this.stamina = 2;
        this.name = name;
        this.level = 1;
        this.xp = 0;
        this.money = 0;
        this.attributePoints = 20;
    }
    //setters -- Sam
    public void setName(String name) {
        this.name = name;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setHealth(double health) {
        this.health = health;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }
    public void setDex(int dex) {
        this.dex = dex;
    }
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setXP(float xp) { //--sam
        this.xp = xp;
        //if player gains enough XP to levelUp multiple times, this will allow many level ups;
        //currently this opens multiple windows if a player levels up multiple times at once
        //we should change it to where it only has one window open
        while(xp >= Math.round((4.0*(getLevel() * getLevel() * getLevel())) / 5)) {
            this.xp = xp - Math.round((4.0*(getLevel() * getLevel() * getLevel())) / 5);
            this.levelUp();
        } //will fix XP offset if it goes below zero
        if(this.xp < 0) {
            this.xp = 0;
        }
    }

    public void setAttributePoints(int num) {
        this.attributePoints = num;
    }
    //getters --Sam
    public String getName() {
        return this.name;
    }
    public int getAttack() {
        return this.attack;
    }
    public double getHealth() {
        return this.health;
    }
    public int getDefense() {
        return this.defense;
    }
    public int getMana() {
        return this.mana;
    }
    public int getDex() {
        return this.dex;
    }
    public int getStamina() {
        return this.stamina;
    }
    public int getMoney() {
        return this.money;
    }
    public int getLevel() {
        return this.level;
    }
    public int getAttributePoints() {
        return this.attributePoints;
    }
    public float getXP() {
        return this.xp;
    }


    public void levelUp() { //--sam
        level += 1;
        attributePoints = getAttributePoints() + 10;
        Game.levelUpScreen();//displays level up popup
    }


}
