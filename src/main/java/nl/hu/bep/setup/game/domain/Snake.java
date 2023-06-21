package nl.hu.bep.setup.game.domain;

import java.io.Serializable;

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


    public void updateSnake(String color, String head, String tail) {
        this.color = color;
        this.head = head;
        this.tail = tail;

    }


}
