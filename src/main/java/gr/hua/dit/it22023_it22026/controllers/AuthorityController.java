package gr.hua.dit.it22023_it22026.controllers;


import gr.hua.dit.it22023_it22026.models.Authority;
import gr.hua.dit.it22023_it22026.models.User;
import gr.hua.dit.it22023_it22026.repositories.AuthorityRepository;
import gr.hua.dit.it22023_it22026.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authority")
public class AuthorityController
{
    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{authority}")
    public void addAuthority(@PathVariable String authority)
    {
        Authority authority1 = authorityRepository.findByAuthority(authority);
        if (authority1 == null)
        {
            authority1 = new Authority();
            authority1.setAuthority(authority);
            authorityRepository.save(authority1);
        }
        
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{authority}/{user_id}")
    public void addAuthorityToUser(@PathVariable String authority, @PathVariable int user_id)
    {
        Authority authority1 = authorityRepository.findByAuthority(authority);
        if (authority1 == null)
        {
            authority1 = new Authority();
            authority1.setAuthority(authority);
        }
        
        User user = userRepository.findById(user_id).orElse(null);
        
        if (user != null)
        {
            user.addAuthority(authority1);
            userRepository.save(user);
        }
        
    }
    
    @GetMapping()
    public List<Authority> getAuthorities()
    {
        return authorityRepository.findAll();
    }
}
