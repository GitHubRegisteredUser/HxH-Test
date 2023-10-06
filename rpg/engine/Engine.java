package rpg.engine;

import rpg.actions.Actions;
import rpg.entitties.Creature;
import rpg.entitties.Monster;
import rpg.entitties.Player;
import rpg.output.Output;
import rpg.skills.Skill;
import rpg.weapon.Weapon;

import java.util.Random;

public class Engine {

    private static final Random RANDOM = new Random();

    // Вычисляет количество кубиков для броска
    public static int calculateModifier(Creature c1, Creature c2) {
        return Math.max(c1.getAttack() - c2.getDefense() + 1, 1);
    }

    // Определяет успех атаки броском кубиков
    public static boolean throwDices(int dices) {
        boolean isSuccessful = false;
        while (dices > 0) {
            int result = RANDOM.nextInt(6) + 1;
            if (result >= 5) {
                isSuccessful = true;
                break;
            }
            dices--;
        }
        return isSuccessful;
    }

    // Вычисляет вероятность попадания по противнику
    public static int calculateAccuracy(Creature c1, Creature c2) {
        return (int) Math.round(((1 - Math.pow((4.0 / 6.0), calculateModifier(c1, c2))) * 100));
    }

    // Вычисляет урон обычной атаки существа
    public static int calculateAttackDamage(Creature c1, Creature c2) {
        int damage = RANDOM.nextInt(c1.getMaxDamage() - c1.getMinDamage() + 1) + c1.getMinDamage();
        return Math.min(damage, c2.getHealth());
    }

    // Вычисляет урон навыка существа
    public static int calculateSkillDamage(Creature c1, Creature c2, Skill s1) {
        int damage = RANDOM.nextInt(c1.getMaxDamage() - c1.getMinDamage() + 1) + c1.getMinDamage();
        damage = (int) (damage * (1 + s1.getTIER() * 0.25));
        return Math.min(damage, c2.getHealth());
    }

    public static void decreaseHealth(Creature c1, int points) {
        c1.setHealth(c1.getHealth() - points);
    }

    public static void decreaseCoolDown(Skill s1) {
        s1.setCoolDown(Math.max(0, s1.getCoolDown() - 1));
    }

    // Вычисляет количество восстанавливаемых игроком очков здоровья
    public static int calculateHeal(Player p1) {
        int heal = (int) (p1.getMaxHealth() * 0.3);
        return Math.min(heal, p1.getMaxHealth() - p1.getHealth());
    }

    public static void decreaseHealCount(Player p1) {
        p1.setHealCount(p1.getHealCount() - 1);
    }

    public static void increaseKillCount(Player p1) {
        p1.setKillCount(p1.getKillCount() + 1);
    }

    // Вычисляет количество получаемых игроком очков опыта
    public static int calculateExperience(Player p1) {
        return 10 + p1.getLevel() * 2;
    }

    public static void increaseExperience(Player p1, int points) {
        p1.setExperience(p1.getExperience() + points);
    }

    // Увеличивает количество очков опыта, необходимых игроку для достижения следующего уровня
    public static void increaseLevelUpExperience(Player p1) {
        p1.setLevelUpExperience((int) (p1.getLevelUpExperience() * 1.1 + p1.getLevel() * 50));
    }

    // Вычисляет количество получаемого игроком золота
    public static int calculateGold(Player p1) {
        int gold = RANDOM.nextInt(11) + 5;
        return gold + p1.getLevel() * 2;
    }

    public static void increaseGold(Player p1, int points) {
        p1.setGold(p1.getGold() + points);
    }

    // Увеличивает статы существа при получении им нового уровня
    public static void increaseStats(Creature c1, int levels) {
        c1.setLevel(c1.getLevel() + levels);
        c1.setAttack(Math.min(30, (int) (c1.getBASE_ATTACK() *
                (1 + (double) (c1.getLevel() - 1) / 10)) + c1.getLevel() - 1));
        c1.setDefense(Math.min(30, (int) (c1.getBASE_DEFENSE() *
                (1 + (double) (c1.getLevel() - 1) / 10)) + c1.getLevel() - 1));
        c1.setMaxHealth((int) (c1.getBASE_MAX_HEALTH() *
                (1 + (double) (c1.getLevel() - 1) / 10)) + (c1.getLevel() - 1) * 5);
        c1.setHealth(c1.getMaxHealth());
        c1.setMinDamage((int) (c1.getBASE_MIN_DAMAGE() *
                (1 + (double) (c1.getLevel() - 1) / 10)) + c1.getLevel() - 1);
        c1.setMaxDamage((int) (c1.getBASE_MAX_DAMAGE() *
                (1 + (double) (c1.getLevel() - 1) / 10)) + c1.getLevel() - 1);
    }

    // Увеличивает статы оружия при успешном его улучшении
    public static void increaseWeaponTier(Player p1) {
        Weapon weapon = p1.getWEAPON();
        weapon.setTier(weapon.getTier() + 1);
        if (weapon.getTier() == 1) {
            p1.setGold(p1.getGold() - weapon.getUpgradeCost());
            weapon.setBonusDamage(4);
            weapon.setUpgradeCost(weapon.getUpgradeCost() + 1900);
            weapon.setName(weapon.getName().replace("Common", "Magic"));
        } else if (weapon.getTier() == 2) {
            p1.setGold(p1.getGold() - weapon.getUpgradeCost());
            weapon.setBonusDamage(8);
            weapon.setUpgradeCost(999999);
            weapon.setName(weapon.getName().replace("Magic", "Unique"));
        }
    }

    // Боевая механика
    public static void battle(Player p1, Monster m1) {
        Skill mSkill = m1.getMAIN_SKILL();
        increaseStats(m1, p1.getLevel() - 1);
        Output.startBattle(m1);
        while (p1.getHealth() > 0) {
            Output.printPlayerTurn();
            Output.makeBattleMove(p1);
            Actions.executeBattleMove(p1, m1);
            Output.mark();
            if (!isCreatureAlive(m1)) {
                break;
            }
            Output.printEnemyTurn();
            makeEnemyBattleMove(m1, p1, mSkill);
            Output.mark();
        }
    }

    public static void rechargeSkills(Skill s1, Skill s2) {
        decreaseCoolDown(s1);
        decreaseCoolDown(s2);
    }

    // Проверяет, готов ли хотя бы один навык к использованию
    public static boolean isAnySkillReady(Player p1) {
        if (p1.getLevel() == 10) {
            return p1.getMAIN_SKILL().getCoolDown() == 0 ||
                    p1.getSECOND_SKILL().getCoolDown() == 0;
        } else return p1.getMAIN_SKILL().getCoolDown() == 0;
    }

    // Проверяет, готов ли выбранный навык к использованию
    public static boolean isSkillRecharging(Player p1, Monster m1, Skill s1) {
        if (s1.getCoolDown() == 0) {
            s1.setCoolDown(s1.getBASE_COOL_DOWN());
            Output.useSkill(p1, m1, s1);
            return false;
        }
        return true;
    }

    // Логика хода противника
    public static void makeEnemyBattleMove(Monster m1, Player p1, Skill s1) {
        if (m1.getLevel() >= 5 && s1.getCoolDown() == 0) {
            Output.useSkill(m1, p1, s1);
            s1.setCoolDown(s1.getBASE_COOL_DOWN());
        } else {
            Output.attack(m1, p1);
            decreaseCoolDown(s1);
        }
    }

    // Проверяет, живо ли существо
    public static boolean isCreatureAlive(Creature c1) {
        if (c1.getHealth() == 0) {
            c1.die();
            c1.setDead(true);
            Output.mark();
            return false;
        }
        return true;
    }

    // Послебоевая механика
    public static void doAfterBattle(Player p1) {
        p1.getMAIN_SKILL().setCoolDown(0);
        p1.getSECOND_SKILL().setCoolDown(0);
        p1.setHealCount(4);
        increaseKillCount(p1);
        if (p1.getLevel() < 10) {
            Output.gainExperience(p1);
        }
        Output.gainGold(p1);
        checkLevelUp(p1);
    }

    // Повышает уровень игрока при накоплении им необходимого количества очков опыта
    public static void checkLevelUp(Player p1) {
        while (p1.getExperience() >= p1.getLevelUpExperience() && p1.getLevel() < 10) {
            increaseStats(p1, 1);
            Output.levelUp(p1);
            if (p1.getLevel() == 10) {
                p1.setExperience(p1.getLevelUpExperience());
            } else {
                increaseLevelUpExperience(p1);
            }
        }
    }

}
