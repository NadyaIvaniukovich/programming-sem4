package server;

import mmf.ivanyukovich.game.io.MoveReader;
import mmf.ivanyukovich.game.io.MoveResultWriter;
import mmf.ivanyukovich.game.model.*;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Hope on 5/4/14.
 */
public class Server implements AutoCloseable {
    private ServerSocket serverSocket;
    private Player firstPlayer;
    private Player secondPlayer;
    private Socket socket;
    private BufferedReader in;
    private PrintStream out;
    private MoveReader moveReader;
    private ObjectMapper mapper;
    private MoveResultWriter moveResultWriter;
    private boolean gameOver;

    public Server() throws IOException {
        firstPlayer = new Player(100);
        secondPlayer = new Player(100);

        moveReader = new MoveReader();
        moveResultWriter = new MoveResultWriter();

        mapper = new ObjectMapper();

        serverSocket = new ServerSocket(8030);
        socket = serverSocket.accept();
        out = new PrintStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    public void startGame() throws IOException {

        Scanner scanner = new Scanner(System.in);

        while (!gameOver){
            Move firstPlayerMove = moveReader.read(scanner, System.out);
            String message = in.readLine();
            Move secondPlayerMove = mapper.readValue(message, Move.class);

            MoveResult firstPlayerMoveResult = getMoveResult(firstPlayer, firstPlayerMove, secondPlayer, secondPlayerMove);
            MoveResult secondPlayerMoveResult = getMoveResult(secondPlayer,secondPlayerMove, firstPlayer,firstPlayerMove);

            int firstPlayerHealth = firstPlayer.getHealthPoints()+ secondPlayerMoveResult.getMyTotalDamage();
            int secondPlayerHealth = secondPlayer.getHealthPoints()+ firstPlayerMoveResult.getMyTotalDamage();
            firstPlayer.setHealthPoints(firstPlayerHealth);
            secondPlayer.setHealthPoints(secondPlayerHealth);

            moveResultWriter.write(firstPlayerMoveResult, System.out);

            if (firstPlayerHealth <= 0 || secondPlayerHealth <= 0){
                if(firstPlayerHealth > secondPlayerHealth){
                    firstPlayerMoveResult.setStatus(GameStatus.WIN);
                    secondPlayerMoveResult.setStatus(GameStatus.LOSS);
                } else if(firstPlayerHealth < secondPlayerHealth){
                    firstPlayerMoveResult.setStatus(GameStatus.LOSS);
                    secondPlayerMoveResult.setStatus(GameStatus.WIN);
                } else {
                    firstPlayerMoveResult.setStatus(GameStatus.FRIENDSHIP_WON);
                    secondPlayerMoveResult.setStatus(GameStatus.FRIENDSHIP_WON);
                }
                gameOver = true;
                System.out.println(firstPlayerMoveResult.getStatus());
            } else {
                firstPlayerMoveResult.setStatus(GameStatus.IN_PROGRESS);
                secondPlayerMoveResult.setStatus(GameStatus.IN_PROGRESS);
            }

            message = mapper.writeValueAsString(secondPlayerMoveResult);
            out.println(message);
            out.flush();
        }

    }

    public MoveResult getMoveResult(Player me, Move myMove, Player opponent, Move opponentMove){
        MoveResult moveResult = new MoveResult();
        moveResult.setMe(me);
        moveResult.setOpponent(opponent);
        moveResult.setOpponentMove(opponentMove);
        moveResult.setMyAttackResult(getAttackResult(myMove, opponentMove));
        return moveResult;
    }

    public Map<KickPoint,Integer> getAttackResult(Move myMove, Move opponentMove){
        Map<KickPoint,Integer> attackResult = new HashMap<>();
        for (KickPoint attackPoint: myMove.getAttackPoints()){
            int kickResult = -attackPoint.getDamage();
            if(opponentMove.getDefencePoints().contains(attackPoint)){
                kickResult = 0;//attack was blocked
            }
            attackResult.put(attackPoint, kickResult);
        }
        return attackResult;
    }

    @Override
    public void close() throws Exception {
        if(serverSocket != null){
            serverSocket.close();
        }
        if(socket != null){
            socket.close();
        }
        if(in != null){
            in.close();
        }
        if(out != null){
            out.close();
        }
    }

    public static void main(String[] args) {
        try(Server server = new Server()) {
            server.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
