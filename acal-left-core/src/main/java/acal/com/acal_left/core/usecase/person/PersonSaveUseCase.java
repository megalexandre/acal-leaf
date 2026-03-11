package acal.com.acal_left.core.usecase.person;

import acal.com.acal_left.core.model.Person;
import acal.com.acal_left.core.repository.PersonRepository;
import org.springframework.stereotype.Component;

@Component
public class PersonSaveUseCase {

    private final PersonRepository repository;

    public PersonSaveUseCase(PersonRepository repository) {
        this.repository = repository;
    }

    public Person execute(Person save) {
        return repository.save(save);
    }
}
