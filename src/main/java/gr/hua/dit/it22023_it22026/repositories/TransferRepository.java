package gr.hua.dit.it22023_it22026.repositories;

import gr.hua.dit.it22023_it22026.models.Transfer;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer>
{
    ArrayList<Transfer> findAllByNewOwnerId(int newOwnerId);
    
    ArrayList<Transfer> findAllByCurrentOwnerId(int currentOwnerId);
    
    @Modifying
    @Query("delete from Transfer t where t.id = ?1")
    void delete(int entityId);

}
