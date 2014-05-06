package mmf.ivanyukovich.game.client;

import mmf.ivanyukovich.game.io.MoveReader;
import mmf.ivanyukovich.game.io.MoveResultWriter;
import mmf.ivanyukovich.game.model.GameStatus;
import mmf.ivanyukovich.game.model.Move;
import mmf.ivanyukovich.game.model.MoveResult;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by Hope on 5/6/14.
 */
public class Client {
    public static void main(String[] args) {

        MoveReader moveReader = new MoveReader();
        ObjectMapper mapper = new ObjectMapper();
        MoveResultWriter moveResultWriter = new MoveResultWriter();

        boolean gameOver = false;

        try(Socket socket = new Socket(InetAddress.getLocalHost(),8030);
            Scanner scanner = new Scanner(System.in);
            PrintStream out = new PrintStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            while (!gameOver){
                Move move = moveReader.read(scanner, System.out);
                String message = mapper.writeValueAsString(move);
                out.println(message);
                out.flush();

//                String message = in.readLine();
//                System.out.println(message);
                message = in.readLine();
                MoveResult moveResult = mapper.readValue(message, MoveResult.class);

                moveResultWriter.write(moveResult,System.out);
                if(moveResult.getStatus() != GameStatus.IN_PROGRESS){
                    gameOver = true;
                    System.out.println(moveResult.getStatus());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
