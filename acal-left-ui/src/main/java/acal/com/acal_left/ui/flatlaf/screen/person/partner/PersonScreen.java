package acal.com.acal_left.ui.flatlaf.screen.person.partner;

import javax.swing.border.*;
import acal.com.acal_left.core.model.Person;
import acal.com.acal_left.core.model.filter.PersonFilter;
import acal.com.acal_left.core.usecase.person.PersonFindUseCase;
import acal.com.acal_left.core.usecase.person.PersonSaveUseCase;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.screen.person.create.PersonCreate;
import acal.com.acal_left.ui.flatlaf.screen.person.model.PersonTableContent;
import acal.com.acal_left.ui.flatlaf.screen.person.model.PersonTableModel;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
@Scope("prototype")
public class PersonScreen extends JPanel {

    public final String name = Screen.PARTNER.name();
    private final PersonFilter filter = new PersonFilter();

    public PersonFindUseCase findAll;
    public PersonSaveUseCase save;

    public PersonScreen(PersonFindUseCase findAll, PersonSaveUseCase save) {
        this.findAll = findAll;
        this.save = save;
        initComponents();
    }

    private void searchActionListener(ActionEvent e) {
        search();
    }

    private void tableSearchMouseClicked(MouseEvent e) {
        if (isDoubleClick(e)) {
            JTable table = (JTable) e.getSource();
            int viewRow = table.getSelectedRow();

            if (viewRow != -1) {
                int modelRow = table.convertRowIndexToModel(viewRow);
                PersonTableModel model = (PersonTableModel) table.getModel();
                Person selectedCategory = model.get(modelRow);
                createDialog(selectedCategory);
            }
        }
    }

    private void createDialog(Person person) {
        Window window = SwingUtilities.getWindowAncestor(this);

        PersonCreate personCreate = new PersonCreate(window, person);
        personCreate.pack();
        personCreate.setLocationRelativeTo(window);
        personCreate.setOnSuccess(e -> {
            if (e != null && e.getSource() instanceof Person savedPerson) {
                save.execute(savedPerson);
                search();
            }
        });
        personCreate.setVisible(true);
    }

    private void search(){
        table.setModel(new PersonTableModel());
        table.setAutoCreateRowSorter(true);
        PersonTableModel model = (PersonTableModel) table.getModel();

        createFilter();
        var itens = findAll.execute(filter).stream().map(PersonTableContent::new).toList();
        model.setList(itens);
    }

    private void createFilter(){
        filter.setName(textPartnerName.getText());
    }

    private void textPartnerNameKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            search();
        }
    }

    private boolean isDoubleClick(MouseEvent e) {
        return e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e);
    }

    private void createActionListener(ActionEvent e) {
        createDialog(null);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        panel3 = new JPanel();
        panel2 = new JPanel();
        buttonCreate = new JButton();
        scrollPane1 = new JScrollPane();
        table = new JTable();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        label1 = new JLabel();
        textPartnerName = new JTextField();
        panel7 = new JPanel();
        buttonSearch2 = new JButton();

        //======== this ========
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());

        //======== panel3 ========
        {
            panel3.setLayout(new VerticalLayout());

            //======== panel2 ========
            {
                panel2.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));

                //---- buttonCreate ----
                buttonCreate.setText("Adicionar");
                buttonCreate.addActionListener(e -> createActionListener(e));
                panel2.add(buttonCreate);
            }
            panel3.add(panel2);
        }
        add(panel3, BorderLayout.NORTH);

        //======== scrollPane1 ========
        {

            //---- table ----
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setShowHorizontalLines(true);
            table.setShowVerticalLines(true);
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tableSearchMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table);
        }
        add(scrollPane1, BorderLayout.CENTER);

        //======== panel4 ========
        {
            panel4.setLayout(new VerticalLayout());

            //======== panel5 ========
            {
                panel5.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));

                //======== panel6 ========
                {
                    panel6.setLayout(new VerticalLayout());

                    //---- label1 ----
                    label1.setText("Nome:");
                    panel6.add(label1);

                    //---- textPartnerName ----
                    textPartnerName.setPreferredSize(new Dimension(200, 25));
                    textPartnerName.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            textPartnerNameKeyPressed(e);
                        }
                    });
                    panel6.add(textPartnerName);
                }
                panel5.add(panel6);
            }
            panel4.add(panel5);

            //======== panel7 ========
            {
                panel7.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));

                //---- buttonSearch2 ----
                buttonSearch2.setText("Consultar");
                buttonSearch2.addActionListener(e -> searchActionListener(e));
                panel7.add(buttonSearch2);
            }
            panel4.add(panel7);
        }
        add(panel4, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JPanel panel3;
    private JPanel panel2;
    private JButton buttonCreate;
    private JScrollPane scrollPane1;
    private JTable table;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JLabel label1;
    private JTextField textPartnerName;
    private JPanel panel7;
    private JButton buttonSearch2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
