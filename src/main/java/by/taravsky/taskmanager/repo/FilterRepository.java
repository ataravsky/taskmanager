package by.taravsky.taskmanager.repo;

import by.taravsky.taskmanager.model.Auto;
import by.taravsky.taskmanager.model.Filter;
import by.taravsky.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long> {
    Iterable<Filter> findByBrand(String brand);

   Iterable<Filter> findByUsercurrent(String usercurrent);

}
