/*
 * Created by JFormDesigner on Mon Mar 02 11:12:30 BRT 2026
 */

package acal.com.acal_left.ui.screen.search.customer;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

/**
 * @author alex
 */
@Component
public class CustomersSearch extends JPanel {
    public CustomersSearch() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - megalexandre@gmail.com
        panelTable = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel1 = new JPanel();
        buttonSerch = new JButton();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
        .swing.border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing
        .border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.
        Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.red
        ), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override
        public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName(
        )))throw new RuntimeException();}});
        setLayout(new BorderLayout());

        //======== panelTable ========
        {
            panelTable.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(table1);
            }
            panelTable.add(scrollPane1, BorderLayout.CENTER);

            //======== panel1 ========
            {
                panel1.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- buttonSerch ----
                buttonSerch.setText("Buscar");
                panel1.add(buttonSerch);
            }
            panelTable.add(panel1, BorderLayout.SOUTH);
        }
        add(panelTable, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - megalexandre@gmail.com
    private JPanel panelTable;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel1;
    private JButton buttonSerch;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
