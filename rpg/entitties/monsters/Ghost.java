package rpg.entitties.monsters;

import rpg.entitties.Monster;
import rpg.skills.monster_skills.ChargedAttack;

public class Ghost extends Monster {

    public Ghost() {
        super("Ghost", 6, 6, 80,
                13, 17, new ChargedAttack());
    }

}
