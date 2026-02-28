package acal.com.acal_left.repository;

import acal.com.acal_left.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer> {

    @Query("SELECT p FROM Partner p JOIN FETCH p.person ORDER BY p.person.name ASC")
    List<Partner> findAllByName();
}
