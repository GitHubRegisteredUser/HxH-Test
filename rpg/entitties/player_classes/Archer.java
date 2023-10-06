package rpg.entitties.player_classes;

import rpg.entitties.Player;
import rpg.weapon.weapon_types.Bow;
import rpg.skills.archer_skills.ExplosiveArrow;
import rpg.skills.archer_skills.PerfectShot;

public class Archer extends Player {

    public Archer() {
        super("Archer", 7, 7, 105,
                15, 20, new PerfectShot(), new ExplosiveArrow(),
                "Archer", new Bow("Common bow"));
    }

}
