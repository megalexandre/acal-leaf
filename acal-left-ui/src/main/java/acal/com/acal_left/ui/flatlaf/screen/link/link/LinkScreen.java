/*
 * Created by JFormDesigner on Tue Mar 03 17:46:51 BRT 2026
 */

package acal.com.acal_left.ui.flatlaf.screen.link.link;

import acal.com.acal_left.core.model.filter.LinkFilter;
import acal.com.acal_left.core.usecase.link.LinkFindUseCase;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.screen.link.model.LinkTableContent;
import acal.com.acal_left.ui.flatlaf.screen.link.model.LinkTableModel;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@Component
@Scope("prototype")
public class LinkScreen extends JPanel {
    public final String name = Screen.LINK.name();

    private final LinkFindUseCase find;

    public LinkScreen(LinkFindUseCase find) {
        this.find = find;
        initComponents();
    }

    private void searchActionListener(ActionEvent e) {
        table.setModel(new LinkTableModel());
        LinkTableModel model = (LinkTableModel) table.getModel();

        var itens = find.execute(LinkFilter.builder().build()).stream().map(LinkTableContent::new).toList();
        model.setList(itens);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        panel1 = new JPanel();
        panel3 = new JPanel();
        label1 = new JLabel();
        panel4 = new JPanel();
        buttonCreate = new JButton();
        scrollPane1 = new JScrollPane();
        table = new JTable();
        panel2 = new JPanel();
        buttonSearch = new JButton();

        //======== this ========
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new VerticalLayout());

            //======== panel3 ========
            {
                panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- label1 ----
                label1.setText("Liga\u00e7\u00f5es");
                panel3.add(label1);
            }
            panel1.add(panel3);

            //======== panel4 ========
            {
                panel4.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- buttonCreate ----
                buttonCreate.setText("Adicionar");
                panel4.add(buttonCreate);
            }
            panel1.add(panel4);
        }
        add(panel1, BorderLayout.NORTH);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table);
        }
        add(scrollPane1, BorderLayout.CENTER);

        //======== panel2 ========
        {
            panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

            //---- buttonSearch ----
            buttonSearch.setText("Consultar");
            buttonSearch.addActionListener(e -> searchActionListener(e));
            panel2.add(buttonSearch);
        }
        add(panel2, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JPanel panel1;
    private JPanel panel3;
    private JLabel label1;
    private JPanel panel4;
    private JButton buttonCreate;
    private JScrollPane scrollPane1;
    private JTable table;
    private JPanel panel2;
    private JButton buttonSearch;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
