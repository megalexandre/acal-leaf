package acal.com.acal_left.resouces.repository.repository.impl;

import acal.com.acal_left.core.model.Document;
import acal.com.acal_left.core.model.Partner;
import acal.com.acal_left.core.model.Person;
import acal.com.acal_left.core.model.filter.PartnerFilter;
import acal.com.acal_left.core.repository.PartnerRepository;
import acal.com.acal_left.resouces.repository.model.PartnerEntity;
import acal.com.acal_left.resouces.repository.model.PersonEntity;
import acal.com.acal_left.resouces.repository.repository.jpa.PartnerJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartnerRepositoryImpl implements PartnerRepository {

    private final PartnerJpaRepository partnerJpaRepository;

    public PartnerRepositoryImpl(PartnerJpaRepository partnerJpaRepository) {
        this.partnerJpaRepository = partnerJpaRepository;
    }

    @Override
    public List<Partner> findByFilter(PartnerFilter filter) {
        return partnerJpaRepository.findByFilter(filter.getName())
                .stream().map(PartnerRepositoryImpl::toEntity).toList();
    }

    public static Partner toEntity(PartnerEntity entity) {
        return Partner.builder()
                .id(entity.getId())
                .person(toPersonEntity(entity.getPerson()))
                .build();
    }

    public static Person toPersonEntity(PersonEntity personEntity) {
        if (personEntity == null) {
            return null;
        }
        return Person.builder()
                .id(personEntity.getId())
                .name(personEntity.getName())
                .document(Document.builder().value(personEntity.getDocument()).build())
                .build();
    }

}


