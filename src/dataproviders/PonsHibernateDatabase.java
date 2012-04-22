package dataproviders;

import model.PonsEntry;
import org.hibernate.Session;

/**
 * Created by IntelliJ IDEA.
 * User: mateusz
 * Date: 22.04.12
 * Time: 11:02
 */
public class PonsHibernateDatabase implements PonsDatabase {

    @Override
    public boolean storeEntry(PonsEntry newPonsEntry) {
        Session session = HibernateSessionFactoryManager.getCurrentSession();
        session.save(newPonsEntry);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public String getEntry(String baseWord) {

        return "";
    }

    @Override
    public boolean deleteEntry(String baseWord) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
