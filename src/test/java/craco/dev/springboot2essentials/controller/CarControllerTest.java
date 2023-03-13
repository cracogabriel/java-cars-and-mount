package craco.dev.springboot2essentials.controller;

import craco.dev.springboot2essentials.domain.Car;
import craco.dev.springboot2essentials.requests.CarPostRequestBody;
import craco.dev.springboot2essentials.requests.CarPutRequestBody;
import craco.dev.springboot2essentials.services.CarService;
import craco.dev.springboot2essentials.util.CarCreator;
import craco.dev.springboot2essentials.util.CarPostRequestBodyCreator;
import craco.dev.springboot2essentials.util.CarPutRequestBodyCreator;
import org.assertj.core.api.Assert;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;


@ExtendWith(SpringExtension.class)
class CarControllerTest {
    // @InjectMocks | when the own class must be tested
    // @Mock | all classes in the class controller

    @InjectMocks
    private CarController carController;

    @Mock
    private CarService carService;

    @BeforeEach
    void setUp() {
        PageImpl<Car> carPage = new PageImpl<>(List.of(CarCreator.createValidCar()));
        BDDMockito.when(carService.listAll(ArgumentMatchers.any()))
                .thenReturn(carPage);

        BDDMockito.when(carService.findByIdOrThrowBadRequest(ArgumentMatchers.any()))
                .thenReturn(CarCreator.createValidCar());

        BDDMockito.when(carService.findAllByColorName(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(carPage);

        BDDMockito.when(carService.save(ArgumentMatchers.any(CarPostRequestBody.class)))
                .thenReturn(CarCreator.createValidCar());

        BDDMockito.doNothing().when(carService).delete(ArgumentMatchers.any());

    }

    @Test
    @DisplayName("List returns list of car inside page when successful")
    void list_ReturnListOfCarsInsidePageObject_WhenSuccessful() {
        String expectedName = CarCreator.createValidCar().getName();
        Page<Car> pageableCars = carController.listAll(null).getBody();

        Assertions.assertThat(pageableCars).isNotNull();
        Assertions.assertThat(pageableCars.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(pageableCars.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("FindById return car when successful")
    void findById_ReturnCar_WhenSuccessful() {
        Integer expectedId = CarCreator.createValidCar().getId();
        Car car = carController.getById(1).getBody();

        Assertions.assertThat(car).isNotNull();
        Assertions.assertThat(car.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("GetAllByColor return car page list when successful")
    void getAllByColor_ReturnPageableCarList_WhenSuccessful() {
        String expectedColor = CarCreator.createValidCar().getColor();
        Page<Car> pageableCars = carController.findAllCarsByColorName("vermelho", null).getBody();

        Assertions.assertThat(pageableCars).isNotNull();
        Assertions.assertThat(pageableCars.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(pageableCars.toList().get(0).getColor()).isEqualTo(expectedColor);
    }

    @Test
    @DisplayName("GetAllByColor return empty car page list when car not found")
    void getAllByColor_ReturnEmptyPageableCarList_WhenCarNotExists() {
        BDDMockito.when(carService.findAllByColorName(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(new PageImpl<Car>(Collections.emptyList()));

        Page<Car> pageableCars = carController.findAllCarsByColorName("yasuo", null).getBody();
        Assertions.assertThat(pageableCars.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Save return car created when successful")
    void save_ReturnCar_WhenSuccessful() {
        String expectedName = CarCreator.createValidCar().getName();
        Car createdCar = carController.save(CarPostRequestBodyCreator.createCarPostRequestBodyToBeSaved()).getBody();

        Assertions.assertThat(createdCar).isNotNull();
        Assertions.assertThat(createdCar.getName()).isNotNull().isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Edit updates car when successful")
    void edit_UpdatesCar_WhenSuccessful() {
        Assertions.assertThatCode(() ->carController.edit(CarPutRequestBodyCreator.createCarPutRequestBodyToBeSaved()).getBody())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Delete updates car when successful")
    void delete_DeletesCar_WhenSuccessful() {
        Assertions.assertThatCode(() -> carService.delete(1))
                .doesNotThrowAnyException();
    }

}