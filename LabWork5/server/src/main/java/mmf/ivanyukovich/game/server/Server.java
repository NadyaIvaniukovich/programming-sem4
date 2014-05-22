package mmf.ivanyukovich.game.server;

import mmf.ivanyukovich.game.model.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Hope on 5/4/14.
 */
public class Server implements AutoCloseable {
    private ServerSocket serverSocket;
    private PlayerSession firstPlayerSession;
    private PlayerSession secondPlayerSession;

    public Server() throws IOException {
        serverSocket = new ServerSocket(8030);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void init(){
        firstPlayerSession = new PlayerSession(this);
        secondPlayerSession = new PlayerSession(this);

        new Thread(new PlayerSessionInitializer(firstPlayerSession)).start();
        new Thread(new PlayerSessionInitializer(secondPlayerSession)).start();
        System.out.println("Waiting for players");
    }

    public boolean startGame() throws IOException {
        if(!firstPlayerSession.hasStarted() || !secondPlayerSession.hasStarted()){
            System.out.println("Player 1 connected");
            return false;
        }
        System.out.println("Player 2 connected, starting game");

        boolean gameOver = false;
        while (!gameOver){
            new Thread(new MoveMaker(firstPlayerSession)).start();
            new Thread(new MoveMaker(secondPlayerSession)).start();

            System.out.println("Waiting for player moves");
            while (firstPlayerSession.getLastMove() == null || secondPlayerSession.getLastMove() == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }

            MoveResult firstPlayerMoveResult = getMoveResult(firstPlayerSession.getPlayer(), firstPlayerSession.getLastMove(), secondPlayerSession.getPlayer(), secondPlayerSession.getLastMove());
            MoveResult secondPlayerMoveResult = getMoveResult(secondPlayerSession.getPlayer(), secondPlayerSession.getLastMove(), firstPlayerSession.getPlayer(), firstPlayerSession.getLastMove());

            int firstPlayerHealth = firstPlayerSession.getPlayer().getHealthPoints() + secondPlayerMoveResult.getMyTotalDamage();
            int secondPlayerHealth = secondPlayerSession.getPlayer().getHealthPoints() + firstPlayerMoveResult.getMyTotalDamage();
            firstPlayerSession.getPlayer().setHealthPoints(firstPlayerHealth);
            secondPlayerSession.getPlayer().setHealthPoints(secondPlayerHealth);

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
                System.out.println("GAME OVER");
                gameOver = true;
            } else {
                firstPlayerMoveResult.setStatus(GameStatus.IN_PROGRESS);
                secondPlayerMoveResult.setStatus(GameStatus.IN_PROGRESS);
            }

            firstPlayerSession.setLastMove(null);
            secondPlayerSession.setLastMove(null);

            firstPlayerSession.sendMoveResult(firstPlayerMoveResult);
            secondPlayerSession.sendMoveResult(secondPlayerMoveResult);
        }

        firstPlayerSession.close();
        secondPlayerSession.close();

        init();

        return true;
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
        if(firstPlayerSession != null){
            firstPlayerSession.close();
        }
        if(secondPlayerSession != null){
            secondPlayerSession.close();
        }
        if(serverSocket != null){
            serverSocket.close();
        }
    }

    public static void main(String[] args) throws Exception {
        try(Server server = new Server()) {
            server.init();

            Scanner scanner = new Scanner(System.in);
            while (true){
                String command = scanner.nextLine();
                if (command.equals("exit")){
                    break;
                }
            }
        }
    }
}
