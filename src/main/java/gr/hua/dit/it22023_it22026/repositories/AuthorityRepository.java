package gr.hua.dit.it22023_it22026.repositories;

import gr.hua.dit.it22023_it22026.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer>
{
    Authority findByAuthority(String authority);
}
