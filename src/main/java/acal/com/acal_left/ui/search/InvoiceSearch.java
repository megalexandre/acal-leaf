
package acal.com.acal_left.ui.search;

import acal.com.acal_left.core.event.ChangeScreenEvent;
import acal.com.acal_left.core.event.Screen;
import acal.com.acal_left.core.usecase.FindAllCategoryUseCase;
import acal.com.acal_left.core.usecase.FindAllPartnerUseCase;
import acal.com.acal_left.ui.model.ComboBoxOption;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.function.Supplier;

@Component
public class InvoiceSearch extends JFrame {

    private final FindAllCategoryUseCase findAllCategory;
    private final FindAllPartnerUseCase findAllPartner;

    public InvoiceSearch(
        FindAllCategoryUseCase findAllCategory,
        FindAllPartnerUseCase findAllPartner
    ) {
        this.findAllCategory = findAllCategory;
        this.findAllPartner = findAllPartner;

        initComponents();
    }


    @EventListener
    public void onScreenChange(ChangeScreenEvent event) {
        if(event.getScreen() != Screen.INVOICE_SEARCH) {
            clear();
            return;
        }
        startScreen();
        this.setVisible(true);
    }


    private void startScreen(){
        startComboBox();
    }

    private void startComboBox(){
        startComboBox(comboBoxStatus);
        startComboBox(comboBoxCategory);
        startComboBox(comboBoxPartner);
        startComboBox(comboBoxAddress);

        ComboBoxOption[] opcoes = {
            new ComboBoxOption(null, ""),
            new ComboBoxOption(1, "Aberta"),
            new ComboBoxOption(2, "Fechada"),
        };
        comboBoxStatus.setModel(new DefaultComboBoxModel<>(opcoes));
    }

    private void startComboBox(JComboBox<?> comboBox){
        comboBox.setEnabled(false);
        comboBox.setSelectedIndex(-1);
        comboBox.setModel(new DefaultComboBoxModel<>());
    }

    private void clear(){

    }

    private void checkBoxCategoryStateChanged(ChangeEvent e) {
        if(e.getSource() instanceof JCheckBox checkBox) {
            updateComboBoxState(comboBoxCategory, checkBox.isSelected(), findAllCategory::execute);
        }
    }

    private void checkBoxPartnerStateChanged(ChangeEvent e) {
        if(e.getSource() instanceof JCheckBox checkBox) {
            updateComboBoxState(comboBoxPartner, checkBox.isSelected(), findAllPartner::execute);
        }
    }

    private void updateComboBoxState(JComboBox<ComboBoxOption> comboBox, boolean enabled, Supplier<List<?>> dataFetcher) {
        comboBox.setEnabled(enabled);

        if (enabled) {
            List<ComboBoxOption> options = dataFetcher.get().stream()
                    .map(it -> {
                        try {
                            var id = (Integer) it.getClass().getMethod("getId").invoke(it);
                            var name = (String) it.getClass().getMethod("getName").invoke(it);
                            return new ComboBoxOption(id, name);
                        } catch (Exception ex) {
                            return new ComboBoxOption(null, "Erro");
                        }
                    }).toList();

            comboBox.setModel(new DefaultComboBoxModel<>(options.toArray(new ComboBoxOption[0])));
        } else {
            comboBox.setModel(new DefaultComboBoxModel<>());
            comboBox.setSelectedIndex(-1);
        }
    }

    private void checkBoxPartnerStateChanged(ActionEvent e) {
    }

    private void checkBoxCategoryListner(ActionEvent e) {
        // TODO add your code here
    }

    private void checkBoxCategoryListener(ActionEvent e) {
        // TODO add your code here
    }

 

 

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - megalexandre@gmail.com
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        panel6 = new JPanel();
        textFieldInvoiceID = new JTextField();
        Filtar = new JCheckBox();
        panel2 = new JPanel();
        label2 = new JLabel();
        panel7 = new JPanel();
        comboBoxStatus = new JComboBox<>();
        checkBox1 = new JCheckBox();
        panel3 = new JPanel();
        label3 = new JLabel();
        panel8 = new JPanel();
        comboBoxCategory = new JComboBox<>();
        checkBoxCategory = new JCheckBox();
        panel4 = new JPanel();
        label4 = new JLabel();
        panel9 = new JPanel();
        comboBoxPartner = new JComboBox<>();
        checkBoxPartner = new JCheckBox();
        panel5 = new JPanel();
        label5 = new JLabel();
        panel10 = new JPanel();
        comboBoxAddress = new JComboBox();
        checkBox4 = new JCheckBox();
        buttonBar = new JPanel();
        searchButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(1024, 768));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
            ( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border
            . TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
            propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setBorder(new TitledBorder("Filtros:"));
                contentPanel.setLayout(new VerticalLayout(10));

                //======== panel1 ========
                {
                    panel1.setBorder(new EtchedBorder());
                    panel1.setLayout(new BorderLayout(10, 10));

                    //---- label1 ----
                    label1.setText("Conta N\u00famero:");
                    panel1.add(label1, BorderLayout.WEST);

                    //======== panel6 ========
                    {
                        panel6.setLayout(new HorizontalLayout());

                        //---- textFieldInvoiceID ----
                        textFieldInvoiceID.setMinimumSize(new Dimension(300, 35));
                        textFieldInvoiceID.setPreferredSize(new Dimension(200, 35));
                        panel6.add(textFieldInvoiceID);
                        panel6.add(Filtar);
                    }
                    panel1.add(panel6, BorderLayout.EAST);
                }
                contentPanel.add(panel1);

                //======== panel2 ========
                {
                    panel2.setBorder(new EtchedBorder());
                    panel2.setLayout(new BorderLayout(10, 10));

                    //---- label2 ----
                    label2.setText("Status:");
                    panel2.add(label2, BorderLayout.WEST);

                    //======== panel7 ========
                    {
                        panel7.setLayout(new HorizontalLayout());

                        //---- comboBoxStatus ----
                        comboBoxStatus.setMinimumSize(new Dimension(300, 35));
                        comboBoxStatus.setPreferredSize(new Dimension(200, 35));
                        panel7.add(comboBoxStatus);
                        panel7.add(checkBox1);
                    }
                    panel2.add(panel7, BorderLayout.EAST);
                }
                contentPanel.add(panel2);

                //======== panel3 ========
                {
                    panel3.setBorder(new EtchedBorder());
                    panel3.setLayout(new BorderLayout(10, 10));

                    //---- label3 ----
                    label3.setText("Categoria:");
                    panel3.add(label3, BorderLayout.WEST);

                    //======== panel8 ========
                    {
                        panel8.setLayout(new HorizontalLayout());

                        //---- comboBoxCategory ----
                        comboBoxCategory.setMinimumSize(new Dimension(300, 35));
                        comboBoxCategory.setPreferredSize(new Dimension(200, 35));
                        panel8.add(comboBoxCategory);

                        //---- checkBoxCategory ----
                        checkBoxCategory.addChangeListener(e -> checkBoxCategoryStateChanged(e));
                        panel8.add(checkBoxCategory);
                    }
                    panel3.add(panel8, BorderLayout.EAST);
                }
                contentPanel.add(panel3);

                //======== panel4 ========
                {
                    panel4.setBorder(new EtchedBorder());
                    panel4.setLayout(new BorderLayout(10, 10));

                    //---- label4 ----
                    label4.setText("S\u00f3cio:");
                    panel4.add(label4, BorderLayout.WEST);

                    //======== panel9 ========
                    {
                        panel9.setLayout(new HorizontalLayout());

                        //---- comboBoxPartner ----
                        comboBoxPartner.setMinimumSize(new Dimension(300, 35));
                        comboBoxPartner.setPreferredSize(new Dimension(200, 35));
                        panel9.add(comboBoxPartner);

                        //---- checkBoxPartner ----
                        checkBoxPartner.addChangeListener(e -> checkBoxPartnerStateChanged(e));
                        panel9.add(checkBoxPartner);
                    }
                    panel4.add(panel9, BorderLayout.EAST);
                }
                contentPanel.add(panel4);

                //======== panel5 ========
                {
                    panel5.setBorder(new EtchedBorder());
                    panel5.setLayout(new BorderLayout(10, 10));

                    //---- label5 ----
                    label5.setText("Logradouro:");
                    panel5.add(label5, BorderLayout.WEST);

                    //======== panel10 ========
                    {
                        panel10.setLayout(new HorizontalLayout());

                        //---- comboBoxAddress ----
                        comboBoxAddress.setMinimumSize(new Dimension(300, 35));
                        comboBoxAddress.setPreferredSize(new Dimension(200, 35));
                        panel10.add(comboBoxAddress);
                        panel10.add(checkBox4);
                    }
                    panel5.add(panel10, BorderLayout.EAST);
                }
                contentPanel.add(panel5);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- searchButton ----
                searchButton.setText("Buscar");
                searchButton.setPreferredSize(new Dimension(100, 35));
                buttonBar.add(searchButton);

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.setPreferredSize(new Dimension(100, 35));
                buttonBar.add(cancelButton);
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
    private JPanel panel6;
    private JTextField textFieldInvoiceID;
    private JCheckBox Filtar;
    private JPanel panel2;
    private JLabel label2;
    private JPanel panel7;
    private JComboBox<ComboBoxOption> comboBoxStatus;
    private JCheckBox checkBox1;
    private JPanel panel3;
    private JLabel label3;
    private JPanel panel8;
    private JComboBox<ComboBoxOption> comboBoxCategory;
    private JCheckBox checkBoxCategory;
    private JPanel panel4;
    private JLabel label4;
    private JPanel panel9;
    private JComboBox<ComboBoxOption> comboBoxPartner;
    private JCheckBox checkBoxPartner;
    private JPanel panel5;
    private JLabel label5;
    private JPanel panel10;
    private JComboBox comboBoxAddress;
    private JCheckBox checkBox4;
    private JPanel buttonBar;
    private JButton searchButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
