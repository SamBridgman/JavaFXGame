package com.example.javafxgame;

import java.util.Random;
//this does not include armor quite yet
public class Item {
    protected String name;
    protected String type; //if it is potion, do we want multile types or just health?
    protected int worth;
    protected String rarity;
    //--SAM--
    public Item(String type) {
        //since java does not have a default parameter, this will act as one
        if(type.equals("")) {
            this.type = generateType(); //generate random type from txt file if not type given
        }
        else {
            this.type = type;
        }
        this.name = Controller.grabItemName(this.type); //generates random name based on given type;
        this.rarity = generateRarity(); //genereates random rarity
    }
    //getters -- sam
    public String getName() {
        return this.name;
    }
    public String getRarity() {
        return this.rarity;
    }
    public String getType() {
        return this.type;
    }

    private String generateType() { //sam
        Random rand = new Random();
        int num = rand.nextInt((4 - 1) + 1) + 1; //random num 1-3
        if(num == 1) {
            return "wand";
        }
        else if(num == 2) {
            return "hand";
        }
        else if (num == 3) {
            return "bow";
        }
        else if (num == 4) {
            return "potion";
        }
        return "null";
    }

    private String generateRarity() { //sam

        if(this.type != "potion") {
            Random rand = new Random();
            int randomNum = rand.nextInt((20 - 1) + 1) + 1; //random num 1-20
            switch(randomNum){ //rarity odds
                case 1:
                    return "legendary";
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                    return "common";
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                    return "rare";

            }
        }
        return "common"; //potions default to common

    }
    //--SAM--
}
