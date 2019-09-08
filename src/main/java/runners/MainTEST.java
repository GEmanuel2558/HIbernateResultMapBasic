package runners;

import models.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainTEST {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        for (int i = 0; i < 10; i++) {
            Author author = new Author();
            author.setFirstName("First "+i);
            author.setLastName("Last "+i);
            session.save(author);
        }

        session.getTransaction().commit();
        session.beginTransaction();

        List<Author> authors = session.createNamedQuery("AuthorQuery").getResultList();
        System.out.println(authors);

        session.getTransaction().commit();
        session.close();
    }
}
