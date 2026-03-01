package acal.com.acal_left.core.usecase;

import acal.com.acal_left.model.Address;
import acal.com.acal_left.repository.AddressRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllAddressUseCase {

    private final AddressRepository repository;

    public FindAllAddressUseCase(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> execute() {
        return repository.findAllByOrderByNameAsc();
    }
}
