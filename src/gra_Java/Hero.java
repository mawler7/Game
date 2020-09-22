package gra_Java;

import java.util.Date;

import static gra_Java.ConsoleUnits.*;

public class Hero {

    //statistics given by player
    private String name;
    private Sex sex;

    // physical stats
    private int strength;
    private int stamina;
    private int dexterity;

    // mind stats
    private int intelligence;
    private int wisdom;
    private int charisma;

    // base stats
    private float baseDamage;
    private float baseBlock;

    // derived stats
    private float movementSpeed;
    private float mana;
    private float health;
    private int coins;
    private Buff buffs = null;

    public Hero(String name, Sex sex, int strength, int stamina, int dexterity, int intelligence, int wisdom, int charisma) {

        int maleBonus = sex == Sex.MALE ? 10 : 0;
        int femaleBonus = sex == Sex.FEMALE ? 10 : 0;
        int otherBonus = sex == Sex.OTHER ? 10 : 0;

        this.name = name;
        this.sex = sex;
        this.strength = strength + maleBonus;
        this.stamina = stamina + maleBonus;
        this.dexterity = dexterity + femaleBonus;
        this.intelligence = intelligence + femaleBonus;
        this.wisdom = wisdom + otherBonus;
        this.charisma = charisma + otherBonus;

        this.baseDamage = strength * 0.1f;
        this.baseBlock = dexterity * 0.1f;
        this.movementSpeed = stamina * 0.1f;

        this.health = strength * 0.5f + stamina * 0.2f + dexterity * 0.1f;
        this.mana = intelligence * 0.5f + wisdom + stamina * 0.1f;

        this.coins = 0;
    }

    public void printInfo() {
        System.out.println(C_BLACK_BOLD + C_RED_BACKGROUND + "===== HERO STATS =====" + C_RESET +
                "\nname: " + name +
                "\nsex: " + sex.name() +
                "\nhealth: " + health +
                "\nmana: " + mana +
                "\n" + C_BLACK_BOLD + C_RED_BACKGROUND + "====STATS====" + C_RESET +
                "\nstrength: " + strength +
                "\nstamina: " + stamina +
                "\ndexterity: " + dexterity +
                "\nintelligence: " + intelligence +
                "\nwisdom: " + wisdom +
                "\ncharisma: " + charisma +
                "\n" + C_BLACK_BOLD + C_RED_BACKGROUND + "====FIGHTS====" + C_RESET +
                "\nbaseDamage: " + baseDamage +
                "\nbaseBlock: " + baseBlock +
                "\ncoins: " + coins +
                "\nmovementSpeed: " + movementSpeed +
                "\nbuffs: " + buffs +
                "\n"
        );
    }

    public void applyDamage(byte amount) {
        System.out.println("Dealing " + amount + " damage to " + name);
        health -= amount;

        if (health < 0) {
            health = 0;

            System.out.println("Enemy: " + name + " is dead!");
        }
    }

    public void attack(char attackType, Enemy enemy) {
        //attackType = 'S'; // Sword, Axe, Fire, Ice
        float attackValue = 1.5f;
        float hitChance = 2.5f;
        float manaCost = 0.1f;

        switch (Character.toUpperCase(attackType)) {
            case 'S':
                attackValue = baseDamage * 2;
                hitChance = dexterity * 2;
                break;
            case 'A':
                attackValue = baseDamage * 5;
                hitChance = dexterity * 1;
                break;
            case 'F':
                attackValue = baseDamage * 10;
                hitChance = intelligence * 5;
                manaCost = 25;
                break;

            case 'I':
                attackValue = baseDamage * 2;
                hitChance = intelligence * 4;
                manaCost = 10;
                break;
        }

        mana -= manaCost;
        enemy.applyDamage((byte) attackValue, hitChance);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void receiveCoins(int coins, Enemy enemy) {
        this.coins = coins + enemy.getCoins();

    }
}
