package nl.hu.bep.setup.game.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game implements Serializable {

    private String gameId;

    private int amountOfMoves;

    private int endingSnakeLength;

    private String snakeName;

    private HashMap<String, Integer> moves = new HashMap<>();

    public Game() {

    }

    public Game(String gameId, String snakeName) {
        this.gameId = gameId;
        this.snakeName = snakeName;
        moves.put("right",0);
        moves.put("up", 0);
        moves.put("left", 0);
        moves.put("down", 0);
    }

    public String getGameId() {
        return gameId;
    }

    public int getAmountOfMoves() {
        return amountOfMoves;
    }

    public int getEndingSnakeLength() {
        return endingSnakeLength;
    }

    public String getSnakeName() {
        return snakeName;
    }

    public void setAmountOfMoves(int amountOfMoves) {
        this.amountOfMoves = amountOfMoves;
    }

    public void setEndingSnakeLength(int endingSnakeLength) {
        this.endingSnakeLength = endingSnakeLength;
    }

    public void setSnakeName(String snakeName) {
        this.snakeName = snakeName;
    }

    public HashMap<String, Integer> getMoves() {
        return moves;
    }

    public void addMove(String move) {
        moves.put(move, moves.get(move) + 1);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + gameId + '\'' +
                ", amountOfMoves=" + amountOfMoves +
                ", endingSnakeLength='" + endingSnakeLength + '\'' +
                ", snakeName='" + snakeName + '\'' +
                '}';
    }
}
