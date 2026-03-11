package acal.com.acal_left.ui.flatlaf.screen.person.model;

import acal.com.acal_left.core.model.Person;
import lombok.Data;

@Data
public class PersonTableContent {

    private final Integer id;
    private final String name;
    private final String document;
    private final Person person;

    public PersonTableContent(Person item) {
        this.id = item.getId();
        this.name = item.getName();
        this.document = item.getDocument().formatted();
        this.person = item;
    }

}