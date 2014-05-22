package mmf.ivanyukovich.game.server;

/**
 * Created by Hope on 5/22/14.
 */
public class MoveMaker implements Runnable {

    private PlayerSession session;

    public MoveMaker(PlayerSession session) {
        this.session = session;
    }

    @Override
    public void run() {
        session.makeMove();
    }
}
