package mmf.ivanyukovich.game.client;

import mmf.ivanyukovich.game.io.MoveReader;
import mmf.ivanyukovich.game.io.MoveResultWriter;
import mmf.ivanyukovich.game.model.GameStatus;
import mmf.ivanyukovich.game.model.Move;
import mmf.ivanyukovich.game.model.MoveResult;
import mmf.ivanyukovich.game.model.ServerCommand;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Hope on 5/6/14.
 */
public class Client {
    public static void main(String[] args) {

        String serverIP = "localhost";
        if(args.length >= 1){
            serverIP = args[0];
        }

        MoveReader moveReader = new MoveReader();
        ObjectMapper mapper = new ObjectMapper();
        MoveResultWriter moveResultWriter = new MoveResultWriter();

        boolean gameOver = false;

        try(Socket socket = new Socket(serverIP, 8030);
            Scanner scanner = new Scanner(System.in);
            PrintStream out = new PrintStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            while (!gameOver){
                ServerCommand command = ServerCommand.valueOf(read(in));
                if (command == ServerCommand.WAIT_FOR_PLAYER_CONNECT) {
                    System.out.println("Waiting for another player to connect.");
                } else if (command == ServerCommand.MAKE_MOVE) {
                    Move move = moveReader.read(scanner, System.out);
                    out.println(mapper.writeValueAsString(move));
                    out.flush();

                    MoveResult moveResult = mapper.readValue(read(in), MoveResult.class);

                    moveResultWriter.write(moveResult,System.out);
                    if(moveResult.getStatus() != GameStatus.IN_PROGRESS){
                        gameOver = true;
                        System.out.println(moveResult.getStatus());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String read(BufferedReader in) throws Exception {
        String message = in.readLine();
        System.out.println("> " + message);
        return message;
    }
}
