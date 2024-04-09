package gr.hua.dit.it22023_it22026.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "authorities")
public class Authority
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    

    @NotEmpty
    @Column(name = "authority")
    private String authority;
    
    @ManyToMany(mappedBy = "authorities",fetch = FetchType.EAGER , cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JsonIgnore
    private Set<User> users = new HashSet<>();
    
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
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority1 = (Authority) o;
        return authority.equals(authority1.authority);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(authority);
    }
}

