/*
 * class for test hibernate manage
 */
package hibernate_level1;

import hibernate_level1.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Jerson Perez
 */
public class CreateUsers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //creates the connection
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
        Session session = factory.openSession();

        // creates a new user
        String userNick = "User" + String.valueOf(Math.round(1000 * Math.random()));
        String userName = userNick + " Name Last Name";
        String userPass = userNick + " PW";
        User newUser = new User(userNick, userName, userPass);
        System.out.println(newUser);

        try {
            //in this trsansaction insert the client
            session.beginTransaction();
            session.save(newUser);
            session.getTransaction().commit();

            System.out.println("Usuario Creado: " + newUser);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
            factory.close();
        }

    }

}
