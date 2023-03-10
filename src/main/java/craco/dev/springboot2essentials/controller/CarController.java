package craco.dev.springboot2essentials.controller;

import craco.dev.springboot2essentials.domain.Car;
import craco.dev.springboot2essentials.requests.CarPostRequestBody;
import craco.dev.springboot2essentials.requests.CarPutRequestBody;
import craco.dev.springboot2essentials.services.CarService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<Car>> listAll(Pageable pageable) {
        return ResponseEntity.ok(carService.listAll(pageable));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<Page<Car>> findAllCarsByColorName(@RequestParam String color, Pageable pageable) {
        return ResponseEntity.ok(carService.findAllByColorName(color, pageable));
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
