package com.example.javafxgame;

public class NPC {
    private String name;
    private Quest quest;

    public void npc(Quest quest,String name) {
        this.name = name;
        this.quest = quest;
    }
    //getters
    public String getName() {
        return this.name;
    }
    public Quest getQuest() {
        return this.quest;
    }
    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setQuest(Quest quest) {
        this.quest = quest;
    }
//--SAM--

}
