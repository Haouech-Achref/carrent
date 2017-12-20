package org.gl3.rentos.controller;

import org.gl3.rentos.model.Car;
import org.gl3.rentos.model.Rent;
import org.gl3.rentos.repository.CarRepository;
import org.gl3.rentos.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class RentController {

    @Autowired
    RentRepository rentRepository;

    @Autowired
    CarRepository carRepository;

    @ModelAttribute("rent")
    public Rent getRent(){
        return new Rent();

    }

    @RequestMapping("/rent")
    public String rent(@RequestBody Rent rent, Model model)
    {
        model.addAttribute("car",rent);
        rentRepository.save(rent);
        return "rent";
    }
    @RequestMapping(value = "/home")
    public String searchAvailable()
    {
        return "home";
    }

    @RequestMapping(value = "/availablecars", method = RequestMethod.POST)
    public String searchAvailable(Rent rent)
    {

        ArrayList<Rent> unavailableDates = rentRepository.findAllByPickupBetweenOrDropoffBetween(rent.getPickup(),rent.getDropoff(),rent.getPickup(),rent.getDropoff());
        unavailableDates.forEach(car -> System.out.println(car.getCar().getRegistration_number()));
        ArrayList<Car> unavailableCars = new ArrayList<>();
        unavailableDates.forEach(rent1 -> unavailableCars.add(rent1.getCar()));
        ArrayList<Car> availableCars = new ArrayList<>();
        carRepository.findAll().forEach(availableCars::add);
        availableCars.removeAll(unavailableCars);

        availableCars.forEach(car -> System.out.println("available: " + car.getCar_id()));
        return "home";}
}
