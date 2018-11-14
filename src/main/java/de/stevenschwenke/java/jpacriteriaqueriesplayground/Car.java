package de.stevenschwenke.java.jpacriteriaqueriesplayground;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private User user;

    public Car() {
    }

    public Car(User user) {
        this.user = user;
    }
}
