package gr.hua.dit.it22023_it22026.repositories;


import gr.hua.dit.it22023_it22026.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{

}