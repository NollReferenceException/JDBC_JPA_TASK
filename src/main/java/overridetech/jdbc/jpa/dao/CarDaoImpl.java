package overridetech.jdbc.jpa.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import overridetech.jdbc.jpa.dataSets.CarDataSet;
import overridetech.jdbc.jpa.dataSets.UserDataSet;
import overridetech.jdbc.jpa.model.Car;
import overridetech.jdbc.jpa.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CarDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public CarDaoImpl() {
    }

    public List<Car> getCarsByCount(int count) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<CarDataSet> list = session.createCriteria(CarDataSet.class).list();

        transaction.commit();
        session.close();

        return list.stream()
                .limit(count)
                .map(Car::new)
                .collect(Collectors.toList());
    }
}
