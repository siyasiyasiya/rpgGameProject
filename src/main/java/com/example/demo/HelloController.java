package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

//I deserve an A because I gave the incorporated concepts that we have used in past projects such as a timer and automatically updating based on user textfield. i also
//I also simplified my code by calling the same function sometimes but chnaging the output thorugh parameters. I also called
//the same function in scenebuilder through buttons and then used action events in order to differentiate.


public class HelloController {
    @FXML
    private Label introText, cardInfo, infoText, displayImprove, costText, errorText, rewardText, infoText2, oppError, playerStats, enemyStats, nameMC, nameEnemy, turnText, rewardsList, weaponStats, weaponLabel, weaponError, weaponStats2;
    @FXML
    private TextField nameText, pointsText;
    @FXML
    private ImageView city, second, mainAvatar, battleIcon, mainAvatar2, battlePlayer, battleEnemy, mainRewards, backHome, weaponIcon, selectedWeapon;
    @FXML
    private AnchorPane startingPane, storyPane1, letterPane, chooseCardPane, chooseAvatarPane, characterPane, sidePane, chooseOpponentPane, battlePane, rewardPane, weaponPane, weaponInfo, sideWeapon, gameOver;
    @FXML
    private Button chooseButton, collectRewards, buyButton;
    @FXML
    private ListView statsList, weaponsList;
    @FXML
    private ProgressBar mainHP, enemyHP, xp;


    int sceneCount = 0;
    String[] types = {"Axe", "Crossbow", "Hammer", "Mace", "Shield", "Spear", "Sword"};
    ArrayList<Weapon> allWeapons = new ArrayList<>();
    ArrayList<String> weaponNames = new ArrayList<>();
    ArrayList<Character> allEnemies = new ArrayList<>();
    //Axe: +15 Luck & -5 Agility
    //Crossbow: +15 Attack & +20 Agility
    //Hammer: +10 Attack & +5 Luck & -5 Agility
    //Mace: +10 Attack & -5 Defense & +10 Luck
    //Shield: +25 Defense & +5 Luck
    //Spear: +5 Attack & +10 Agility
    //Sword: +20 Attack & +15 Agility
    String mainImage = "src/main/resources/Avatars/pinkGirl.png";
    Character mainCharacter;
    Character enemyCharacter;
    String enemyImage;
    Weapon showWeapon;
    int classChosen = 1;
    int visa = 3;
    int where = 0;
    boolean playerTurn = true;
    boolean playerWon = false;
    String opponent;
    long visaTime;

    String[] images = {"aquaGirl", "blackBoy", "blondeBoy", "blondeBoy2", "blueBoy", "brownBoy", "cyanBoy", "cyanGirl", "grayBoy",
                       "greenBoy", "greenGirl1", "greenGirl2", "hairGirl", "magentaGirl", "purpleBoy", "pinkGirl", "purpleGirl",
                       "redBoy", "redGirl", "silverBoy", "violetGirl", "whiteBoy", "whiteGirl"};

    public HelloController(){
        //runs for every element in the type of weapons array
        for (int x = 0; x < 7; x++){
            //runs for ever possible weapon level
            for(int i = 1; i < 5; i++){
                String image = "src/main/resources/weapons/" + types[x].toLowerCase(Locale.ROOT) + "Level" + i + ".png";
                switch (x){
                    case 0: allWeapons.add(new Weapon(types[x], image, i, 0, -5*i, 0, 15*i, 50*i));
                            break;
                    case 1: allWeapons.add(new Weapon(types[x], image, i, 15*i, 20*i, 0, 0, 70*i));
                            break;
                    case 2: allWeapons.add(new Weapon(types[x], image, i, 10*i, -5*i, 0, 5*i, 20*i));
                            break;
                    case 3: allWeapons.add(new Weapon(types[x], image, i, 10*i, 0, -5*i, 10*i, 40*i));
                            break;
                    case 4: allWeapons.add(new Weapon(types[x], image, i, 0, 0, 25*i, 5*i, 60*i));
                            break;
                    case 5: allWeapons.add(new Weapon(types[x], image, i, 5*i, 10*i, 0, 0, 30*i));
                            break;
                    case 6: allWeapons.add(new Weapon(types[x], image, i, 20*i, 15*i, 0, 0, 70*i));
                            break;
                }
                weaponNames.add(types[x]);
            }
        }

//        for (int i = 0; i < allWeapons.size(); i++){
//            System.out.println(allWeapons.get(i).getName() + " Level: " + allWeapons.get(i).getLevel() + " Cost: $" + allWeapons.get(i).getCost() + " Attack: " + allWeapons.get(i).getAttack() + " Agility: " + allWeapons.get(i).getAgility() + " Defense: " + allWeapons.get(i).getDefense() + " Luck: " + allWeapons.get(i).getLuck());
//        }

        //runs for every possible level
        for (int lvl = 1; lvl < 13; lvl++){
            //makes 10 enemies of each card level
            for(int c = 0; c < 10; c++){
                //Spades
                allEnemies.add(new Character("Enemy", randomNumber(170 + (lvl - 1)*50, 230 + (lvl - 1)*50), randomNumber(lvl * 50 - 20, lvl * 50 + 20), randomNumber((lvl+1) * 50 - 20, (lvl+1) * 50 + 20), randomNumber(lvl * 50 - 20, lvl * 50 + 20), randomNumber(10 + (lvl - 1)* 5 - 2, 10 + (lvl - 1) * 5 + 2), "Spades", lvl));
                //Hearts
                allEnemies.add(new Character("Enemy", randomNumber(170 + (lvl - 1)*50, 230 + (lvl - 1)*50), randomNumber((lvl+1) * 50 - 20, (lvl+1) * 50 + 20), randomNumber(lvl * 50 - 20, lvl * 50 + 20), randomNumber(lvl * 50 - 20, lvl * 50 + 20), randomNumber(10 + (lvl - 1)* 5 - 2, 10 + (lvl - 1) * 5 + 2), "Hearts", lvl));
                //Clubs
                allEnemies.add(new Character("Enemy", randomNumber(170 + (lvl - 1)*50, 230 + (lvl - 1)*50), randomNumber(lvl * 50 - 20, lvl * 50 + 20), randomNumber(lvl * 50 - 20, lvl * 50 + 20), randomNumber((lvl+1) * 50 - 20, (lvl+1) * 50 + 20), randomNumber(10 + (lvl - 1)* 5 - 2, 10 + (lvl - 1) * 5 + 2), "Clubs", lvl));
                //Diamonds
                allEnemies.add(new Character("Enemy", randomNumber(170 + (lvl - 1)*50, 230 + (lvl - 1)*50), randomNumber(lvl * 50 - 20, lvl * 50 + 20), randomNumber(lvl * 50 - 20, lvl * 50 + 20), randomNumber(lvl * 50 - 20, lvl * 50 + 20), randomNumber(10 + lvl* 5 - 2, 10 + lvl * 5 + 2), "Diamonds", lvl));
            }
        }

        //Assigns all the weapons in certain level to enemies in certain levels.
        for (Character enemy: allEnemies){
            for (Weapon w: allWeapons){
                if(enemy.getLevel() >=0 && enemy.getLevel() <= 3 && w.getLevel() == 1){
                    enemy.addWeapon(w);
                } else if (enemy.getLevel() >=4 && enemy.getLevel() <= 6 && w.getLevel() == 2){
                    enemy.addWeapon(w);
                } else if (enemy.getLevel() >=7 && enemy.getLevel() <= 9 && w.getLevel() == 3){
                    enemy.addWeapon(w);
                } else if (enemy.getLevel() >=10 && enemy.getLevel() <= 12 && w.getLevel() == 4){
                    enemy.addWeapon(w);
                }
            }


//            System.out.print("Level: " + enemy.getLevel() + "Weapon Level: ");
//            for (int i = 0; i < enemy.getWeapons().size(); i++){
//                System.out.print(enemy.getWeapon(i).getLevel() + " ");
//            }
//            System.out.println();

        }

        mainCharacter = new Character("Siya", 200, 50, 50, 50, 10, "Diamond", 1);
        enemyCharacter = new Character("Enemy", 200, 50, 50, 50, 10, "Diamond", 1);

        visaTime = System.nanoTime();
    }
    @FXML
    public void startGame() {
        startingPane.setVisible(false);
        characterPane.setVisible(false);
        storyPane1.setVisible(true);
        visa = 3;
        sceneCount = 0;
        city.setVisible(true);
        second.setVisible(false);
        gameOver.setVisible(false);
        mainCharacter = new Character("Siya", 200, 50, 50, 50, 10, "Diamond", 1);
    }

    @FXML
    public void loadGame() {
        startingPane.setVisible(false);
        handleLoad();
        setCharacterPane();
    }

    @FXML
    public void sceneOne() {
        if (sceneCount == 0){
            introText.setText("After a whole day of pointlessly wandering around it seems that the whole city has shut down. No electricity. No people. No life. Suddenly in the pitch dark there seems to be glowing yellow light coming from one of the buildings. Thrilled to see some form of light you make your way over to the building.");
            try {
                FileInputStream input = new FileInputStream("src/main/resources/buildingLit.png");
                city.setImage(new Image(input));
            } catch (Exception e) {
                e.printStackTrace();
            }
            sceneCount ++;
        } else if (sceneCount == 1){
            city.setVisible(false);
            second.setVisible(true);
            introText.setText("As you enter the building and approach the main desk you greeted with a small envelope. Filled with curiosity you open it and find a key card for Room 1.");
            sceneCount ++;
        } else if (sceneCount == 2){
            introText.setText("Opening the door to Room 1, it seems to be an small empty room. In the middle, there is a wooden table with all of the 4 King of Cards lying next to them lays a letter.");
            try {
                FileInputStream input3 = new FileInputStream("src/main/resources/desk.png");
                second.setImage(new Image(input3));
            } catch (Exception e) {
                e.printStackTrace();
            }
            sceneCount ++;
        } else if (sceneCount == 3){
            storyPane1.setVisible(false);
            letterPane.setVisible(true);
            sceneCount ++;
        }
    }

    @FXML
    public void createCharacter(){
//        mainCharacter = new Character(nameText.getText(), 200, 50, 50, 50, 10, "Diamond", 1);
        letterPane.setVisible(false);
        chooseCardPane.setVisible(true);
    }

    @FXML
    public void classSpades(){
        chooseButton.setVisible(true);
        cardInfo.setText("Class: Spade \nStats: +50 Attack \nWeapon: Sword");
        classChosen = 1;
    }

    @FXML
    public void classHearts(){
        chooseButton.setVisible(true);
        cardInfo.setText("Class: Heart \nStats: +50 Defense \nWeapon: Shield");
        classChosen = 2;
    }

    @FXML
    public void classClubs(){
        chooseButton.setVisible(true);
        cardInfo.setText("Class: Club \nStats: +50 Agility \nWeapon: Spear");
        classChosen = 3;
    }

    @FXML
    public void classDiamonds(){
        chooseButton.setVisible(true);
        cardInfo.setText("Class: Diamond \nStats: +10 Luck \nWeapon: Crossbow");
        classChosen = 4;
    }

    @FXML
    public void setClassChosen(){
        if(classChosen == 1){
            mainCharacter.setCard("Spades");
            mainCharacter.setAttack(mainCharacter.getAttack() + 50);
            mainCharacter.addWeapon(allWeapons.get(24));
        } else if (classChosen == 2){
            mainCharacter.setCard("Hearts");
            mainCharacter.setDefense(mainCharacter.getDefense() + 50);
            mainCharacter.addWeapon(allWeapons.get(16));
        } else if (classChosen == 3){
            mainCharacter.setCard("Clubs");
            mainCharacter.setAgility(mainCharacter.getAgility() + 50);
            mainCharacter.addWeapon(allWeapons.get(20));
        } else if (classChosen == 4){
            mainCharacter.setCard("Diamonds");
            mainCharacter.setLuck(mainCharacter.getLuck() + 10);
            mainCharacter.addWeapon(allWeapons.get(4));
        }
        chooseCardPane.setVisible(false);
        chooseAvatarPane.setVisible(true);
    }

    @FXML
    public void avatarClicked(MouseEvent t){
        mainImage = "src/main/resources/Avatars/" + t.getPickResult().getIntersectedNode().getId() + ".png";
        System.out.println(mainImage);
//        try {
//            String cssDefault = "-fx-border-color: blue;\n" + "-fx-border-insets: 5;\n" + "-fx-border-width: 3;\n" + "-fx-border-style: dashed;\n";
//            brownBoy.setStyle(cssDefault);
//        } catch(Exception e) {
//            System.out.println(errorText);
//        }
//        t.getPickResult().getIntersectedNode().getId()

    }

    @FXML
    public void setCharacterPane(){
        battlePane.setVisible(false);
        chooseAvatarPane.setVisible(false);
        characterPane.setVisible(true);
        visaTimer();

        try {
            FileInputStream input = new FileInputStream(mainImage);
            mainAvatar.setImage(new Image(input));
            System.out.println(mainAvatar);
        } catch (Exception e) {
            e.printStackTrace();
        }

        updateList(statsList);

        infoText.setText("Level: " + mainCharacter.getLevel() + "\nMoney: $" + mainCharacter.getMoney()
                         + "\nVisa: " + Integer.toString(visa) + " Days");

        checkLevel();
    }

    @FXML
    public void checkLevel(){
        int total = mainCharacter.getTotalHP() + mainCharacter.getAgility() + mainCharacter.getAttack() + mainCharacter.getDefense();
        if (total >= 200 * (mainCharacter.getLevel()+1) + 100){
            mainCharacter.setLevel(mainCharacter.getLevel() + 1);
        }
        //finding the number of xp needed to reach the next level using the difference of the goal for next level
        //and current xp over 200 becuase 200 is the interval between each cutting mark
        xp.setProgress(1 - ((200 * (mainCharacter.getLevel()+1) + 100) - total)/200.0);
    }

    @FXML
    public void updateList(ListView list){
        list.getItems().clear();
        list.getItems().add(mainCharacter.getName().toUpperCase(Locale.ROOT));
        list.getItems().add("Class: " + mainCharacter.getCard());
        list.getItems().add("HP: " + mainCharacter.getTotalHP());
        list.getItems().add("Attack: " + mainCharacter.getAttack());
        list.getItems().add("Defense: " + mainCharacter.getDefense());
        list.getItems().add("Agility: " + mainCharacter.getAgility());
        list.getItems().add("Luck: " + mainCharacter.getLuck());
        list.getItems().add("WEAPON");

        for(int i = 0; i <mainCharacter.getWeapons().size(); i++){
            list.getItems().add("Level " + mainCharacter.getWeapon(i).getLevel() + " " + mainCharacter.getWeapon(i).getName());
        }
    }

    @FXML
    public void changeStats(MouseEvent x) throws FileNotFoundException {
        where = statsList.getSelectionModel().getSelectedIndex();
        sideWeapon.setVisible(false);
        sidePane.setVisible(false);

         if (where == 2) {
             sidePane.setVisible(true);
             displayImprove.setText("Improve HP Stat");
        } else if (where == 3) {
             sidePane.setVisible(true);
             displayImprove.setText("Improve Attack Stat");
        } else if (where == 4) {
             sidePane.setVisible(true);
             displayImprove.setText("Improve Defense Stat");
        } else if (where == 5) {
             sidePane.setVisible(true);
             displayImprove.setText("Improve Agility Stat");
         } else if (where == 6) {
             sidePane.setVisible(true);
             displayImprove.setText("Improve Luck Stat");
         } else if (where > 7) {
             sideWeapon.setVisible(true);
             showWeapon = mainCharacter.getWeapons().get(where - 8);
             FileInputStream input = new FileInputStream(showWeapon.getImage());
             selectedWeapon.setImage(new Image(input));
             weaponStats2.setText("Attack: " + showWeapon.getAttack() + "\nAgility: " + showWeapon.getAgility() + "\nDefense: " + showWeapon.getDefense() + "\nLuck: " + showWeapon.getLuck());
         }

         displayImprove.setText(displayImprove.getText() + "\nEach Stat Point Costs 5 Dollar");
    }

    @FXML
    public void updateCost(){
        try{
            int points = 5 * Integer.parseInt(pointsText.getText());
            costText.setText("Cost: $" + String.valueOf(points));
        } catch(NumberFormatException e){
            costText.setText("Cost: Enter an Integer");
        }
    }

    @FXML
    public void upgradeStats(){
        int points = 5 * Integer.parseInt(pointsText.getText());
        if (mainCharacter.getMoney() >= points){
            mainCharacter.setMoney(mainCharacter.getMoney() - points);
            switch (where) {
                case 2: mainCharacter.setTotalHP(mainCharacter.getTotalHP() + points/5);
                        break;
                case 3: mainCharacter.setAttack(mainCharacter.getAttack() + points/5);
                        break;
                case 4: mainCharacter.setDefense(mainCharacter.getDefense() + points/5);
                        break;
                case 5: mainCharacter.setAgility(mainCharacter.getAgility() + points/5);
                        break;
                case 6: mainCharacter.setLuck(mainCharacter.getLuck() + points/5);
                        break;
            }
            setCharacterPane();
        } else {
            errorText.setText("Not Enough Money to Purchase Stat Points");
        }
    }

    @FXML
    public void equipWeapon(){
        mainCharacter.setChosenWeapon(showWeapon);
    }

    @FXML
    public void pickOpponent(){
        battlePane.setVisible(false);
        characterPane.setVisible(false);
        chooseOpponentPane.setVisible(true);
        try {
            FileInputStream input = new FileInputStream(mainImage);
            mainAvatar2.setImage(new Image(input));
        } catch (Exception e) {
            e.printStackTrace();
        }
        infoText2.setText("Level: " + mainCharacter.getLevel() + "\nClass: " + mainCharacter.getCard()
                + "\nVisa: " + Integer.toString(visa) + " Days");
    }

    @FXML
    public void spadesOpp(){
        rewardText.setText("Attack: +" + mainCharacter.getLevel()*10 + "\nVisa: +" + mainCharacter.getLevel() + " Days" + "\nMoney: +$" + mainCharacter.getLevel()*10);
        opponent = "Spades";
    }

    @FXML
    public void diamondOpp(){
        rewardText.setText("Luck: +" + mainCharacter.getLevel()*10 + "\nVisa: +" + mainCharacter.getLevel() + " Days" + "\nMoney: +$" + mainCharacter.getLevel()*10);
        opponent = "Diamonds";
    }

    @FXML
    public void heartOpp(){
        rewardText.setText("Defense: +" + mainCharacter.getLevel()*10 + "\nVisa: +" + mainCharacter.getLevel() + " Days" + "\nMoney: +$" + mainCharacter.getLevel()*10);
        opponent = "Hearts";
    }

    @FXML
    public void clubOpp(){
        rewardText.setText("Agility: +" + mainCharacter.getLevel()*10 + "\nVisa: +" + mainCharacter.getLevel() + " Days" + "\nMoney: +$" + mainCharacter.getLevel()*10);
        opponent = "Clubs";
    }

    @FXML
    public void oppChosen(){
        boolean lookingOpp = true;
        //This is generating random enemies from the enemies array until the enemy and player have the same level and the enemy has the selected class
        while(lookingOpp){
            enemyCharacter = allEnemies.get(randomNumber(0, allEnemies.size()-1));
            enemyCharacter.setChosenWeapon(enemyCharacter.getWeapon(randomNumber(0, enemyCharacter.getWeapons().size()-1)));
            if (enemyCharacter.getCard().equals(opponent) && enemyCharacter.getLevel() == mainCharacter.getLevel()){
                lookingOpp = false;
            }
        }
        enemyImage = "src/main/resources/Avatars/" + images[randomNumber(0, images.length-1)] + ".png";
//        System.out.println(enemyCharacter.getTotalHP());
        chooseOpponentPane.setVisible(false);
        battlePane.setVisible(true);

        //This is selecting a random wepaon for the player if they selected battle without picking a weapon
        if(mainCharacter.getChosenWeapon() == null){
            mainCharacter.setChosenWeapon(mainCharacter.getWeapon(randomNumber(0, mainCharacter.getWeapons().size()-1)));
        }
        try {
            FileInputStream input = new FileInputStream(mainImage);
            battlePlayer.setImage(new Image(input));
            FileInputStream enemy = new FileInputStream(enemyImage);
            battleEnemy.setImage(new Image(enemy));
        } catch (Exception e) {
            e.printStackTrace();
        }
        nameMC.setText(mainCharacter.getName() + "      Level: " + mainCharacter.getLevel());
        nameEnemy.setText(enemyCharacter.getName() + "      Level: " + enemyCharacter.getLevel());
        turnText.setText("Turn: " + mainCharacter.getName());
        enemyCharacter.setHp(enemyCharacter.getTotalHP());
        mainCharacter.setHp(mainCharacter.getTotalHP());
        updateStats();

    }

    @FXML
    public void updateStats(){
        try {
            playerStats.setText("Class: " + mainCharacter.getCard() + "\nAttack: " + mainCharacter.getAttack() + "\nDefense: " + mainCharacter.getDefense() + "\nAgility: " + mainCharacter.getAgility() + "\nLuck: " + mainCharacter.getLuck() + "\nLevel " + mainCharacter.getChosenWeapon().getLevel() + " " + mainCharacter.getChosenWeapon().getName());
            enemyStats.setText("Class: " + enemyCharacter.getCard() + "\nAttack: " + enemyCharacter.getAttack() + "\nDefense: " + enemyCharacter.getDefense() + "\nAgility: " + enemyCharacter.getAgility() + "\nLuck: " + enemyCharacter.getLuck() + "\nLevel " + enemyCharacter.getChosenWeapon().getLevel() + " " + enemyCharacter.getChosenWeapon().getName());
        } catch (NullPointerException e){
            System.out.println(e.getCause());
        }
        mainHP.setProgress(mainCharacter.getHp()/Double.valueOf(mainCharacter.getTotalHP()));
        enemyHP.setProgress(enemyCharacter.getHp()/Double.valueOf(enemyCharacter.getTotalHP()));
    }

    @FXML
    public void enemyMove(ActionEvent e){
        int which = randomNumber(0, 4);
        System.out.println(which);
        if (!playerTurn){
            if(which <= 2){
                attack(e);
            } else if (which == 3){
                recover(enemyCharacter);
            } else {
                specialMove(enemyCharacter, mainCharacter);
            }
        }
        playerTurn = true;
    }

    @FXML
    public void recoverClicked(){
        if(playerTurn){
            recover(mainCharacter);
        }
        playerTurn = false;
    }

    @FXML
    public void recover(Character a){
        if(a.equals(enemyCharacter) && enemyCharacter.getHp() + 100 > enemyCharacter.getTotalHP()){
            specialMove(enemyCharacter, mainCharacter);
        }
        double num = randomNumber(1, 30);
        a.setHp((int) (a.getHp() + num/100 * a.getHp()));
        turnText.setText(a.getName() + " Improved HP By " + num + "%");
        updateStats();
    }

    @FXML
    public void attack(ActionEvent e){
        if(playerTurn && e.getSource().toString().equals("Button[id=playerAttack, styleClass=button]' Attack'")){
            move(mainCharacter, enemyCharacter);
            playerTurn = false;
        } else if (!playerTurn && e.getSource().toString().equals("Button[id=enemyAttack, styleClass=button]'Enemy Move'")){
            move(enemyCharacter, mainCharacter);
            playerTurn = true;
        }
        updateStats();
        if (enemyCharacter.getHp() <= 0){
            turnText.setText("You Won :)");
            collectRewards.setVisible(true);
            playerWon = true;
        } else if(mainCharacter.getHp() <= 0){
            turnText.setText("You Lost :(");
            collectRewards.setVisible(true);
            playerWon = false;
        }
    }

    @FXML
    public void move(Character a, Character b){
        //Taking the difference of the opponenents and users agility and calculating the chances of dodging an attack
        if(randomNumber(1, 50) <= (b.getAgility() + b.getChosenWeapon().getAgility()) - (a.getAgility() + a.getChosenWeapon().getAgility())){
            turnText.setText(b.getName() + " Dodged The Hit.\nNext Turn: " + b.getName());
        } else if ((double)((a.getLuck() + a.getChosenWeapon().getLuck())/500) >= Math.random()) {
            if(randomNumber(1, 100) == 1) {
                turnText.setText(b.getName() + " Was Frightened and Lost All HP");
                b.setHp(0);
            }
        } else {
            int damage = (int) (Math.pow((a.getAttack() + a.getChosenWeapon().getAttack()), 2) / ((a.getAttack() + a.getChosenWeapon().getAttack()) + (b.getDefense() + b.getChosenWeapon().getDefense())));
            b.setHp(b.getHp()-damage);
            turnText.setText("Damage: -" + damage + " HP\nNext Turn: " + b.getName());
        }
        updateStats();
    }

    @FXML
    public void specialMoveClicked(){
        if (playerTurn){
            specialMove(mainCharacter, enemyCharacter);
        }
        playerTurn = false;
    }

    @FXML
    public void specialMove(Character a, Character b){
        if(randomNumber(1, 50) == 1){
            turnText.setText(a.getName() + " Found A Strong Recovery Potion");
            a.setHp(a.getHp() + 100);
        } else if(randomNumber(1, 10) == 1){
            if(a.equals(mainCharacter)){
                turnText.setText(a.getName() + " Found " + a.getLevel() * 10 + " Lying Around");
                a.setMoney(a.getMoney() + a.getLevel() * 10);
            } else {
                turnText.setText(a.getName() + " Robbed You");
                if (a.getMoney() > 0){
                    a.setMoney(a.getMoney() - 10);
                } else {
                    turnText.setText(a.getName() + " Tried Robbing You But Found Out You Are Broke");
                }
            }
        } else {
            turnText.setText(a.getName() + " Tripped " + b.getName() + "\n" + b.getName() + ": -10 HP");
            b.setHp(b.getHp() - 10);
            System.out.println(b.getName() + " " + b.getHp());
        }
        updateStats();
    }

    @FXML
    public void setRewards(){
        rewardPane.setVisible(true);
        try {
            FileInputStream input = new FileInputStream(mainImage);
            mainRewards.setImage(new Image(input));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(playerWon){
            visa += mainCharacter.getLevel();
            mainCharacter.setMoney(mainCharacter.getMoney() + mainCharacter.getLevel() * 10);
            rewardsList.setText("Money: $" + mainCharacter.getMoney() + " +" + mainCharacter.getLevel()*10);
            rewardsList.setText(rewardsList.getText() + "\nVisa: " + visa + " Days +" + mainCharacter.getLevel());

            mainCharacter.setTotalHP(mainCharacter.getTotalHP() + mainCharacter.getLevel() * 10);
            rewardsList.setText(rewardsList.getText() + "\nHP: " + mainCharacter.getTotalHP() + " +" + mainCharacter.getLevel()*10);

            if(opponent.equals("Spades")){
                mainCharacter.setAttack(mainCharacter.getAttack() + mainCharacter.getLevel() * 10);
                rewardsList.setText(rewardsList.getText() + "\nAttack: " + mainCharacter.getAttack() + " +" + mainCharacter.getLevel()*10);

            } else if(opponent.equals("Hearts")){
                mainCharacter.setDefense(mainCharacter.getDefense() + mainCharacter.getLevel() * 10);
                rewardsList.setText(rewardsList.getText() + "\nDefense: " + mainCharacter.getDefense() + " +" + mainCharacter.getLevel()*10);

            } else if (opponent.equals("Clubs")){
                mainCharacter.setAgility(mainCharacter.getAgility() + mainCharacter.getLevel() * 10);
                rewardsList.setText(rewardsList.getText() + "\nAgility: " + mainCharacter.getAgility() + " +" + mainCharacter.getLevel()*10);

            } else if (opponent.equals("Diamonds")){
                mainCharacter.setLuck(mainCharacter.getLuck() + mainCharacter.getLevel() * 10);
                rewardsList.setText(rewardsList.getText() + "\nLuck: " + mainCharacter.getLuck() + " +" + mainCharacter.getLevel()*10);
            }
        } else {
            rewardsList.setText("Sorry You Lost So \nNo Rewards :(");
        }
    }

    @FXML
    public void exitRewards(){
        rewardPane.setVisible(false);
        collectRewards.setVisible(false);
    }

    @FXML
    public void setWeaponPane(){
        weaponPane.setVisible(true);
        weaponsList.getItems().clear();
        for(String name: types){
            weaponsList.getItems().add(name);
        }
    }

    @FXML
    public void showWeapon(){
        String which = weaponsList.getSelectionModel().getSelectedItem().toString();
        weaponInfo.setVisible(true);

        int where = weaponNames.indexOf(which);
        showWeapon = allWeapons.get(where);

        while(mainCharacter.getWeapons().indexOf(showWeapon) >= 0 && showWeapon.getName().equals(which)){
            where += 1;
            showWeapon = allWeapons.get(where);
        }
        System.out.println(showWeapon.getName());
        System.out.println(mainCharacter.getWeapons().indexOf(showWeapon));
//        System.out.println(mainCharacter.getWeapons().indexOf(showWeapon) > 0);
        System.out.println(showWeapon.equals(mainCharacter.getWeapon(0)));
        System.out.println(mainCharacter.getWeapon(0).getName() + "\nLevel: " + mainCharacter.getWeapon(0).getLevel() + "\nCost: $" + mainCharacter.getWeapon(0).getCost());
        System.out.println("Attack: " + mainCharacter.getWeapon(0).getAttack() + "\nAgility: " + mainCharacter.getWeapon(0).getAgility() + "\nDefense: " + mainCharacter.getWeapon(0).getDefense() + "\nLuck: " + mainCharacter.getWeapon(0).getLuck());
        System.out.println(mainCharacter.getWeapon(0).getImage());
        System.out.println(showWeapon.getName() + "\nLevel: " + showWeapon.getLevel() + "\nCost: $" + showWeapon.getCost());
        System.out.println(("Attack: " + showWeapon.getAttack() + "\nAgility: " + showWeapon.getAgility() + "\nDefense: " + showWeapon.getDefense() + "\nLuck: " + showWeapon.getLuck()));
        System.out.println(showWeapon.getImage());
        try {
            FileInputStream input = new FileInputStream(showWeapon.getImage());
            weaponIcon.setImage(new Image(input));
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(showWeapon.getLevel() * 3 - 2 >= mainCharacter.getLevel() && (showWeapon.getLevel() <= mainCharacter.getLevel()/3 || showWeapon.getLevel() <= mainCharacter.getLevel()%3)){
            buyButton.setDisable(false);
            weaponError.setVisible(false);
        } else {
            buyButton.setDisable(true);
            weaponError.setText("Player Level Not High Enough To Purchase");
            weaponError.setVisible(true);
        }
//        System.out.println(showWeapon.getLevel() * 3 - 2 >= mainCharacter.getLevel());
//        System.out.println(showWeapon.getLevel() <= mainCharacter.getLevel()/3);
//        System.out.println(showWeapon.getLevel());
//        System.out.println((double)(mainCharacter.getLevel())/3);

        weaponLabel.setText(showWeapon.getName() + "\nLevel: " + showWeapon.getLevel() + "\nCost: $" + showWeapon.getCost() );
        weaponStats.setText("Attack: " + showWeapon.getAttack() + "\nAgility: " + showWeapon.getAgility() + "\nDefense: " + showWeapon.getDefense() + "\nLuck: " + showWeapon.getLuck());
    }

    @FXML
    public void buyWeapon(){
        boolean pushed = false;
        if(mainCharacter.getMoney() < showWeapon.getCost()){
            weaponError.setText("Not Enough Money");
            weaponError.setVisible(true);
        } else {
            mainCharacter.setMoney(mainCharacter.getMoney() - showWeapon.getCost());

            //Looking through the current array to see if the weapon is already there and if it is then replacing it
            for(int i = 0; i < mainCharacter.getWeapons().size(); i++){
                if(mainCharacter.getWeapon(i).getName().equals(showWeapon.getName())){
                    mainCharacter.setWeapon(showWeapon, i);
                    pushed = true;
                }
            }
            if (!pushed){
                mainCharacter.addWeapon(showWeapon);
            }
            showWeapon();
            setCharacterPane();
        }
    }

    @FXML
    public void exitWeapons(){
        System.out.println("running");
        weaponPane.setVisible(false);
    }

    @FXML
    public void saveButton(){
        save();
    }

    @FXML
    public void save(){
        String outFile = "src/main/resources/mainStats.txt";
        try{
            PrintWriter out = new PrintWriter(outFile);
            out.println(mainCharacter.getName());
            out.println(mainCharacter.getTotalHP());
            out.println(mainCharacter.getLevel());
            out.println(mainCharacter.getMoney());
            out.println(mainCharacter.getDefense());
            out.println(mainCharacter.getAttack());
            out.println(mainCharacter.getAgility());
            out.println(mainCharacter.getLuck());
            out.println(mainCharacter.getCard());
            out.println(visa);
            out.println(mainImage);
            out.close();
        } catch (FileNotFoundException e){
            System.out.println("no file");
        }

        String outFile2 = "src/main/resources/mainWeapon.txt";
        try{
            PrintWriter out2 = new PrintWriter(outFile2);
            for (Weapon a: mainCharacter.getWeapons()){
                out2.println(a.getName());
                out2.println(a.getImage());
                out2.println(a.getLevel());
                out2.println(a.getAttack());
                out2.println(a.getAgility());
                out2.println(a.getDefense());
                out2.println(a.getLuck());
                out2.println(a.getCost());
            }
            out2.close();
        } catch (FileNotFoundException e2){
            System.out.println("no file 2");
        }
    }

    @FXML
    public void handleLoad(){
        try {
            FileReader reader = new FileReader("src/main/resources/mainStats.txt");
            Scanner in = new Scanner(reader);

            ArrayList <String> stats = new ArrayList<>();

            while(in.hasNextLine()) {
                stats.add(in.nextLine());
            }

            setStats(stats);
        } catch (IOException ex){
            System.out.println("Something is very wrong");
        }

        try{
            FileReader reader2 = new FileReader("src/main/resources/mainWeapon.txt");
            Scanner in2 = new Scanner(reader2);
            ArrayList <String> weaponLoad = new ArrayList<>();

            while (in2.hasNextLine()){
                weaponLoad.add(in2.nextLine());
            }

            loadWeapons(weaponLoad);
        } catch (FileNotFoundException ex2){
            System.out.println("Something is very wrong 2");
        }
    }

    @FXML
    public void setStats(ArrayList<String> x){
        mainCharacter.setName(x.get(0));
        mainCharacter.setTotalHP(Integer.parseInt(x.get(1)));
        mainCharacter.setLevel(Integer.parseInt(x.get(2)));
        mainCharacter.setMoney(Integer.parseInt(x.get(3)));
        mainCharacter.setDefense(Integer.parseInt(x.get(4)));
        mainCharacter.setAttack(Integer.parseInt(x.get(5)));
        mainCharacter.setAgility(Integer.parseInt(x.get(6)));
        mainCharacter.setLuck(Integer.parseInt(x.get(7)));
        mainCharacter.setCard(x.get(8));
        visa = Integer.parseInt(x.get(9));
        mainImage = x.get(10);
    }

    @FXML
    public void loadWeapons(ArrayList<String> x){
        mainCharacter.getWeapons().clear();
        for(int i = 0; i < x.size(); i += 8){
            mainCharacter.getWeapons().add(new Weapon(x.get(i), x.get(i+1), Integer.parseInt(x.get(i + 2)), Integer.parseInt(x.get(i+3)), Integer.parseInt(x.get(i+4)), Integer.parseInt(x.get(i+5)), Integer.parseInt(x.get(i+6)), Integer.parseInt(x.get(i+7))));
        }
        System.out.println(mainCharacter.getWeapon(0).getName());
    }

    @FXML
    public void visaTimer(){
        long current = System.nanoTime();

        double timeElapsed = (current - visaTime) / 1_000_000_000;
        timeElapsed = roundToPlace(timeElapsed, 1);

        if(timeElapsed > 30.0){
            visa -= 1;
            visaTime = current;
            if(visa==0){
                gameOver.setVisible(true);
            }
            if(visa==1){
                infoText.setText("Level: " + mainCharacter.getLevel() + "\nMoney: $" + mainCharacter.getMoney()
                        + "\nVisa: WARNING ONLY " + Integer.toString(visa) + " DAY");
            }
        }
    }

    public int randomNumber(int a, int b) {
        double x = Math.floor(Math.random() * (b - a + 1) + a);
        return (int) x;
    }

    public double roundToPlace(double num, int place) {
        num*=Math.pow(10, place);
        num = Math.round(num);
        num/=Math.pow(10, place);
        return num;
    }
}