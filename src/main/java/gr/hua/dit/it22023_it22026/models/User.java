package gr.hua.dit.it22023_it22026.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "AFM")
    private Long AFM;
    
    @Column(name = "phone_number")
    private Long phone_number;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private final List<Car> cars = new ArrayList<>();
    
    public User()
    {
    
    }
    
    public User(String name, String email, String address, Long AFM, Long phone_number)
    {
        this.name = name;
        this.email = email;
        this.address = address;
        this.AFM = AFM;
        this.phone_number = phone_number;

    }
    
    public void addCar(Car car)
    {
        cars.add(car);
        car.setUser(this);
    }
    
    
    // region Getters and Setters
    
    public int getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
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
    
    // endregion
}
