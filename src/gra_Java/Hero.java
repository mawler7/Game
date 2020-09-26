package gra_Java;

import java.util.Date;

import static gra_Java.ConsoleUnits.*;

public class Hero {

    //statistics given by player
    private String name;
    private Sex sex;

    //max stats
    private int maxmp;
    private int maxhp;

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
    private int mana;
    private int health;
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

        this.health = strength + stamina + dexterity;
        this.mana = intelligence + wisdom + stamina;

        this.coins = 10;
        this.maxhp = 100;
        this.maxmp = 100;
    }

    public void printInfo() {
        System.out.println(
                "\n>>>>>>>>>|>>>>>>>>20]>>>>>>>>]>>>>>>>40]>>>>>>>>50]>>>>>>>>60]>>>>>>>>70]>>>>>>>>80>>>>>>>>90]>>>>>>>100]>>>>>>>110]>>>>>>>120]\n" + C_CYAN +
                        "+_______________________+" +
                        "\n|------PLAYER INFO------|" +
                        "\n name\t: " + name +
                        "\n sex\t: " + sex.name() +
                        "\n health\t: " + health + "/" + maxhp +
                        "\n mana\t: " + mana + "/" + maxmp +
                        "\n|---------STATS---------|" +
                        "\n strength\t\t: " + strength +
                        "\n stamina\t\t: " + stamina +
                        "\n dexterity\t\t: " + dexterity +
                        "\n intelligence\t: " + intelligence +
                        "\n wisdom\t\t\t: " + wisdom +
                        "\n charisma\t\t: " + charisma +
                        "\n|-----FIGHTS STATS------|" +
                        "\n baseDamage\t\t: " + baseDamage +
                        "\n baseBlock\t\t: " + baseBlock +
                        "\n coins\t\t\t: " + coins +
                        "\n movementSpeed\t: " + movementSpeed +
                        "\n buffs\t\t\t: " + buffs +
                        "\n Enemies killed\t: " + Enemy.enemiesCount +
                        "\n+-----------------------+" + C_RESET
        );
    }

    public void applyDamage(byte amount) {

        System.out.println("\nDealing " + C_RED + amount + C_RESET + " damage to " + C_CYAN_BACKGROUND + C_BLACK_BOLD + name + C_RESET);
        health -= amount;

        if (health < 0) {
            health = 0;

            System.out.println("Hero: " + name + " is dead!");
        }
    }

    public void attack(char attackType, Enemy enemy) {

        float attackValue = 1.5f;
        float hitChance = 2.5f;
        float manaCost = 0;

        switch (Character.toUpperCase(attackType)) {

            case 'Q':
                System.out.println("\nPLAYER: " + name + " LEFT THE GAME");
                System.exit(0);

            case 'S':
                attackValue = baseDamage * 2;
                hitChance = dexterity * 2;
                break;
            case 'A':
                attackValue = baseDamage * 5;
                hitChance = dexterity * 1;
                break;
            case 'F':
                if (mana > manaCost) {
                    attackValue = baseDamage * 10;
                    hitChance = intelligence * 5;
                    manaCost = 25;
                } else {
                    System.out.println("Not enough mana to attack");
                }
                break;
            case 'I':
                if (mana > manaCost) {
                    attackValue = baseDamage * 2;
                    hitChance = intelligence * 4;
                    manaCost = 10;
                } else {
                    System.out.println("Not enough mana to attack");
                }
                break;
        }

        mana -= manaCost;
        enemy.applyDamage((byte) attackValue, hitChance);

    }

    public void runAway(int coins) {
        int coinsCost = 5;
        this.coins -= coinsCost;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void receiveCoins(Enemy enemy) {
        this.coins += enemy.getCoins();

    }

    public float getHealth() {
        return health;
    }

    public float getMana() {
        return mana;
    }

    public void restoreStats() {
        this.health = 100;
        this.mana = 100;
        this.coins = coins - 20;
    }

    public void getStats() {
        System.out.println("\n" + C_CYAN_BACKGROUND + C_BLACK_BOLD + "----" + name + "-----" + C_RESET + C_CYAN +
                "\n|---STATS---|" +
                "\n| HP\t: " + health +
                "|\n| MP\t: " + mana +
                "|\n+-----------+" + C_RESET);
    }
}
