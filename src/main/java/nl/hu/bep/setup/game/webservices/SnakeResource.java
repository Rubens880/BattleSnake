package nl.hu.bep.setup.game.webservices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.hu.bep.setup.game.domain.BattleSnake;
import nl.hu.bep.setup.game.domain.Game;
import nl.hu.bep.setup.game.domain.Snake;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("snake")
public class SnakeResource {
    private String lastMove;


    //haalt snake op
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSnake() {
        return Response.ok(BattleSnake.getMy_BattleSnake().getSnake()).build();
    }

    //Veranderd snake values
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response changeSnake(SnakeDTO snakeDTO) {
        Snake snake = BattleSnake.getMy_BattleSnake().getSnake();
        snake.updateSnake(snakeDTO.color, snakeDTO.head, snakeDTO.tail);

        return Response.ok(snake).build();
    }

    private static class SnakeDTO{
        public String color;
        public String tail;
        public String head;
    }

    //Methode slaat data van game op bij start
    @POST
    @Path("start")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response startGame(GameDataDtos gameDataDtos) {

        Game game = new Game(gameDataDtos.gameDTO.id, gameDataDtos.youDTO.name);
        BattleSnake.getMy_BattleSnake().addGame(game);
        return Response.ok().build();

    }

    //move method geeft de move terug die de snake moet doen
    @POST
    @Path("move")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response move(GameDataDtos gameDataDtos) {
        Snake snake = BattleSnake.getMy_BattleSnake().getSnake();
        Game game = BattleSnake.getMy_BattleSnake().getGameById(gameDataDtos.gameDTO.id);


        Map<String, String> move = snake.getNextMove(gameDataDtos);

        game.addMove(move.get("move"));

        return Response.ok().entity(move).build();
    }

    //Slaat alle data op bij einde game
    @POST
    @Path("end")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response endGame(GameDataDtos gameDataDtos) {
        Game game = BattleSnake.getMy_BattleSnake().getGameById(gameDataDtos.gameDTO.id);
        if (game == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        game.setAmountOfMoves(gameDataDtos.turn);
        game.setEndingSnakeLength(gameDataDtos.youDTO.length);

        return Response.ok().build();

    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GameDataDtos {
        @JsonProperty("game")
        public GameDTO gameDTO;

        @JsonProperty("turn")
        public int turn;

        @JsonProperty("board")
        public BoardDTO boardDTO;

        @JsonProperty("you")
        public YouDTO youDTO;

    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GameDTO {
        public String id;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BoardDTO {
        public int height;

        public int width;

        public List<Map<String, Integer>> food;


    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class YouDTO {
        public String name;
        public int length;


        public List<Map<String, Integer>> body;

        public Map<String, Integer> head;


    }



}
