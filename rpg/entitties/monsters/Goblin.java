package rpg.entitties.monsters;

import rpg.entitties.Monster;
import rpg.skills.monster_skills.ChargedAttack;

public class Goblin extends Monster {

    public Goblin() {
        super("Goblin", 6, 6, 80,
                13, 17, new ChargedAttack());
    }

}
