package org.gl3.rentos.controller;
import org.gl3.rentos.model.User;


import org.gl3.rentos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller


public class UserController {
    private  String validEmailSignUp="Sign Up ";
    private  String validEmailSignIn="Welcome back";


    @Autowired
    UserRepository userRepository;

    @ModelAttribute("user")
     public User getUser(){
            return new User();

    }

    @RequestMapping("/signin")
    public String signin(Model model){

        model.addAttribute("message",validEmailSignIn);
        return "signin";
    }


    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String validSignIn(User user , Model model , HttpSession session)
    {

        User userDB =  userRepository.findByEmail(user.getEmail());

        if (userDB == null)
            {System.out.println("*********** INvalid Email");
                validEmailSignIn="Error ..  User does not exist";
                model.addAttribute("message",validEmailSignIn);
            return "redirect:signin";
            }

        else   if ((userDB.getEmail().equals(user.getEmail()))&& (userDB.getPassword().equals(user.getPassword()))  )
        {System.out.println("*********** Valid USER");


         session.setAttribute("sessionRole",userDB.getRole());



        return "redirect:cars";}

         else if (!(userDB.getPassword().equals(user.getPassword())))
        { System.out.println("*********** Invalid PASSWORD ya khra333");
            validEmailSignIn="Incorrect password. Please try Again.";
            model.addAttribute("message",validEmailSignIn);

        return "redirect:signin";}



        return "redirect:signin" ;


    }



    @RequestMapping("/users/{id}")
    public String editUser(@PathVariable int id, Model model)
    {
        User user = userRepository.findOne(id);
        model.addAttribute("user",user);
        return "formSignUp";
    }

    @RequestMapping("/signup")
    public String signUp(Model model)
    {
        model.addAttribute("message",validEmailSignUp);
        return "formSignUp";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String addUser(User user,  Model model)
    {  User userDB =  userRepository.findByEmail(user.getEmail());
    user.setRole("USER");
    if (!(userDB == null))
        { System.out.println("Email already in use");
         validEmailSignUp="Error.. Email already in use";
         model.addAttribute("message",validEmailSignUp);
        return "redirect:signup" ;}


         userRepository.save(user);
        validEmailSignUp="Sign Up ";
        return "redirect:cars";
    }
}
