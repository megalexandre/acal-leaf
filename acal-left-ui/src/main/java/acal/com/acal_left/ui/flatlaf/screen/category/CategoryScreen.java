
package acal.com.acal_left.ui.flatlaf.screen.category;

import acal.com.acal_left.ui.event.Screen;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class CategoryScreen extends JPanel {

    public final String name = Screen.CATEGORY.name();


    public CategoryScreen() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        label1 = new JLabel();

        //======== this ========
        setLayout(new BorderLayout());

        //---- label1 ----
        label1.setText("Category");
        add(label1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
