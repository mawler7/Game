package gra_Java;

import static gra_Java.ConsoleUnits.*;
import static gra_Java.Sex.*;

public class HeroBuilder {

    public static HeroBuilder Instance;

    HeroBuilder() {
    }

    public static HeroBuilder getInstance() {
        if (Instance == null) {
            Instance = new HeroBuilder();
        }
        return Instance;
    }

    public static int skillPoints = 100;

    public Hero buildHero() {

        String name = promptForString("\n" + C_GREEN_BOLD + "Enter character name" + C_RESET + "...: ");
        String sexInput = promptForString("\n" + C_GREEN_BOLD + "Choose character sex:" + C_RESET
                + C_BLUE_BOLD + "\n[MALE] " + C_RESET
                + C_RED_BOLD + "[FEMALE]" + C_RESET + C_PURPLE + " [OTHER]"
                + C_RESET + "...:");
        String sexSafeInput = sexInput.toLowerCase();

        Sex sex;

        switch (sexSafeInput) {
            case "m":
            case "ma":
            case "mal":
            case "male":
                printDebug("SELECTED MALE");
                sex = MALE;
                break;
            case "f":
            case "fe":
            case "fem":
            case "fema":
            case "femal":
            case "female":
                printDebug("SELECTED FEMALE");
                sex = FEMALE;
                break;
            case "o":
            case "ot":
            case "oth":
            case "othe":
            case "other":
            default:
                printDebug("SELECTED OTHER");
                sex = OTHER;
                break;
        }

        int strength = 0;
        int stamina = 0;
        int dexterity = 0;
        int intelligence = 0;
        int wisdom = 0;
        int charisma = 0;

        boolean exit = false;


        do {
            clearScreen();
            System.out.println("\n" + C_BLACK_BOLD + "Skill points left: " + skillPoints + C_RESET + "\n\n" +
                    "[1] strength:     " + strength + "\n" +
                    "[2] stamina:      " + stamina + "\n" +
                    "[3] dexterity:    " + dexterity + "\n" +
                    "[4] intelligence: " + intelligence + "\n" +
                    "[5] wisdom:       " + wisdom + "\n" +
                    "[6] charisma:     " + charisma + "\n\n"
            );
            Boolean allStatFieldsSet =
                    strength > 0 &&
                            stamina > 0 &&
                            dexterity > 0 &&
                            intelligence > 0 &&
                            wisdom > 0 &&
                            charisma > 0;

            if (skillPoints == 0 && allStatFieldsSet) {
                clearScreen();
                exit = true;
            } else {


                int choice = promptForInt(C_GREEN_BOLD + "Choose number of the skill and press Enter" + C_RESET + "...: ");
                switch (choice) {
                    case 1:
                        strength = readSkillValueFor("strength", strength);
                        break;
                    case 2:
                        stamina = readSkillValueFor("stamina", stamina);
                        break;
                    case 3:
                        dexterity = readSkillValueFor("dexterity", dexterity);
                        break;
                    case 4:
                        intelligence = readSkillValueFor("intelligence", intelligence);
                        break;
                    case 5:
                        wisdom = readSkillValueFor("wisdom", wisdom);
                        break;
                    case 6:
                        charisma = readSkillValueFor("charisma", charisma);
                        break;
                    default:

                }
            }
        } while (!exit);

        return new Hero(name, sex, strength, stamina, dexterity, intelligence, wisdom, charisma);
    }

    public int readSkillValueFor(String fieldName, int currentFieldValue) {
        int value;
        int newSkillPointValue;

        do {
            value = promptForInt(C_GREEN_BOLD + "Enter " + fieldName + " points...: " + C_RESET);
            if (value < 0) {
                System.out.println(C_RED_BOLD + "New value has to be >= 0 !" + C_RESET);
            }

            newSkillPointValue = skillPoints - value + currentFieldValue;
            if (newSkillPointValue < 0) {
                System.out.println(C_RED_BOLD + "Not enough skill points!\nOnly " + C_RED_BOLD + skillPoints + " skill points left" + C_RESET);
            }
        } while (value < 0 || newSkillPointValue < 0);

        skillPoints = newSkillPointValue;

        return value;
    }


}

