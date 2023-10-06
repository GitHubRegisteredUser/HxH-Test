package rpg.entitties;

import rpg.skills.Skill;

public class Creature {

    private String name;
    private int level = 1;
    private final int BASE_ATTACK;
    private int attack;
    private final int BASE_DEFENSE;
    private int defense;
    private final int BASE_MAX_HEALTH;
    private int maxHealth;
    private int health;
    private final int BASE_MIN_DAMAGE;
    private int minDamage;
    private final int BASE_MAX_DAMAGE;
    private int maxDamage;
    private final Skill MAIN_SKILL;
    private boolean dead = false;

    public Creature(String name, int BASE_ATTACK, int BASE_DEFENSE, int BASE_MAX_HEALTH,
                    int BASE_MIN_DAMAGE, int BASE_MAX_DAMAGE, Skill MAIN_SKILL) {
        this.name = name;
        this.BASE_ATTACK = BASE_ATTACK;
        this.attack = BASE_ATTACK;
        this.BASE_DEFENSE = BASE_DEFENSE;
        this.defense = BASE_DEFENSE;
        this.BASE_MAX_HEALTH = BASE_MAX_HEALTH;
        this.maxHealth = BASE_MAX_HEALTH;
        this.health = maxHealth;
        this.BASE_MIN_DAMAGE = BASE_MIN_DAMAGE;
        this.minDamage = BASE_MIN_DAMAGE;
        this.BASE_MAX_DAMAGE = BASE_MAX_DAMAGE;
        this.maxDamage = BASE_MAX_DAMAGE;
        this.MAIN_SKILL = MAIN_SKILL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBASE_ATTACK() {
        return BASE_ATTACK;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getBASE_DEFENSE() {
        return BASE_DEFENSE;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getBASE_MAX_HEALTH() {
        return BASE_MAX_HEALTH;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getBASE_MIN_DAMAGE() {
        return BASE_MIN_DAMAGE;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getBASE_MAX_DAMAGE() {
        return BASE_MAX_DAMAGE;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public Skill getMAIN_SKILL() {
        return MAIN_SKILL;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void die() {
        System.out.println(name + " died");
    }

}
