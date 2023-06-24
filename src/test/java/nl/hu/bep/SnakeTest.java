package nl.hu.bep;


import nl.hu.bep.setup.game.domain.BattleSnake;
import nl.hu.bep.setup.game.domain.Game;
import nl.hu.bep.setup.game.domain.Snake;
import nl.hu.bep.setup.game.webservices.SnakeResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeTest {

    private Snake snake;


    @BeforeEach
    public void loadSnake() {
        this.snake = new Snake("#FF5733", "missle","round-bum");
    }


    @Test
    public void updateSnakeHead() {
        snake.updateSnake("#050505", "default","default");

        assertEquals("default",snake.getHead());

    }

    @Test
    public void updateSnakeTail() {
        snake.updateSnake("#050505", "default","curled");

        assertEquals("curled",snake.getTail());

    }

    @Test
    public void updateSnakeColor() {
        snake.updateSnake("#00FF13", "default","curled");

        assertEquals("#00FF13",snake.getColor());

    }

    @Test
    public void testNextMoveDown() {
        // Maak gameData aan
        SnakeResource.GameDataDtos gameDataDtos = new SnakeResource.GameDataDtos();
        gameDataDtos.boardDTO = new SnakeResource.BoardDTO();
        gameDataDtos.boardDTO.width = 11;
        gameDataDtos.boardDTO.height = 11;
        gameDataDtos.boardDTO.food = List.of(
                Map.of("x", 10, "y", 8)
        );
        //Maak eigenSnake Data aan
        gameDataDtos.youDTO = new SnakeResource.YouDTO();
        gameDataDtos.youDTO.head = Map.of("x", 1, "y", 1);
        gameDataDtos.youDTO.body = List.of(
                Map.of("x", 2, "y", 2),
                Map.of("x", 2, "y", 1),
                Map.of("x", 1, "y", 2),
                Map.of("x",0,"y",1),
                Map.of("x", 0,"y",2)
        );


        Snake snake = new Snake();
        Map<String, String> move = snake.getNextMove(gameDataDtos);

        assertEquals("down", snake.getNextMove(gameDataDtos).get("move"));

    }

    @Test
    public void testNextMoveRight() {
        // Maak gameData aan
        SnakeResource.GameDataDtos gameDataDtos = new SnakeResource.GameDataDtos();
        gameDataDtos.boardDTO = new SnakeResource.BoardDTO();
        gameDataDtos.boardDTO.width = 11;
        gameDataDtos.boardDTO.height = 11;
        gameDataDtos.boardDTO.food = List.of(
                Map.of("x", 10, "y", 8)
        );
        //Maak eigenSnake Data aan
        gameDataDtos.youDTO = new SnakeResource.YouDTO();
        gameDataDtos.youDTO.head = Map.of("x", 0, "y", 0);
        gameDataDtos.youDTO.body = List.of(
                Map.of("x", 0, "y", 1),
                Map.of("x", 1, "y", 1)
        );


        Snake snake = new Snake();
        Map<String, String> move = snake.getNextMove(gameDataDtos);

        assertEquals("right", snake.getNextMove(gameDataDtos).get("move"));

    }

    @Test
    public void testNextMoveLeft() {
        // Maak gameData aan
        SnakeResource.GameDataDtos gameDataDtos = new SnakeResource.GameDataDtos();
        gameDataDtos.boardDTO = new SnakeResource.BoardDTO();
        gameDataDtos.boardDTO.width = 11;
        gameDataDtos.boardDTO.height = 11;
        gameDataDtos.boardDTO.food = List.of(
                Map.of("x", 10, "y", 8)
        );
        //Maak eigenSnake Data aan
        gameDataDtos.youDTO = new SnakeResource.YouDTO();
        gameDataDtos.youDTO.head = Map.of("x", 1, "y", 0);
        gameDataDtos.youDTO.body = List.of(
                Map.of("x", 2, "y", 0),
                Map.of("x", 2, "y", 1),
                Map.of("x",1,"y",1)
        );


        Snake snake = new Snake();

        assertEquals("left", snake.getNextMove(gameDataDtos).get("move"));

    }

    @Test
    public void testNextMoveUp() {
        // Maak gameData aan
        SnakeResource.GameDataDtos gameDataDtos = new SnakeResource.GameDataDtos();
        gameDataDtos.boardDTO = new SnakeResource.BoardDTO();
        gameDataDtos.boardDTO.width = 11;
        gameDataDtos.boardDTO.height = 11;
        gameDataDtos.boardDTO.food = List.of(
                Map.of("x", 10, "y", 8)
        );
        //Maak eigenSnake Data aan
        gameDataDtos.youDTO = new SnakeResource.YouDTO();
        gameDataDtos.youDTO.head = Map.of("x", 0, "y", 0);
        gameDataDtos.youDTO.body = List.of(
                Map.of("x", 1, "y", 0),
                Map.of("x", 1, "y", 1)
        );


        Snake snake = new Snake();

        assertEquals("up", snake.getNextMove(gameDataDtos).get("move"));

    }

    @Test
    public void testNoMovesPossibleShouldBeNextMoveUp() {
        // Maak gameData aan
        SnakeResource.GameDataDtos gameDataDtos = new SnakeResource.GameDataDtos();
        gameDataDtos.boardDTO = new SnakeResource.BoardDTO();
        gameDataDtos.boardDTO.width = 11;
        gameDataDtos.boardDTO.height = 11;
        gameDataDtos.boardDTO.food = List.of(
                Map.of("x", 10, "y", 8)
        );
        //Maak eigenSnake Data aan
        gameDataDtos.youDTO = new SnakeResource.YouDTO();
        gameDataDtos.youDTO.head = Map.of("x", 0, "y", 0);
        gameDataDtos.youDTO.body = List.of(
                Map.of("x", 1, "y", 0),
                Map.of("x", 1, "y", 1),
                Map.of("x",0,"y",1)
        );


        Snake snake = new Snake();

        assertEquals("up", snake.getNextMove(gameDataDtos).get("move"));

    }

    @Test
    public void testBorderUpNotPossible() {
        // Maak gameData aan
        SnakeResource.GameDataDtos gameDataDtos = new SnakeResource.GameDataDtos();
        gameDataDtos.boardDTO = new SnakeResource.BoardDTO();
        gameDataDtos.boardDTO.width = 11;
        gameDataDtos.boardDTO.height = 11;
        gameDataDtos.boardDTO.food = List.of(
                Map.of("x", 10, "y", 8)
        );
        //Maak eigenSnake Data aan
        gameDataDtos.youDTO = new SnakeResource.YouDTO();
        gameDataDtos.youDTO.head = Map.of("x", 0, "y", 10);
        gameDataDtos.youDTO.body = List.of(
                Map.of("x", 0, "y", 9)
        );


        Snake snake = new Snake();
        assertEquals("right", snake.getNextMove(gameDataDtos).get("move"));


    }

    @Test
    public void testBorderDownNotPossible() {
        // Maak gameData aan
        SnakeResource.GameDataDtos gameDataDtos = new SnakeResource.GameDataDtos();
        gameDataDtos.boardDTO = new SnakeResource.BoardDTO();
        gameDataDtos.boardDTO.width = 11;
        gameDataDtos.boardDTO.height = 11;
        gameDataDtos.boardDTO.food = List.of(
                Map.of("x", 10, "y", 8)
        );
        //Maak eigenSnake Data aan
        gameDataDtos.youDTO = new SnakeResource.YouDTO();
        gameDataDtos.youDTO.head = Map.of("x", 0, "y", 0);
        gameDataDtos.youDTO.body = List.of(
                Map.of("x", 0, "y", 1)
        );


        Snake snake = new Snake();
        assertEquals("right", snake.getNextMove(gameDataDtos).get("move"));


    }

    @Test
    public void testBorderRightNotPossible() {
        // Maak gameData aan
        SnakeResource.GameDataDtos gameDataDtos = new SnakeResource.GameDataDtos();
        gameDataDtos.boardDTO = new SnakeResource.BoardDTO();
        gameDataDtos.boardDTO.width = 11;
        gameDataDtos.boardDTO.height = 11;
        gameDataDtos.boardDTO.food = List.of(
                Map.of("x", 10, "y", 8)
        );
        //Maak eigenSnake Data aan
        gameDataDtos.youDTO = new SnakeResource.YouDTO();
        gameDataDtos.youDTO.head = Map.of("x", 10, "y", 0);
        gameDataDtos.youDTO.body = List.of(
                Map.of("x", 10, "y", 1)
        );


        Snake snake = new Snake();
        assertEquals("left", snake.getNextMove(gameDataDtos).get("move"));


    }

    @Test
    public void testBorderLeftNotPossible() {
        // Maak gameData aan
        SnakeResource.GameDataDtos gameDataDtos = new SnakeResource.GameDataDtos();
        gameDataDtos.boardDTO = new SnakeResource.BoardDTO();
        gameDataDtos.boardDTO.width = 11;
        gameDataDtos.boardDTO.height = 11;
        gameDataDtos.boardDTO.food = List.of(
                Map.of("x", 10, "y", 8)
        );
        //Maak eigenSnake Data aan
        gameDataDtos.youDTO = new SnakeResource.YouDTO();
        gameDataDtos.youDTO.head = Map.of("x", 0, "y", 5);
        gameDataDtos.youDTO.body = List.of(
                Map.of("x", 0, "y", 4),
                Map.of("x",1,"y",4),
                Map.of("x",1,"y",5)
        );


        Snake snake = new Snake();
        assertEquals("up", snake.getNextMove(gameDataDtos).get("move"));


    }





}
