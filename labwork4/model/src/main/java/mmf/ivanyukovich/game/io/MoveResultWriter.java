package mmf.ivanyukovich.game.io;

import mmf.ivanyukovich.game.model.KickPoint;
import mmf.ivanyukovich.game.model.MoveResult;

import java.io.PrintStream;
import java.util.Map;

/**
 * Created by Hope on 5/6/14.
 */
public class MoveResultWriter {
    public void write(MoveResult moveResult, PrintStream out){
        out.println("Move result: ");
        out.println("Me: " + moveResult.getMe().getHealthPoints() + " HP");
        out.println("My attack result: ");
        for (Map.Entry<KickPoint,Integer> kickResult: moveResult.getMyAttackResult().entrySet()){
            out.println("    " + kickResult.getKey() + " " + (kickResult.getValue() < 0 ? kickResult.getValue() : "blocked!" ));
        }
        out.println("Total damage: " + moveResult.getMyTotalDamage());

        out.println();

        out.println("Opponent: " + moveResult.getOpponent().getHealthPoints() + " HP");
        out.println("Opponent move: ");
        out.println("    defence: " + moveResult.getOpponentMove().getDefencePoints());
        out.println("    attack: " + moveResult.getOpponentMove().getAttackPoints());
        out.println();
    }
}
