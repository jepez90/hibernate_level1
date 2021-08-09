/*
 * class for test hibernate manage
 */
package hibernate_level1;

import hibernate_level1.models.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Jerson Perez
 */
public class UpdateUsers {

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
        System.out.println("New User: " + newUser);

        try {

            //insert the new user to the db
            session.beginTransaction();
            session.save(newUser);
            session.getTransaction().commit();

            Thread.sleep(1000);
            
            // change the password 
            newUser.setPassword(newUser.getPassword() + " new");
            System.out.println("Updated User: " + newUser);

            //in this trsansaction update the client
            session.beginTransaction();
            session.update(newUser);
            session.getTransaction().commit();

            Thread.sleep(1000);

            //get the client for the db
            session.beginTransaction();
            System.out.println("Updated User from db: " + session.get(User.class, newUser.getId()));
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
            factory.close();
        }

    }

}
