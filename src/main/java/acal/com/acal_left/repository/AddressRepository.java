package acal.com.acal_left.repository;

import acal.com.acal_left.model.Address;
import acal.com.acal_left.model.Category;
import acal.com.acal_left.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAllByOrderByNameAsc();
}
