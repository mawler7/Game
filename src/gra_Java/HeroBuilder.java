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

        String name = promptForString("Enter character name...: ");
        String sexInput = promptForString("Enter character sex...: \n\t\t\t\t\t\t[M]ale\n\t\t\t\t\t\t[F]emale \n\t\t\t\t\t\t[O]ther...:");
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
            System.out.println("\nSkill points left: " + skillPoints + "\n\n" +
                    "[1] strength:     " + strength + "\n" +
                    "[2] stamina:      " + stamina + "\n" +
                    "[3] dexterity:    " + dexterity + "\n" +
                    "[4] intelligence: " + intelligence + "\n" +
                    "[5] wisdom:       " + wisdom + "\n" +
                    "[6] charisma:     " + charisma + "\n\n"
            );
            Boolean allStatFieldsSet =
                            strength        > 0 &&
                            stamina         > 0 &&
                            dexterity       > 0 &&
                            intelligence    > 0 &&
                            wisdom          > 0 &&
                            charisma        > 0;

            if (skillPoints == 0 && allStatFieldsSet) {
                clearScreen();
                exit = true;
            } else {


                int choice = promptForInt("Choose number of the skill and press Enter\n...: ");
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
            value = promptForInt("Enter " + fieldName + " points...: ");
            if (value < 0) {
                System.out.println("New value has to be >= 0 !");
            }

            newSkillPointValue = skillPoints - value + currentFieldValue;
            if (newSkillPointValue < 0) {
                System.out.println("Not enough skill points!\nOnly " + skillPoints + " skill points left");
            }
        } while (value < 0 || newSkillPointValue < 0);

        skillPoints = newSkillPointValue;

        return value;
    }


    }

