package overridetech.jdbc.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import overridetech.jdbc.jpa.dao.CarDaoImpl;
import overridetech.jdbc.jpa.model.Car;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarDaoImpl carDao;

    public List<Car> getCarsByCount(int count) {
        return carDao.getCarsByCount(count);
    }
}
