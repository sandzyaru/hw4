package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 3000;
    public static int bossDamage = 80;
    public static String bossDefence = "";

    public static int[] heroesHealth = {270, 260, 250, 450, 1500, 250, 230, 400};
    public static int[] heroesDamage = {15, 20, 25, 0, 1, 10, 15, 40};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky", "Berserk", "Thor"};
    public static int round_number = 0;

    public static void main(String[] args) {
        printStatistics(); // До начала игры вывод статистики
        while (!isGameFinished()) {
            round();
        }
    }

    public static void changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); //0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose " + bossDefence);
    }

    public static void round() {
        round_number++;
        changeBossDefence();
        bossHits();
        heroesHit();
        medicHill();
        golem();
        lucky();
        berserk();
        thor();
        printStatistics();
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; //2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void medicHill() {
        int hill = 30;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i==3){
                continue;
            }
            if (heroesHealth[i] > 0 & heroesHealth[i] < 100 & heroesHealth[3] > 0) {
                heroesHealth[i] = heroesHealth[i] + hill;
                System.out.println("Medics Hill: " + hill+" "+heroesAttackType[i]);
                break;
            }


        }

    }

 /*   public static void golem() {
        int shield = bossDamage / 5;
        int aliveHero = 0;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i==4) {
                continue;
            }

            if (heroesHealth[4] > 0 ) {
               aliveHero++;
                heroesHealth[i]+=shield;}
                else if (heroesHealth[i]>0) {
                    heroesHealth[4]-=shield*aliveHero;
                    System.out.println("Golems shield: " + shield);
                }
        }

    }*/
 public static void golem() {
     int shield = bossDamage / 5;
     int aliveHero = 0;
     if (heroesHealth[4] > 0 ) {
         for (int i = 0; i < heroesHealth.length; i++) {
             if (i==4) {
                 continue;
             }
             else if (heroesHealth[i] > 0 ) {
                 aliveHero++;
                 heroesHealth[i]+=shield;}
         }
     }

     heroesHealth[4]-=shield*aliveHero;
     System.out.println("Golems shield: " + shield*aliveHero);

 }

    public static void lucky() {

     if (Math.random() < 0.5 && heroesHealth[5] > 0) {
            heroesHealth[5] += bossDamage;
            System.out.println("Luckys evasion!!!");
        } else {
            heroesHealth[5] =heroesHealth[5];
        }
    }

    public static void berserk() {
     Random random=new Random();
     int block = random.nextInt(20,80);

        for (int i = 0; i < heroesHealth.length; i++) {


        if (heroesHealth[6] > 0) {
            heroesHealth[6] +=block;
            System.out.println("Berserks repulse: " + block);
            break;
        }
        }

    }


    public static void thor() {
        Random random=new Random();

            for (int i = 0; i < heroesHealth.length; i++) {
                if (Math.random() < 0.5 && heroesHealth[7] > 0) {
                    bossDamage -= bossDamage;
                    System.out.println("Thors stun");
                    break;
                } else {
                    bossDamage = 80;
                    break;
                }
            }
 }




    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {

            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }


    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println(round_number + " ROUND _______________");
        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " (" + heroesDamage[i] + ")");
        }
        System.out.println("_______________");
    }
}
