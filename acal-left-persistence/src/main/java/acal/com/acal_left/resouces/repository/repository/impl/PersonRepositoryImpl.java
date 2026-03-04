package acal.com.acal_left.resouces.repository.repository.impl;

import acal.com.acal_left.core.model.Document;
import acal.com.acal_left.core.model.Partner;
import acal.com.acal_left.core.model.Person;
import acal.com.acal_left.resouces.repository.model.PersonEntity;
import acal.com.acal_left.resouces.repository.repository.jpa.PersonJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryImpl  {

    private final PersonJpaRepository repository;

    public PersonRepositoryImpl(PersonJpaRepository repository) {
        this.repository = repository;
    }

    public static Person toEntity(PersonEntity personEntity) {
        return Person.builder()
                .id(personEntity.getId())
                .name(personEntity.getName())
                .partner(
                    Partner.builder()
                        .build()
                )
                .document(Document.builder().value(personEntity.getDocument()).build())
                .build();
    }

}


