package com.example.javafxgame;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Controller {
    //---SAM---
    //grabs a random string name from itemWeaponsNames.txt and returns it
    //will include armor in this eventually
    public static String grabItemName(String type) {  //-sam
        String file;
        if(type == "hand") {
            file = "itemWeaponNames.txt";
        }
        if(type == "wand") {
            file="itemWandNames.txt";
        }
        if (type == "potion") {
            return "health potion";
        }
        else {
            file="itemBowNames.txt";
        }
        String name= "";
        Random rand = new Random();
        int numberToStop = rand.nextInt((20 - 1) + 1) + 1;
        try (BufferedReader in = new BufferedReader(new FileReader(file)))
        {
            //scanner with delimiter
            Scanner read = new Scanner(in);
            read.useDelimiter(",");
            int count = 0;
            while(read.hasNext() && count != numberToStop)
            {

                //grabs value from txt
                name = read.next();
                count++;
            }
            read.close();

        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        return name;
    }
    //---SAM---
    //key movement stuff
    public void moveUp() {
        System.out.println("Moving up");
    }
    public void moveDown() {
        System.out.println("Moving down");
    }
    public void moveRight() {
        System.out.println("Moving Right");
    }
    public void moveLeft() {
        System.out.println("Moving left");
    }
}
