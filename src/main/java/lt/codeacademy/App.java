package lt.codeacademy;

import lt.codeacademy.config.HibernateConfig;
import lt.codeacademy.entities.Voter;
import lt.codeacademy.model.Menu;
import lt.codeacademy.model.constanta.Candidates;
import lt.codeacademy.model.constanta.City;
import lt.codeacademy.model.constanta.Gender;
import lt.codeacademy.util.DateTime;
import org.hibernate.Session;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        menu.run();
    }
}