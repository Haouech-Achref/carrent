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
public class CarController {

    @Autowired
    CarRepository carRepository;

    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    public void addCar(@RequestBody Car tester)
    {
        System.out.println(tester.getColor() + "   " + tester.getModel());
        carRepository.save(tester); }

    @RequestMapping(value = "/cars")
    public String listCar(Car car, Model model)
    {
        List<Car> lister = new ArrayList<>();
        carRepository.findAll().forEach(lister::add);
        model.addAttribute("cars",lister);
        return "cars";
    }


}
