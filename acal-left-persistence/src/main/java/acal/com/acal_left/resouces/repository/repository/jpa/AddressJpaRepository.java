package acal.com.acal_left.resouces.repository.repository.jpa;

import acal.com.acal_left.resouces.repository.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressJpaRepository extends JpaRepository<AddressEntity, Integer> {
    List<AddressEntity> findAllByOrderByNameAsc();
}
