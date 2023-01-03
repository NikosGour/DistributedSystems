package gr.hua.dit.it22023_it22026.repositories;

import gr.hua.dit.it22023_it22026.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface TransferRepository extends JpaRepository<Transfer, Integer>
{
    ArrayList<Transfer> findAllByNewOwnerId(int newOwnerId);
}
