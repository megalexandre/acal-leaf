package acal.com.acal_left.core.usecase.partner;

import acal.com.acal_left.core.model.Partner;
import acal.com.acal_left.core.model.filter.PartnerFilter;
import acal.com.acal_left.core.repository.PartnerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PartnerFindUseCase {

    private final PartnerRepository repository;

    public PartnerFindUseCase(PartnerRepository repository) {
        this.repository = repository;
    }

    public List<Partner> execute(PartnerFilter filter) {
        return repository.findByFilter(filter);
    }
}
