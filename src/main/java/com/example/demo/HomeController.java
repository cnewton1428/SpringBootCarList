package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    CarRepository carRepository;

    @RequestMapping("/")
    public String listCars(Model model){
        model.addAttribute("cars", carRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String carForm(Model model) {
        model.addAttribute("car", new Car());
        return "carform";
    }

    @PostMapping("/process")
    public String processForm (@Valid Car car, BindingResult result) {
        if (result.hasErrors()){
            return "carform";
        }
        carRepository.save(car);
        return "redirect:/";
    }

    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping("/cat")
    public String listCategory(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        return "list";
    }

    @GetMapping("/catadd")
    public String categoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "categoryform";
    }

    @PostMapping("/processcat")
    public String processCatForm (@Valid Category category, BindingResult result) {
        if (result.hasErrors()){
            return "categoryform";
        }
        categoryRepository.save(category);
        return "redirect:/";
    }

    @RequestMapping("/carList/{id}")
    public String showCar(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", carRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "carform";
    }

    @RequestMapping("/delete/{id}")
    public String delCar(@PathVariable("id") long id) {
        carRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/1")
    public String carList(){
        return "carList";
    }

    @RequestMapping("/2")
    public String addCategory(){
        return "addCategory";
    }

    @RequestMapping("/3")
    public String addCar(){
        return "addCar";
    }

    @RequestMapping("/car")
    public String index(Model model) {
        Car car = new Car();
        car.setManufacturer("BMW");
        car.setModel("8-series");
        car.setYear(2018);
        car.setMsrp(75000);

        Category category = new Category();

        Set<Category> categories = new HashSet<Category>();
        categories.add(category);

        category= new Category();
        categories.add(category);

        car.setCategories(categories);

        carRepository.save(car);

        model.addAttribute("cars", carRepository.findAll());
        return "index";
    }
}
