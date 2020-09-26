package gra_Java;

import static gra_Java.ConsoleUnits.*;
import static gra_Java.ConsoleUnits.c_purple;

public class Game {

    public void start() {

        ConsoleUnits.debugMode = false;

        System.out.println("\n\n" + C_BLACK_BOLD + C_RED_BACKGROUND + "WELCOME TO THE YAMBA X GAME" + C_RESET + "\n" +
                "\n" + C_PURPLE_BOLD + ">>>>>>>>>|>>>>>>>>20]>>>>>>>>]>>>>>>>40]>>>>>>>>50]>>>>>>>>60]>>>>>>>>70]>>>>>>>>80>>>>>>>>90]>>>>>>>100]>>>>>>>110]>>>>>>>120]" + C_RESET);

        HeroBuilder hb = new HeroBuilder();
        Hero hero = hb.buildHero();


        String names[] = {"[ENEMY LVL 2]", "[ENEMY LVL 3]", "[ENEMY LVL 4]", "[ENEMY LVL 5]"};
        String attackType = "";
        String actionType = "";

        Enemy enemy = new Enemy();
        enemy.name = "[ENEMY LVL 1]";
        hero.printInfo();
        actionType = promptForString(C_GREEN_BOLD + "PRESS [ENTER] TO FIGHT YOUR FIRST ENEMY:\n" + C_RESET);
        clearScreen();
        enemy.getRandomTaunt();
        enemy.getInfo();
        hero.applyDamage((byte) 1);


        do {

            do {

                if (Enemy.enemiesCount == 5) {
                    hero.receiveCoins(enemy);
                    System.out.println("[" + C_GREEN_BACKGROUND + C_BLACK + "STAGE I COMPLETED" + C_RESET);
                    break;


                } else if (enemy.isDead()) {
                    hero.receiveCoins(enemy);
                    enemy = new Enemy(names[Enemy.enemiesCount - 1]);
                    enemy.getRandomTaunt();
                    enemy.getInfo();
                    actionType = promptForString(C_GREEN_BOLD + "ACTIONS:" + C_RESET + C_RED + "\t[FIGHT]" + C_RESET + " [RUN]");


                } else if (!enemy.isDead()) {

                    hero.getStats();
                    enemy.getInfo();
                    actionType = promptForString(C_GREEN_BOLD + "ACTIONS:" + C_RESET + C_RED + "\t[FIGHT]" + C_RESET + " [RUN]");


                }
                switch (actionType.toUpperCase()) {

                    case "R":
                        System.out.println("You've run away from Enemy");
                        hero.runAway(5);
                        clearScreen();
                        hero.printInfo();

                        break;

                    case "F":
                        if (!enemy.isDead()) {
                            attackType = promptForString("\nCHOOSE YOUR ATTACK"
                                    + "\t[" + C_BLACK_BOLD + C_YELLOW_BACKGROUND + "AXE" + C_RESET + "]"
                                    + " [" + C_BLACK_BOLD + C_GREEN_BACKGROUND + "SWORD" + C_RESET + "]"
                                    + " [" + C_BLACK_BOLD + C_RED_BACKGROUND + "FIRE" + C_RESET + "]"
                                    + " [" + C_BLACK_BOLD + C_WHITE_BACKGROUND + "ICE" + C_RESET + "]"
                                    + " [" + C_BLACK_BOLD + "QUIT" + C_RESET + "]..:"
                            );
                            clearScreen();
                            hero.attack(attackType.charAt(0), enemy);
                            if (!enemy.isDead()) {
                                hero.applyDamage((byte) 1);
                            } else {

                                break;
                            }
                        }
                    default:


                }


            } while (true);


        } while (attackType.isEmpty());

        hero.printInfo();
        actionType = promptForString(
                C_GREEN_BOLD + "You have received coins for killing enemies" + C_RESET +
                        "\n\n" + C_GREEN_BOLD + "Would you like restore your HP and MP for 20 coins  ?" + C_RESET +
                        "\n" + C_GREEN_BOLD + "[YES] " + C_RESET + C_RED_BOLD + "[NO]" + C_RESET + "...:");
        switch (actionType.toUpperCase()) {

            case "Y":
                clearScreen();
                hero.restoreStats();
                hero.printInfo();
                break;

            case "N":
                clearScreen();
                hero.printInfo();
                break;
        }
        actionType = promptForString(C_GREEN_BOLD + "PRESS [ENTER] TO CONTINUE:\n" + C_RESET);


        do {
            String namesStage2[] = {"[ENEMY LVL 7]", "[ENEMY LVL 8]", "[ENEMY LVL 9]", "[ENEMY LVL 10]"};

            Enemy enemyS2 = new Enemy();
            enemyS2.name = "[ENEMY LVL 6]";
            clearScreen();
            enemyS2.getRandomTaunt();
            enemyS2.getInfo();
            hero.applyDamage((byte) 1);
            do {

                if (Enemy.enemiesCount == 10) {
                    hero.receiveCoins(enemyS2);
                    System.out.println("[" + C_GREEN_BACKGROUND + C_BLACK + "STAGE II COMPLETED" + C_RESET);
                    break;


                } else if (enemyS2.isDead()) {
                    Enemy.enemiesCountStage2++;
                    hero.receiveCoins(enemyS2);
                    enemyS2 = new Enemy(namesStage2[Enemy.enemiesCountStage2 - 1]);
                    enemyS2.getRandomTaunt();
                    enemyS2.getInfo();
                    actionType = promptForString(C_GREEN_BOLD + "ACTIONS:" + C_RESET + C_RED + "\t[FIGHT]" + C_RESET + " [RUN]");


                } else if (!enemyS2.isDead()) {

                    hero.getStats();
                    enemyS2.getInfo();
                    actionType = promptForString(C_GREEN_BOLD + "ACTIONS:" + C_RESET + C_RED + "\t[FIGHT]" + C_RESET + " [RUN]");


                }
                switch (actionType.toUpperCase()) {

                    case "R":
                        System.out.println("You've run away from Enemy");
                        hero.runAway(5);
                        clearScreen();
                        hero.printInfo();

                        break;

                    case "F":
                        if (!enemyS2.isDead()) {
                            attackType = promptForString("\nCHOOSE YOUR ATTACK"
                                    + "\t[" + C_BLACK_BOLD + C_YELLOW_BACKGROUND + "AXE" + C_RESET + "]"
                                    + " [" + C_BLACK_BOLD + C_GREEN_BACKGROUND + "SWORD" + C_RESET + "]"
                                    + " [" + C_BLACK_BOLD + C_RED_BACKGROUND + "FIRE" + C_RESET + "]"
                                    + " [" + C_BLACK_BOLD + C_WHITE_BACKGROUND + "ICE" + C_RESET + "]"
                                    + " [" + C_BLACK_BOLD + "QUIT" + C_RESET + "]..:"
                            );
                            clearScreen();
                            hero.attack(attackType.charAt(0), enemyS2);
                            if (!enemyS2.isDead()) {
                                hero.applyDamage((byte) 1);
                            } else {

                                break;
                            }
                        }
                    default:


                }


            } while (true);


        } while (attackType.isEmpty());

        hero.printInfo();
        actionType = promptForString(
                C_GREEN_BOLD + "You have received coins for killing enemies" + C_RESET +
                        "\n\n" + C_GREEN_BOLD + "Would you like restore your HP and MP for 20 coins  ?" + C_RESET +
                        "\n" + C_GREEN_BOLD + "[YES] " + C_RESET + C_RED_BOLD + "[NO]" + C_RESET + "...:");
        switch (actionType.toUpperCase()) {

            case "Y":
                clearScreen();
                hero.restoreStats();
                hero.printInfo();
                break;

            case "N":
                clearScreen();
                hero.printInfo();
                break;
        }

        actionType = promptForString(C_GREEN_BOLD + "PRESS [ENTER] TO CONTINUE:\n" + C_RESET);

        String namesStage3[] = {"[ENEMY LVL 12]", "[ENEMY LVL 13]", "[ENEMY LVL 14]", "[ENEMY LVL 15]"};

        Enemy enemyS3 = new Enemy();
        enemyS3.name = "[ENEMY LVL 11]";
        clearScreen();
        enemyS3.getRandomTaunt();
        enemyS3.getInfo();
        hero.applyDamage((byte) 1);

        do {

            do {

                if (Enemy.enemiesCount == 15) {
                    hero.receiveCoins(enemyS3);
                    System.out.println("[" + C_GREEN_BACKGROUND + C_BLACK + "STAGE III COMPLETED" + C_RESET);
                    break;


                } else if (enemyS3.isDead()) {
                    Enemy.enemiesCountStage3++;
                    hero.receiveCoins(enemyS3);
                    enemyS3 = new Enemy(namesStage3[Enemy.enemiesCountStage3 - 1]);
                    enemyS3.getRandomTaunt();
                    enemyS3.getInfo();
                    actionType = promptForString(C_GREEN_BOLD + "ACTIONS:" + C_RESET + C_RED + "\t[FIGHT]" + C_RESET + " [RUN]");


                } else if (!enemyS3.isDead()) {

                    hero.getStats();
                    enemyS3.getInfo();
                    actionType = promptForString(C_GREEN_BOLD + "ACTIONS:" + C_RESET + C_RED + "\t[FIGHT]" + C_RESET + " [RUN]");


                }
                switch (actionType.toUpperCase()) {

                    case "R":
                        System.out.println("You've run away from Enemy");
                        hero.runAway(5);
                        clearScreen();
                        hero.printInfo();

                        break;

                    case "F":
                        if (!enemyS3.isDead()) {
                            attackType = promptForString("\nCHOOSE YOUR ATTACK"
                                    + "\t[" + C_BLACK_BOLD + C_YELLOW_BACKGROUND + "AXE" + C_RESET + "]"
                                    + " [" + C_BLACK_BOLD + C_GREEN_BACKGROUND + "SWORD" + C_RESET + "]"
                                    + " [" + C_BLACK_BOLD + C_RED_BACKGROUND + "FIRE" + C_RESET + "]"
                                    + " [" + C_BLACK_BOLD + C_WHITE_BACKGROUND + "ICE" + C_RESET + "]"
                                    + " [" + C_BLACK_BOLD + "QUIT" + C_RESET + "]..:"
                            );
                            clearScreen();
                            hero.attack(attackType.charAt(0), enemyS3);
                            if (!enemyS3.isDead()) {
                                hero.applyDamage((byte) 1);
                            } else {

                                break;
                            }
                        }
                    default:


                }


            } while (true);


        } while (attackType.isEmpty());
        hero.printInfo();
        System.out.println("\nGAMER OVER");
        System.exit(0);


    }
}