package acal.com.acal_left.core.usecase.link;

import acal.com.acal_left.core.model.Link;
import acal.com.acal_left.core.model.filter.LinkFilter;
import acal.com.acal_left.core.repository.LinkRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LinkFindUseCase {

    private final LinkRepository repository;

    public LinkFindUseCase(LinkRepository repository) {
        this.repository = repository;
    }

    public List<Link> execute(LinkFilter filter) {
        return repository.findByFilter(filter);
    }
}

