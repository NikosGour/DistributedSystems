package gr.hua.dit.it22023_it22026.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "transfers")
public class Transfer
{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "current_owner_id")
    @JsonBackReference(value = "user-outgoing-transfer")
    private User currentOwner;
    
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "new_owner_id")
    @JsonBackReference(value = "user-incoming-transfer")
    private User newOwner;
    
    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "car_id",unique = true)
    private Car car;
    private String transferAddress;
    private long newOwnerAFM;
    
    public Transfer()
    {
    }
    
    public Transfer(User currentOwner, User newOwner, Car car, String transferAddress)
    {
        this.currentOwner = currentOwner;
        this.newOwner = newOwner;
        this.car = car;
        this.transferAddress = transferAddress;
        this.newOwnerAFM = newOwner.getAFM();
    }
    
    
    // region Getters and Setters
    
    public int getId()
    {
        return id;
    }
    
    public User getCurrentOwner()
    {
        return currentOwner;
    }
    
    public void setCurrentOwner(User currentOwner)
    {
        this.currentOwner = currentOwner;
    }
    
    public User getNewOwner()
    {
        return newOwner;
    }
    
    public void setNewOwner(User newOwner)
    {
        this.newOwner = newOwner;
    }
    
    public Car getCar()
    {
        return car;
    }
    
    public void setCar(Car car)
    {
        this.car = car;
    }
    
    public String getTransferAddress()
    {
        return transferAddress;
    }
    
    public void setTransferAddress(String transferAddress)
    {
        this.transferAddress = transferAddress;
    }
    
    public long getNewOwnerAFM()
    {
        return newOwnerAFM;
    }
    
    public void setNewOwnerAFM(long newOwnerAFM)
    {
        this.newOwnerAFM = newOwnerAFM;
    }
    
    
    // endregion
}
