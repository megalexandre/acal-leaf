package acal.com.acal_left.ui.flatlaf.component.model;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.util.List;
import java.util.function.Supplier;

public class ComboBoxLoader {

    public static void setupLazyLoad(JComboBox<ComboBoxOption> comboBox, Supplier<List<ComboBoxOption>> dataSupplier) {
        comboBox.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                if (comboBox.getItemCount() == 0) {
                    List<ComboBoxOption> data = dataSupplier.get();
                    if (data != null) {
                        DefaultComboBoxModel<ComboBoxOption> model = new DefaultComboBoxModel<>();
                        model.addElement(ComboBoxOption.empty());
                        if (!data.isEmpty()) {
                            model.addAll(data);
                        }
                        comboBox.setModel(model);
                    }
                }
            }

            @Override public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
            @Override public void popupMenuCanceled(PopupMenuEvent e) {}
        });
    }
}