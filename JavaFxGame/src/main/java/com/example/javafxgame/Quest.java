package com.example.javafxgame;

public class Quest {
    private String name;
    private String desc;
    private boolean complete = false;
    private int money;
    private Item[] itemReward;

    public void quest(String name) {
        this.name = name;
    }
    //setters --sam
    public void setName(String name) {
        this.name = name;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setComplete() {
        this.complete = true;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public void setItemReward(Item[] rewards) {
        this.itemReward = rewards;
    }
    //getters --sam
    public String getName() {
        return this.name;
    }
    public String getDesc() {
        return this.desc;
    }
    public int getMoney() {
        return this.money;
    }
    public Item[] getItemReward() {
        return this.itemReward;
    }
    public boolean checkCompletion() {
        return this.complete;
    }
    //--SAM--
}
