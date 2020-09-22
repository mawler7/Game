package gra_Java;

import static gra_Java.ConsoleUnits.*;
import static gra_Java.ConsoleUnits.c_purple;

public class Game {

    public void start() {

        ConsoleUnits.debugMode = false;

        System.out.println( C_BLACK_BOLD + C_RED_BACKGROUND + "Welcome to The Yamba X" + C_RESET );

        HeroBuilder hb = new HeroBuilder();
        Hero hero = hb.buildHero();
        hero.printInfo();

        String names[] = {"A", "B", "C"};

        Enemy enemy = new Enemy();
        enemy.name = "Enemy";
        enemy.getInfo();
//        enemy.applyDamage((byte)5,(float)5);
        enemy.getRandomTaunt();

        String attackType = "";

        do {
            if (Enemy.enemiesCount == 3){
                hero.receiveCoins(enemy.getCoins(), enemy);
                System.out.println("Already killed " + c_purple("" + Enemy.enemiesCount) + " enemies\n");
                System.out.println("Stage 1 Completed!");
                break;

            }
            else if(enemy.isDead()){
                hero.receiveCoins(enemy.getCoins(), enemy);
                System.out.println("Already killed " + c_purple("" + Enemy.enemiesCount) + " enemies\n");
                enemy = new Enemy(names[0]);
                enemy.getInfo();
                enemy.getRandomTaunt();
            }

            else if(!enemy.isDead()) {

                attackType = ConsoleUnits.promptForString("ATTACK WITH"
                        + C_BLACK_BOLD + C_RED_BACKGROUND + " [S]" + C_RESET + "word"
                        + C_BLACK_BOLD + C_RED_BACKGROUND + " [A]" + C_RESET + "xe"
                        + C_BLACK_BOLD + C_RED_BACKGROUND + " [F]" + C_RESET + "ire"
                        + C_BLACK_BOLD + C_RED_BACKGROUND + " [I]" + C_RESET + "ce");

                hero.attack(attackType.charAt(0), enemy);

                System.out.println("\nEnemy health: " + enemy.getHealth() + "\n");
            }

        }
         while (!attackType.isEmpty());

        hero.printInfo();
        System.out.println("GAME OVER");
        System.exit(0);

    }
}
