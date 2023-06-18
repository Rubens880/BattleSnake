package nl.hu.bep.setup.setup;



import nl.hu.bep.setup.game.persistence.PersistenceManager;
import nl.hu.bep.setup.security.MyUser;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class MyServletContextListener implements ServletContextListener {


    //Functie die wordt aangeroepen bij starten van de applicatie
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing application");
        try {
            PersistenceManager.loadSnakeFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //Gebruiker wordt aangemaakt
        MyUser.createUser("Ruben", "123", "user");

    }

    //Functie die wordt aangeroepen bij het afsluiten van de applicatie
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            PersistenceManager.saveSnakeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Terminating application");

    }

}
