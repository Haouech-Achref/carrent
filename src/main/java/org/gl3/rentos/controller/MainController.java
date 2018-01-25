package org.gl3.rentos.controller;

import org.gl3.rentos.model.Rent;
import org.gl3.rentos.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @ModelAttribute("rent")
    public Rent getRent(){

        return new Rent();

    }
    @RequestMapping(value = "/")
    public String home(HttpSession session, Model model)
    {
        User user =(User) session.getAttribute("user");

        boolean signedIn = user != null;
        model.addAttribute("signedIn", signedIn);
        return "home";
    }




}
