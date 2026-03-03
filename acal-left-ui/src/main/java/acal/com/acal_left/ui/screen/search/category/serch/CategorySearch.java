/*
 * Created by JFormDesigner on Mon Mar 02 11:19:33 BRT 2026
 */

package acal.com.acal_left.ui.screen.search.category.serch;

import acal.com.acal_left.core.model.Category;
import acal.com.acal_left.core.usecase.category.CategoryFindAllUseCase;
import acal.com.acal_left.core.usecase.category.CategorySaveUseCase;
import acal.com.acal_left.ui.screen.search.category.model.CategoryTableContent;
import acal.com.acal_left.ui.screen.search.category.model.CategoryTableModel;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.List;

@Component
public class CategorySearch extends JPanel {

    private final CategoryFindAllUseCase findAll;
    private final CategorySaveUseCase save;

    public CategorySearch(
            CategoryFindAllUseCase findAll,
            CategorySaveUseCase save
            ) {
        this.findAll = findAll;
        this.save = save;

        initComponents();

        CategoryTableModel model = new CategoryTableModel();
        tableCategorySerch.setModel(model);

        tableCategorySerch.getSelectionModel().addListSelectionListener(this::onRowSelected);
        loadData();
    }

    private void onRowSelected(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            int selectedRow = tableCategorySerch.getSelectedRow();
            if (selectedRow >= 0) {
                /*
                CategoryTableModel model = (CategoryTableModel) tableCategorySerch.getModel();
                CategoryEntity selectedCategoryEntity = model.getList().get(selectedRow).getCategoryEntityEntity();

                Window window = SwingUtilities.getWindowAncestor(this);
                CategoryEdit categoryEdit = new CategoryEdit(window, selectedCategoryEntity);

                categoryEdit.setOnOkListener(e -> {
                    CategoryEntity updatedCategoryEntity = categoryEdit.getUpdatedCategory();
                    repository.save(updatedCategoryEntity);
                    loadData();
                });

                categoryEdit.setVisible(true);
                */
            }
        }
    }

    public void loadData() {
        CategoryTableModel model = (CategoryTableModel) tableCategorySerch.getModel();
        var itens = find().stream().map(CategoryTableContent::new).toList();
        model.setList(itens);
    }

    private List<Category> find(){
        return findAll.execute();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - megalexandre@gmail.com
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        tableCategorySerch = new JTable();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border
        .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e" , javax. swing .border . TitledBorder. CENTER ,javax
        . swing. border .TitledBorder . BOTTOM, new Font ( "D\u0069al\u006fg", Font. BOLD ,
        12 ) ,Color .red ) , getBorder () ) );  addPropertyChangeListener( new java. beans
        .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062or\u0064er" .equals ( e.
        getPropertyName () ) )throw new RuntimeException( ) ;} } );
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(tableCategorySerch);
            }
            panel1.add(scrollPane1, BorderLayout.CENTER);
        }
        add(panel1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - megalexandre@gmail.com
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable tableCategorySerch;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
