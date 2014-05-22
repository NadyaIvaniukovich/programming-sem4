package mmf.ivanyukovich.game.model;

import java.util.List;

/**
 * Created by Hope on 5/4/14.
 */
public class Move {
    private Mode mode;
    private List<KickPoint> defencePoints;
    private List<KickPoint> attackPoints;

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public List<KickPoint> getDefencePoints() {
        return defencePoints;
    }

    public void setDefencePoints(List<KickPoint> defencePoints) {
        this.defencePoints = defencePoints;
    }

    public List<KickPoint> getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(List<KickPoint> attackPoints) {
        this.attackPoints = attackPoints;
    }
}
