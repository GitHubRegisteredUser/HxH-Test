package rpg;

import rpg.actions.Actions;
import rpg.entitties.Player;
import rpg.output.Output;

public class Game {

    private static boolean GameCompleted = false;

    public static void main(String[] args) {
        Output.enterName();
        String name = Actions.executeNameInput();
        Output.selectPlayerClass();
        Player player = Actions.selectCombatStyle();
        player.setName(name);
        Output.startGame();
        while (!player.isDead() && !isGameCompleted()) {
            Output.selectAction(player);
            Actions.executeAction(player);
        }
        if (player.isDead()) {
            Output.endGame();
        } else {
            Output.completeGame();
        }
    }

    public static boolean isGameCompleted() {
        return GameCompleted;
    }

    public static void setGameCompleted(boolean gameCompleted) {
        GameCompleted = gameCompleted;
    }

}
