package craco.dev.springboot2essentials.repository;

import craco.dev.springboot2essentials.domain.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Page<Car> findByColor(String color, Pageable pageable);
}
