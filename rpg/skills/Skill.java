package rpg.skills;

public class Skill {

    private final int BASE_COOL_DOWN;
    private int coolDown = 0;
    private final String NAME;
    private final int TIER;

    public Skill(int BASE_COOL_DOWN, String NAME, int TIER) {
        this.BASE_COOL_DOWN = BASE_COOL_DOWN;
        this.NAME = NAME;
        this.TIER = TIER;
    }

    public int getBASE_COOL_DOWN() {
        return BASE_COOL_DOWN;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public String getNAME() {
        return NAME;
    }

    public int getTIER() {
        return TIER;
    }

}
