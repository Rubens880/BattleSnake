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
import java.util.HashMap;
import java.util.Map;

@Path("snake")
public class SnakeResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSnake() {
        System.out.println(BattleSnake.getMy_BattleSnake().getSnake().toString());
        System.out.println("getSnake aangeroepen");
        return Response.ok(BattleSnake.getMy_BattleSnake().getSnake()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response changeSnake(SnakeDTO snakeDTO) {
        System.out.println("changeSnake aangeroepen.");

        Snake snake = BattleSnake.getMy_BattleSnake().getSnake();
        snake.updateSnake(snakeDTO.color, snakeDTO.head, snakeDTO.tail);

        return Response.ok(snake).build();
    }

    private static class SnakeDTO{
        public String color;
        public String tail;
        public String head;
    }

    @POST
    @Path("start")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response startGame(GameDataDtos gameDataDtos) {

        Game game = new Game(gameDataDtos.gameDTO.id, gameDataDtos.youDTO.name);
        BattleSnake.getMy_BattleSnake().addGame(game);
        return Response.ok().build();

    }

    @POST
    @Path("move")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response move(GameDataDtos gameDataDtos) {
        double number =  Math.random() * 10;
        Map<String, String> messages = new HashMap<>();
        Game game = BattleSnake.getMy_BattleSnake().getGameById(gameDataDtos.gameDTO.id);
        if (game == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }


            if (number < 2) {
                messages.put("move", "right");
                messages.put("shout", "Going right!!!");

            } else if (number < 4) {
                messages.put("move", "left");
                messages.put("shout", "Going left!!!");


            } else if (number < 6) {
                messages.put("move", "up");
                messages.put("shout", "Going up!!!");

            } else {
                messages.put("move", "down");
                messages.put("shout", "Going down!!!");
            }


        return Response.ok().entity(messages).build();



    }


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

        System.out.println(game);

        return Response.ok().build();

    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class GameDataDtos {
        @JsonProperty("game")
        public GameDTO gameDTO;

        @JsonProperty("turn")
        public int turn;

        @JsonProperty("you")
        public YouDTO youDTO;

    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class GameDTO {
        public String id;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class YouDTO {
        public String name;
        public int length;
    }



}
