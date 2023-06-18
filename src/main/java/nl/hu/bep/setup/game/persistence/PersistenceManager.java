package nl.hu.bep.setup.game.persistence;




import nl.hu.bep.setup.game.domain.Snake;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class PersistenceManager {

    //Laad Agenda object uit de homeDirectory
    public static void loadSnakeFromFile() throws IOException, ClassNotFoundException {
        Path homeDirectory = Path.of("/home");
        Path snakeStorage = Path.of("/home/snake.obj");
        if (!Files.exists(snakeStorage)) {
            return;
        }

        InputStream is = Files.newInputStream(snakeStorage);
        ObjectInputStream ois = new ObjectInputStream(is);
        Snake snake = (Snake) ois.readObject();
        Snake snake1 = Snake.getSnake();
        snake1.setMy_snake(snake);
        is.close();
        ois.close();
        System.out.println(snake.toString());


    }

    //Slaat agenda op in de /home directory
    public static void saveSnakeToFile() throws IOException {
        Path homeDirectory = Path.of("/home");
        if (!Files.exists(homeDirectory)) {
            Files.createDirectory(homeDirectory);
        }

        Snake snakeToSave = Snake.getSnake();
        Path snakeStorage = Path.of("/home/snake.obj");

        OutputStream os = Files.newOutputStream(snakeStorage);

        ObjectOutputStream oos = new ObjectOutputStream(os);

        oos.writeObject(snakeToSave);

        os.close();
        oos.close();
        System.out.println(snakeToSave.toString());
    }

}
