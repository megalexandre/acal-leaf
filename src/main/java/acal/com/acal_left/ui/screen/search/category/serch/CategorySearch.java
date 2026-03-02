/*
 * Created by JFormDesigner on Mon Mar 02 11:19:33 BRT 2026
 */

package acal.com.acal_left.ui.screen.search.category.serch;

import acal.com.acal_left.resouces.repository.CategoryJpaRepository;
import acal.com.acal_left.ui.screen.search.category.model.CategoryRecord;
import acal.com.acal_left.ui.screen.search.category.model.CategoryTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

@Component
public class CategorySearch extends JPanel {

    private static final Logger log = LoggerFactory.getLogger(CategorySearch.class);

    private final CategoryJpaRepository repository;

    public CategorySearch(CategoryJpaRepository repository) {
        this.repository = repository;

        initComponents();

        CategoryTableModel model = new CategoryTableModel();
        tableCategorySerch.setModel(model);

        // Adicionar listener de seleção de linha
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
        var itens = repository.findAll().stream().map(CategoryRecord::new).toList();
        model.setList(itens);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - megalexandre@gmail.com
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        tableCategorySerch = new JTable();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
        border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER
        , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font
        .BOLD ,12 ), java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (
        new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r"
        .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
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
