package craco.dev.springboot2essentials.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CarPostRequestBody {
    public CarPostRequestBody(String name, String color, Integer year) {
        this.name = name;
        this.color = color;
        this.year = year;
    }

    @NotEmpty(message = "The car name can't be empty")
    private String name;
    @NotEmpty(message = "The car's color can't be empty")
    private String color;
    private Integer year;

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
