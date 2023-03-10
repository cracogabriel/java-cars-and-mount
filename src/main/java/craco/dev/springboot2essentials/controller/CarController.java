package craco.dev.springboot2essentials.controller;

import craco.dev.springboot2essentials.domain.Car;
import craco.dev.springboot2essentials.requests.CarPostRequestBody;
import craco.dev.springboot2essentials.requests.CarPutRequestBody;
import craco.dev.springboot2essentials.services.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cars")
public class CarController {
    public CarController(CarService carService) {
        this.carService = carService;
    }

    private CarService carService;

    @GetMapping()
    public ResponseEntity<List<Car>> listAll() {
        return ResponseEntity.ok(carService.listAll());
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Car>> findAllCarsByColorName(@RequestParam String color) {
        return ResponseEntity.ok(carService.findAllByColorName(color));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Car> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(carService.findByIdOrThrowBadRequest(id));
    }

    @PostMapping()
    public ResponseEntity<Car> save(@RequestBody @Valid CarPostRequestBody carPostRequestBody) {
        return new ResponseEntity<Car>(carService.save(carPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Car> edit(@RequestBody @Valid CarPutRequestBody carPutRequestBody) {
        carService.edit(carPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        carService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
