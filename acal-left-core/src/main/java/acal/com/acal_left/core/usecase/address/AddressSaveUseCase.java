package acal.com.acal_left.core.usecase.address;

import acal.com.acal_left.core.model.Address;
import acal.com.acal_left.core.repository.AddressRepository;
import org.springframework.stereotype.Component;

@Component
public class AddressSaveUseCase {

    private final AddressRepository repository;

    public AddressSaveUseCase(AddressRepository repository) {
        this.repository = repository;
    }

    public Address execute(Address address) {
        return repository.save(address);
    }
}
