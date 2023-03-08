package craco.dev.springboot2essentials.repository;

import craco.dev.springboot2essentials.domain.Car;

import java.util.List;

public interface CarRepository {
    List<Car> listAll();
}
