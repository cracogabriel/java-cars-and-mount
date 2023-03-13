package craco.dev.springboot2essentials.util;

import craco.dev.springboot2essentials.domain.Car;

public class CarCreator {

    private static final Integer ID = 1;

    public static Car createCarToBeSaved() { // without id
        Car newCar = new Car();
        newCar.setYear(2000);
        newCar.setColor("vermelho");
        newCar.setName("Eclipse");

        return newCar;
    }

    public static Car createValidCar() {  // with id
        Car newCar = new Car();
        newCar.setId(ID);
        newCar.setYear(2000);
        newCar.setColor("vermelho");
        newCar.setName("Eclipse");

        return newCar;
    }

    public static Car createValidUpdatedCar() { // another car with id
        Car newCar = new Car();
        newCar.setId(ID);
        newCar.setYear(2000);
        newCar.setColor("prata");
        newCar.setName("Saveiro");

        return newCar;
    }

}
