package nl.hu.bep.setup.game.webservices;

import nl.hu.bep.setup.game.domain.Snake;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response startGame() {

        return Response.ok().build();

    }

    @POST
    @Path("move")
    public Response move() {
        MoveDTO moveDTO = new MoveDTO();
        moveDTO.move = "right";
        moveDTO.shout = "Moving up!";

        return Response.ok(moveDTO).build();



    }

    private static class MoveDTO {
        public String move;
        public String shout;

    }

    @POST
    @Path("end")
    public Response endGame() {

        return Response.ok().build();

    }


}
