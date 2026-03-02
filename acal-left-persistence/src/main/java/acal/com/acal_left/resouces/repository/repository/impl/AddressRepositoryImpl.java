package acal.com.acal_left.resouces.repository.repository.impl;

import acal.com.acal_left.core.model.Address;
import acal.com.acal_left.core.repository.AddressRepository;
import acal.com.acal_left.resouces.repository.model.AddressEntity;
import acal.com.acal_left.resouces.repository.repository.jpa.AddressJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    public final AddressJpaRepository addressJpaRepository;

    public AddressRepositoryImpl(AddressJpaRepository addressJpaRepository){
        this.addressJpaRepository = addressJpaRepository;
    }

    @Override
    public List<Address> findAllByOrderByNameAsc() {
        return addressJpaRepository.findAllByOrderByNameAsc()
            .stream().map(AddressRepositoryImpl::toEntity).toList();
    }

    public static Address toEntity(AddressEntity entity) {
        return Address.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public static AddressEntity fromEntity(Address address) {
        return AddressEntity.builder()
                .id(address.getId())
                .name(address.getName())
                .build();
    }
}
