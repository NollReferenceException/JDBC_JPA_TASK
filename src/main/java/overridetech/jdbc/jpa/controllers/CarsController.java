package overridetech.jdbc.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import overridetech.jdbc.jpa.model.Car;
import overridetech.jdbc.jpa.service.CarService;

import java.util.List;


@Controller
public class CarsController {

    @Autowired
    CarService carService;

    @GetMapping("/cars")
    public String getCars(Model model) {
        List<Car> cars = carService.getCarsByCount(228);
        model.addAttribute("cars", cars);
        return "cars/carsTable";
    }
}
