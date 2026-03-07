
package acal.com.acal_left.ui.flatlaf.screen.address.create;

import acal.com.acal_left.core.model.Address;
import lombok.Setter;
import lombok.val;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddressCreate extends JDialog {

    @Setter
    private ActionListener onSuccess;
    private Address address;

    private final List<String> types = List.of("Avenida", "Fazenda", "Praça", "Rua", "Travessa");

    public AddressCreate(Window owner, Address address) {
        super(owner);
        initComponents();
        this.address = address;

        setModal(true);
        init();
    }


    private void init() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("");
        model.addAll(types);
        comboBoxType.setModel(model);

        if(address != null) {
            textFieldName.setText(address.getFullAddress());
            comboBoxType.setSelectedItem(address.getType());
        }
    }


    private void okActionListener(ActionEvent e) {
        val address = Address.builder()
                .id(getId())
                .name(getAddressName())
                .type(getTypeName())
            .build();

        if (onSuccess != null) {
            onSuccess.actionPerformed(new ActionEvent(address, ActionEvent.ACTION_PERFORMED, "OK"));
        }

        dispose();
    }

    private Integer getId(){
        return address == null ? null : address.getId();
    }

    private String getTypeName(){
        return (String) comboBoxType.getSelectedItem();
    }

    private String getAddressName() {
        return textFieldName.getText();
    }

    private void cancelActionListener(ActionEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        panel3 = new JPanel();
        panel1 = new JPanel();
        panel4 = new JPanel();
        label3 = new JLabel();
        comboBoxType = new JComboBox<>();
        panel2 = new JPanel();
        label2 = new JLabel();
        textFieldName = new JTextField();
        dialogPane = new JPanel();
        buttonBar = new JPanel();
        cancelButton = new JButton();
        okButton = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(350, 250));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(10, 10));

        //======== panel3 ========
        {
            panel3.setBorder(new EmptyBorder(10, 10, 10, 10));
            panel3.setLayout(new BorderLayout(10, 10));

            //======== panel1 ========
            {
                panel1.setLayout(new VerticalLayout(10));

                //======== panel4 ========
                {
                    panel4.setLayout(new VerticalLayout());

                    //---- label3 ----
                    label3.setText("Tipo:");
                    panel4.add(label3);
                    panel4.add(comboBoxType);
                }
                panel1.add(panel4);

                //======== panel2 ========
                {
                    panel2.setLayout(new VerticalLayout());

                    //---- label2 ----
                    label2.setText("Nome:");
                    panel2.add(label2);
                    panel2.add(textFieldName);
                }
                panel1.add(panel2);
            }
            panel3.add(panel1, BorderLayout.CENTER);
        }
        contentPane.add(panel3, BorderLayout.CENTER);

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> cancelActionListener(e));
                buttonBar.add(cancelButton);

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> okActionListener(e));
                buttonBar.add(okButton);
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JPanel panel3;
    private JPanel panel1;
    private JPanel panel4;
    private JLabel label3;
    private JComboBox<String> comboBoxType;
    private JPanel panel2;
    private JLabel label2;
    private JTextField textFieldName;
    private JPanel dialogPane;
    private JPanel buttonBar;
    private JButton cancelButton;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
