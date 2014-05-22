package mmf.ivanyukovich.game.io;

import mmf.ivanyukovich.game.model.KickPoint;
import mmf.ivanyukovich.game.model.Mode;
import mmf.ivanyukovich.game.model.Move;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Hope on 5/5/14.
 */
public class MoveReader {
    public Move read(Scanner scanner, PrintStream out){
        Move move = new Move();
        out.println("Enter move mode, 0 - defence, 1 - attack: ");
        out.flush();
        move.setMode(Mode.values()[scanner.nextInt()]);

        out.println("Enter defence points, 0 - head, 1 - body, 2 - legs, 3 - right arm, 4 - left arm: ");
        out.flush();
        move.setDefencePoints(new ArrayList<KickPoint>());
        for (int i = 0; i < move.getMode().getDefencePointNumber(); i++){
            move.getDefencePoints().add(KickPoint.values()[scanner.nextInt()]);
        }

        out.println("Enter attack points, 0 - head, 1 - body, 2 - legs, 3 - right arm, 4 - left arm: ");
        out.flush();
        move.setAttackPoints(new ArrayList<KickPoint>());
        for (int i = 0; i < move.getMode().getAttackPointNumber(); i++){
            move.getAttackPoints().add(KickPoint.values()[scanner.nextInt()]);
        }
        return move;
    }
}
