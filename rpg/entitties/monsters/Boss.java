package rpg.entitties.monsters;

import rpg.entitties.Monster;
import rpg.skills.monster_skills.DemonicSlash;

public class Boss extends Monster {

    public Boss() {
        super("Ancient demon", 7, 7, 115,
                16, 22, new DemonicSlash());
    }

}
