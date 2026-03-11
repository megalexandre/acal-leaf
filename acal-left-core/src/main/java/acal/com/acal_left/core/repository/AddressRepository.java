package acal.com.acal_left.core.repository;

import acal.com.acal_left.core.model.Address;

import java.util.List;

public interface AddressRepository {
    List<Address> findAllByOrderByNameAsc();
    Address save(Address address);
}
