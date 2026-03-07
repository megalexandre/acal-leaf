package acal.com.acal_left.resouces.repository.repository.impl;

import acal.com.acal_left.core.model.Link;
import acal.com.acal_left.core.model.filter.LinkFilter;
import acal.com.acal_left.core.repository.LinkRepository;
import acal.com.acal_left.resouces.repository.model.LinkEntity;
import acal.com.acal_left.resouces.repository.repository.jpa.LinkJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LinkRepositoryImpl implements LinkRepository {

    private final LinkJpaRepository repository;

    public LinkRepositoryImpl(LinkJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Link> findByFilter(LinkFilter filter) {
        if(filter == null){
            filter = new LinkFilter();
        }

        return repository.findAllWithEagerLoading(
                filter.getInactive(),
                filter.getPerson(),
                filter.getAddress()
                )
            .stream()
            .map(LinkEntity::toEntity)
            .toList();
    }

    @Override
    public Link save(Link link) {
        return LinkEntity.toEntity(repository.save(LinkEntity.toEntity(link)));
    }


}
