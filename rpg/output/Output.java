package rpg.output;

import rpg.engine.Engine;
import rpg.entitties.Creature;
import rpg.entitties.Monster;
import rpg.entitties.Player;
import rpg.skills.Skill;

public class Output {

    public static void printInputLine() {
        System.out.print("-> ");
    }

    public static void enterName() {
        System.out.println("Enter your name (Latin letters, digits, underscore only; max 20 characters)");
        printInputLine();
    }

    public static void selectPlayerClass() {
        System.out.println("""
                Select class:
                1.Warrior
                2.Archer
                3.Mage""");
        printInputLine();
    }

    public static void startGame() {
        System.out.println("Game starts");
        System.out.println("To complete the game you should be at least level 5 and defeat the Boss");
        mark();
    }

    public static void mark() {
        System.out.println("--------------------");
    }

    public static void selectAction(Player p1) {
        if (p1.getLevel() >= 5) {
            System.out.println("""
                    Select action:
                    1.Show stats
                    2.Fight monsters
                    3.Fight Boss""");
            if (p1.getWEAPON().getTier() < 2) {
                System.out.println("4.Upgrade weapon (" + p1.getWEAPON().getUpgradeCost() + " gold)");
            }
            System.out.println("0.Exit game");
        } else {
            System.out.println("""
                    Select action:
                    1.Show stats
                    2.Fight monsters
                    0.Exit game""");
        }
        printInputLine();
    }

    public static void showPlayerStats(Player p1) {
        System.out.println(p1.getName() + " stats: ");
        System.out.println("Class: " + p1.getCOMBAT_STYLE());
        System.out.println("Level: " + p1.getLevel() + " (" + p1.getExperience() +
                "/" + p1.getLevelUpExperience() + " exp)");
        System.out.println("Gold: " + p1.getGold());
        System.out.println("Weapon: " + p1.getWEAPON().getName() + " (" +
                p1.getWEAPON().getBonusDamage() + " bonus damage)");
        System.out.println("Attack: " + p1.getAttack());
        System.out.println("Defense: " + p1.getDefense());
        System.out.println("Health: " + p1.getHealth() + "/" + p1.getMaxHealth());
        System.out.println("Damage: " + p1.getMinDamage() + "-" + p1.getMaxDamage());
        System.out.println("Kill count: " + p1.getKillCount());
        mark();
    }

    public static void startBattle(Monster m1) {
        System.out.println("Your enemy: " + m1.getName());
        System.out.println("Fight!");
        mark();
    }

    public static void printPlayerTurn() {
        System.out.println("Your turn");
        mark();
    }

    public static void makeBattleMove(Player p1) {
        if (p1.getLevel() >= 5) {
            System.out.println("""
                    Select action:
                    1.Show stats
                    2.Attack
                    3.Heal
                    4.Use skill""");
        } else {
            System.out.println("""
                    Select action:
                    1.Show stats
                    2.Attack
                    3.Heal""");
        }
        printInputLine();
    }

    public static void showStats(Monster m1, Player p1) {
        System.out.printf("%-35s", p1.getName() + " stats:");
        System.out.println("| " + m1.getName() + " stats:");
        System.out.printf("%-35s", "Level: " + p1.getLevel());
        System.out.println("| Level: " + m1.getLevel());
        System.out.printf("%-35s", "Attack: " + p1.getAttack());
        System.out.println("| Attack: " + m1.getAttack());
        System.out.printf("%-35s", "Defense: " + p1.getDefense());
        System.out.println("| Defense: " + m1.getDefense());
        System.out.printf("%-35s", "Health: " + p1.getHealth() + "/" + p1.getMaxHealth());
        System.out.println("| Health: " + m1.getHealth() + "/" + m1.getMaxHealth());
        System.out.printf("%-35s", "Damage: " + p1.getMinDamage() + "-" + p1.getMaxDamage());
        System.out.println("| Damage: " + m1.getMinDamage() + "-" + m1.getMaxDamage());
        System.out.printf("%-35s", "Chance to hit " + m1.getName() + ": " + Engine.calculateAccuracy(p1, m1) + "%");
        System.out.println("| Chance to hit " + p1.getName() + ": " + Engine.calculateAccuracy(m1, p1) + "%");
        mark();
    }

    public static void attack(Creature c1, Creature c2) {
        System.out.println(c1.getName() + " attacks " + c2.getName());
        int dices = Engine.calculateModifier(c1, c2);
        if (Engine.throwDices(dices)) {
            System.out.println("Successful attack");
            int damage = Engine.calculateAttackDamage(c1, c2);
            System.out.println(c2.getName() + " received " + damage + " damage");
            Engine.decreaseHealth(c2, damage);
            System.out.println(c2.getName() + " health: " + c2.getHealth() + "/" + c2.getMaxHealth());
        } else {
            System.out.println("Attack failed");
        }
    }

    public static void healPlayer(Player p1) {
        int heal = Engine.calculateHeal(p1);
        System.out.println("You recovered " + heal + " health");
        p1.heal(heal);
        System.out.println("Your health: " + p1.getHealth() + "/" + p1.getMaxHealth());
        Engine.decreaseHealCount(p1);
        System.out.println("You can heal " + p1.getHealCount() + " more times");
    }

    public static void denyHealing() {
        System.out.println("You can't heal anymore!");
        mark();
    }

    public static void selectSkill(Player p1) {
        if (p1.getLevel() == 10) {
            System.out.println("Select skill to use:");
            System.out.println("1." + p1.getMAIN_SKILL().getNAME());
            System.out.println("2." + p1.getSECOND_SKILL().getNAME());
        } else {
            System.out.println("Select skill to use:");
            System.out.println("1." + p1.getMAIN_SKILL().getNAME());
        }
        printInputLine();
    }

    public static void useSkill(Creature c1, Creature c2, Skill s1) {
        System.out.println(c1.getName() + " uses " + s1.getNAME());
        int dices = Engine.calculateModifier(c1, c2);
        if (Engine.throwDices(dices)) {
            System.out.println("Successful attack");
            int damage = Engine.calculateSkillDamage(c1, c2, s1);
            System.out.println(c2.getName() + " received " + damage + " damage");
            Engine.decreaseHealth(c2, damage);
            System.out.println(c2.getName() + " health: " + c2.getHealth() + "/" + c2.getMaxHealth());
        } else {
            System.out.println("Attack failed");
        }
    }

    public static void denySkillAccess() {
        System.out.println("All your skills are recharging");
        mark();
    }

    public static void denySkillUsage(Skill s1) {
        System.out.println(s1.getNAME() + " is recharging, " + s1.getCoolDown() + " turn(s) left");
        mark();
    }

    public static void printEnemyTurn() {
        System.out.println("Enemy turn");
        mark();
    }

    public static void gainExperience(Player p1) {
        int experience = Engine.calculateExperience(p1);
        System.out.println("You gained " + experience + " experience");
        Engine.increaseExperience(p1, experience);
    }

    public static void gainGold(Player p1) {
        int gold = Engine.calculateGold(p1);
        System.out.println("You gained " + gold + " gold");
        Engine.increaseGold(p1, gold);
        mark();
    }

    public static void levelUp(Player p1) {
        System.out.println("Level up!");
        System.out.println("You are level " + p1.getLevel() + " now");
        if (p1.getLevel() == 5) {
            System.out.println("You unlocked new skill!");
            System.out.println("You unlocked weapon upgrades!");
        }
        if (p1.getLevel() == 10) {
            System.out.println("You unlocked new skill!");
            System.out.println("You reached max level");
        }
        mark();
    }

    public static void upgradeWeapon(Player p1) {
        System.out.println("Your weapon was upgraded!");
        Engine.increaseWeaponTier(p1);
        if (p1.getWEAPON().getTier() == 2) {
            System.out.println("Your weapon reached its upgrade limit");
        }
        mark();
    }

    public static void declineUpgrade() {
        System.out.println("Not enough gold for upgrade");
    }

    public static void declineInput() {
        System.out.println("Incorrect input, try again");
        mark();
    }

    public static void endGame() {
        System.out.println("Game over");
    }

    public static void completeGame() {
        System.out.println("Congratulations! You completed the game!");
    }

}
