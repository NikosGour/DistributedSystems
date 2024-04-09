package gr.hua.dit.it22023_it22026.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import gr.hua.dit.it22023_it22026.models.Authority;
import gr.hua.dit.it22023_it22026.models.Car;
import gr.hua.dit.it22023_it22026.models.User;
import gr.hua.dit.it22023_it22026.repositories.AuthorityRepository;
import gr.hua.dit.it22023_it22026.repositories.CarRepository;
import gr.hua.dit.it22023_it22026.repositories.TransferRepository;
import gr.hua.dit.it22023_it22026.repositories.UserRepository;
import gr.hua.dit.it22023_it22026.utils.Constants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Autowired
    TransferRepository  transferRepository;
    
    @Autowired
    CarRepository carRepository;
    
    
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @PostMapping("/login")
    public ResponseEntity<HashMap<String,Integer>> login(@RequestBody String username)
    {
        User user = userRepository.findByUsername(username);
        if (user == null)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        
        int id = user.getId();
        var res = new HashMap<String,Integer>();
        res.put("id",id);
        return ResponseEntity.status(250).body(res);
    }
    
    
    @PostMapping("/afm/{afm}")
    public int getUserByAFM(@PathVariable Long afm)
    {
        User user =  userRepository.findByAFM(afm);
        if (user == null)
        {
            return 0;
        }
        return user.getId();
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public User insertUser(@RequestBody User user)
    {
        if (user.getId() != 0)
        {
            return null;
        }
        Authority authority = authorityRepository.findByAuthority(Constants.USER);
        
        if (authority == null)
        {
            authority = new Authority();
            authority.setAuthority(Constants.USER);
        }
        
        user.addAuthority(authority);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        return userRepository.save(user);
    }
    
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}")
    public User updateUser(@RequestBody User user , @PathVariable int id)
    {
        User user1 = userRepository.findById(id).orElse(null);
        if (user1 != null)
        {
            user1.setUsername(user.getUsername());
            user1.setAddress(user.getAddress());
            user1.setPhone_number(user.getPhone_number());
            user1.setAFM(user.getAFM());
            user1.setEmail(user.getEmail());
            return userRepository.save(user1);
        }
        return null;
        
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Transactional
    public User deleteUser(@PathVariable int id){
        User user=userRepository.findById(id).orElse(null);
        
        if(user != null) {
            
            transferRepository.deleteAllByUser(user);
            carRepository.deleteAllByUser(user);
            
            userRepository.deleteById(id);
            return user;
        }
        return null;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }
    
}
