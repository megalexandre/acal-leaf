package acal.com.acal_left.core.usecase;

import acal.com.acal_left.model.Addresses;
import acal.com.acal_left.repository.AddressRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressFindAllUseCase {

    private final AddressRepository repository;

    public AddressFindAllUseCase(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Addresses> execute() {
        return repository.findAllByOrderByNameAsc();
    }
}
