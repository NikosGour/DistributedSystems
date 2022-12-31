package gr.hua.dit.it22023_it22026.controllers;

import gr.hua.dit.it22023_it22026.models.Car;
import gr.hua.dit.it22023_it22026.models.User;
import gr.hua.dit.it22023_it22026.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    private UserRepository userRepository;

    
    
    @PostMapping()
    public User insertUser(@RequestBody User user)
    {
        if (user.getId() != 0)
        {
            return null;
        }
        return userRepository.save(user);
    }
    
    
    @PostMapping("/{id}")
    public User updateUser(@RequestBody User user , @PathVariable int id)
    {
        User user1 = userRepository.findById(id).orElse(null);
        if (user1 != null)
        {
            user1.setName(user.getName());
            user1.setAddress(user.getAddress());
            user1.setPhone_number(user.getPhone_number());
            user1.setAFM(user.getAFM());
            user1.setEmail(user.getEmail());
            return userRepository.save(user1);
        }
        return null;
        
    }
    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable int id){
        User user=userRepository.findById(id).orElse(null);
        if(user != null) {
            userRepository.deleteById(id);
            return user;
        }
        return null;
    }


    
    
}
