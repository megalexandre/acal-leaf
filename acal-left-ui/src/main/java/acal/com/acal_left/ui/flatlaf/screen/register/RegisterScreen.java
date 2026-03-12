/*
 * Created by JFormDesigner on Wed Mar 11 13:01:48 BRT 2026
 */

package acal.com.acal_left.ui.flatlaf.screen.register;

import acal.com.acal_left.core.model.filter.InvoiceQuery;
import acal.com.acal_left.core.usecase.invoice.InvoiceListUseCase;
import acal.com.acal_left.shared.LocalDateUtil;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.screen.register.model.RegisterTableContent;
import acal.com.acal_left.ui.flatlaf.screen.register.model.RegisterTableModel;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@Scope("prototype")
public class RegisterScreen extends JPanel {
    public final String name = Screen.REGISTER.name();

    @Autowired
    private InvoiceListUseCase list;

    public RegisterScreen() {
        initComponents();
        init();
    }

    private void init(){
        createMask(formattedTextFieldStart, "##/##/####");
        createMask(formattedTextFieldEnd, "##/##/####");

        table.setAutoCreateRowSorter(true);
        table.setModel(new RegisterTableModel());
    }

    private void buttonActionListener(ActionEvent e) {
        var filter = InvoiceQuery.builder()
            .periodStart(getStart())
            .periodEnd(getEnd())
            .build();

        RegisterTableModel model = (RegisterTableModel) table.getModel();

        var itens = list.execute(filter).stream().map(RegisterTableContent::new).toList();
        model.setList(itens);
        table.setModel(model);
    }

    private void createMask(JFormattedTextField field, String mask){
        try{
            MaskFormatter item = new MaskFormatter(mask);
            item.setPlaceholderCharacter('_');
            item.install(field);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private LocalDateTime getStart(){
        var date = LocalDateUtil.fromString(formattedTextFieldStart.getText());
        if(date == null){
            return null;
        }

        return date.atStartOfDay();
    }

    private LocalDateTime getEnd(){
        var date = LocalDateUtil.fromString(formattedTextFieldEnd.getText());
        if(date == null){
            return null;
        }

        return date.atTime(LocalTime.MAX);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        scrollPane1 = new JScrollPane();
        table = new JTable();
        panel1 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        panel2 = new JPanel();
        Inicio = new JLabel();
        formattedTextFieldStart = new JFormattedTextField();
        panel3 = new JPanel();
        Inicio2 = new JLabel();
        formattedTextFieldEnd = new JFormattedTextField();
        panel7 = new JPanel();
        panel4 = new JPanel();
        label1 = new JLabel();
        button = new JButton();

        //======== this ========
        setLayout(new BorderLayout());

        //======== scrollPane1 ========
        {

            //---- table ----
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setShowHorizontalLines(true);
            table.setShowVerticalLines(true);
            table.setBorder(new EmptyBorder(5, 5, 5, 5));
            scrollPane1.setViewportView(table);
        }
        add(scrollPane1, BorderLayout.CENTER);

        //======== panel1 ========
        {
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

            //======== panel5 ========
            {
                panel5.setBorder(new EmptyBorder(5, 5, 5, 5));
                panel5.setLayout(new BorderLayout(10, 10));

                //======== panel6 ========
                {
                    panel6.setLayout(new HorizontalLayout(5));

                    //======== panel2 ========
                    {
                        panel2.setLayout(new VerticalLayout());

                        //---- Inicio ----
                        Inicio.setText("Inicio:");
                        panel2.add(Inicio);

                        //---- formattedTextFieldStart ----
                        formattedTextFieldStart.setPreferredSize(new Dimension(150, 25));
                        panel2.add(formattedTextFieldStart);
                    }
                    panel6.add(panel2);

                    //======== panel3 ========
                    {
                        panel3.setLayout(new VerticalLayout());

                        //---- Inicio2 ----
                        Inicio2.setText("Final:");
                        panel3.add(Inicio2);

                        //---- formattedTextFieldEnd ----
                        formattedTextFieldEnd.setPreferredSize(new Dimension(150, 25));
                        panel3.add(formattedTextFieldEnd);
                    }
                    panel6.add(panel3);
                }
                panel5.add(panel6, BorderLayout.WEST);

                //======== panel7 ========
                {
                    panel7.setLayout(new VerticalLayout());

                    //======== panel4 ========
                    {
                        panel4.setLayout(new VerticalLayout());

                        //---- label1 ----
                        label1.setText("Buscar:");
                        panel4.add(label1);

                        //---- button ----
                        button.setText("Consultar");
                        button.addActionListener(e -> buttonActionListener(e));
                        panel4.add(button);
                    }
                    panel7.add(panel4);
                }
                panel5.add(panel7, BorderLayout.EAST);
            }
            panel1.add(panel5);
        }
        add(panel1, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JScrollPane scrollPane1;
    private JTable table;
    private JPanel panel1;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel2;
    private JLabel Inicio;
    private JFormattedTextField formattedTextFieldStart;
    private JPanel panel3;
    private JLabel Inicio2;
    private JFormattedTextField formattedTextFieldEnd;
    private JPanel panel7;
    private JPanel panel4;
    private JLabel label1;
    private JButton button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
