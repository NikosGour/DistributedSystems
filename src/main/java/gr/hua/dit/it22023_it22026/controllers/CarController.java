package gr.hua.dit.it22023_it22026.controllers;

import gr.hua.dit.it22023_it22026.models.Car;
import gr.hua.dit.it22023_it22026.models.User;
import gr.hua.dit.it22023_it22026.repositories.CarRepository;
import gr.hua.dit.it22023_it22026.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRepository  carRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @PostMapping()
    public Car insertCar(@RequestBody Car car){

        if(car.getLiscence_plate_number() == null){
            return null;
        }
        return carRepository.save(car);
    }
    @PostMapping("/{liscence_plate}")
    public Car updateCar(@RequestBody Car car,@PathVariable String liscence_plate){

         Car car1=carRepository.findById(liscence_plate).orElse(null);
         if(car1!= null){
             car1.setBrand(car.getBrand());
             car1.setColor(car.getColor());
             car1.setHorse_power(car.getHorse_power());
             car1.setLiscence_plate_number(car.getLiscence_plate_number());
             car1.setModel(car.getModel());
             car1.setKilometers_driven(car.getKilometers_driven());
             car1.setRelease_date(car.getRelease_date());
             car1.setOwner(car.getOwner());
             return carRepository.save(car1);

         }
         return null;
    }

    @DeleteMapping("/{liscence_plate}")
    public Car deleteCar(@PathVariable String liscence_plate){

        Car car=carRepository.findById(liscence_plate).orElse(null);
        if(car != null ){
            carRepository.delete(car);
            return car;
        }
        return null;

    }
    
    
    @PutMapping("/{liscence_plate}/{id}")
    public boolean setCarOwner(@PathVariable String liscence_plate,@PathVariable int id) {
        
        User user = userRepository.findById(id).orElse(null);
        Car car = carRepository.findById(liscence_plate).orElse(null);
        
        if (user != null && car != null) {
            car.setOwner(user);
            carRepository.save(car);
            return true;
        }
        return false;
        
    }



}
