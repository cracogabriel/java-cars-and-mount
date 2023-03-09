package craco.dev.springboot2essentials.controller;

import craco.dev.springboot2essentials.domain.Car;
import craco.dev.springboot2essentials.services.CarService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cars")
public class CarController {
    public CarController(CarService carService) {
        this.carService = carService;
    }

    private CarService carService;

    @GetMapping()
    public ResponseEntity<ArrayList<Car>> listAll() {
        return ResponseEntity.ok(carService.listAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Car> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(carService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<Car> save(@RequestBody Car car) {
        return new ResponseEntity<Car>(carService.save(car), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Car> edit(@RequestBody Car car) {
        carService.edit(car);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path ="{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        carService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
