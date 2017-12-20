package org.gl3.rentos.controller;

import org.gl3.rentos.model.Rent;
import org.gl3.rentos.model.User;
import org.gl3.rentos.repository.RentRepository;
import org.gl3.rentos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RentController {

    @Autowired
    RentRepository rentRepository;

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
    {return "home";}

    @RequestMapping(value = "/availablecars", method = RequestMethod.POST)
    public String searchAvailable(Rent rent)
    {
        System.out.println(rent.getDropoff()+ "   blyat   "+ rent.getPickup());
        return "home";}
}
