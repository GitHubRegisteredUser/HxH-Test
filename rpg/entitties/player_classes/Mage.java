package rpg.entitties.player_classes;

import rpg.entitties.Player;
import rpg.weapon.weapon_types.Staff;
import rpg.skills.mage_skills.Fireball;
import rpg.skills.mage_skills.Thunderbolt;

public class Mage extends Player {

    public Mage() {
        super("Mage", 8, 6, 95,
                16, 22, new Fireball(), new Thunderbolt(),
                "Mage", new Staff("Common staff"));
    }

}
