package acal.com.acal_left.resouces.repository.repository.jpa;

import acal.com.acal_left.resouces.repository.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpaRepository extends JpaRepository<PersonEntity, Integer> {

}
