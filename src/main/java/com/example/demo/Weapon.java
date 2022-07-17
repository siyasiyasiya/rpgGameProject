package com.example.demo;

public class Weapon {
    private String name;
    private int attack;
    private int agility;
    private int defense;
    private int luck;
    private String image;
    private int level;
    private int cost;

    public Weapon(String n, String i, int l, int at, int ag, int d, int lk, int c){
        name = n;
        image = i;
        level = l;
        attack = at;
        agility = ag;
        defense = d;
        luck = lk;
        cost = c;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getAgility() {
        return agility;
    }

    public int getDefense() {
        return defense;
    }

    public int getLuck() {
        return luck;
    }

    public String getImage() {
        return image;
    }

    public int getCost() {
        return cost;
    }

    public int getLevel() {
        return level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
