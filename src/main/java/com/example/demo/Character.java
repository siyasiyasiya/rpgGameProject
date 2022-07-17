package com.example.demo;

import java.util.ArrayList;

public class Character {
    private String name;
    private int totalHP;
    private int hp;
    private int level;
    private int money;
    private int defense;
    private int attack;
    private int agility;
    private int luck;
    private String card;
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private Weapon chosenWeapon;

    public Character(String n, int h, int r, int at, int ag, int l, String c, int lev){
        name = n;
        hp = h;
        totalHP = h;
        defense = r;
        attack = at;
        agility = ag;
        luck = l;
        card = c;
        level = lev;
        money = 50;
    }

//                if(opponent.equals("Spades")){
//        enemyCharacter.setAttack(randomNumber((where+1) * 50 - 20, (where+1) * 50 + 20));
//        enemyCharacter.setDefense(randomNumber(where * 50 - 20, where * 50 + 20));
//        enemyCharacter.setAgility(randomNumber(where * 50 - 20, where * 50 + 20));
//        enemyCharacter.setLuck(randomNumber(10 + (where - 1)* 5 - 2, 10 + (where - 1) * 5 + 2));
//    } else if(opponent.equals("Hearts")){
//        enemyCharacter.setDefense(randomNumber((where+1) * 50 - 20, (where+1) * 50 + 20));
//        enemyCharacter.setAttack(randomNumber(where * 50 - 20, where * 50 + 20));
//        enemyCharacter.setAgility(randomNumber(where * 50 - 20, where * 50 + 20));
//        enemyCharacter.setLuck(randomNumber(10 + (where - 1)* 5 - 2, 10 + (where - 1) * 5 + 2));
//    } else if(opponent.equals("Clubs")){
//        enemyCharacter.setAgility(randomNumber((where+1) * 50 - 20, (where+1) * 50 + 20));
//        enemyCharacter.setDefense(randomNumber(where * 50 - 20, where * 50 + 20));
//        enemyCharacter.setAttack(randomNumber(where * 50 - 20, where * 50 + 20));
//        enemyCharacter.setLuck(randomNumber(10 + (where - 1)* 5 - 2, 10 + (where - 1) * 5 + 2));
//    } else if (opponent.equals("Diamonds")){
//        enemyCharacter.setAttack(randomNumber(where * 50 - 20, where * 50 + 20));
//        enemyCharacter.setDefense(randomNumber(where * 50 - 20, where * 50 + 20));
//        enemyCharacter.setAgility(randomNumber(where * 50 - 20, where * 50 + 20));
//        enemyCharacter.setLuck(randomNumber(10 + where* 5 - 2, 10 + where * 5 + 2));
//    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getTotalHP() {
        return totalHP;
    }

    public int getLevel() {
        return level;
    }

    public int getMoney() {
        return money;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public int getAgility() {
        return agility;
    }

    public int getLuck() {
        return luck;
    }

    public String getCard() {
        return card;
    }

    public ArrayList<Weapon> getWeapons(){
        return weapons;
    }

    public Weapon getWeapon(int i){
        return weapons.get(i);
    }

    public Weapon getChosenWeapon() {
        return chosenWeapon;
    }

    //    public String getWeaponName(int i){
//        return weapons.get(i).getName();
//    }

//    public int getWeaponAttack(int i){
//        return weapons.get(i).getAttack();
//    }
//
//    public int getWeaponAgility(int i){
//        return weapons.get(i).getAgility();
//    }
//
//    public int getWeaponDefense(int i) {
//        return weapons.get(i).getDefense();
//    }
//
//    public int getWeaponLuck(int i){
//        return weapons.get(i).getLuck();
//    }
//
//    public String getWeaponImage(int i){
//        return weapons.get(i).getImage();
//    }

//    public int getWeaponLevel(int i){
//        return weapons.get(i).getLevel();
//    }

    public void addWeapon(Weapon w) {
        this.weapons.add(w);
    }

    public void setWeapons(ArrayList<Weapon> w) {
        this.weapons = w;
    }

    public void setWeapon(Weapon w, int i){
        this.weapons.set(i, w);
    }

    public void setChosenWeapon(Weapon chosenWeapon) {
        this.chosenWeapon = chosenWeapon;
    }

    //    public void setWeaponImage(int i, String s){
//        this.weapons.get(i).setImage(s);
//    }


    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setTotalHP(int totalHP) {
        this.totalHP = totalHP;
        this.hp = totalHP;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public void setCard(String card) {
        this.card = card;
    }

}
