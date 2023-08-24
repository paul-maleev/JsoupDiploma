package com.example.jsoupdiploma;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ObservableCountry {

    private SimpleStringProperty name;
    private SimpleStringProperty  capital;
    private SimpleIntegerProperty population;
    private SimpleDoubleProperty area;

    public ObservableCountry(SimpleStringProperty name, SimpleStringProperty capital, SimpleIntegerProperty population, SimpleDoubleProperty area) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCapital() {
        return capital.get();
    }

    @Override
    public String toString() {
        return "ObservableCountry{" +
                "name=" + name +
                ", capital=" + capital +
                ", population=" + population +
                ", area=" + area +
                '}';
    }

    public SimpleStringProperty capitalProperty() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital.set(capital);
    }

    public int getPopulation() {
        return population.get();
    }

    public SimpleIntegerProperty populationProperty() {
        return population;
    }

    public void setPopulation(int population) {
        this.population.set(population);
    }

    public double getArea() {
        return area.get();
    }

    public SimpleDoubleProperty areaProperty() {
        return area;
    }

    public void setArea(double area) {
        this.area.set(area);
    }
}
