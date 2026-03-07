package acal.com.acal_left.core.usecase.link;

import acal.com.acal_left.core.model.Link;
import acal.com.acal_left.core.repository.LinkRepository;
import org.springframework.stereotype.Component;

@Component
public class LinkSaveUseCase {

    private final LinkRepository repository;

    public LinkSaveUseCase(LinkRepository repository) {
        this.repository = repository;
    }

    public Link execute(Link link) {
        return repository.save(link);
    }
}

