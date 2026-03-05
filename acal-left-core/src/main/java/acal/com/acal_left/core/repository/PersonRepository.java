package acal.com.acal_left.core.repository;

import acal.com.acal_left.core.model.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> findAll();
}
