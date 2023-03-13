package craco.dev.springboot2essentials.services;

import craco.dev.springboot2essentials.domain.Car;
import craco.dev.springboot2essentials.exception.BadRequestException;
import craco.dev.springboot2essentials.repository.CarRepository;
import craco.dev.springboot2essentials.util.CarCreator;
import craco.dev.springboot2essentials.util.CarPostRequestBodyCreator;
import craco.dev.springboot2essentials.util.CarPutRequestBodyCreator;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class CarServiceTest {
    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        PageImpl<Car> carPage = new PageImpl<>(List.of(CarCreator.createValidCar()));
        BDDMockito.when(carRepository.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(carPage);

        BDDMockito.when(carRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(CarCreator.createValidCar()));

        BDDMockito.when(carRepository.findByColor(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(carPage);

        BDDMockito.when(carRepository.save(ArgumentMatchers.any(Car.class)))
                .thenReturn(CarCreator.createValidCar());

        BDDMockito.doNothing().when(carRepository).delete(ArgumentMatchers.any(Car.class));

    }

    @Test
    @DisplayName("List returns list of car inside page when successful")
    void list_ReturnListOfCarsInsidePageObject_WhenSuccessful() {
        String expectedName = CarCreator.createValidCar().getName();
        Page<Car> pageableCars = carService.listAll(PageRequest.of(1, 1));

        Assertions.assertThat(pageableCars).isNotNull();
        Assertions.assertThat(pageableCars.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(pageableCars.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequest return car when successful")
    void findByIdOrThrowBadRequest_ReturnCar_WhenSuccessful() {
        Integer expectedId = CarCreator.createValidCar().getId();
        Car car = carService.findByIdOrThrowBadRequest(1);

        Assertions.assertThat(car).isNotNull();
        Assertions.assertThat(car.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequest throws bad request when car is not founded")
    void findByIdOrThrowBadRequest_ThrowsBadRequestException_WhenCarNotFounded() {
        BDDMockito.when(carRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() ->  carService.findByIdOrThrowBadRequest(1))
                .isInstanceOf(BadRequestException.class);

    }


    @Test
    @DisplayName("GetAllByColor return car page list when successful")
    void getAllByColor_ReturnPageableCarList_WhenSuccessful() {
        String expectedColor = CarCreator.createValidCar().getColor();
        Page<Car> pageableCars = carService.findAllByColorName("vermelho", null);

        Assertions.assertThat(pageableCars).isNotNull();
        Assertions.assertThat(pageableCars.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(pageableCars.toList().get(0).getColor()).isEqualTo(expectedColor);
    }

    @Test
    @DisplayName("GetAllByColor return empty car page list when car not found")
    void getAllByColor_ReturnEmptyPageableCarList_WhenCarNotExists() {
        BDDMockito.when(carRepository.findByColor(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(new PageImpl<Car>(Collections.emptyList()));

        Page<Car> pageableCars = carService.findAllByColorName("yasuo", null);
        Assertions.assertThat(pageableCars.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Save return car created when successful")
    void save_ReturnCar_WhenSuccessful() {
        String expectedName = CarCreator.createValidCar().getName();
        Car createdCar = carService.save(CarPostRequestBodyCreator.createCarPostRequestBodyToBeSaved());

        Assertions.assertThat(createdCar).isNotNull();
        Assertions.assertThat(createdCar.getName()).isNotNull().isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Edit updates car when successful")
    void edit_UpdatesCar_WhenSuccessful() {
        Assertions.assertThatCode(() -> carService.edit(CarPutRequestBodyCreator.createCarPutRequestBodyToBeSaved()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Delete updates car when successful")
    void delete_DeletesCar_WhenSuccessful() {
        Assertions.assertThatCode(() -> carService.delete(1))
                .doesNotThrowAnyException();
    }
}