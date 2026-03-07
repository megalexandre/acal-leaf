package acal.com.acal_left.resouces.repository.repository.impl;

import acal.com.acal_left.core.model.Address;
import acal.com.acal_left.core.repository.AddressRepository;
import acal.com.acal_left.resouces.repository.model.AddressEntity;
import acal.com.acal_left.resouces.repository.repository.jpa.AddressJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    public final AddressJpaRepository repository;

    public AddressRepositoryImpl(AddressJpaRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Address> findAllByOrderByNameAsc() {
        return repository.findAllByOrderByTypeAscNameAsc()
            .stream().map(AddressRepositoryImpl::toEntity).toList();
    }

    @Override
    public Address save(Address address) {
        return toEntity(repository.save( toModel(address)));
    }

    public static Address toEntity(AddressEntity item) {
        return Address.builder()
                .id(item.getId())
                .type(item.getType())
                .name(item.getName())
                .build();
    }

    public static AddressEntity toModel(Address item) {
        return AddressEntity.builder()
                .id(item.getId())
                .type(item.getType())
                .name(item.getName())
                .build();
    }

}
