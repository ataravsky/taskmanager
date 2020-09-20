package by.taravsky.taskmanager.repo;

import by.taravsky.taskmanager.model.Auto;
import by.taravsky.taskmanager.model.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {
    Optional<Auto> findByUrl(String url);
    Optional<Auto> findByBrand(String brand);

    Page<Auto> findAll(Pageable pageable);

    List<Auto> findByModelContainingAndBrandContainingAndYear(
            String model, String brand, Integer year);

    List<Auto> findByModelContainingAndBrandContaining(
            String model, String brand);

    List<Auto> findByBody(String body);

    @Query("SELECT a FROM Auto a WHERE (:model is '' or a.model = :model) and (:city is '' or a.city = :city) and (:body is '' or a.body = :body) and (:engine is '' or a.engine = :engine) and (:transmission is '' or a.transmission = :transmission) and (:brand is '' or a.brand = :brand) and ((:mileagefrom is null and :mileagebefore is null) or a.mileage between :mileagefrom and :mileagebefore) and ((:costfrom is null and :costbefore is null) or a.cost between :costfrom and :costbefore) and ((:capacityfrom is null and :capacitybefore is null) or a.capacity between :capacityfrom and :capacitybefore) and ((:yearfrom is null and :yearbefore is null) or a.year between :yearfrom and :yearbefore)")
    List<Auto> findByFilter(String model, String engine, String city, Long mileagefrom, Long mileagebefore, String body, String brand, String transmission, Long costfrom, Long costbefore, Float capacityfrom, Float capacitybefore, Integer yearfrom, Integer yearbefore);

}
