package gr.hua.dit.it22023_it22026.controllers;

import gr.hua.dit.it22023_it22026.models.Car;
import gr.hua.dit.it22023_it22026.models.User;
import gr.hua.dit.it22023_it22026.repositories.CarRepository;
import gr.hua.dit.it22023_it22026.repositories.TransferRepository;
import gr.hua.dit.it22023_it22026.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRepository  carRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    
    @Autowired
    private TransferRepository transferRepository;
    
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public Car insertCar(@RequestBody Car car){

        if(car.getLiscence_plate_number() == null){
            return null;
        }
        return carRepository.save(car);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
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
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{liscence_plate}")
    @Transactional
    public Car deleteCar(@PathVariable String liscence_plate){

        Car car=carRepository.findById(liscence_plate).orElse(null);
        if(car != null ){
            
            transferRepository.deleteAllByCar(car);
            carRepository.delete(car);
            return car;
        }
        return null;

    }
    
    
    @PreAuthorize("hasAuthority('ADMIN')")
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

    
    @GetMapping()
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    
    @GetMapping("/{id}")
    public List<Car> getCarsByOwner(@PathVariable int id){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            return carRepository.findAllByOwner(user);
        }
        return null;
    }
    

}
