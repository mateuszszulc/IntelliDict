package testing.manytoone;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 21.04.12
 * Time: 16:52
 */

import dataproviders.HibernateSessionFactoryManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManyToOneTest {

    public static void main(String[] args) {
        Session session = HibernateSessionFactoryManager.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Address address = new Address("OMR Road", "Chennai", "TN", "600097");
//By using cascade=all option the address need not be saved explicitly when the student object is persisted the address will be automatically saved.
//session.save(address);
            Student student1 = new Student("Eswar", address);
            Student student2 = new Student("Joe", address);
            session.save(student1);
            session.save(student2);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
}
