package rpg.factories;

import rpg.entitties.Monster;
import rpg.entitties.monsters.*;
import rpg.skills.monster_skills.ChargedAttack;

public class MonsterFactory {

    public static Monster getMonster(int enemy) {
        Monster monster;
        switch (enemy) {
            case 0:
                monster = new Skeleton();
                break;
            case 1:
                monster = new Ghost();
                break;
            case 2:
                monster = new Zombie();
                break;
            case 3:
                monster = new Orc();
                break;
            case 4:
                monster = new Goblin();
                break;
            case 5:
                monster = new Troll();
                break;
            default:
                monster = new Monster("Unknown monster", 1, 1, 10,
                        1, 2, new ChargedAttack());
        }
        return monster;
    }

}
