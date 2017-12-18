package org.gl3.rentos.model;


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
    @JoinColumn(name = "user_id",insertable = false, updatable=false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "car_id",insertable = false, updatable=false)
    private Car car;
    @Column(name = "pickup_date", table = "rent")
    private Date pickup_date;
    @Column(name = "dropoff_date", table = "rent")
    private Date dropoff_date;

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

    public Date getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(Date pickup_date) {
        this.pickup_date = pickup_date;
    }

    public Date getDropoff_date() {
        return dropoff_date;
    }

    public void setDropoff_date(Date dropoff_date) {
        this.dropoff_date = dropoff_date;
    }
}
