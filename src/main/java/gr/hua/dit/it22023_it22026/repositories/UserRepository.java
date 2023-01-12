package gr.hua.dit.it22023_it22026.repositories;


import gr.hua.dit.it22023_it22026.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByUsername(String username);
    
    @Modifying
    @Query("delete from User t where t.id = ?1")
    void deleteById(int entityId);
}