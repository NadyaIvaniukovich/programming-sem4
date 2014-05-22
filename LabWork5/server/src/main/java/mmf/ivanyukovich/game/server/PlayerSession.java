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
 * Created by Hope on 5/22/14.
 */
public class PlayerSession {

    private static ObjectMapper mapper = new ObjectMapper();

    private Server server;

    private Socket playerSocket;
    private BufferedReader in;
    private PrintStream out;

    private Player player;
    private Move lastMove;

    public PlayerSession(Server server) {
        this.server = server;
        this.player = new Player(100);
    }

    public void setLastMove(Move lastMove) {
        this.lastMove = lastMove;
    }

    public Player getPlayer() {
        return player;
    }

    public Move getLastMove() {
        return lastMove;
    }

    public void start() {
        try {
            playerSocket = server.getServerSocket().accept();
            out = new PrintStream(playerSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));

            boolean gameStarted = server.startGame();
            if(!gameStarted){
                out.println(ServerCommand.WAIT_FOR_PLAYER_CONNECT);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasStarted() {
        return playerSocket != null;
    }

    public void makeMove() {
        try {
            out.println(ServerCommand.MAKE_MOVE);
            out.flush();
            lastMove = mapper.readValue(in.readLine(), Move.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMoveResult(MoveResult moveResult) throws IOException {
        out.println(mapper.writeValueAsString(moveResult));
        out.flush();
    }

    public void close() {
        try {
            if(playerSocket != null){
                playerSocket.close();
                playerSocket = null;
            }
            if(in != null){
                in.close();
                in = null;
            }
            if(out != null){
                out.close();
                out = null;
            }
        } catch (IOException e) {}
    }
}
