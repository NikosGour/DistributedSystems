package gr.hua.dit.it22023_it22026;

import gr.hua.dit.it22023_it22026.models.Authority;
import gr.hua.dit.it22023_it22026.models.Car;
import gr.hua.dit.it22023_it22026.models.User;
import gr.hua.dit.it22023_it22026.repositories.AuthorityRepository;
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
        if (authority == null)
        {
            authority = new Authority();
            authority.setAuthority(Constants.ADMIN);
         
        }
        if (user == null)
        {
            user = new User();
            user.setUsername("root");
            user.setPassword(passwordEncoder.encode("root"));
            user.setAFM(123456789L);
            user.setEmail("root@gmail.com");
            user.setPhone_number(1234567890L);
        }
        
        var authorities = user.getAuthorities();
        if (!authorities.contains(authority))
        {
            user.addAuthority(authority);
        }
        userRepository.save(user);
        
        
    }
    
    @GetMapping("/")
    public String hello()
    {
        return "Hello World!";
    }
    
    
    
    
    
}
