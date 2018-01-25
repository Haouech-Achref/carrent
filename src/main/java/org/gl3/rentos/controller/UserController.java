package org.gl3.rentos.controller;

import org.gl3.rentos.model.User;


import org.gl3.rentos.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Controller


public class UserController {
    private String validEmailSignUp = "Sign Up ";
    private String validEmailSignIn = "Welcome back";

    private static final Logger errorLogger = LoggerFactory.getLogger("error");
    private static final Logger infoLogger = LoggerFactory.getLogger("info");

    @Autowired
    UserRepository userRepository;

    @ModelAttribute("user")
    public User getUser() {
        return new User();

    }
    @RequestMapping(value = "/users")
    public String listCar(User user, Model model,HttpSession session)
    {

        User userSession = (User) session.getAttribute("user");

        if (session.getAttribute("sessionRole")== null)
        {   infoLogger.info("Access to /users denied: user not logged in.");
            return "accessdenied";
        }

        else if (session.getAttribute("sessionRole").equals("admin"))
        { List<User> lister = new ArrayList<>();
            userRepository.findAll().forEach(lister::add);
            model.addAttribute("users",lister);

            infoLogger.info("Access to /users granted to administrator "+ userSession.getFname());
            return "users"; }

        else if    (!(session.getAttribute("sessionRole").equals("admin")))
        {

            infoLogger.info("Access to /users denied: User "+ userSession.getFname()+" is not an administrator.");
            return "accessdenied";
        }


        return "redirect:signin";
    }

    @RequestMapping(value = "users/add")
    public String addCar(HttpSession session)
    {
        User userSession = (User) session.getAttribute("user");
        if ( session.getAttribute("sessionRole")!= null && session.getAttribute("sessionRole").equals("admin"))
        {
            infoLogger.info("Access to /user/add granted to administrator ");
            return "formSignUp";

        }
        else {

            infoLogger.info("Access to /users/add denied: User is not an administrator.");
            return "accessdenied";
        }
    }

    @RequestMapping(value = "/users/delete/{id}")
    public String deleteCar(@PathVariable int id, Model model, HttpSession session)
    {
        if (session.getAttribute("sessionRole")== null)
        {   infoLogger.info("Access to /user/delete denied: user not logged in.");
            return "accessdenied";
        }
        else if (session.getAttribute("sessionRole").equals("admin")){
            infoLogger.info("User "+ id + " deleted");
            userRepository.delete(id);
            return "redirect:/users";}

        else if    (!(session.getAttribute("sessionRole").equals("admin")))
        {

            infoLogger.info("Access to /user/delete denied: User "+ " is not an administrator.");
            return "accessdenied";
        }
        return "redirect:signin";
    }



    @RequestMapping("/signin")
    public String signin(Model model, HttpSession session) {
        session.removeAttribute("sessionRole");
        session.removeAttribute("user");
        model.addAttribute("message", validEmailSignIn);
        return "signin";
    }



    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String validSignIn(User user, Model model, HttpSession session) {

        User userDB = userRepository.findByEmail(user.getEmail());

        if (userDB == null) {
            errorLogger.error("User attempted to sign in with invalid email.");
            validEmailSignIn = "Error ..  User does not exist";
            model.addAttribute("message", validEmailSignIn);
            return "redirect:signin";
        } else if ((userDB.getEmail().equals(user.getEmail())) && (userDB.getPassword().equals(user.getPassword()))) {

            infoLogger.info("User " + userDB.getFname() + " signed in.");
            session.setAttribute("user", userDB);
            session.setAttribute("sessionRole", userDB.getRole());


            return "redirect:/";
        } else if (!(userDB.getPassword().equals(user.getPassword()))) {
            errorLogger.error("User attempted to sign in with invalid password.");
            validEmailSignIn = "Incorrect password. Please try Again.";
            model.addAttribute("message", validEmailSignIn);

            return "redirect:signin";
        }


        return "redirect:signin";


    }




    @RequestMapping("/profile")
    public String editUser( Model model, HttpSession session) {


        User user = (User) session.getAttribute("user");
        if (session.getAttribute("sessionRole") != null ) {
            infoLogger.info("Access to /profile granted.");
            User userDB = userRepository.findOne(user.getUser_id());
            model.addAttribute("user", userDB);
            return "profile";


        } else {

            infoLogger.info("Access to /profile denied: User is not signed in.");
            return "accessdeniedprofile";
        }
    }

    @RequestMapping(value = "users", method = RequestMethod.POST)
    public String saveCar(HttpSession session ,User tester,
                          RedirectAttributes redirectAttributes)
    {

        if (session.getAttribute("sessionRole") != null ) {
            userRepository.save(tester);
            infoLogger.info("User #"+ tester.getUser_id()+" updated to the database.");
            return "redirect:profile";}
        else {

            infoLogger.info("Access to /profile denied: User is not signed in.");
            return "accessdenied";
        }
    }

    @RequestMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("message", validEmailSignUp);
        return "formSignUp";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String addUser(User user, Model model) {
        User userDB = userRepository.findByEmail(user.getEmail());
        user.setRole("USER");
        if (!(userDB == null)) {

            errorLogger.error("User attempted to sign up with an already existing email.");
            validEmailSignUp = "Error.. Email already exists.";
            model.addAttribute("message", validEmailSignUp);
            return "redirect:signup";
        }


        userRepository.save(user);
        infoLogger.info("New user #" + user.getUser_id() + " added.");
        validEmailSignUp = "Sign Up ";
        return "redirect:/";
    }
}
