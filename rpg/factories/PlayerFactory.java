package rpg.factories;

import rpg.entitties.Player;
import rpg.entitties.player_classes.Archer;
import rpg.entitties.player_classes.Mage;
import rpg.entitties.player_classes.Warrior;
import rpg.skills.Skill;
import rpg.weapon.Weapon;

public class PlayerFactory {

    public static Player getPlayerClass(String combatStyle) {
        Player player;
        switch (combatStyle) {
            case "1":
                player = new Warrior();
                break;
            case "2":
                player = new Archer();
                break;
            case "3":
                player = new Mage();
                break;
            default:
                player = new Player("Player", 1, 1, 10,
                        1, 2, new Skill(2, "Skill", 1),
                        new Skill(2, "Skill", 1), "Hand-to-hand fighter",
                        new Weapon("Weapon"));
        }
        return player;
    }

}
