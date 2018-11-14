package de.stevenschwenke.java.jpacriteriaqueriesplayground;

import javax.persistence.*;

@Entity
public class Ownership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @OneToOne
    private Car car;

    public Ownership() {
    }

    public Ownership(User user, Car car) {
        this.user = user;
        this.car = car;
    }
}
