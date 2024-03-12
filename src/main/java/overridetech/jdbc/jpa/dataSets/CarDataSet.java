package overridetech.jdbc.jpa.dataSets;

import overridetech.jdbc.jpa.model.Car;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class CarDataSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String model;

    @Column
    private int series;

    public CarDataSet(Car car) {
        this.model = car.getModel();
        this.series = car.getSeries();
    }

    public CarDataSet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}