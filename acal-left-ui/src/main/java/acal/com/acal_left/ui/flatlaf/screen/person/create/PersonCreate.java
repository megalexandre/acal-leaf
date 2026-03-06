package acal.com.acal_left.ui.flatlaf.screen.person.create;

import acal.com.acal_left.core.model.Document;
import acal.com.acal_left.core.model.Person;
import lombok.Setter;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonCreate extends JDialog {

    @Setter
    private ActionListener onSuccess;

    private final Person person;

    public PersonCreate(Window owner, Person person) {
        super(owner);
        initComponents();
        this.person = person;
        init();
    }

    private void init() {
        if(person != null) {
            textFieldName.setText(person.getName());
            textPartnerNumber.setText(person.getPartnerNumber());

            Document document = person.getDocument();
            textFieldDocument.setText(document == null ? "" : document.getNumber());
        }
    }

    private void okButtonActionListener(ActionEvent e) {
        Integer id = (person == null ? null : person.getId());

        Person person = Person.builder()
            .id(id)
            .name(getNamePersonName())
            .partnerNumber(getPartnerNumber())
            .document(Document.builder().value(getDocumentNumber()).build())
            .build();

        if (onSuccess != null) {
            onSuccess.actionPerformed(new ActionEvent(person, ActionEvent.ACTION_PERFORMED, "OK"));
        }
        dispose();
    }

    private String getNamePersonName(){
        return textFieldName.getText();
    }

    private String getDocumentNumber(){
        return textFieldDocument.getText();
    }

    private String getPartnerNumber(){
        return textPartnerNumber.getText();
    }

    private void cancelButtonActionListener(ActionEvent e) {
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        textFieldName = new JTextField();
        panel2 = new JPanel();
        label2 = new JLabel();
        textFieldDocument = new JTextField();
        panel3 = new JPanel();
        label3 = new JLabel();
        textPartnerNumber = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setPreferredSize(new Dimension(384, 260));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new VerticalLayout(10));

                //======== panel1 ========
                {
                    panel1.setLayout(new VerticalLayout());

                    //---- label1 ----
                    label1.setText("Nome:");
                    panel1.add(label1);
                    panel1.add(textFieldName);
                }
                contentPanel.add(panel1);

                //======== panel2 ========
                {
                    panel2.setLayout(new VerticalLayout());

                    //---- label2 ----
                    label2.setText("Documento:");
                    panel2.add(label2);
                    panel2.add(textFieldDocument);
                }
                contentPanel.add(panel2);

                //======== panel3 ========
                {
                    panel3.setLayout(new VerticalLayout());

                    //---- label3 ----
                    label3.setText("N\u00famero de S\u00f3cio");
                    panel3.add(label3);
                    panel3.add(textPartnerNumber);
                }
                contentPanel.add(panel3);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> okButtonActionListener(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> cancelButtonActionListener(e));
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel1;
    private JLabel label1;
    private JTextField textFieldName;
    private JPanel panel2;
    private JLabel label2;
    private JTextField textFieldDocument;
    private JPanel panel3;
    private JLabel label3;
    private JTextField textPartnerNumber;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
