package acal.com.acal_left.ui.flatlaf.screen.invoice.invoice.model;

import acal.com.acal_left.core.model.Address;
import acal.com.acal_left.core.model.Person;
import acal.com.acal_left.ui.flatlaf.component.model.ComboBoxOption;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InvoiceScreenData {

    private List<Person> persons = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();

    public boolean hasPersons() {
        return persons.isEmpty();
    }

    public boolean hasAddresses() {
        return addresses.isEmpty();
    }

    public ComboBoxOption[] getPersonsOptions() {
        if (persons == null) {
            return new ComboBoxOption[0];
        }

        return persons.stream()
                .map( it -> new ComboBoxOption(it.getId(), it.getName()))
                .toArray(ComboBoxOption[]::new);
    }

        public ComboBoxOption[] getAddressesOptions() {
            if (addresses == null) {
                return new ComboBoxOption[0];
            }

            return addresses.stream()
                .map( it -> new ComboBoxOption(it.getId(), it.getFullAddress()))
                .toArray(ComboBoxOption[]::new);

        }



}
