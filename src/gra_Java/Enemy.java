package gra_Java;
import java.util.concurrent.ThreadLocalRandom;
import static gra_Java.ConsoleUnits.*;


public class Enemy {

    public static int enemiesCount = 0;

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

    public int getCoins(){
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
        System.out.println("Name: " + name + "\nHp: " + health + "\nCoins: " + coins);
    }

    public void applyDamage(byte amount, float attackHitChance) {
        float random = ThreadLocalRandom.current().nextFloat();
        boolean isHit = attackHitChance < random * 100;

//        System.out.println("[DEBUG] random: " + random + " attackChance: " + attackHitChance);

        if (isHit) {
            System.out.println("\nDealing " + C_RED + amount + C_RESET + " damage to " + name);
            health -= amount;

            // przypadek gdy wrog zginal
            if(health < 0) {
                health = 0;

                isDead = true;
                enemiesCount++;

                System.out.println("\nEnemy: " + name + " is dead!");

            }
        } else {
            System.out.println("\nEnemy dodged this!");
        }
    }

    public void getRandomTaunt() {
        int random = ThreadLocalRandom.current().nextInt(0, 3 + 1);

        switch (random) {
            case 0:
                System.out.println("Bring it on!\n");
                break;
            case 1:
                System.out.println("I'll kill you!\n");
                break;
            case 2:
                System.out.println("Don't mess with me!\n");
                break;
            case 3:
                System.out.println("I feel your blood!\n");
                break;
            default:
                System.out.println("[ERROR] Taunt [" + random +"] does not exist");
        }
    }

    public String getName() {

        return name;
    }

    public void setName(String n) {
        name = n;
    }
//    - metoda pozwalająca atakować bohatera
}