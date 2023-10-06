package rpg.entitties.player_classes;

import rpg.entitties.Player;
import rpg.weapon.weapon_types.Sword;
import rpg.skills.warrior_skills.CalculatedStrike;
import rpg.skills.warrior_skills.SevereBlow;

public class Warrior extends Player {

    public Warrior() {
        super("Warrior", 6, 8, 115,
                14, 18, new CalculatedStrike(), new SevereBlow(),
                "Warrior", new Sword("Common sword"));
    }

}
