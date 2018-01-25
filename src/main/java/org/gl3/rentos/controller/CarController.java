package org.gl3.rentos.controller;

import org.gl3.rentos.model.Car;
import org.gl3.rentos.model.User;
import org.gl3.rentos.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/cars")
public class CarController {

    private static final Logger errorLogger = LoggerFactory.getLogger("error");
    private static final Logger infoLogger = LoggerFactory.getLogger("info");


    private static String UPLOADED_FOLDER = "src/main/resources/static/img/";
    @Autowired
    CarRepository carRepository;

    @ModelAttribute("car")
    public Car getCar(){
        Car car = new Car();
        car.setPicture("alt.png");
        return car;
    }


    @RequestMapping(value = "delete/{id}")
    public String deleteCar(@PathVariable int id, Model model, HttpSession session)
    {
        User user =(User) session.getAttribute("user");
        boolean signedIn = user != null;
        model.addAttribute("signedIn", signedIn);

        if (session.getAttribute("sessionRole")== null)
        {   infoLogger.info("Access to /cars/delete denied: user not logged in.");
            return "accessdenied";
        }
        else if (session.getAttribute("sessionRole").equals("admin")){
        infoLogger.info("Car "+ id + " deleted");
        carRepository.delete(id);
        return "redirect:/cars";}

        else if    (!(session.getAttribute("sessionRole").equals("admin")))
        {

            infoLogger.info("Access to /cars/delete denied: User "+ " is not an administrator.");
            return "accessdenied";
        }
        return "redirect:signin";
    }


    @RequestMapping(value = "")
    public String listCar(Car car, Model model,HttpSession session)
    {

        User user = (User) session.getAttribute("user");
        boolean signedIn = user != null;
        model.addAttribute("signedIn", signedIn);

        if (session.getAttribute("sessionRole")== null)
        {   infoLogger.info("Access to /cars denied: user not logged in.");
            return "accessdenied";
        }

        else if (session.getAttribute("sessionRole").equals("admin"))
              { List<Car> lister = new ArrayList<>();
                 carRepository.findAll().forEach(lister::add);
                 model.addAttribute("cars",lister);

                  infoLogger.info("Access to /cars granted to administrator "+ user.getFname());
                 return "cars"; }

        else if    (!(session.getAttribute("sessionRole").equals("admin")))
        {

            infoLogger.info("Access to /cars denied: User "+ user.getFname()+" is not an administrator.");
            return "accessdenied";
        }


        return "redirect:signin";
    }

    @RequestMapping(value = "/add")
    public String addCar(HttpSession session, Model model)
    {
        User user = (User) session.getAttribute("user");
        boolean signedIn = user != null;
        model.addAttribute("signedIn", signedIn);
        if ( session.getAttribute("sessionRole")!= null && session.getAttribute("sessionRole").equals("admin"))
        {
            infoLogger.info("Access to /cars/add granted to administrator ");
            return "formCar";

        }
        else {

            infoLogger.info("Access to /cars/add denied: User is not an administrator.");
            return "accessdenied";
        }
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveCar(HttpSession session ,Car tester, @RequestParam("file") MultipartFile file,
                          Model model)
    {

        User user =(User) session.getAttribute("user");
        boolean signedIn = user != null;
        model.addAttribute("signedIn", signedIn);

        if (file.isEmpty()) {
            errorLogger.warn("No car photo uploaded. Using default picture instead.");
            tester.setPicture("alt.png");
            carRepository.save(tester);
            return "redirect:cars";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            tester.setPicture(file.getOriginalFilename());
            infoLogger.info("Sucessfully uploaded car photo.");

        } catch (IOException e) {
            errorLogger.error(e.getMessage());
            errorLogger.info("Using default picture instead.");
            tester.setPicture("alt.png");
            carRepository.save(tester);
            return "redirect:cars";
        }

        carRepository.save(tester);
        infoLogger.info("Car #"+ tester.getCar_id()+" added to the database.");
        return "redirect:cars";
    }

    @RequestMapping(value = "/{id}")
    public String editCarInfo(@PathVariable int id, Model model,HttpSession session)
    {


        User user =(User) session.getAttribute("user");
        boolean signedIn = user != null;
        model.addAttribute("signedIn", signedIn);

        if ( session.getAttribute("sessionRole")!= null && session.getAttribute("sessionRole").equals("admin"))
        {
            infoLogger.info("Access to /cars/" + id + " granted to administrator ");
            Car car = carRepository.findOne(id);
            model.addAttribute("car",car);
            return "formCar";

        }
        else {

            infoLogger.info("Access to /cars/" + id + " denied: User is not an administrator.");
            return "accessdenied";
        }

    }




}
