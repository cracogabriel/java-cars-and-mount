package craco.dev.springboot2essentials.services;

import craco.dev.springboot2essentials.domain.Car;
import craco.dev.springboot2essentials.exception.BadRequestException;
import craco.dev.springboot2essentials.repository.CarRepository;
import craco.dev.springboot2essentials.requests.CarPostRequestBody;
import craco.dev.springboot2essentials.requests.CarPutRequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Page<Car> listAll(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    public Page<Car> findAllByColorName(String color, Pageable pageable) {
        return carRepository.findByColor(color, pageable);
    }

    public Car findByIdOrThrowBadRequest(Integer id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Carro não encontrado"));
    }

    public Car save(CarPostRequestBody carPostRequestBody) {
        var newCar = new Car();
        newCar.setName(carPostRequestBody.getName());
        newCar.setColor(carPostRequestBody.getColor());
        newCar.setYear(carPostRequestBody.getYear());
        return carRepository.save(newCar);
    }

    public void edit(CarPutRequestBody carPutRequestBody) {
        Car savedCar = findByIdOrThrowBadRequest(carPutRequestBody.getId());

        var newCar = new Car();
        newCar.setId(savedCar.getId());
        newCar.setName(carPutRequestBody.getName());
        newCar.setColor(carPutRequestBody.getColor());
        newCar.setYear(carPutRequestBody.getYear());
        carRepository.save(newCar);
    }

    public void delete(Integer id) {
        carRepository.deleteById(id);
    }
}
