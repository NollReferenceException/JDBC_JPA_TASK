package overridetech.jdbc.jpa.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import overridetech.jdbc.jpa.model.Car;


import java.util.List;

@Repository
public class CarDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    @Value("${requests.maxCar}")
    private int maxCar;

    public CarDaoImpl() {
    }

    public List<Car> getCarsByCount(int count) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from " + Car.class.getName());

        if (count < maxCar) {
            query.setMaxResults(count);
        }

        List<Car> list = query.list();

        transaction.commit();
        session.close();

        return list;
    }
}
