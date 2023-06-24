package nl.hu.bep;

import nl.hu.bep.setup.game.domain.BattleSnake;
import nl.hu.bep.setup.game.domain.Game;
import nl.hu.bep.setup.game.domain.Snake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    private Game testGame;
    private Game testGame2;

    @BeforeEach
    public void loadSnake() {
        this.testGame = new Game("testGame","testSnake");
        this.testGame2 = new Game("testGame2","testSnake");

    }


    @Test
    public void getGameById() {
        BattleSnake battleSnake = new BattleSnake();
        battleSnake.addGame(testGame);

        assertEquals(testGame, battleSnake.getGameById("testGame"));

    }

    @Test
    public void saveGames() {
        BattleSnake battleSnake = new BattleSnake();
        battleSnake.addGame(testGame);
        battleSnake.addGame(testGame2);

        assertEquals(2, battleSnake.getGames().size());

    }

    @Test
    public void deleteGame() {
        BattleSnake battleSnake = new BattleSnake();
        battleSnake.addGame(testGame);
        battleSnake.deleteGame(testGame);

        assertEquals(0, battleSnake.getGames().size());

    }

    static Stream<Arguments> differentGameIds() {
        return Stream.of(
                Arguments.of(
                        new Game("id1", "snakeTest"),
                        "id1"
                ),
                Arguments.of(
                        new Game("id2", "snakeTest"),
                        "id2"
                ),
                Arguments.of(
                        new Game("id3", "snakeTest"),
                        "id3"
                ),
                Arguments.of(
                        new Game("id4", "snakeRuben"),
                        "id4"
                ),
                Arguments.of(
                        new Game("id5", "snakeBas"),
                        "id5"
                ),
                Arguments.of(
                        new Game("testId", "snakeMick"),
                        "testId"
                )
        );
    }

    @ParameterizedTest
    @DisplayName("findMultipleGamesById")
    @MethodSource("differentGameIds")
    public void findGameByIdMore(Game game, String id) {
        BattleSnake battleSnake = new BattleSnake();
        battleSnake.addGame(game);

        assertEquals(game, battleSnake.getGameById(id));


    }


}
