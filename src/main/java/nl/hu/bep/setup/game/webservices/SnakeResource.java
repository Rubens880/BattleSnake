package nl.hu.bep.setup.game.webservices;

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
        System.out.println(Snake.getSnake().toString());
        return Response.ok(Snake.getSnake()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeSnake(SnakeDTO snakeDTO) {

        Snake snake = Snake.getSnake();
        snake.setColor(snakeDTO.color);
        snake.setHead(snakeDTO.head);
        snake.setTail(snakeDTO.tail);

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
    public Response startGame() {

        return Response.ok().build();

    }

    @POST
    @Path("move")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response move() {
        double number =  Math.random() * 10;
        Map<String, String> messages = new HashMap<>();

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
    public Response endGame() {

        return Response.ok().build();

    }


}
