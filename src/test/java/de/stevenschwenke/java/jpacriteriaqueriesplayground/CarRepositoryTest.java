package de.stevenschwenke.java.jpacriteriaqueriesplayground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnershipRepository ownershipRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void findAllJoined() {

        User user = userRepository.save(new User());

        Car carWithOwnership = new Car(user);
        carRepository.save(carWithOwnership);
        ownershipRepository.save(new Ownership(user, carWithOwnership));

        Car submittedCar = new Car(user);
        carRepository.save(submittedCar);

        Car reviewedCar1 = new Car(user);
        carRepository.save(reviewedCar1);

        List<Ownership> joins = ownershipRepository.findAll(CarSpecifications.ownershipsWithCarsJoin());
        assertEquals(1, joins.size());
    }

    @Test
    @Transactional
    void ownershipTest() {

        User user = userRepository.save(new User());

        Car carWithOwnership = new Car(user);
        carRepository.save(carWithOwnership);
        ownershipRepository.save(new Ownership(user, carWithOwnership));

        Car submittedCar = new Car(user);
        carRepository.save(submittedCar);

        Car reviewedCar1 = new Car(user);
        carRepository.save(reviewedCar1);

        List<Car> everything = carRepository.findAll(CarSpecifications.everything());
        assertEquals(3, everything.size());

        List<Car> owned = carRepository.findAll(CarSpecifications.with(true));
        assertEquals(1, owned.size());

        List<Car> unowned = carRepository.findAll(CarSpecifications.with(false));
        assertEquals(2, unowned.size());
    }
}