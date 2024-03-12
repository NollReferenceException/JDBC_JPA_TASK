package overridetech.jdbc.jpa.dataSets;

import overridetech.jdbc.jpa.model.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserDataSet implements Serializable {

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
    private CarDataSet carDataSet;

    @SuppressWarnings("UnusedDeclaration")
    public UserDataSet() {
    }

    public UserDataSet(String name, String lastName, byte age) {

        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public UserDataSet(User user) {

        this.name = user.getName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.carDataSet = new CarDataSet(user.getCar());
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

    public CarDataSet getCarDataSet() {
        return carDataSet;
    }

    public void setCarDataSet(CarDataSet carDataSet) {
        this.carDataSet = carDataSet;
    }
}
