package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model;

import acal.com.acal_left.core.model.Person;
import acal.com.acal_left.ui.flatlaf.component.model.ComboBoxOption;
import lombok.Data;

import java.util.List;

@Data
public class InvoiceScreenData {

    private List<Person> persons;

    public ComboBoxOption[] getPersonsOptions() {
        if (persons == null) {
            return new ComboBoxOption[0];
        }

        return persons.stream()
                .map( it -> new ComboBoxOption(it.getId(), it.getName()))
                .toArray(ComboBoxOption[]::new);
    }



    public boolean hasPersons() {
        return persons != null && !persons.isEmpty();
    }


}
