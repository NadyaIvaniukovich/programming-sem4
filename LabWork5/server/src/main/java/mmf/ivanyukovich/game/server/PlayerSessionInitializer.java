package mmf.ivanyukovich.game.server;

import mmf.ivanyukovich.game.model.Move;
import mmf.ivanyukovich.game.model.MoveResult;
import mmf.ivanyukovich.game.model.Player;
import mmf.ivanyukovich.game.model.ServerCommand;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Hope on 5/4/14.
 */
public class PlayerSessionInitializer implements Runnable {



    private PlayerSession session;

    public PlayerSessionInitializer(PlayerSession session) {
        this.session = session;
    }

//

    @Override
    public void run() {
        session.start();
    }

}
