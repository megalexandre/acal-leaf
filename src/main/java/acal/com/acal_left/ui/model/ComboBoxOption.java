package acal.com.acal_left.ui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboBoxOption {

    private Integer id;
    private String name;

    public static Integer getSelectedId(Object selectedItem) {
        return (selectedItem instanceof ComboBoxOption option) ? option.getId() : null;
    }

    @Override
    public String toString() {
        return name;
    }
}
