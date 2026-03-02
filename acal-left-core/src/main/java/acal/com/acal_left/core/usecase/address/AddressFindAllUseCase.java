package acal.com.acal_left.core.usecase.address;

import acal.com.acal_left.core.model.Address;
import acal.com.acal_left.core.repository.AddressRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressFindAllUseCase {

    private final AddressRepository repository;

    public AddressFindAllUseCase(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> execute() {
        return repository.findAllByOrderByNameAsc();
    }
}
