package acal.com.acal_left.ui.flatlaf.screen.category.create;

import acal.com.acal_left.core.model.Category;
import acal.com.acal_left.shared.model.MemberGroup;
import acal.com.acal_left.ui.flatlaf.component.filter.MoneyTextField;
import acal.com.acal_left.ui.flatlaf.component.render.MemberGroupRenderer;
import acal.com.acal_left.ui.flatlaf.component.render.YesNoComboBoxRenderer;
import acal.com.acal_left.ui.flatlaf.screen.category.model.CategoryViewModel;
import lombok.Setter;
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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class CategoryCreate extends JDialog {
    @Setter
    private ActionListener onSuccess;

    private CategoryViewModel model;

    public CategoryCreate(Window owner, Category category) {
        super(owner);
        initComponents();

        setModal(true);

        if(category != null) {
            this.model = CategoryViewModel.buildFromEntity(category);
        }

        this.init();
        okButton.addActionListener(e -> onOkButtonClicked());
    }

    private void init() {
        comboBoxGroup.setModel(new DefaultComboBoxModel<>(MemberGroup.values()));
        comboBoxGroup.setRenderer(new MemberGroupRenderer());
        comboBoxHydrometer.setModel(new DefaultComboBoxModel<>(new Boolean[]{TRUE, FALSE}));
        comboBoxHydrometer.setSelectedItem(FALSE);
        comboBoxHydrometer.setRenderer(new YesNoComboBoxRenderer());

        if(model != null) {
            comboBoxGroup.setSelectedItem(model.getMemberGroup());
            comboBoxHydrometer.setSelectedItem(model.isHydrometer());
            textFieldName.setText(model.getName());
            textFieldNamePartnerValue.setBigDecimal(model.getAmountPartner());
            textFieldNameWaterValue.setBigDecimal(model.getAmountWater());
        }

    }

    private void onOkButtonClicked() {
        Integer id = (model == null? null : model.getId());

        Category category = Category.builder()
            .id(id)
            .name(textFieldName.getText())
            .isHydrometer((Boolean) comboBoxHydrometer.getSelectedItem())
            .amountPartner(textFieldNamePartnerValue.getBigDecimal())
            .amountWater(textFieldNameWaterValue.getBigDecimal())
            .memberGroup((MemberGroup) comboBoxGroup.getSelectedItem())
            .build();

        onSuccess.actionPerformed(new ActionEvent(category, ActionEvent.ACTION_PERFORMED, "OK"));
        dispose();
    }

    private void onCancelButtonClicked(ActionEvent e) {
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        comboBoxGroup = new JComboBox<>();
        panel2 = new JPanel();
        label2 = new JLabel();
        textFieldName = new JTextField();
        panel5 = new JPanel();
        label5 = new JLabel();
        comboBoxHydrometer = new JComboBox<>();
        panel3 = new JPanel();
        label3 = new JLabel();
        textFieldNameWaterValue = new MoneyTextField();
        panel4 = new JPanel();
        label4 = new JLabel();
        textFieldNamePartnerValue = new MoneyTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(512, 380));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

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

                //======== panel5 ========
                {
                    panel5.setLayout(new VerticalLayout());

                    //---- label5 ----
                    label5.setText("Hidr\u00f4metro?:");
                    panel5.add(label5);
                    panel5.add(comboBoxHydrometer);
                }
                contentPanel.add(panel5);

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
                cancelButton.addActionListener(e -> onCancelButtonClicked(e));
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
    private JComboBox<MemberGroup> comboBoxGroup;
    private JPanel panel2;
    private JLabel label2;
    private JTextField textFieldName;
    private JPanel panel5;
    private JLabel label5;
    private JComboBox<Boolean> comboBoxHydrometer;
    private JPanel panel3;
    private JLabel label3;
    private MoneyTextField textFieldNameWaterValue;
    private JPanel panel4;
    private JLabel label4;
    private MoneyTextField textFieldNamePartnerValue;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
