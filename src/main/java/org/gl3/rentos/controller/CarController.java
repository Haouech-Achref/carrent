package org.gl3.rentos.controller;

import org.gl3.rentos.model.Car;
import org.gl3.rentos.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/cars")
public class CarController {

    private static String UPLOADED_FOLDER = "images";
    @Autowired
    CarRepository carRepository;

    @ModelAttribute("car")
    public Car getCar(){
        return new Car();
    }

    @RequestMapping(value = "")
    public String listCar(Car car, Model model)
    {
        List<Car> lister = new ArrayList<>();
        carRepository.findAll().forEach(lister::add);
        model.addAttribute("cars",lister);
        return "cars";
    }

    @RequestMapping(value = "/add")
    public String addCar()
    {
        return "formCar";
        }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveCar(Car tester,         @RequestParam("file") MultipartFile file,
                          RedirectAttributes redirectAttributes)
    {


        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:cars/add";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            tester.setPicture(path.toString());

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

       carRepository.save(tester);
        return "redirect:cars";
    }

    @RequestMapping(value = "/{id}")
    public String editCarInfo(@PathVariable int id, Model model)
    {
        Car car = carRepository.findOne(id);
        model.addAttribute("car",car);
        return "formCar";   }



}
