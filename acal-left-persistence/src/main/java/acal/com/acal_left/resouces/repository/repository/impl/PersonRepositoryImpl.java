package acal.com.acal_left.resouces.repository.repository.impl;

import acal.com.acal_left.core.model.Person;
import acal.com.acal_left.core.repository.PersonRepository;
import acal.com.acal_left.resouces.repository.model.PersonEntity;
import acal.com.acal_left.resouces.repository.repository.jpa.PersonJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonJpaRepository repository;

    public PersonRepositoryImpl(PersonJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Person> findAll() {
        return repository.findOrderByName().stream().map(PersonEntity::toEntity).toList();
    }

}


