package org.gl3.rentos.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "car", schema = "rentos")
public class Car implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int car_id;
    @Column(name = "registration_number", table = "car")
    private String registration_number;
    @Column(name = "manufacturer", table = "car")
    private String manufacturer;
    @Column(name = "model", table = "car")
    private String model;
    @Column(name = "mileage", table = "car")
    private int mileage;
    @Column(name = "color", table = "car")
    private String color;
    @Column(name = "power", table = "car")
    private int power;
    @Column(name = "price", table = "car")
    private int price;
    @Column(name = "is_available", table = "car")
    private boolean isAvailable;
    @Column(name = "picture", table = "car")
    private String picture;


    public Car() {
    }

    public Car(String registration_number, String manufacturer, String model, int mileage, String color, int power, int price, boolean isAvailable, String picture) {
        this.registration_number = registration_number;
        this.manufacturer = manufacturer;
        this.model = model;
        this.mileage = mileage;
        this.color = color;
        this.power = power;
        this.price = price;
        this.isAvailable = isAvailable;
        this.picture = picture;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int id) {
        this.car_id = id;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
