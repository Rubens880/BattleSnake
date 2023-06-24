package nl.hu.bep.setup.game.webservices;

import nl.hu.bep.setup.game.domain.BattleSnake;
import nl.hu.bep.setup.game.domain.Game;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("games")
public class GameResource {

    //Haalt alle game namen/ids op
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response getGamesNames() {
        List<Game> games = BattleSnake.getMy_BattleSnake().getGames();
        List<String> gameIds = new ArrayList<>();
        for (Game game:  games) {
            gameIds.add(game.getGameId());}

        return Response.ok(gameIds).build();
    }


    //Haalt game op bij id
    @GET
    @Path("game/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response getGameByID(@PathParam("id") String id) {
        Game game = BattleSnake.getMy_BattleSnake().getGameById(id);
        System.out.println(game);

        if (game == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(game).build();
    }


    //Verwijderd game met id
    @DELETE
    @Path("game/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response deleteGame(@PathParam("id") String id) {
        Game game = BattleSnake.getMy_BattleSnake().getGameById(id);

        if (!BattleSnake.getMy_BattleSnake().deleteGame(game)) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        return Response.ok(game).build();
    }
}
