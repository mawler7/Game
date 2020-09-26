package gra_Java;

import java.util.concurrent.ThreadLocalRandom;

import static gra_Java.ConsoleUnits.*;


public class Enemy {

    public static int enemiesCount = 0;
    public static int enemiesCountStage2 = 0;
    public static int enemiesCountStage3 = 0;


    String name = "unnamed_enemy";
    private byte health = 15;

    private int strength = 1;
    private float baseDamage = 1;
    private float baseBlock = 1;
    private float movementSpeed = 5.0f;
    private float mana = 10;
    private int coins = 10;
    private Guild guild = Guild.A;
    private Buff buffs = null;
    private boolean isDead = false;


    public boolean isDead() {
        return isDead;
    }

    public byte getHealth() {
        return health;
    }

    public int getCoins() {
        return coins;
    }

    public Enemy() {
        printDebug("empty constructor called from Enemy class");

    }

    public Enemy(String name) {
//        super();
        this();
        this.name = name;
    }

    public void getInfo() {
        System.out.println(C_RED_BACKGROUND + C_BLACK_BOLD + name + C_RESET + C_RED +
                "\n|---STATS---|" +
                "\n| HP\t: " + health +
                "|\n| Coins\t: " + coins +
                "|\n+-----------+" + C_RESET);
    }

    public void applyDamage(byte amount, float attackHitChance) {
        float random = ThreadLocalRandom.current().nextFloat();
        boolean isHit = attackHitChance < random * 100;

//        System.out.println("[DEBUG] random: " + random + " attackChance: " + attackHitChance);

        if (isHit) {
            System.out.println("\nDealing " + C_RED + amount + C_RESET + " damage to " + C_RED_BACKGROUND + C_BLACK_BOLD + name + C_RESET);
            health -= amount;

            // przypadek gdy wrog zginal
            if (health < 0) {
                health = 0;


                enemiesCount++;
                isDead = true;
                System.out.println("\nEnemy: " + C_RED_BACKGROUND + C_BLACK_BOLD + name + C_RESET + " is dead!\n");
                System.out.println("Already killed: " + C_CYAN + enemiesCount + C_RESET + " enemies!\n");

            }
        } else {
            System.out.println(C_RED_BACKGROUND + C_BLACK_BOLD + name + C_RESET + " dodged this!");
        }
    }

    public void getRandomTaunt() {
        int random = ThreadLocalRandom.current().nextInt(0, 3 + 1);

        switch (random) {
            case 0:
                System.out.println("Bring it on!");
                break;
            case 1:
                System.out.println("I'll kill you!");
                break;
            case 2:
                System.out.println("Don't mess with me!");
                break;
            case 3:
                System.out.println("I feel your blood!");
                break;
            default:
                System.out.println("[ERROR] Taunt [" + random + "] does not exist");
        }
    }

    public String getName() {

        return name;
    }

    public void attackHero(byte amount, Hero hero) {
        hero.applyDamage(amount);

    }

    public void setName(String n) {
        name = n;
    }

//    - metoda pozwalająca atakować bohatera
}