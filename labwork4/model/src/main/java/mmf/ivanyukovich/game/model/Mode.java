package mmf.ivanyukovich.game.model;

/**
 * Created by Hope on 5/4/14.
 */
public enum Mode {
    DEFENCE(2,1), ATTACK(1,2);

    private int defencePointNumber;
    private int attackPointNumber;

    Mode(int defencePointNumber, int attackPointNumber) {
        this.defencePointNumber = defencePointNumber;
        this.attackPointNumber = attackPointNumber;
    }

    public int getDefencePointNumber() {
        return defencePointNumber;
    }

    public void setDefencePointNumber(int defencePointNumber) {
        this.defencePointNumber = defencePointNumber;
    }

    public int getAttackPointNumber() {
        return attackPointNumber;
    }

    public void setAttackPointNumber(int attackPointNumber) {
        this.attackPointNumber = attackPointNumber;
    }
}
