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
public class ListUsers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //creates the connection
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
        Session session = factory.openSession();

        try {
            //in this trsansaction list all clients
            session.beginTransaction();
            List<User> users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();

            users.forEach(user -> {
                System.out.println(user);
            }); //get users with name countain 7
            
            session.beginTransaction();
            users = session.createQuery("from User u where u.name like '%7%'").getResultList();
            session.getTransaction().commit();

            users.forEach(user -> {
                System.out.println(user);
            }); //get users with name countain 7

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
            factory.close();
        }

    }

}
