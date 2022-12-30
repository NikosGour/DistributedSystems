package gr.hua.dit.it22023_it22026.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


@Entity
@Table(name = "cars")
public class Car
{
    
    @Id
    @Column(name = "liscence_plate_number",unique = true)
    @Size(max = 7,message = "Liscence plate number must be 7 characters long")
    private String liscence_plate_number;
    
    @Column(name = "model")
    private String model;
    
    @Column(name = "brand")
    private String brand;
    
    @Column(name = "color")
    private String color;
    
    @Column(name = "release_date")
    private LocalDate release_date;
    
    @Column(name = "kilometers")
    private int kilometers_driven;
    
    @Column(name = "horse_power")
    private int horse_power;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    
    public Car()
    {
    
    }
    
    
    public Car(String model, String brand, String color, String liscence_plate_number, LocalDate release_date, int kilometers_driven, int horse_power)
    {
        this.model = model;
        this.brand = brand;
        this.color = color;
        this.liscence_plate_number = liscence_plate_number;
        this.release_date = release_date;
        this.kilometers_driven = kilometers_driven;
        this.horse_power = horse_power;
    }
    
    
    
    // region Getters and Setters
    public String getModel()
    {
        return model;
    }
    
    public void setModel(String model)
    {
        this.model = model;
    }
    
    public String getBrand()
    {
        return brand;
    }
    
    public void setBrand(String brand)
    {
        this.brand = brand;
    }
    
    public String getColor()
    {
        return color;
    }
    
    public void setColor(String color)
    {
        this.color = color;
    }
    
    public String getLiscence_plate_number()
    {
        return liscence_plate_number;
    }
    
    public void setLiscence_plate_number(String liscence_plate_number)
    {
        this.liscence_plate_number = liscence_plate_number;
    }
    
    public LocalDate getRelease_date()
    {
        return release_date;
    }
    
    public void setRelease_date(LocalDate release_date)
    {
        this.release_date = release_date;
    }
    
    public int getKilometers_driven()
    {
        return kilometers_driven;
    }
    
    public void setKilometers_driven(int kilometers_driven)
    {
        this.kilometers_driven = kilometers_driven;
    }
    
    public int getHorse_power()
    {
        return horse_power;
    }
    
    public void setHorse_power(int horse_power)
    {
        this.horse_power = horse_power;
    }
    
    public User getUser()
    {
        return user;
    }
    
    public void setUser(User user)
    {
        this.user = user;
    }
    
    // endregion
}
