package rpg.entitties.monsters;

import rpg.entitties.Monster;
import rpg.skills.monster_skills.ChargedAttack;

public class Skeleton extends Monster {

    public Skeleton() {
        super("Skeleton", 7, 5, 70,
                14, 19, new ChargedAttack());
    }

}
