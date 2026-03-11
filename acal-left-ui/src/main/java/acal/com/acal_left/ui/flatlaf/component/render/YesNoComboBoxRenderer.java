package acal.com.acal_left.ui.flatlaf.component.render;

import javax.swing.*;
import java.awt.*;

public class YesNoComboBoxRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, 
                                                  int index, boolean isSelected, boolean cellHasFocus) {

        if(value == null){
            value = " ";
        }

        else if (value instanceof Boolean){
            value = (Boolean) value ? "Sim" : "Não";
        }

        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }
}