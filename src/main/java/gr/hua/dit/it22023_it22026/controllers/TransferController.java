package gr.hua.dit.it22023_it22026.controllers;

import gr.hua.dit.it22023_it22026.models.Car;
import gr.hua.dit.it22023_it22026.models.Transfer;
import gr.hua.dit.it22023_it22026.models.User;
import gr.hua.dit.it22023_it22026.repositories.CarRepository;
import gr.hua.dit.it22023_it22026.repositories.TransferRepository;
import gr.hua.dit.it22023_it22026.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/actions")
public class TransferController
{
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CarRepository carRepository;
    
    @Autowired
    private TransferRepository transferRepository;
    
    @PostMapping("/{licensePlate}/{newOwnerId}")
    public boolean transferCar(@RequestBody String transfer_address ,
                               @PathVariable String licensePlate , @PathVariable int newOwnerId)
    {
        Car car = carRepository.findById(licensePlate).orElse(null);
        User newOwner = userRepository.findById(newOwnerId).orElse(null);
        
        if (car != null && newOwner != null)
        {
            User currentOwner = car.getOwner();
            
            if (currentOwner == newOwner)
            {
                return false;
            }
            Transfer transfer = new Transfer(currentOwner,newOwner , car , transfer_address);
            transferRepository.save(transfer);
            return true;
        }
        return false;
        
    }
    
    @GetMapping("/{newOwnerId}")
    public List<Transfer> getAllTransfersByNewOwner(@PathVariable int newOwnerId)
    {
        return transferRepository.findAllByNewOwnerId(newOwnerId);
    }
    
    
}


