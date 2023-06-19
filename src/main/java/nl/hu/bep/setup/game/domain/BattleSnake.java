package nl.hu.bep.setup.game.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BattleSnake implements Serializable {

    private static BattleSnake my_battleSnake = new BattleSnake();

    private Snake snake = new Snake();

    private List<Game> games = new ArrayList<>();

    public BattleSnake() {

    }
    public BattleSnake(Snake snake, List<Game> games) {
        this.snake = snake;
        this.games = games;
    }

    public static BattleSnake getMy_BattleSnake() { return my_battleSnake;}

    public void setMy_BattleSnake(BattleSnake battleSnake) {
        this.snake = battleSnake.getSnake();
        this.games = battleSnake.getGames();
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public Snake getSnake() {
        return snake;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Game getGameById(String id) {


        for (Game game : games) {
            System.out.println(id);
            System.out.println(game.getGameId());
            if (game.getGameId().equals(id)) {
                return game;
            }
        }
        return null;
    }

    public boolean deleteGame(Game game) {
        for (Game game1 : games) {
            if (game1.equals(game)) {
                games.remove(game1);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "BattleSnake{" +
                "snake=" + snake +
                ", games=" + games +
                '}';
    }
}
