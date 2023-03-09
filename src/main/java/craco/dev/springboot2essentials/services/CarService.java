package craco.dev.springboot2essentials.services;

import craco.dev.springboot2essentials.domain.Car;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CarService {
    private static ArrayList<Car> mockedCars = new ArrayList<Car>();


    public ArrayList<Car> listAll() {
        return mockedCars;
    }

    public Car getById(Integer id) {
        return mockedCars.stream()
                .filter(car -> car.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carro não encontrado"));
    }

    public Car save(Car car) {
        car.setId(ThreadLocalRandom.current().nextInt(3, 10000000));
        mockedCars.add(car);
        return car;
    }

    public void edit(Car car) {
        mockedCars.remove(car.getId());
        mockedCars.add(car);
    }

    public void delete(Integer id){
        mockedCars.remove(getById(id));
    }
}
