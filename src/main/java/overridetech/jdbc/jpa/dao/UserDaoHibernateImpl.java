package overridetech.jdbc.jpa.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import overridetech.jdbc.jpa.model.User;

import java.util.List;


@Repository
public class UserDaoHibernateImpl implements UserDao {
    private final String tableId = "users.users";

    @Autowired
    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createSQLQuery("create table if not exists " + tableId +
                " (id BIGSERIAL PRIMARY KEY, name VARCHAR(255), lastname VARCHAR(255), age smallint)");
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createSQLQuery("drop table if exists " + tableId);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(new User(name, lastName, age));

        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(user);

        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getUserByModelAndSerial(String model, int series) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users;

        Query query = session.createQuery("from " + User.class.getName() +
                " u left join fetch u.car where (model = :model) AND (series = :series)");

        query.setParameter("model", model);
        query.setParameter("series", series);

        users = query.list();

        transaction.commit();
        session.close();

        return users;
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(session.load(User.class, id));

        session.flush();
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<User> list = session.createCriteria(User.class).list();

        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("delete from " + User.class.getName());
        query.executeUpdate();

        transaction.commit();
        session.close();
    }
}
