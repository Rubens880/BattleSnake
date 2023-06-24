package nl.hu.bep.setup.game.domain;

import nl.hu.bep.setup.game.webservices.SnakeResource;

import java.io.Serializable;
import java.util.*;

public class Snake implements Serializable {

    private String apiversion = "1";
    private String color = "#888888";
    private String head = "beluga" ;
    private String tail = "curled";


    public Snake() {

    }
    public Snake(String color, String head, String tail) {
        this.color = color;
        this.head = head;
        this.tail = tail;
    }

    public String getColor() {
        return color;
    }

    public String getHead() {
        return head;
    }

    public String getTail() {
        return tail;
    }

    public String getApiversion() {return apiversion;}

    public Map<String, String> getNextMove(SnakeResource.GameDataDtos gameDataDtos) {
        List<String> possibleMoves = new ArrayList<>(List.of("right","left","up","down"));
        int mapHeight = gameDataDtos.boardDTO.height;
        int mapWidth = gameDataDtos.boardDTO.width;
        Map<String, Integer> headLocation = gameDataDtos.youDTO.head;
        List<Map<String, Integer>> bodyLocations = gameDataDtos.youDTO.body;

        //Check for borders
        if (headLocation.get("x") == mapWidth - 1) {possibleMoves.remove("right");}

        if (headLocation.get("x") == 0) {possibleMoves.remove("left");}

        if (headLocation.get("y") == mapHeight - 1) {possibleMoves.remove("up");}

        if (headLocation.get("y") == 0) {possibleMoves.remove("down");}

        //Check for body

        for (Map<String, Integer> bodyLocation : bodyLocations) {
            Map<String, Integer> headLocationXOmhoog = new HashMap<>(headLocation);
            headLocationXOmhoog.put("x", headLocationXOmhoog.get("x") + 1);

            Map<String, Integer> headLocationXOmlaag = new HashMap<>(headLocation);
            headLocationXOmlaag.put("x", headLocationXOmlaag.get("x") - 1);

            Map<String, Integer> headLocationYOmhoog = new HashMap<>(headLocation);
            headLocationYOmhoog.put("y", headLocationYOmhoog.get("y") + 1);

            Map<String, Integer> headLocationYOmlaag = new HashMap<>(headLocation);
            headLocationYOmlaag.put("y", headLocationYOmlaag.get("y") - 1);


            //Check X omhoog
            if (bodyLocation.equals(headLocationXOmhoog)) {
                    possibleMoves.remove("right");
            }
            //Check X omlaag
            if (bodyLocation.equals(headLocationXOmlaag)) {
                    possibleMoves.remove("left");
            }
            //Check Y omhoog
            if (bodyLocation.equals(headLocationYOmhoog)) {

                    possibleMoves.remove("up");
            }
            //Check Y omlaag
            if (bodyLocation.equals(headLocationYOmlaag)) {

                    possibleMoves.remove("down");
            }

        }

        String moveToReturn;

        if (possibleMoves.size() > 1) {

            Random random = new Random();
            int randomNumber = random.nextInt(possibleMoves.size() );
            moveToReturn = possibleMoves.get(randomNumber);

        } else if (possibleMoves.size() == 0) {
            moveToReturn = "up";
        } else {
            moveToReturn = possibleMoves.get(0);
        }

        Map<String, String> move = new HashMap<>();
        move.put("move", moveToReturn);
        move.put("shout", "Going right!!!");

        return move;
    }


    public void updateSnake(String color, String head, String tail) {
        this.color = color;
        this.head = head;
        this.tail = tail;

    }


}
