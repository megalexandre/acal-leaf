/*
 * Created by JFormDesigner on Tue Mar 03 17:46:51 BRT 2026
 */

package acal.com.acal_left.ui.flatlaf.screen.link.link;

import acal.com.acal_left.core.model.filter.LinkFilter;
import acal.com.acal_left.core.usecase.link.LinkFindUseCase;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.screen.link.model.LinkTableContent;
import acal.com.acal_left.ui.flatlaf.screen.link.model.LinkTableModel;
import acal.com.acal_left.ui.flatlaf.screen.link.render.LinkTableRenderer;
import acal.com.acal_left.ui.render.YesNoComboBoxRenderer;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Component
@Scope("prototype")
public class LinkScreen extends JPanel {
    public final String name = Screen.LINK.name();

    private final LinkFilter filter = new LinkFilter();
    private final LinkFindUseCase find;

    public LinkScreen(LinkFindUseCase find) {
        this.find = find;
        initComponents();
        init();
    }


    private void searchActionListener(ActionEvent e) {
        table.setModel(new LinkTableModel());
        createRender();
        LinkTableModel model = (LinkTableModel) table.getModel();

        loadFilter();
        var itens = find.execute(filter).stream().map(LinkTableContent::new).toList();
        model.setList(itens);
    }

    private void loadFilter(){
        filter.reset();
        filter.setActive((Boolean) comboBoxActive.getModel().getSelectedItem());
    }

    private void createRender(){
        LinkTableRenderer customRenderer = new LinkTableRenderer();

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
        }
    }

    private void init(){

        comboBoxActive.setModel(new DefaultComboBoxModel<>(new Boolean[]{null, TRUE, FALSE}));
        comboBoxActive.setSelectedItem(null);
        comboBoxActive.setRenderer(new YesNoComboBoxRenderer());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        panel1 = new JPanel();
        panel4 = new JPanel();
        buttonCreate = new JButton();
        scrollPane1 = new JScrollPane();
        table = new JTable();
        panel2 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        panel8 = new JPanel();
        label2 = new JLabel();
        textField1 = new JTextField();
        panel9 = new JPanel();
        label3 = new JLabel();
        textField2 = new JTextField();
        panel10 = new JPanel();
        label4 = new JLabel();
        comboBoxActive = new JComboBox<>();
        panel7 = new JPanel();
        buttonSearch = new JButton();

        //======== this ========
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new VerticalLayout());

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

            //---- table ----
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setShowHorizontalLines(true);
            table.setShowVerticalLines(true);
            scrollPane1.setViewportView(table);
        }
        add(scrollPane1, BorderLayout.CENTER);

        //======== panel2 ========
        {
            panel2.setLayout(new VerticalLayout());

            //======== panel5 ========
            {
                panel5.setLayout(new VerticalLayout());

                //======== panel6 ========
                {
                    panel6.setLayout(new FlowLayout(FlowLayout.TRAILING));

                    //======== panel8 ========
                    {
                        panel8.setLayout(new FlowLayout(FlowLayout.LEFT));

                        //---- label2 ----
                        label2.setText("S\u00f3cio");
                        panel8.add(label2);

                        //---- textField1 ----
                        textField1.setPreferredSize(new Dimension(200, 25));
                        panel8.add(textField1);
                    }
                    panel6.add(panel8);

                    //======== panel9 ========
                    {
                        panel9.setLayout(new FlowLayout(FlowLayout.LEFT));

                        //---- label3 ----
                        label3.setText("Rua:");
                        panel9.add(label3);

                        //---- textField2 ----
                        textField2.setPreferredSize(new Dimension(200, 25));
                        panel9.add(textField2);
                    }
                    panel6.add(panel9);

                    //======== panel10 ========
                    {
                        panel10.setLayout(new FlowLayout(FlowLayout.LEFT));

                        //---- label4 ----
                        label4.setText("Ativo:");
                        panel10.add(label4);

                        //---- comboBoxActive ----
                        comboBoxActive.setPreferredSize(new Dimension(150, 25));
                        panel10.add(comboBoxActive);
                    }
                    panel6.add(panel10);
                }
                panel5.add(panel6);

                //======== panel7 ========
                {
                    panel7.setLayout(new FlowLayout(FlowLayout.RIGHT));

                    //---- buttonSearch ----
                    buttonSearch.setText("Consultar");
                    buttonSearch.addActionListener(e -> searchActionListener(e));
                    panel7.add(buttonSearch);
                }
                panel5.add(panel7);
            }
            panel2.add(panel5);
        }
        add(panel2, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JPanel panel1;
    private JPanel panel4;
    private JButton buttonCreate;
    private JScrollPane scrollPane1;
    private JTable table;
    private JPanel panel2;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel8;
    private JLabel label2;
    private JTextField textField1;
    private JPanel panel9;
    private JLabel label3;
    private JTextField textField2;
    private JPanel panel10;
    private JLabel label4;
    private JComboBox<Boolean> comboBoxActive;
    private JPanel panel7;
    private JButton buttonSearch;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
