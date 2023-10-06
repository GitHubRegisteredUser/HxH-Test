package rpg.entitties.monsters;

import rpg.entitties.Monster;
import rpg.skills.monster_skills.ChargedAttack;

public class Zombie extends Monster {

    public Zombie() {
        super("Zombie", 5, 7, 90,
                12, 15, new ChargedAttack());
    }

}
