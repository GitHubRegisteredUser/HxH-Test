package rpg.entitties;

import rpg.skills.Skill;

public class Monster extends Creature {

    public Monster(String name, int BASE_ATTACK, int BASE_DEFENSE, int BASE_MAX_HEALTH,
                   int BASE_MIN_DAMAGE, int BASE_MAX_DAMAGE, Skill MAIN_SKILL) {
        super(name, BASE_ATTACK, BASE_DEFENSE, BASE_MAX_HEALTH, BASE_MIN_DAMAGE, BASE_MAX_DAMAGE, MAIN_SKILL);
    }

}
