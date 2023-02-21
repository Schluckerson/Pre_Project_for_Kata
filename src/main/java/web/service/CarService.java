package web.service;

import web.model.Car;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarService {
    public static List<Car> getSomeCars(int amount, List<Car> cars) {
        return cars.stream().limit(amount).collect(Collectors.toList());
    }
}
