package gr.hua.dit.it22023_it22026.services;

import gr.hua.dit.it22023_it22026.models.CustomUserDetails;
import gr.hua.dit.it22023_it22026.models.User;
import gr.hua.dit.it22023_it22026.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService
{
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user =  userRepository.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("Could not find user");
        }
        
        return new CustomUserDetails(user);
    }
}
