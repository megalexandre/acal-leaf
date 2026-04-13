package acal.com.acal_left.ui.flatlaf.screen.category.category;

import acal.com.acal_left.core.model.Category;
import acal.com.acal_left.core.usecase.category.CategoryFindAllUseCase;
import acal.com.acal_left.core.usecase.category.CategorySaveUseCase;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.component.utils.AppFontUtils;
import acal.com.acal_left.ui.flatlaf.component.utils.ButtonStyleUtils;
import acal.com.acal_left.ui.flatlaf.screen.category.create.CategoryCreate;
import acal.com.acal_left.ui.flatlaf.screen.category.model.CategoryTableContent;
import acal.com.acal_left.ui.flatlaf.screen.category.model.CategoryTableModel;
import acal.com.acal_left.ui.flatlaf.screen.category.render.CategoryTableRenderer;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

@Component
@Scope("prototype")
@SuppressWarnings("FieldCanBeLocal")
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
        applyModernTheme();

    }

    private void searchActionListener(ActionEvent e) {
        table.setModel(new CategoryTableModel());
        table.setAutoCreateRowSorter(true);
        CategoryTableModel model = (CategoryTableModel) table.getModel();
        var itens = findAll.execute().stream().map(CategoryTableContent::new).toList();
        model.setList(itens);
        applyModernTheme();
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

    private void applyModernTheme() {
        Font bodyFont = AppFontUtils.font(Font.PLAIN, 13f);
        Font headerFont = AppFontUtils.font(Font.BOLD, 13f);

        table.setFont(bodyFont);
        table.setRowHeight(40);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setGridColor(resolveNeutralRowLineColor());
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setFillsViewportHeight(true);

        CategoryTableRenderer renderer = new CategoryTableRenderer(2, Set.of(3, 4, 5));
        table.setDefaultRenderer(Object.class, renderer);
        table.setDefaultRenderer(String.class, renderer);

        var header = table.getTableHeader();
        header.setFont(headerFont);
        header.setReorderingAllowed(false);
        header.setResizingAllowed(true);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 38));

        ButtonStyleUtils.applyPrimary(buttonCreate2);
        ButtonStyleUtils.applySecondary(buttonSearch);
    }

    private Color resolveNeutralRowLineColor() {
        Color separator = UIManager.getColor("Separator.foreground");
        if (separator != null) {
            return separator;
        }
        return new Color(210, 214, 220);
    }

    @SuppressWarnings("Convert2MethodRef")
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        panel3 = new JPanel();
        panel4 = new JPanel();
        buttonCreate2 = new JButton();
        scrollPane1 = new JScrollPane();
        table = new JTable();
        panel1 = new JPanel();
        buttonSearch = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(500, 300));
        setPreferredSize(new Dimension(500, 300));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());

        //======== panel3 ========
        {
            panel3.setLayout(new VerticalLayout());

            //======== panel4 ========
            {
                panel4.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));

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

            //---- table ----
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setShowHorizontalLines(true);
            table.setShowVerticalLines(true);
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tableCategorySearchMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table);
        }
        add(scrollPane1, BorderLayout.CENTER);

        //======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));

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
    private JTable table;
    private JPanel panel1;
    private JButton buttonSearch;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
