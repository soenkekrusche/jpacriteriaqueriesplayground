package de.stevenschwenke.java.jpacriteriaqueriesplayground;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
public interface CarRepository extends
        JpaRepository<Car, Long>,
        PagingAndSortingRepository<Car, Long>,
        JpaSpecificationExecutor<Car> {
}
