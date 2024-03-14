package overridetech.jdbc.jpa.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import overridetech.jdbc.jpa.dataSets.CarDataSet;
import overridetech.jdbc.jpa.model.Car;


import java.util.List;
import java.util.stream.Collectors;

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

        Query query = session.createQuery("from " + CarDataSet.class.getName());

        if (count < maxCar) {
            query.setMaxResults(count);
        }

        List<CarDataSet> list = query.list();

        transaction.commit();
        session.close();

        return list.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }
}
