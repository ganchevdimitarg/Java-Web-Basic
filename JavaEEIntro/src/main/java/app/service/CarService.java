package app.service;

import java.util.List;

public interface CarService {
    void createCar(CarServiceModel car);

    List<CarServiceModel> allCars();
}
