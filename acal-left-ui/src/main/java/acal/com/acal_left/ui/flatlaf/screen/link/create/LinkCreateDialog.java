/*
 * Created by JFormDesigner on Sat Mar 07 09:48:07 BRT 2026
 */

package acal.com.acal_left.ui.flatlaf.screen.link.create;

import acal.com.acal_left.core.model.Address;
import acal.com.acal_left.core.model.Category;
import acal.com.acal_left.core.model.Link;
import acal.com.acal_left.core.model.Person;
import acal.com.acal_left.core.model.filter.PersonFilter;
import acal.com.acal_left.core.usecase.address.AddressFindAllUseCase;
import acal.com.acal_left.core.usecase.category.CategoryFindAllUseCase;
import acal.com.acal_left.core.usecase.link.LinkSaveUseCase;
import acal.com.acal_left.core.usecase.person.PersonFindUseCase;
import acal.com.acal_left.shared.BigDecimalUtil;
import acal.com.acal_left.ui.flatlaf.component.model.ComboBoxLoader;
import acal.com.acal_left.ui.flatlaf.component.model.ComboBoxOption;
import acal.com.acal_left.ui.flatlaf.utils.SwingValidator;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class LinkCreateDialog extends JDialog {

    @Autowired
    private PersonFindUseCase findPersons;

    @Autowired
    private CategoryFindAllUseCase findCategories;

    @Autowired
    private AddressFindAllUseCase findAddresses;

    @Autowired
    private LinkSaveUseCase saveLink;

    private List<Person> persons = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();

    private Person selectedPerson = null;
    private Category selectedCategory = null;
    private Address selectedAddress = null;

    public LinkCreateDialog(Window owner) {
        super(owner);
        initComponents();
        init();
    }

    private void init(){
        initPerson();
        initCategory();
        initAddress();
    }

    private void initAddress(){
        ComboBoxLoader.setupLazyLoad(comboBoxAddress, this::getOrLoadAddresses);
        comboBoxAddress.addActionListener(e -> {
            ComboBoxOption selectedItem = (ComboBoxOption) comboBoxAddress.getSelectedItem();

            if(selectedItem == null || selectedItem.getId() == null) {
                selectedAddress = null;
                return;
            }

            selectedAddress = addresses.stream()
                    .filter(p -> p.getId().equals(selectedItem.getId()))
                    .findFirst()
                    .orElse(null);
        });
    }

    private void initCategory(){
        ComboBoxLoader.setupLazyLoad(comboBoxCategory, this::getOrLoadCategories);
        comboBoxCategory.addActionListener(e -> {
            ComboBoxOption selectedCategoryItem = (ComboBoxOption) comboBoxCategory.getSelectedItem();

            if(selectedCategoryItem == null || selectedCategoryItem.getId() == null) {
                selectedCategory = null;
                return;
            }

            selectedCategory = categories.stream()
                    .filter(p -> p.getId().equals(selectedCategoryItem.getId()))
                    .findFirst()
                    .orElse(null);
        });
    }

    private void initPerson(){
        ComboBoxLoader.setupLazyLoad(comboBoxPerson, this::getOrLoadPersons);
        comboBoxPerson.addActionListener(e -> {
            ComboBoxOption selectedItem = (ComboBoxOption) comboBoxPerson.getSelectedItem();

            if(selectedItem == null || selectedItem.getId() == null) {
                selectedPerson = null;
                return;
            }

            selectedPerson = persons.stream()
                    .filter(p -> p.getId().equals(selectedItem.getId()))
                    .findFirst()
                    .orElse(null);
        });
    }

    private List<ComboBoxOption> getOrLoadAddresses() {
        if(addresses.isEmpty()){
            this.addresses = findAddresses.execute();
        }

        return addresses.stream().map(it -> new ComboBoxOption(it.getId(), it.getName())).toList();
    }

    private List<ComboBoxOption> getOrLoadPersons() {
        if(persons.isEmpty()){
            this.persons = findPersons.execute(new PersonFilter());
        }

        return persons.stream().map(it -> new ComboBoxOption(it.getId(), it.getName())).toList();
    }

    private List<ComboBoxOption> getOrLoadCategories() {
        if(categories.isEmpty()){
            this.categories = findCategories.execute();
        }

        return categories.stream().map(it ->
            new ComboBoxOption(it.getId(), getCategoryRender(it))).toList();
    }

    private void onDispose(ActionEvent e) {
        this.dispose();
    }

    private String getCategoryRender(Category category){
        return "["  +BigDecimalUtil.toBRL(category.getAmount()) + "] "+ category.getFullName();
    }

    private void okActionListener(ActionEvent e) {
        var model = LinkCreateForm.builder()
                .number(textFieldNumber.getText())
                .person(selectedPerson)
                .category(selectedCategory)
                .address(selectedAddress)
                .build();

        var fieldMap = Map.of(
                "person", (JComponent) comboBoxPerson,
                "category", (JComponent) comboBoxCategory,
                "address", (JComponent) comboBoxAddress,
                "number", (JComponent) textFieldNumber
        );

        if (SwingValidator.validate(this, model, fieldMap)) {
            var link = Link.builder()
                    .number(model.getNumber())
                    .person(model.getPerson())
                    .category(model.getCategory())
                    .address(model.getAddress())
                    .active(true)
                    .build();

            saveLink.execute(link);
            dispose();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        comboBoxPerson = new JComboBox<>();
        panel2 = new JPanel();
        label2 = new JLabel();
        comboBoxCategory = new JComboBox<>();
        panel3 = new JPanel();
        label3 = new JLabel();
        comboBoxAddress = new JComboBox<>();
        panel4 = new JPanel();
        label4 = new JLabel();
        textFieldNumber = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setPreferredSize(new Dimension(640, 320));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new VerticalLayout(10));

                //======== panel1 ========
                {
                    panel1.setLayout(new VerticalLayout());

                    //---- label1 ----
                    label1.setText("S\u00f3cio:");
                    panel1.add(label1);
                    panel1.add(comboBoxPerson);
                }
                contentPanel.add(panel1);

                //======== panel2 ========
                {
                    panel2.setLayout(new VerticalLayout());

                    //---- label2 ----
                    label2.setText("Categoria:");
                    panel2.add(label2);
                    panel2.add(comboBoxCategory);
                }
                contentPanel.add(panel2);

                //======== panel3 ========
                {
                    panel3.setLayout(new VerticalLayout());

                    //---- label3 ----
                    label3.setText("Rua:");
                    panel3.add(label3);
                    panel3.add(comboBoxAddress);
                }
                contentPanel.add(panel3);

                //======== panel4 ========
                {
                    panel4.setLayout(new VerticalLayout());

                    //---- label4 ----
                    label4.setText("N\u00famero:");
                    panel4.add(label4);
                    panel4.add(textFieldNumber);
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
                okButton.addActionListener(e -> okActionListener(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> onDispose(e));
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
    private JComboBox<ComboBoxOption> comboBoxPerson;
    private JPanel panel2;
    private JLabel label2;
    private JComboBox<ComboBoxOption> comboBoxCategory;
    private JPanel panel3;
    private JLabel label3;
    private JComboBox<ComboBoxOption> comboBoxAddress;
    private JPanel panel4;
    private JLabel label4;
    private JTextField textFieldNumber;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
