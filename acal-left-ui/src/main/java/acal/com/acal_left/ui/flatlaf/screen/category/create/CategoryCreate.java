/*
 * Created by JFormDesigner on Tue Mar 03 00:21:48 BRT 2026
 */

package acal.com.acal_left.ui.flatlaf.screen.category.create;

import acal.com.acal_left.core.model.Category;
import acal.com.acal_left.shared.model.MemberGroup;
import acal.com.acal_left.ui.filter.MoneyTextField;
import acal.com.acal_left.ui.flatlaf.screen.category.model.CategoryCreateAttempt;
import acal.com.acal_left.ui.render.MemberGroupRenderer;
import lombok.Setter;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryCreate extends JDialog {

    @Setter
    private ActionListener onSuccess;
    public CategoryCreateAttempt attempt;

    public CategoryCreate(Window owner, Category category) {
        super(owner);
        initComponents();

        setModal(true);

        if(category != null) {
            this.attempt = CategoryCreateAttempt.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .amountPartner(category.getAmountPartner())
                    .amountWater(category.getAmountWater())
                    .memberGroup(category.getMemberGroup())
                    .build();
        }

        this.init();
        okButton.addActionListener(e -> onOkButtonClicked());
    }

    private void init() {
        comboBoxGroup.setModel(new DefaultComboBoxModel<>(MemberGroup.values()));
        comboBoxGroup.setRenderer(new MemberGroupRenderer());

        if(attempt != null) {
            comboBoxGroup.setSelectedItem(attempt.getMemberGroup());
            textFieldName.setText(attempt.getName());
            textFieldNamePartnerValue.setBigDecimal(attempt.getAmountPartner());
            textFieldNameWaterValue.setBigDecimal(attempt.getAmountWater());
        }

    }

    private void onOkButtonClicked() {
        Category builder = Category.builder()
            .id(attempt.getId())
            .name(textFieldName.getText())
            .amountPartner(textFieldNamePartnerValue.getBigDecimal())
            .amountWater(textFieldNameWaterValue.getBigDecimal())
            .memberGroup((MemberGroup) comboBoxGroup.getSelectedItem())
            .build();

        onSuccess.actionPerformed(new ActionEvent(builder, ActionEvent.ACTION_PERFORMED, "OK"));
        dispose();
    }


    private void onCancelButtonClicked() {
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        dialogPane = new JPanel();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        comboBoxGroup = new JComboBox<>();
        panel2 = new JPanel();
        label2 = new JLabel();
        textFieldName = new JTextField();
        panel3 = new JPanel();
        label3 = new JLabel();
        textFieldNameWaterValue = new MoneyTextField();
        panel4 = new JPanel();
        label4 = new JLabel();
        textFieldNamePartnerValue = new MoneyTextField();

        //======== this ========
        setPreferredSize(new Dimension(512, 380));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);

            //======== contentPanel ========
            {
                contentPanel.setLayout(new VerticalLayout(10));

                //======== panel1 ========
                {
                    panel1.setLayout(new VerticalLayout());

                    //---- label1 ----
                    label1.setText("Grupo:");
                    panel1.add(label1);

                    //---- comboBoxGroup ----
                    comboBoxGroup.setPreferredSize(new Dimension(200, 25));
                    panel1.add(comboBoxGroup);
                }
                contentPanel.add(panel1);

                //======== panel2 ========
                {
                    panel2.setLayout(new VerticalLayout());

                    //---- label2 ----
                    label2.setText("Nome:");
                    panel2.add(label2);
                    panel2.add(textFieldName);
                }
                contentPanel.add(panel2);

                //======== panel3 ========
                {
                    panel3.setLayout(new VerticalLayout());

                    //---- label3 ----
                    label3.setText("Valor \u00c1gua:");
                    panel3.add(label3);
                    panel3.add(textFieldNameWaterValue);
                }
                contentPanel.add(panel3);

                //======== panel4 ========
                {
                    panel4.setLayout(new VerticalLayout());

                    //---- label4 ----
                    label4.setText("Valor S\u00f3cio:");
                    panel4.add(label4);
                    panel4.add(textFieldNamePartnerValue);
                }
                contentPanel.add(panel4);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JPanel dialogPane;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel contentPanel;
    private JPanel panel1;
    private JLabel label1;
    private JComboBox<MemberGroup> comboBoxGroup;
    private JPanel panel2;
    private JLabel label2;
    private JTextField textFieldName;
    private JPanel panel3;
    private JLabel label3;
    private MoneyTextField textFieldNameWaterValue;
    private JPanel panel4;
    private JLabel label4;
    private MoneyTextField textFieldNamePartnerValue;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
