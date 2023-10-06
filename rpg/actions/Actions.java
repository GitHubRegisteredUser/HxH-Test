package rpg.actions;

import rpg.Game;
import rpg.engine.Engine;
import rpg.entitties.Monster;
import rpg.entitties.Player;
import rpg.entitties.monsters.Boss;
import rpg.factories.MonsterFactory;
import rpg.factories.PlayerFactory;
import rpg.output.Output;
import rpg.skills.Skill;

import java.util.Random;
import java.util.Scanner;

public class Actions {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String executeNameInput() {
        String name;
        name = SCANNER.nextLine();
        while (!name.matches("\\w{1,20}")) {
            Output.declineInput();
            Output.enterName();
            name = SCANNER.nextLine();
        }
        return name;
    }

    public static Player selectCombatStyle() {
        String combatStyle;
        combatStyle = SCANNER.next();
        while (!combatStyle.matches("[1-3]")) {
            Output.declineInput();
            Output.selectPlayerClass();
            combatStyle = SCANNER.next();
        }
        return PlayerFactory.getPlayerClass(combatStyle);
    }

    public static void executeAction(Player p1) {
        Random random = new Random();
        Monster m1;
        String action;
        do {
            action = SCANNER.next();
            switch (action) {
                case "1":
                    Output.showPlayerStats(p1);
                    break;
                case "2":
                    int enemy = random.nextInt(6);
                    m1 = MonsterFactory.getMonster(enemy);
                    Engine.battle(p1, m1);
                    if (Engine.isCreatureAlive(p1)) {
                        Engine.doAfterBattle(p1);
                    }
                    break;
                case "3":
                    if (p1.getLevel() >= 5) {
                        m1 = new Boss();
                        Engine.battle(p1, m1);
                        if (Engine.isCreatureAlive(p1)) {
                            Game.setGameCompleted(true);
                        }
                    } else {
                        Output.declineInput();
                        Output.selectAction(p1);
                        action = "";
                    }
                    break;
                case "4":
                    if (p1.getLevel() >= 5 && p1.getWEAPON().getTier() < 2) {
                        if (p1.getGold() >= p1.getWEAPON().getUpgradeCost()) {
                            Output.upgradeWeapon(p1);
                        } else {
                            Output.declineUpgrade();
                        }
                    } else {
                        Output.declineInput();
                        Output.selectAction(p1);
                        action = "";
                    }
                    break;
                case "0":
                    System.exit(0);
                default:
                    Output.declineInput();
                    Output.selectAction(p1);
            }
        } while (!action.matches("[1-4]"));
    }

    public static void executeBattleMove(Player p1, Monster m1) {
        Skill pSkill1 = p1.getMAIN_SKILL();
        Skill pSkill2 = p1.getSECOND_SKILL();
        String battleMove;
        do {
            battleMove = SCANNER.next();
            switch (battleMove) {
                case "1":
                    Output.showStats(m1, p1);
                    Output.makeBattleMove(p1);
                    battleMove = "";
                    break;
                case "2":
                    Output.attack(p1, m1);
                    Engine.rechargeSkills(pSkill1, pSkill2);
                    break;
                case "3":
                    if (p1.getHealCount() > 0) {
                        Output.healPlayer(p1);
                        Engine.rechargeSkills(pSkill1, pSkill2);
                    } else {
                        Output.denyHealing();
                        Output.makeBattleMove(p1);
                        battleMove = "";
                    }
                    break;
                case "4":
                    if (p1.getLevel() >= 5) {
                        if (Engine.isAnySkillReady(p1)) {
                            Output.selectSkill(p1);
                            executeSkill(p1, m1, pSkill1, pSkill2);
                        } else {
                            Output.denySkillAccess();
                            Output.makeBattleMove(p1);
                            battleMove = "";
                        }
                    } else {
                        Output.declineInput();
                        Output.makeBattleMove(p1);
                        battleMove = "";
                    }
                    break;
                default:
                    Output.declineInput();
                    Output.makeBattleMove(p1);
            }
        } while (!battleMove.matches("[1-4]"));
    }

    public static void executeSkill(Player p1, Monster m1, Skill s1, Skill s2) {
        String skill;
        do {
            skill = SCANNER.next();
            switch (skill) {
                case "1":
                    if (Engine.isSkillRecharging(p1, m1, s1)) {
                        Output.denySkillUsage(s1);
                        Output.selectSkill(p1);
                        skill = "";
                    } else {
                        Engine.decreaseCoolDown(s2);
                    }
                    break;
                case "2":
                    if (p1.getLevel() == 10) {
                        if (Engine.isSkillRecharging(p1, m1, s2)) {
                            Output.denySkillUsage(s2);
                            Output.selectSkill(p1);
                            skill = "";
                        } else {
                            Engine.decreaseCoolDown(s1);
                        }
                    } else {
                        Output.declineInput();
                        Output.selectSkill(p1);
                        skill = "";
                    }
                    break;
                default:
                    Output.declineInput();
                    Output.selectSkill(p1);
            }
        } while (!skill.matches("[1-2]"));
    }

}
