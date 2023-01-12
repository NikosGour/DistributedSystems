package gr.hua.dit.it22023_it22026.repositories;

import gr.hua.dit.it22023_it22026.models.Car;
import gr.hua.dit.it22023_it22026.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, String>
{
    
    @Modifying
    @Query("delete from Car t where t.owner = ?1")
    void deleteAllByUser(User user);
    
    @Modifying
    @Query("delete from Car t where t = ?1")
    void delete(Car car);
}
