package mate.jdbc;

import java.util.List;
import mate.jdbc.lib.Injector;
import mate.jdbc.model.Car;
import mate.jdbc.service.CarService;
import mate.jdbc.service.DriverService;
import mate.jdbc.service.ManufacturerService;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.jdbc");

    public static void main(String[] args) {
        Car car = new Car();
        car.setModel("Logan");
        ManufacturerService manufacturerService =
                (ManufacturerService) injector.getInstance(ManufacturerService.class);
        car.setManufacturer(manufacturerService.get(1L));
        DriverService driverService = (DriverService) injector.getInstance(DriverService.class);
        car.setDrivers(List.of(driverService.get(1L),driverService.get(2L)));
        CarService carService = (CarService) injector.getInstance(CarService.class);
        carService.create(car);
        carService.getAllByDriver(1L).forEach(System.out::println);
        carService.addDriverToCar(driverService.get(1L), car);
        carService.removeDriverFromCar(driverService.get(1L), car);
        System.out.println(carService.get(car.getId()));
        carService.delete(1L);
        carService.getAll().forEach(System.out::println);
    }
}
