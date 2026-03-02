package acal.com.acal_left.resouces.repository;

import acal.com.acal_left.resouces.model.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Addresses, Integer> {
    List<Addresses> findAllByOrderByNameAsc();
}
