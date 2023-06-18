package nl.hu.bep.setup.game.domain;

import java.io.Serializable;

public class Snake implements Serializable {

    private static Snake my_snake = new Snake();

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

    public static Snake getSnake() {
        return my_snake;
    }

    public void setMy_snake(Snake snake) {
        my_snake = snake;
        tail = snake.tail;
        head = snake.head;
        color = snake.getColor();
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

    public void setColor(String color) {
        this.color = color;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }


}
