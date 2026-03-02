package acal.com.acal_left.ui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboBoxOption {

    private Integer id;
    private String name;

    public static Integer getSelectedId(JComboBox<ComboBoxOption> comboBox) {
        if(comboBox == null) {
            return null;
        }

        Object selected = comboBox.getSelectedItem();

        if (selected instanceof ComboBoxOption option) {
            return option.getId();
        }

        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
