package overridetech.jdbc.jpa.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;

    @OneToOne(cascade = CascadeType.ALL)
    private Car car;

    @SuppressWarnings("UnusedDeclaration")
    public User() {
    }

    public User(String name, String lastName, byte age) {

        this.name = name;
        this.lastName = lastName;
        this.age = age;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "ID - " + id + " | " +
                "Имя - " + name + " | " +
                "Фамилия - " + lastName + " | " +
                "Возраст - " + age + " | " +
                "Модель - " + car.getModel() + " | " +
                "Серия - " + car.getSeries();
    }
}
