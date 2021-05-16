package lt.codeacademy;

import lt.codeacademy.config.HibernateConfig;
import lt.codeacademy.model.Menu;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        HibernateConfig.buildSessionFactory();
        Menu menu = new Menu();
        menu.run();
        HibernateConfig.closeSessionFactory();
    }
}