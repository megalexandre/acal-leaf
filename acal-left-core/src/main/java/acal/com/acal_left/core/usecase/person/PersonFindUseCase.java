package acal.com.acal_left.core.usecase.person;

import acal.com.acal_left.core.model.Person;
import acal.com.acal_left.core.model.filter.PersonFilter;
import acal.com.acal_left.core.repository.PersonRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonFindUseCase {

    private final PersonRepository repository;

    public PersonFindUseCase(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> execute(PersonFilter personFilter) {
        return repository.findByFilter(personFilter);
    }
}
