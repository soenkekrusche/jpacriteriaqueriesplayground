package de.stevenschwenke.java.jpacriteriaqueriesplayground;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

public class CarSpecifications {

    public static Specification<Car> everything() {

        return (car, query, builder) -> builder.conjunction();
    }

    public static Specification<Ownership> ownershipsWithCarsJoin() {

        return (ownership, query, builder) -> {
            ownership.join("car");
            return builder.conjunction();
        };
    }

    public static Specification<Car> with(boolean withOwnership) {

        return (car, query, builder) -> {

            if (withOwnership) {

                Subquery<Ownership> ownershipSubquery = query.subquery(Ownership.class);
                Root<Ownership> ownershipSubqueryRoot = ownershipSubquery.from(Ownership.class);
                ownershipSubquery.select(ownershipSubqueryRoot);

                Predicate ownershipPredicate = builder.equal(ownershipSubqueryRoot.get("car").get("id"), car.get("id"));
                ownershipSubquery.select(ownershipSubqueryRoot).where(ownershipPredicate);
                return builder.exists(ownershipSubquery);
            } else {

                Subquery<Ownership> ownershipSubquery = query.subquery(Ownership.class);
                Root<Ownership> ownershipSubqueryRoot = ownershipSubquery.from(Ownership.class);
                ownershipSubquery.select(ownershipSubqueryRoot);

                Predicate ownershipPredicate = builder.equal(ownershipSubqueryRoot.get("car").get("id"), car.get("id"));
                ownershipSubquery.select(ownershipSubqueryRoot).where(ownershipPredicate);
                return builder.exists(ownershipSubquery).not();
            }
        };
    }
}
