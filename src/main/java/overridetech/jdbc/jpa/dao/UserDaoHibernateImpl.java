package overridetech.jdbc.jpa.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import overridetech.jdbc.jpa.dataSets.CarDataSet;
import overridetech.jdbc.jpa.dataSets.UserDataSet;
import overridetech.jdbc.jpa.model.Car;
import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.util.Util;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDaoHibernateImpl implements UserDao {
    private final String tableId = "users.users";
    private final SessionFactory sessionFactory = Util.getInstance().getSessionFactory();

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

        session.save(new UserDataSet(name, lastName, age));

        transaction.commit();
        session.close();
    }

    @Override
    public void saveFullUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(new UserDataSet(user));

        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getUserByModelAndSerial(String model, int series) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<UserDataSet> users;

        Query query = session.createQuery("from " + UserDataSet.class.getName() +
                " u left join fetch u.carDataSet where (model = :model) AND (series = :series)");

        query.setParameter("model", model);
        query.setParameter("series", series);

        users = query.list();

        transaction.commit();
        session.close();

        return users.stream()
                .map(userData -> new User(userData.getName(),
                        userData.getLastName(),
                        userData.getAge(),
                        new Car(userData.getCarDataSet().getModel(),
                                userData.getCarDataSet().getSeries())))
                .collect(Collectors.toList());
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(session.load(UserDataSet.class, id));

        session.flush();
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<UserDataSet> list = session.createCriteria(UserDataSet.class).list();

        transaction.commit();
        session.close();

        return list.stream()
                .map(userData -> new User(userData.getName(),
                        userData.getLastName(),
                        userData.getAge(),
                        new Car(userData.getCarDataSet().getModel(),
                                userData.getCarDataSet().getSeries())))
                .collect(Collectors.toList());
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("delete from " + UserDataSet.class.getName());
        query.executeUpdate();

        transaction.commit();
        session.close();
    }
}
