package craco.dev.springboot2essentials.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Car {
    public Car(Integer id, String name, String color, Integer year) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.year = year;
    }

    @Id
    @Column(name="CAR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="CAR_NAME")
    @NotEmpty(message = "The car name can't be empty")
    private String name;
    @Column(name="CAR_COLOR")
    @NotEmpty(message = "The car's color can't be empty")
    private String color;
    @Column(name="CAR_YEAR")
    private Integer year;

    public Car() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
