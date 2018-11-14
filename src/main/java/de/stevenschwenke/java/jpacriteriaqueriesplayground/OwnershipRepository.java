package de.stevenschwenke.java.jpacriteriaqueriesplayground;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface OwnershipRepository extends
        JpaRepository<Ownership,Long>,
        JpaSpecificationExecutor<Ownership> {
}
