package acal.com.acal_left.ui.flatlaf.screen.person.create;

import acal.com.acal_left.core.model.Document;
import acal.com.acal_left.core.model.Person;
import acal.com.acal_left.ui.flatlaf.component.filter.CpfCnpjTextField;
import acal.com.acal_left.ui.flatlaf.component.utils.AppFontUtils;
import acal.com.acal_left.ui.flatlaf.component.utils.ButtonIconUtils;
import acal.com.acal_left.ui.flatlaf.component.utils.ButtonStyleUtils;
import acal.com.acal_left.ui.flatlaf.screen.person.model.PersonCreateForm;
import acal.com.acal_left.ui.flatlaf.utils.SwingValidator;
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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

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
        applyModernTheme();
        if(person != null) {
            textFieldName.setText(person.getName());
            textPartnerNumber.setText(person.getPartnerNumber());

            Document document = person.getDocument();
            textFieldDocument.setText(document == null ? "" : document.getNumber());
        }
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void applyModernTheme() {
        Font labelFont = AppFontUtils.font(Font.PLAIN, 13f);
        Font fieldFont = AppFontUtils.font(Font.PLAIN, 13f);

        label1.setFont(labelFont);
        label2.setFont(labelFont);
        label3.setFont(labelFont);

        textFieldName.setFont(fieldFont);
        textFieldName.setPreferredSize(new Dimension(280, 32));
        textFieldDocument.setFont(fieldFont);
        textFieldDocument.setPreferredSize(new Dimension(280, 32));
        textPartnerNumber.setFont(fieldFont);
        textPartnerNumber.setPreferredSize(new Dimension(280, 32));

        panel1.setBorder(new EmptyBorder(0, 0, 8, 0));
        panel2.setBorder(new EmptyBorder(0, 0, 8, 0));
        panel3.setBorder(new EmptyBorder(0, 0, 0, 0));

        // Botões modernizados
        ButtonStyleUtils.applyPrimary(okButton);
        okButton.setIcon(ButtonIconUtils.createCheckIcon(okButton.getForeground(), 14));
        okButton.setIconTextGap(6);
        okButton.setText("Salvar");

        ButtonStyleUtils.applySecondary(cancelButton);
        cancelButton.setIcon(ButtonIconUtils.createSearchIcon(cancelButton.getForeground(), 14));
        cancelButton.setIconTextGap(6);
        cancelButton.setText("Cancelar");

        // Rodapé com layout melhorado
        buttonBar.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        buttonBar.setBorder(new EmptyBorder(16, 0, 0, 0));
    }

    private void okButtonActionListener(ActionEvent e) {
        // Obter o documento sem formatação (apenas dígitos)
        String documentValue = (textFieldDocument instanceof CpfCnpjTextField)
            ? ((CpfCnpjTextField) textFieldDocument).getUnformattedValue()
            : textFieldDocument.getText();

        PersonCreateForm form = PersonCreateForm.builder()
            .name(textFieldName.getText())
            .document(documentValue)
            .partnerNumber(textPartnerNumber.getText())
            .build();

        boolean valid = SwingValidator.validate(this, form, Map.of(
            "name",          textFieldName,
            "document",      textFieldDocument,
            "partnerNumber", textPartnerNumber
        ));

        if (!valid) return;

        Integer id = (person == null ? null : person.getId());

        Person person = Person.builder()
            .id(id)
            .name(form.getName())
            .partnerNumber(form.getPartnerNumber())
            .document(Document.builder().value(form.getDocument()).build())
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
        textFieldDocument = new CpfCnpjTextField();
        panel3 = new JPanel();
        label3 = new JLabel();
        textPartnerNumber = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(420, 300));
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
    private CpfCnpjTextField textFieldDocument;
    private JPanel panel3;
    private JLabel label3;
    private JTextField textPartnerNumber;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
