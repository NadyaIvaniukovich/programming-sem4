package mmf.ivanyukovich.game.model;

/**
 * Created by Hope on 5/4/14.
 */
public enum KickPoint {
    HEAD(20), BODY(10), LEGS(5), RIGHT_ARM(7), LEFT_ARM(3);

    private int damage;

    KickPoint(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
