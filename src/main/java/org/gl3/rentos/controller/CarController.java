package org.gl3.rentos.controller;

import org.gl3.rentos.model.Car;
import org.gl3.rentos.model.Tester;
import org.gl3.rentos.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {

    @Autowired
    CarRepository carRepository;

    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    public void addCar(@RequestBody Car tester)
    {
        System.out.println(tester.getColor() + "   " + tester.getModel());
        carRepository.save(tester); }

    @RequestMapping(value = "/cars")
    public List<Car> aCar(Car car)
    {
        List<Car> lister = new ArrayList<>();
        carRepository.findAll().forEach(lister::add);
        return lister;
    }
}
