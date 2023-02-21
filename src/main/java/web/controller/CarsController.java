package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarsController {
    @GetMapping(value = "/cars")
    public String initCars(Model model, @RequestParam(value = "count", defaultValue = "5") Integer count) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("model1", "unit1", 1));
        cars.add(new Car("model2", "unit2", 2));
        cars.add(new Car("model3", "unit3", 3));
        cars.add(new Car("model4", "unit4", 4));
        cars.add(new Car("model5", "unit5", 5));

        model.addAttribute("cars", CarService.getSomeCars(count, cars));
        return "cars";
    }
}
