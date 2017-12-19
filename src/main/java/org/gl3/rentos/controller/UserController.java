package org.gl3.rentos.controller;
import org.gl3.rentos.model.User;


import org.gl3.rentos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @ModelAttribute("user")
     public User getUser(){
            return new User();

    }

    @RequestMapping("")
    public String home(){
        return "home";
    }

    @RequestMapping("/signup")
    public String signUp()
    {
        return "formSignUp";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String addUser(User user)
    {  User userDB =  userRepository.findByEmail(user.getEmail());

    user.setRole("USER");
    if (userDB == null)
        System.out.println("Email n'existe pas aaa");
        userRepository.save(user);
        return "redirect:cars";
    }
}
