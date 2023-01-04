package gr.hua.dit.it22023_it22026.config;


import gr.hua.dit.it22023_it22026.utils.Constants;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    
    @Resource
    private DataSource dataSource;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    // TODO: FIX ROLES AND PERMISSIONS
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(auth -> auth
                               .requestMatchers(HttpMethod.POST , "/api/users").permitAll()
                                .requestMatchers( "/api/authority").hasAuthority(Constants.ADMIN)
                               .anyRequest().authenticated()
                )
                .formLogin().permitAll()
                .and().logout().permitAll()
                .and().userDetailsService(userDetailsService)
                .cors()
                .and().csrf().disable()
                .httpBasic();
        
        http.headers().frameOptions().sameOrigin();
        
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
