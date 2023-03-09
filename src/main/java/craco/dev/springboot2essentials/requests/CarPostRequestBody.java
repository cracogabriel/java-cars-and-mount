package craco.dev.springboot2essentials.requests;

public class CarPostRequestBody {
    public CarPostRequestBody(String name, String color, Integer year) {
        this.name = name;
        this.color = color;
        this.year = year;
    }

    private String name;
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
