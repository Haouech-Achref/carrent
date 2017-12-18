package org.gl3.rentos.controller;

import org.gl3.rentos.model.Car;
import org.gl3.rentos.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/cars")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @ModelAttribute("car")
    public Car getCar(){
        return new Car();
    }

    @RequestMapping(value = "")
    public String listCar(Car car, Model model)
    {
        List<Car> lister = new ArrayList<>();
        carRepository.findAll().forEach(lister::add);
        model.addAttribute("cars",lister);
        return "cars";
    }

    @RequestMapping(value = "/add")
    public String addCar()
    {
        return "formCar";
        }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCar(@RequestBody Car tester)
    {
        System.out.println("**********************************************post mta3 el add");
       // carRepository.save(tester);
        return "car";
    }





}
