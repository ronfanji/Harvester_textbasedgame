import java.util.*;
import java.io.*;
import java.time.*;


// successfully attacking head instantly kills but chance is prob/4
// successfully attacking torso does most damage to enemy
// successfully attacking arms results in less enemy attack dmg
// successfully attacking legs results in much lower probability of hit

public class Harvester {

    static String currWeapon;
    static int currEssence;
    static int currHP;
    static HashMap<String, Integer> weaponDamage;
    static HashMap<String, Double> weaponProb;
    static HashMap<String, String> weaponRange;
    static Random rand;
    static ArrayList<Character> abilityChar;

    public static void main(String[] args) throws IOException {

        rand = new Random();

        Scanner in = new Scanner(System.in);

        weaponDamage = new HashMap<>();
        weaponProb = new HashMap<>();
        weaponRange = new HashMap<>();
        abilityChar = new ArrayList<>();
        for (int j = 32; j <= 125; j++)
            abilityChar.add((char) j); // size of 94


        currWeapon = "Bare Fists";
        weaponDamage.put("Bare Fists", 10);
        weaponProb.put("Bare Fists", 0.95);
        weaponRange.put("Bare Fists", "close"); // distances close, medium, and long

        currHP = 100;
        currEssence = 10000;


        String s = "\"ATTENTION: INTRUDER SPOTTED WITHIN THE PREMISES.PROCEED WITH CAUTION.TARGET WAS SEEN WEARING" +
                " A RED SWEATER AND JEANS.AGE: ADOLESCENT.WEAPONS: UNKNOWN.PROJECTED ESSENCE: EXTREME\"";
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            System.out.print(c);
            if (c == '.') {
                System.out.println();
                sleep(500);
            } else {
                sleep(25);
            }
        }
        System.out.println("\n\n");

        sleep(2000);

        print("\"God Damnit\", you curse to yourself softly. The endless white halls have been numbing to " +
                "traverse. An intersection approaches you.\n\t[forward]  [left]  [right]  [wait]  [status]", 200);
        boolean left = true;
        boolean right = true;

        while (true) {
            String inp1 = in.next();

            if (inp1.equals("status")) {
                currentStatus();
            } else if (inp1.equals("forward")) {
                s = "You take a daring step forward. A jarring roar erupts, shaking the slippery floors" +
                        " beneath you.";
                print(s, 100);
                break;
            } else if (inp1.equals("left") && left) {
                print("You begin traversing the left side of the hallway. The lights glimmer onto the pure" +
                        "white walls. Nightmarish. A chest is befront you. Do you open? \n\t[yes]  [no]", 100);
                String op = in.next();
                if (op.equals("yes")) {
                    print("The chest uncovers an old pair of safety scissors. The kind used for cutting paper " +
                            "as a child. They have a very short range of attack. Do you acquire? \n\t [yes] [no]", 100);
                    String newWeapon1 = in.next();
                    if (newWeapon1.equals("yes")) {
                        print("You have acquired Safety Scissors. They produce 20 damage per attack, but only" +
                                " hit 75% of the time.", 100);
                        currWeapon = "Safety Scissors";
                        weaponDamage.put("Safety Scissors", 20);
                        weaponProb.put("Safety Scissors", 0.75);
                        print("You return back to the cross.", 150);
                    } else if (newWeapon1.equals("no")) {
                        print("The weapon does not look appealing to you. You close the chest in discouragement.", 100);
                    }

                } else if (op.equals("no")) {
                    print("You trek back to the intersection, refusing to uncover the mysterious chest.", 75);
                }
                left = false;
            } else if (inp1.equals("left") && !left) {
                print("You refuse to search the same place twice.", 0);
            } else if (inp1.equals("right") && right) {
                print("You begin to step slowly through the right side of the intersection. The walls paint a " +
                        "blinding pale hue. At the end of the hallway, stands there is another wall.", 100);
                print("You have wasted your time.", 500);
                print("You return in silence.", 100);
                right = false;
            } else if (inp1.equals("right") && !right) {
                print("You refuse to walk down a deadend once again.", 100);
            } else if (inp1.equals("wait")) {
                sleep(1000);
                print("You pause for a moment. Waiting for something to occur, but nothing ever arrives", 100);
            }

            sleep(500);
            print("\t[forward]  [left]  [right]  [wait]  [status]", 100);
        }
        print("The tremors put you off balance. As you fall, you catch a glimpse of the dark figure at the end of the " +
                "hall: a harvester. \nThe head of this monstrosity was shaped like a bull, but his hefty body seemed " +
                "more akin to a macho wrestler than any cattle you've ever seen. \nHis arm muscles bulge as if they were turgid" +
                " water balloons, and his veins pump through his body like vigorous snakes, \ntracking down its prey as they slither down" +
                " from his shoulder to his forearm every time he flexed. \nHe aims his sharp horns with a furious intent to kill." +
                "\nIn that split second,", 200);
        sleep(1000);
        print("\tyou close your eyes and ready yourself.", 750);

        sleep(1000);
        System.out.println("\n");
        print("Harvesters. Monsters that take but never give. Human-like creatures that steal when they don’t need to steal.", 200);
        print("Essence. It's the living matter, also known as the soul, that they feed on. You can never forgive them for what they did.", 200);
        print("You'll take back everything they've stole.", 500);


        if (!firstFight(in)) {
            print("The Bullman looks down with a contempt satisfaction, his eyebrows furrow. He begins to speak softly,", 150);
            print("I was expecting more. Maybe in another life, kiddo", 400);
            return; // breaks the entire main?
        }
        else{
            print("The Bullman collapses. You have won the battle. You approach the Bullman, or what's left of him", 300);
            print("He choked on his voice as he spoke, but with his final words, he huffed, \"You are strong, kiddo.\" I stared into his fearful eyes, overflowing with tears and remorse.", 300);
            print("A look of realization appeared on his face, as he cracks a slight smile. \"Y-You look just like your father.\" Content with his answer, he rested his head, his body disintegrating into black dust as his ashes disappeared" +
                    " into the wind.\n", 500);
            print("Father?", 300);
        }

    }


    public static void currentStatus(){

        System.out.println("----------------\nYour Current Status:\n");
        print("Weapon: " + currWeapon, 100);
        print("Damage: " + weaponDamage.get(currWeapon), 100);
        print("Hit Rate: " + weaponProb.get(currWeapon), 100);
        print("Range: " + weaponRange.get(currWeapon), 100);
        System.out.println();
        print("Stats", 100);
        print("Essence: " + currEssence, 100);
        print("Health: " + currHP, 100);
        System.out.println("----------------");

    }
    public static void print(String s, int y){

        String[] arr = s.split(" ");
        for(String x: arr){
            System.out.print(x + " ");
            sleep(y);
        }
        System.out.println();

    }

    public static void sleep(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean firstFight(Scanner in){


//        print("The Bullman faces you, his eyes bleed a maroon red. You sigh in resignation. " +
//                "This isn't your first rodeo. ", 350);

        boolean torso = true;
        boolean arms = true;
        boolean legs = true;
        double enemyProb = 0.7;
        double enemyDamage = 20;
        int enemyHealth = 80;

        while(currHP > 0){

            print("What to do?: \n--------------- \n\t[attack]  [status]", 200);
            String s2 = in.next();

            if(s2.equals("attack")) {
                print("Where to attack? \n---------------- \n\t[head]  [torso]  [arms]  [legs]  [ability]", 150);
                String s = in.next();

                if (s.equals("ability")) {

                    print("You have decided to use your ability. If this fails, you risk imploding onto yourself.", 500);
                    print("Clenching your right fist, you can feel the essence surging through your veins. ", 250);
                    print("To finish your ability, you need total focus.", 200);

                    print(". . . ", 750);


                    boolean t = true;

                    for (int j = 0; j < 3; j++) {
                        char c = abilityChar.get(rand.nextInt(94));
                        System.out.println(c);
                        long start = System.currentTimeMillis();
                        String inp = in.next();
                        long finish = System.currentTimeMillis();
                        if (!inp.equals("" + c) || finish - start >= 2000) t = false;
                    }

                    if (t) {
                        print("Success.", 100);
                        enemyHealth -= 40;
                        print("The bullman now has " + enemyHealth + " HP.", 100);
                    } else {
                        System.out.println("The attack failed.");
                        currHP -= 10;
                    }
                    currEssence -= 100;
                    print("You now have " + currEssence + " essence.", 150);

                } else if (s.equals("head")) {
                    if (attackHit(range(weaponProb.get(currWeapon))/3))
                        enemyHealth = 0;
                    else
                        print("Attack missed", 100);


                } else if (s.equals("torso")) {
                    if (attackHit(weaponProb.get(currWeapon))) {
                        enemyHealth -= weaponDamage.get(currWeapon) * 2;
                        print("You striked the bullman's torso! The bullman claspes his chest in agony.", 350);
                        print("The bullman now has " + enemyHealth + " HP.", 100);
                    } else {
                        print("Attack missed", 100);
                    }
                } else if (s.equals("arms")) {
                    if (attackHit(weaponProb.get(currWeapon))) {
                        enemyHealth -= weaponDamage.get(currWeapon) / 2;
                        enemyDamage -= 5;
                        print("You striked the bullman's arms! The bullman cannot swing his arms as hard.", 350);
                        print("The bullman now has " + enemyHealth + " HP.", 100);
                    } else {
                        print("Attack missed", 100);
                    }

                } else if (s.equals("legs")) {
                    if (attackHit(weaponProb.get(currWeapon))) {
                        enemyHealth -= weaponDamage.get(currWeapon) / 2;
                        enemyProb /= 1.5;
                        print("You striked the bullman's legs! The bullman's attacks have become much more dodgeable.", 350);
                        print("The bullman now has " + enemyHealth + " HP.", 100);
                    } else {
                        print("Attack missed", 100);
                    }

                } else {
                    print("Not a valid body part", 100);
                }
            } else if (s2.equals("status")) {

                currentStatus();
                print("Enemy Status:\n", 500);
                print("EnemyHP: " + enemyHealth, 300);
                print("Enemy Damage: " + enemyDamage, 300);
                print("Enemy Hit Chance: " + enemyProb, 300);
                print("\n ------------------- \n", 200);

            }
            if(enemyHealth <= 0){
                break;
            }
            if(enemyDamage < 0) enemyDamage = 0;
            if(enemyProb < 0) enemyProb = 0;

            print("Bullman's turn to attack", 0);
            print("Thinking . . .\n", 750);


            if(Math.random() > 0.5){ // charge attack
                print("The Bullman readies his horns for attack.", 200);
                print(". . .", 1000);
                if(attackHit(enemyProb)){
                    print("You got hit.", 0);
                    currHP -= enemyDamage;
                    print("", 500);
                    print("You now have " + currHP + " HP.", 150);
                }
                else
                    print("He missed", 0);
            }
            else{ // Confusion, do nothing
                print("The Bullman huffs smoke through his nostrils, yet he continues to stand in place.", 200);
            }




        }
        if(currHP <= 0){ // lose
            print("in defeat, you lay your head down.", 500);
            return false;
        }
        else{ // won against bullman
            print("You have claimed victory. ", 350);
            return true;
        }

    }

    public static double range(double prob){

        if(weaponRange.get(currWeapon).equals("close")){
            return prob/2.7;
        }
        else if(weaponRange.get(currWeapon).equals("medium")){
            return prob/1.3;
        }
        return prob;

    }
    public static boolean attackHit(double prob){
        if(Math.random() < prob)
            return true;
        return false;
    }

    public static void doAbility(){


    }

    public static void ability1(){


    }


}
