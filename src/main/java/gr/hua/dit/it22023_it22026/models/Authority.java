package gr.hua.dit.it22023_it22026.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "authorities")
public class Authority
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    

    @Column(name = "authority")
    private String authority;
    
    @ManyToMany(mappedBy = "authorities",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonIgnore
    private Set<User> users;
    
    public Authority()
    {
    }
    
    public Authority( String authority)
    {

        this.authority = authority;
    }
    

    
    public String getAuthority()
    {
        return authority;
    }
    
    public void setAuthority(String authority)
    {
        this.authority = authority;
    }
    
    public Set<User> getUsers()
    {
        return users;
    }
}

