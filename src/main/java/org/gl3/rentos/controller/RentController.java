package org.gl3.rentos.controller;

import org.gl3.rentos.model.Car;
import org.gl3.rentos.model.Rent;
import org.gl3.rentos.model.User;
import org.gl3.rentos.repository.CarRepository;
import org.gl3.rentos.repository.RentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class RentController {

    private static final Logger errorLogger = LoggerFactory.getLogger("error");
    private static final Logger infoLogger = LoggerFactory.getLogger("info");

    @Autowired
    RentRepository rentRepository;

    @Autowired
    CarRepository carRepository;

    @ModelAttribute("rent")
    public Rent getRent(){

        return new Rent();

    }

    @GetMapping("/rent/{id}")
    public String rent(@PathVariable int id, Model model, HttpSession session)
    {
        User user =(User) session.getAttribute("user");
        boolean signedIn = user != null;
        model.addAttribute("signedIn", signedIn);
        if (user == null) {
            errorLogger.error("Cannot complete rent operation: user not signed in.");
            return "redirect:/signin";
        }

        Rent rent =(Rent) session.getAttribute("rent");
        Car car = carRepository.findOne(id);
        rent.setCar(car);
        rent.setUser(user);
        rentRepository.save(rent);
        session.removeAttribute("rent");
        infoLogger.info("Rent operation complete. Redirecting to home page.");
        return "home";
    }

    @RequestMapping(value = "/availablecars", method = RequestMethod.POST)
    public String searchAvailable(Rent rent, Model model, HttpSession session)
    {
        User user =(User) session.getAttribute("user");
        boolean signedIn = user != null;
        model.addAttribute("signedIn", signedIn);
        ArrayList<Rent> unavailableDates = rentRepository.findAllByPickupBetweenOrDropoffBetween(rent.getPickup(),rent.getDropoff(),rent.getPickup(),rent.getDropoff());
        unavailableDates.forEach(car -> System.out.println(car.getCar().getRegistration_number()));
        ArrayList<Car> unavailableCars = new ArrayList<>();
        unavailableDates.forEach(rent1 -> unavailableCars.add(rent1.getCar()));
        ArrayList<Car> availableCars = new ArrayList<>();
        carRepository.findAll().forEach(availableCars::add);
        availableCars.removeAll(unavailableCars);
        model.addAttribute("cars",availableCars);
        session.setAttribute("rent",rent);
        infoLogger.info("Showing available cars between " + rent.getPickup() + " and" + rent.getDropoff() + ".");
        return "availablecars";
    }
}
