
package acal.com.acal_left.ui.flatlaf.screen.category.category;

import acal.com.acal_left.core.model.Category;
import acal.com.acal_left.core.usecase.category.CategoryFindAllUseCase;
import acal.com.acal_left.core.usecase.category.CategorySaveUseCase;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.screen.category.create.CategoryCreate;
import acal.com.acal_left.ui.flatlaf.screen.category.model.CategoryTableContent;
import acal.com.acal_left.ui.flatlaf.screen.category.model.CategoryTableModel;
import org.jdesktop.swingx.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
@Scope("prototype")
public class CategoryScreen extends JPanel {
    public final String name = Screen.CATEGORY.name();

    private final CategoryFindAllUseCase findAll;
    private final CategorySaveUseCase save;

    public CategoryScreen(
            CategoryFindAllUseCase findAll,
            CategorySaveUseCase save
    ) {
        this.findAll = findAll;
        this.save = save;

        initComponents();

    }

    private void searchActionListener(ActionEvent e) {
        tableCategorySearch.setModel(new CategoryTableModel());
        CategoryTableModel model = (CategoryTableModel) tableCategorySearch.getModel();
        var itens = findAll.execute().stream().map(CategoryTableContent::new).toList();
        model.setList(itens);
    }

    private void addActionListener(ActionEvent e) {
        createDialog(null);
    }

    private void tableCategorySearchMouseClicked(MouseEvent e) {
        if (isDoubleClick(e)) {

            JTable table = (JTable) e.getSource();
            int viewRow = table.getSelectedRow();

            if (viewRow != -1) {
                int modelRow = table.convertRowIndexToModel(viewRow);
                CategoryTableModel model = (CategoryTableModel) table.getModel();
                Category selectedCategory = model.getCategory(modelRow);
                createDialog(selectedCategory);
            }
        }
    }

    private void createDialog(Category category) {
        Window window = SwingUtilities.getWindowAncestor(this);

        CategoryCreate categoryEdit = new CategoryCreate(window, category);
        categoryEdit.pack();
        categoryEdit.setLocationRelativeTo(window);
        categoryEdit.setOnSuccess(e -> {
            save.execute((Category) e.getSource());
                searchActionListener(null);
        });
        categoryEdit.setVisible(true);
    }

    private boolean isDoubleClick(MouseEvent e) {
        return e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        panel3 = new JPanel();
        panel4 = new JPanel();
        buttonCreate2 = new JButton();
        scrollPane1 = new JScrollPane();
        tableCategorySearch = new JTable();
        panel1 = new JPanel();
        buttonSearch = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(500, 300));
        setPreferredSize(new Dimension(500, 300));
        setLayout(new BorderLayout());

        //======== panel3 ========
        {
            panel3.setLayout(new VerticalLayout());

            //======== panel4 ========
            {
                panel4.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- buttonCreate2 ----
                buttonCreate2.setText("Adicionar");
                buttonCreate2.addActionListener(e -> addActionListener(e));
                panel4.add(buttonCreate2);
            }
            panel3.add(panel4);
        }
        add(panel3, BorderLayout.NORTH);

        //======== scrollPane1 ========
        {

            //---- tableCategorySearch ----
            tableCategorySearch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tableCategorySearch.setShowHorizontalLines(true);
            tableCategorySearch.setShowVerticalLines(true);
            tableCategorySearch.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tableCategorySearchMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(tableCategorySearch);
        }
        add(scrollPane1, BorderLayout.CENTER);

        //======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.RIGHT));

            //---- buttonSearch ----
            buttonSearch.setText("Consultar");
            buttonSearch.addActionListener(e -> searchActionListener(e));
            panel1.add(buttonSearch);
        }
        add(panel1, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JPanel panel3;
    private JPanel panel4;
    private JButton buttonCreate2;
    private JScrollPane scrollPane1;
    private JTable tableCategorySearch;
    private JPanel panel1;
    private JButton buttonSearch;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
