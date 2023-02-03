package gr.hua.dit.it22023_it22026;

import gr.hua.dit.it22023_it22026.models.Authority;
import gr.hua.dit.it22023_it22026.models.Car;
import gr.hua.dit.it22023_it22026.models.User;
import gr.hua.dit.it22023_it22026.repositories.AuthorityRepository;
import gr.hua.dit.it22023_it22026.repositories.CarRepository;
import gr.hua.dit.it22023_it22026.repositories.UserRepository;
import gr.hua.dit.it22023_it22026.utils.Constants;
import gr.hua.dit.it22023_it22026.utils.Test;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@SpringBootApplication
@RestController
public class DistributedSystemsAssignmentApplication
{

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    AuthorityRepository authorityRepository;
    
    @Autowired
    CarRepository carRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    public static void main(String[] args)
    {
        SpringApplication.run(DistributedSystemsAssignmentApplication.class , args);

    }
    
    @PostConstruct
    public void init()
    {
        User user = userRepository.findByUsername("root");
        Authority authority = authorityRepository.findByAuthority(Constants.ADMIN);
        Authority authority1 = authorityRepository.findByAuthority(Constants.USER);
        
        boolean isBootstrapped = true;
        if (authority == null)
        {
            authority = new Authority();
            authority.setAuthority(Constants.ADMIN);
            authorityRepository.save(authority);
        }
        if (authority1 == null)
        {
            authority1 = new Authority();
            authority1.setAuthority(Constants.USER);
            authorityRepository.save(authority1);
        }
        if (user == null)
        {
            isBootstrapped = false;
            user = new User();
            user.setUsername("root");
            user.setPassword(passwordEncoder.encode("root"));
            user.setAFM(1L);
            user.setEmail("root@gmail.com");
            user.setAddress("root address");
            user.setPhone_number(1L);
        }
        
        var authorities = user.getAuthorities();
        if (!authorities.contains(authority))
        {
            user.addAuthority(authority);
        }
        if (!authorities.contains(authority1))
        {
            user.addAuthority(authority1);
        }
        userRepository.save(user);
        
        
        if (!isBootstrapped)
        {
            setDummyData();
        }
        
    }
    
    private void setDummyData()
    {
        for (int i = 0; i < 10; i++)
        {
            User user = new User();
            user.setUsername("user" + i);
            user.setPassword(passwordEncoder.encode("user" + i));
            user.setAFM(123456789L + i);
            user.setEmail("user" + i + "@gmail.com");
            user.setPhone_number(1234567890L + i);
            user.setAddress("user" + i + " address");
            user.addAuthority(authorityRepository.findByAuthority(Constants.USER));
            userRepository.save(user);
        }
        
        User user1 = userRepository.findByUsername("user1");
        User user2 = userRepository.findByUsername("user2");
        
        for (int i = 0; i < 30; i++)
        {
            Car car = new Car();
            car.setBrand("brand" + i);
            car.setModel("model" + i);
            car.setLiscence_plate_number("lpn" + i);
            car.setColor("color" + i);
            car.setRelease_date(2002 + i);
            car.setHorse_power(100 + i);
            car.setKilometers_driven(10000 + i);
            
            if (i < 15)
            {
                car.setOwner(user1);
            }
            else
            {
                car.setOwner(user2);
            }
            
            carRepository.save(car);
            
        }
    }
    
 
    
}
