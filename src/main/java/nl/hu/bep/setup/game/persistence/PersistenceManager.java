package nl.hu.bep.setup.game.persistence;




import nl.hu.bep.setup.game.domain.BattleSnake;
import nl.hu.bep.setup.game.domain.Snake;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class PersistenceManager {

    //Laad Battlesnake object uit de homeDirectory
    public static void loadBattleSnakeFromFile() throws IOException, ClassNotFoundException {
//        Path homeDirectory = Path.of("/home");
//        Path snakeStorage = Path.of("/home/snake.obj");
//        if (!Files.exists(snakeStorage)) {
//            return;
//        }
//
//        InputStream is = Files.newInputStream(snakeStorage);
//        ObjectInputStream ois = new ObjectInputStream(is);
//        Snake snake = (Snake) ois.readObject();
//        Snake snake1 = Snake.getSnake();
//        snake1.setMy_snake(snake);
//        is.close();
//        ois.close();
//        System.out.println(snake.toString());
        Path battleSnakeStorage = Path.of("/home/battlesnake.obj");
        if (!Files.exists(battleSnakeStorage)) {
            return;
        }

        InputStream is = Files.newInputStream(battleSnakeStorage);
        ObjectInputStream ois = new ObjectInputStream(is);
        BattleSnake battleSnake = (BattleSnake) ois.readObject();
        BattleSnake battleSnake1 = BattleSnake.getMy_BattleSnake();
        battleSnake1.setMy_BattleSnake(battleSnake);
        is.close();
        ois.close();
        System.out.println(battleSnake.toString());


    }

    //Slaat BattleSnake op in de /home directory
    public static void saveBattleSnakeToFile() throws IOException {
        Path homeDirectory = Path.of("/home");
        if (!Files.exists(homeDirectory)) {
            Files.createDirectory(homeDirectory);
        }

        BattleSnake battleSnakeToSave = BattleSnake.getMy_BattleSnake();
        Path battleSnakeStorage = Path.of("/home/battlesnake.obj");

        OutputStream os = Files.newOutputStream(battleSnakeStorage);

        ObjectOutputStream oos = new ObjectOutputStream(os);

        oos.writeObject(battleSnakeToSave);

        os.close();
        oos.close();
        System.out.println(battleSnakeToSave.toString());
    }




}
