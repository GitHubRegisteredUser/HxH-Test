package rpg.entitties.monsters;

import rpg.entitties.Monster;
import rpg.skills.monster_skills.ChargedAttack;

public class Orc extends Monster {

    public Orc() {
        super("Orc", 7, 5, 70,
                14, 19, new ChargedAttack());
    }

}
