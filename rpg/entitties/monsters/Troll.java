package rpg.entitties.monsters;

import rpg.entitties.Monster;
import rpg.skills.monster_skills.ChargedAttack;

public class Troll extends Monster {

    public Troll() {
        super("Troll", 5, 7, 90,
                12, 15, new ChargedAttack());
    }

}
