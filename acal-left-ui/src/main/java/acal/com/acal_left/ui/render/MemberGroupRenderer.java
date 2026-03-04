package acal.com.acal_left.ui.render;

import acal.com.acal_left.shared.model.MemberGroup;

import javax.swing.*;
import java.awt.*;

public class MemberGroupRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value,                                                  int index, boolean isSelected,
                                                  boolean cellHasFocus) {

        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof MemberGroup group) {
            setText(group.getDescription());
        }

        return this;
    }
}