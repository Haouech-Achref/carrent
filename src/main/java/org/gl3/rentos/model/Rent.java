package org.gl3.rentos.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "rent",schema = "rentos")
public class Rent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @Column(name = "pickup", table = "rent")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pickup;
    @Column(name = "dropoff", table = "rent")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dropoff;

    public Rent(int id, User user, Car car) {
        this.id = id;
        this.user = user;
        this.car = car;
    }

    public Rent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getPickup() {
        return pickup;
    }

    public void setPickup(Date pickup_date) {
        this.pickup = pickup_date;
    }

    public Date getDropoff() {
        return dropoff;
    }

    public void setDropoff(Date dropoff_date) {
        this.dropoff = dropoff_date;
    }
}
