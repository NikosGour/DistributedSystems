package gr.hua.dit.it22023_it22026.controllers;

import gr.hua.dit.it22023_it22026.models.Car;
import gr.hua.dit.it22023_it22026.models.User;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/test")
public class TestController
{
    @Resource
    EntityManager entityManager;
    
    @GetMapping("/user")
    @Transactional
    public User testUser()
    {
        Session session = entityManager.unwrap(Session.class);
        User user = new User("Nikos Gournakis" , "it22023@hua.gr", "Eudoksou 61", 555133L , 6947815830L);
    
        Car car = session.get(Car.class, "ZHB8313");
        user.addCar(car);
        session.persist(user);
        return user;
    }
    
    
    
    @GetMapping("/car")
    @Transactional
    public Car CarTest()
    {
        System.out.println("Starting");
        Car car =
                new Car("A180" , "Mercedes Benz" , "grey" , "ZHB8313" , 2018 , 0 , 120);
        
        Session session = entityManager.unwrap(Session.class);
        session.merge(car);
        System.out.println("Done");
        
        return car;
    }
    
    

}
