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
        Boolean active = filter != null ? filter.getActive() : null;
        Boolean inactive = active == null ? null : !active;

        return repository.findAllWithEagerLoading(inactive)
            .stream()
            .map(LinkRepositoryImpl::toEntity)
            .toList();
    }

    public static Link toEntity(LinkEntity entity) {
        return Link.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .active(!entity.isInactive())
                .address(AddressRepositoryImpl.toEntity(entity.getAddress()))
                .category(CategoryRepositoryImpl.toEntity( entity.getCategory()))
                .person(PersonRepositoryImpl.toEntity(entity.getPerson()))
                .build();
    }

}
