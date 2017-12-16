package org.gl3.rentos.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "rent",schema = "rentos")
public class Rent implements Serializable {
    @Id
    @ManyToOne(targetEntity = User.class )
    private int user_id;
    @Id
    @ManyToOne(targetEntity = Car.class)
    private int car_id;
    @Column(name = "pickup_date", table = "rent")
    private Date pickup_date;
    @Column(name = "dropoff_date", table = "rent")
    private Date dropoff_date;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
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
