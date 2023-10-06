package rpg.entitties;

import rpg.skills.Skill;
import rpg.weapon.Weapon;

public class Player extends Creature {

    private int experience = 0;
    private int levelUpExperience = 75;
    private int gold = 0;
    private int healCount = 4;
    private int killCount = 0;
    private final Skill SECOND_SKILL;
    private final String COMBAT_STYLE;
    private final Weapon WEAPON;

    public Player(String name, int BASE_ATTACK, int BASE_DEFENSE, int BASE_MAX_HEALTH,
                  int BASE_MIN_DAMAGE, int BASE_MAX_DAMAGE, Skill MAIN_SKILL, Skill SECOND_SKILL,
                  String COMBAT_STYLE, Weapon WEAPON) {
        super(name, BASE_ATTACK, BASE_DEFENSE, BASE_MAX_HEALTH, BASE_MIN_DAMAGE, BASE_MAX_DAMAGE, MAIN_SKILL);
        this.SECOND_SKILL = SECOND_SKILL;
        this.COMBAT_STYLE = COMBAT_STYLE;
        this.WEAPON = WEAPON;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExperience() {
        return experience;
    }

    public int getLevelUpExperience() {
        return levelUpExperience;
    }

    public void setLevelUpExperience(int levelUpExperience) {
        this.levelUpExperience = levelUpExperience;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHealCount() {
        return healCount;
    }

    public void setHealCount(int healCount) {
        this.healCount = healCount;
    }

    public int getKillCount() {
        return killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    public Skill getSECOND_SKILL() {
        return SECOND_SKILL;
    }

    public String getCOMBAT_STYLE() {
        return COMBAT_STYLE;
    }

    public Weapon getWEAPON() {
        return WEAPON;
    }

    public void heal(int heal) {
        setHealth(getHealth() + heal);
    }

    @Override
    public int getMinDamage() {
        return super.getMinDamage() + getWEAPON().getBonusDamage();
    }

    @Override
    public int getMaxDamage() {
        return super.getMaxDamage() + getWEAPON().getBonusDamage();
    }

}
