package nl.hu.bep.setup.game.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Game my_Games = new Game();

    private List<Game> games = new ArrayList<>();


//    public Game() {
//
//    }
//
//    public Game() {
//
//    }

    public static Game getMy_Games(){
        return my_Games;
    }

}
