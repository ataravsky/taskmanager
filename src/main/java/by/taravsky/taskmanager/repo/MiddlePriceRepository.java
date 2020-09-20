package by.taravsky.taskmanager.repo;

import by.taravsky.taskmanager.model.MiddlePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiddlePriceRepository extends JpaRepository<MiddlePrice, Long> {
    MiddlePrice findByBrandAndModelAndYear(String brand, String model, Integer year);
}
