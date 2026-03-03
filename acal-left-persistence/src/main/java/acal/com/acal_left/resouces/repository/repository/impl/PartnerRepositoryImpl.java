package acal.com.acal_left.resouces.repository.repository.impl;

import acal.com.acal_left.core.model.Partner;
import acal.com.acal_left.core.model.Person;
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
    public List<Partner> findOrderByName() {
        return partnerJpaRepository.findAllByName()
                .stream().map(PartnerRepositoryImpl::toEntity).toList();
    }

    public static Partner toEntity(PartnerEntity entity) {
        return Partner.builder()
                .id(entity.getId())
                .person(toPersonEntity(entity.getPerson()))
                .build();
    }

    public static PartnerEntity fromEntity(Partner partner) {
        PartnerEntity entity = new PartnerEntity();
        entity.setId(partner.getId());
        entity.setPerson(fromPersonEntity(partner.getPerson()));
        return entity;
    }

    public static Person toPersonEntity(PersonEntity personEntity) {
        if (personEntity == null) {
            return null;
        }
        return Person.builder()
                .id(personEntity.getId())
                .name(personEntity.getName())
                .build();
    }

    public static PersonEntity fromPersonEntity(Person person) {
        if (person == null) {
            return null;
        }
        PersonEntity entity = new PersonEntity();
        entity.setId(person.getId());
        entity.setName(person.getName());
        return entity;
    }
}


