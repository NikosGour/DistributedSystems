package gr.hua.dit.it22023_it22026.models;

import gr.hua.dit.it22023_it22026.models.Authority;
import gr.hua.dit.it22023_it22026.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class SecurityUserDetails implements UserDetails
{
    private User user;
    
    public SecurityUserDetails(User user)
    {
        this.user = user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        Set<Authority> authorities = user.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        
        for (Authority authority : authorities)
        {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
            
        }
        return grantedAuthorities;
    }
    
    @Override
    public String getPassword()
    {
        return user.getPassword();
    }
    
    @Override
    public String getUsername()
    {
        return user.getUsername();
    }
    
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }
    
    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
