package mmf.ivanyukovich.game.model;

/**
 * Created by Hope on 5/4/14.
 */
public class Player {
    private int healthPoints;

    public Player() {
    }

    public Player(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
}
