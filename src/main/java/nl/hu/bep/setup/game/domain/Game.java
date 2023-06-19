package nl.hu.bep.setup.game.domain;


import java.io.Serializable;
import java.util.List;

public class Game implements Serializable {

    private String gameId;

    private int amountOfMoves;

    private int endingSnakeLength;

    private String snakeName;

    private List<String > moves;

    public Game() {

    }

    public Game(String gameId, String snakeName) {
        this.gameId = gameId;
        this.snakeName = snakeName;
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

    public void addMove(String move) {
        this.moves.add(move);
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
