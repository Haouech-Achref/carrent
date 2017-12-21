package org.gl3.rentos.controller;

import org.gl3.rentos.model.Rent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @ModelAttribute("rent")
    public Rent getRent(){

        return new Rent();

    }
    @RequestMapping(value = "/")
    public String home()
    {
         return "home";
    }




}
