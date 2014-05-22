package mmf.ivanyukovich.game.model;

import java.util.Map;

/**
 * Created by Hope on 5/4/14.
 */
public class MoveResult {
    private Player me;
    private Player opponent;
    private Move opponentMove;
    private Map<KickPoint,Integer> myAttackResult;
    private int myTotalDamage;
    private GameStatus status;

    public Player getMe() {
        return me;
    }

    public void setMe(Player me) {
        this.me = me;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Move getOpponentMove() {
        return opponentMove;
    }

    public void setOpponentMove(Move opponentMove) {
        this.opponentMove = opponentMove;
    }

    public Map<KickPoint, Integer> getMyAttackResult() {
        return myAttackResult;
    }

    public void setMyAttackResult(Map<KickPoint, Integer> myAttackResult) {
        this.myAttackResult = myAttackResult;
        for (Integer damage: myAttackResult.values()){
            myTotalDamage += damage;
        }
    }

    public int getMyTotalDamage() {
        return myTotalDamage;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}
