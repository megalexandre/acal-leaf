package acal.com.acal_left.core.repository;

import acal.com.acal_left.core.model.Person;
import acal.com.acal_left.core.model.filter.PersonFilter;

import java.util.List;

public interface PersonRepository {
    List<Person> findByFilter(PersonFilter filter);
    Person save(Person person);
}
