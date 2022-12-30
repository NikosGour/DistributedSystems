package gr.hua.dit.it22023_it22026;

import gr.hua.dit.it22023_it22026.models.Car;
import gr.hua.dit.it22023_it22026.utils.Test;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@SpringBootApplication
@RestController
public class DistributedSystemsAssignmentApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(DistributedSystemsAssignmentApplication.class , args);
//        Test.main(null);
    }
    
    @GetMapping("/")
    public String hello()
    {
        return "Hello World!";
    }
    
    
    
    
    
}
