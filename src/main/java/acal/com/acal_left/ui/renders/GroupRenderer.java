package acal.com.acal_left.ui.renders;

import acal.com.acal_left.resouces.model.Group;

import javax.swing.table.DefaultTableCellRenderer;

public class GroupRenderer extends DefaultTableCellRenderer {
    @Override
    protected void setValue(Object value) {
        if (value instanceof Group group) {
            setText(group.getDescription());
        } else {
            super.setValue(value);
        }
    }
}