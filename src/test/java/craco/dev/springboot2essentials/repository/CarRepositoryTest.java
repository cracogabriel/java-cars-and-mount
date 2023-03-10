package craco.dev.springboot2essentials.repository;

import craco.dev.springboot2essentials.domain.Car;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Car Repository")
class CarRepositoryTest {

    private Car createCar() {
        Car newCar = new Car();
        newCar.setYear(2000);
        newCar.setColor("vermelho");
        newCar.setName("Eclipse");

        return newCar;
    }

    @Autowired
    private CarRepository carRepository;

    @Test
    @DisplayName("Save created car when successful")
    void save_PersistentCar_WhenSuccessful() {
        Car carToBeSaved = createCar();
        Car carSaved = this.carRepository.save(carToBeSaved);

        Assertions.assertThat(carSaved).isNotNull();
        Assertions.assertThat(carSaved.getId()).isNotNull();

        Assertions.assertThat(carSaved.getName()).isEqualTo(carToBeSaved.getName());
        Assertions.assertThat(carSaved.getColor()).isEqualTo(carToBeSaved.getColor());
        Assertions.assertThat(carSaved.getYear()).isEqualTo(carToBeSaved.getYear());
    }

    @Test
    @DisplayName("Save updated car when successful")
    void update_UpdateCar_WhenSuccessful() {
        Car carToBeSaved = createCar();
        Car carSaved = this.carRepository.save(carToBeSaved);

        carSaved.setName("350i");
        carSaved.setYear(2001);

        Car carUpdated = this.carRepository.save(carSaved);

        Assertions.assertThat(carUpdated).isNotNull();
        Assertions.assertThat(carUpdated.getId()).isNotNull();

        Assertions.assertThat(carUpdated.getName()).isEqualTo(carSaved.getName());
        Assertions.assertThat(carUpdated.getColor()).isEqualTo(carSaved.getColor());
        Assertions.assertThat(carUpdated.getYear()).isEqualTo(carSaved.getYear());
    }

    @Test
    @DisplayName("Delete created car when successful")
    void delete_DeleteCar_WhenSuccessful() {
        Car carToBeSaved = createCar();
        Car carSaved = this.carRepository.save(carToBeSaved);

        this.carRepository.delete(carSaved);

        Optional<Car> carFinded = this.carRepository.findById(carSaved.getId());

        Assertions.assertThat(carFinded.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Find by color and return list of car when successful")
    void findByColor_ReturnListOfCars_WhenSuccessful() {
        Car carToBeSaved = createCar();
        Car carSaved = this.carRepository.save(carToBeSaved);

        List<Car> carsFounded = this.carRepository.findByColor(carSaved.getColor());

        Assertions.assertThat(carsFounded).isNotEmpty().contains(carSaved);
    }

    @Test
    @DisplayName("Find by color and return empty list when car is not founded")
    void findByColor_ReturnList_WhenCarIsNotFounded() {
        List<Car> carsFounded = this.carRepository.findByColor("yasuo");
        Assertions.assertThat(carsFounded).isEmpty();
    }

    //exceptions
    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty ")
    void save_ThrowsConstraintViolationException_WhenNameIsEmpty() {
        Car carToBeSaved = createCar();
        carToBeSaved.setName(null);
        Assertions.assertThatThrownBy(() -> this.carRepository.save(carToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when color is empty ")
    void save_ThrowsConstraintViolationException_WhenColorIsEmpty() {
        Car carToBeSaved = createCar();
        carToBeSaved.setColor(null);
        Assertions.assertThatThrownBy(() -> this.carRepository.save(carToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }

}