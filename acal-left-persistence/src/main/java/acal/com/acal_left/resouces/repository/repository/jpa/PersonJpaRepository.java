package acal.com.acal_left.resouces.repository.repository.jpa;

import acal.com.acal_left.resouces.repository.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonJpaRepository extends JpaRepository<PersonEntity, Integer> {

    @Query(
            """
            SELECT person FROM PersonEntity person
                JOIN FETCH person.partner partner
            ORDER BY person.name ASC
            """
    )
    List<PersonEntity> findOrderByName();

}
