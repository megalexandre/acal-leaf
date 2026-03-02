package acal.com.acal_left.core.usecase;

import acal.com.acal_left.resouces.model.Partner;
import acal.com.acal_left.resouces.repository.PartnerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PartnerFindAllUseCase {

    private final PartnerRepository repository;

    public PartnerFindAllUseCase(PartnerRepository repository) {
        this.repository = repository;
    }

    public List<Partner> execute() {
        return repository.findAllByName();
    }
}
