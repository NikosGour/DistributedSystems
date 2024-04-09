package gr.hua.dit.it22023_it22026.controllers;

import gr.hua.dit.it22023_it22026.models.Car;
import gr.hua.dit.it22023_it22026.models.Transfer;
import gr.hua.dit.it22023_it22026.models.User;
import gr.hua.dit.it22023_it22026.repositories.CarRepository;
import gr.hua.dit.it22023_it22026.repositories.TransferRepository;
import gr.hua.dit.it22023_it22026.repositories.UserRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
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
    
    @PostMapping("/{newOwnerId}/{transferID}/accept")
    @Transactional
    public boolean acceptTransfer( @PathVariable int newOwnerId, @PathVariable int transferID )
    {
    

        Transfer transfer = transferRepository.findById(transferID).orElse(null);
        User newOwner = userRepository.findById(newOwnerId).orElse(null);
        
        
        
        if (transfer != null && newOwner != null)
        {
            if (transfer.getNewOwner() == newOwner)
            {
                transfer.getCar().setOwner(newOwner);
                transferRepository.delete(transferID);
                return true;
            }

        }
        return false;
    }
    
    
    @GetMapping("/{newOwnerId}")
    public List<Car> getAllTransfersByNewOwner(@PathVariable int newOwnerId)
    {
        var t = transferRepository.findAllByNewOwnerId(newOwnerId);
        var x = new ArrayList<Car>();
    
        for (Transfer transfer : t)
        {
            x.add(transfer.getCar());
        }
        return x;
    }
    
    @GetMapping("{license_plate_number}/car")
    public Transfer getTransferByCar(@PathVariable String license_plate_number)
    {
        var x = transferRepository.findByCarId(license_plate_number);
        if (x.size() > 0)
        {
            return x.get(0);
        }
        return null;
    }
    
    @GetMapping("/{currentOwnerId}/current")
    public List<Transfer> getAllTransfersByCurrentOwner(@PathVariable int currentOwnerId)
    {
        return transferRepository.findAllByCurrentOwnerId(currentOwnerId);
    
    }
    
    
}


