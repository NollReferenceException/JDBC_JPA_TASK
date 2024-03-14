package overridetech.jdbc.jpa.model;

import overridetech.jdbc.jpa.dataSets.CarDataSet;


public class Car {
    private long id;
    private String model;

    private int series;

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public Car(CarDataSet carDataSet) {
        this.id = carDataSet.getId();
        this.model = carDataSet.getModel();
        this.series = carDataSet.getSeries();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
