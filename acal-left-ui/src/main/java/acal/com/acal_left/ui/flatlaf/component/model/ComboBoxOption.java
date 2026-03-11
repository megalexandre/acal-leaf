package acal.com.acal_left.ui.flatlaf.component.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.JComboBox;

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

        if (comboBox.getSelectedItem() instanceof ComboBoxOption option) {
            return option.getId();
        }

        return null;
    }

    public static ComboBoxOption empty() {
        return new ComboBoxOption(null, "");
    }

    @Override
    public String toString() {
        return name;
    }
}
