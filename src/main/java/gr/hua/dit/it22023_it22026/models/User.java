package gr.hua.dit.it22023_it22026.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotEmpty
    @Column(name = "username",nullable = false,unique = true)
    private String username;
    
    @NotEmpty
    @Column(name = "password",nullable = false)
    private String password;
    
    private boolean enabled = true;
    
    @Column(name = "email")
    @Email(message = "Email should be valid")
    private String email;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "AFM")
    private Long AFM;
    
    @Column(name = "phone_number")
    private Long phone_number;

    @JsonManagedReference(value = "user-car")
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonIgnore
    private final List<Car> cars = new ArrayList<>();
    
    
    @JsonManagedReference(value = "user-outgoing-transfer")
    @OneToMany(mappedBy = "currentOwner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transfer> outgoing_transfers;
    
    @JsonManagedReference(value = "user-incoming-transfer")
    @OneToMany(mappedBy = "newOwner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transfer> incoming_transfers;
    
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
    @JsonIgnore
    private Set<Authority> authorities = new HashSet<>();
    public User()
    {
    
    }
    
    public User(String name, String email, String address, Long AFM, Long phone_number)
    {
        this.username = name;
        this.email    = email;
        this.address = address;
        this.AFM = AFM;
        this.phone_number = phone_number;
        

    }
    
    public void addCar(Car car)
    {
        cars.add(car);
        car.setOwner(this);
    }
    
    
    public void addAuthority(Authority authority)
    {
        authorities.add(authority);
        authority.getUsers().add(this);
    }

    
    // region Getters and Setters
    
    public int getId()
    {
        return id;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String name)
    {
        this.username = name;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public Long getAFM()
    {
        return AFM;
    }
    
    public void setAFM(Long AFM)
    {
        this.AFM = AFM;
    }
    
    public Long getPhone_number()
    {
        return phone_number;
    }
    
    public void setPhone_number(Long phone_number)
    {
        this.phone_number = phone_number;
    }
    
    public List<Car> getCars()
    {
        return cars;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public boolean isEnabled()
    {
        return enabled;
    }
    
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
    
    public Set<Authority> getAuthorities()
    {
        return authorities;
    }
    
    public void setAuthorities(Set<Authority> authorities)
    {
        this.authorities = authorities;
    }
    
    // endregion
}
