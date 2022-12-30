package gr.hua.dit.it22023_it22026;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DistributedSystemsAssignmentApplication
{
    
    public static void main(String[] args)
    {
        SpringApplication.run(DistributedSystemsAssignmentApplication.class , args);
    }
    
    @GetMapping("/")
    public String hello()
    {
        return "Hello World!";
    }
    
}
