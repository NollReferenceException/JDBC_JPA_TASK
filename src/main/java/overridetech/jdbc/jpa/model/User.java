package overridetech.jdbc.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

public class User {
    private Long id;
    private String name;
    private String lastName;
    private Byte age;

    private Car car;

    public User() {
    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public User(String name, String lastName, Byte age, Car car) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Имя - " + name + " | " +
                "Фамилия - " + lastName + " | " +
                "Возраст - " + age + " | " +
                "Модель - " + car.getModel() + " | " +
                "Серия - " + car.getSeries();
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
