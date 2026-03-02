/*
 * Created by JFormDesigner on Mon Mar 02 13:24:56 BRT 2026
 */

package acal.com.acal_left.ui.screen.search.category.item;

import acal.com.acal_left.resouces.model.CategoryEntity;
import acal.com.acal_left.resouces.model.Group;
import lombok.Setter;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author alex
 */
public class CategoryEdit extends JDialog {

    @Setter
    private ActionListener onOkListener;
    public CategoryEntity categoryEntity;

    public CategoryEdit(Window owner, CategoryEntity categoryEntity) {
        super(owner);
        initComponents();

        this.categoryEntity = categoryEntity;

        okButton.addActionListener(e -> onOkButtonClicked());
        cancelButton.addActionListener(e -> onCancelButtonClicked());

        this.loadData();
    }

    public CategoryEntity getUpdatedCategory() {
        categoryEntity.setGroup((Group) comboBoxGroup.getSelectedItem());
        categoryEntity.setName(textFieldName.getText());
        return categoryEntity;
    }

    private void onOkButtonClicked() {
        categoryEntity.setGroup((Group) comboBoxGroup.getSelectedItem());
        categoryEntity.setName(textFieldName.getText());

        if (onOkListener != null) {
            onOkListener.actionPerformed(new java.awt.event.ActionEvent(this, 0, "OK"));
        }
        dispose();
    }

    private void onCancelButtonClicked() {
        // Apenas fecha a tela sem emitir evento
        dispose();
    }

    private void loadData() {
        comboBoxGroup.setModel(new DefaultComboBoxModel<>(Group.values()));
        comboBoxGroup.setSelectedItem(categoryEntity.getGroup());
        textFieldName.setText(categoryEntity.getName());
        //textFieldNameWaterValue.setText(String.valueOf(category.getWaterValue()));
        //textFieldNameWaterPartner.setText(String.valueOf(category.getPartnerValue()));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - megalexandre@gmail.com
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        comboBoxGroup = new JComboBox<>();
        panel2 = new JPanel();
        label2 = new JLabel();
        textFieldName = new JTextField();
        panel3 = new JPanel();
        label3 = new JLabel();
        textFieldNameWaterValue = new JTextField();
        panel4 = new JPanel();
        label4 = new JLabel();
        textFieldNameWaterPartner = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(512, 384));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new EmptyBorder
            ( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing. border
            . TitledBorder. BOTTOM, new Font ("Dia\u006cog" ,Font .BOLD ,12 ), Color. red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
            propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );
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
                    panel4.add(textFieldNameWaterPartner);
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
    // Generated using JFormDesigner Evaluation license - megalexandre@gmail.com
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel1;
    private JLabel label1;
    private JComboBox<Group> comboBoxGroup;
    private JPanel panel2;
    private JLabel label2;
    private JTextField textFieldName;
    private JPanel panel3;
    private JLabel label3;
    private JTextField textFieldNameWaterValue;
    private JPanel panel4;
    private JLabel label4;
    private JTextField textFieldNameWaterPartner;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
