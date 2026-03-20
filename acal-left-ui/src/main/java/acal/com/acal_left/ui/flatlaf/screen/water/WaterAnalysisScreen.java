
package acal.com.acal_left.ui.flatlaf.screen.water;

import acal.com.acal_left.core.model.WaterAnalysis;
import acal.com.acal_left.core.usecase.water.WaterAnalysisListUseCase;
import acal.com.acal_left.core.usecase.water.WaterAnalysisSaveUseCase;
import acal.com.acal_left.ui.event.Screen;
import acal.com.acal_left.ui.flatlaf.screen.water.model.WaterAnalysisTableContent;
import acal.com.acal_left.ui.flatlaf.screen.water.model.WaterAnalysisTableModel;
import acal.com.acal_left.ui.flatlaf.utils.Toast;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.util.Collection;
import java.util.List;

@Component
@Scope("prototype")
public class WaterAnalysisScreen extends JPanel {
    public final String name = Screen.WATER_ANALYSIS.name();

    @Autowired
    public WaterAnalysisListUseCase list;

    @Autowired
    public WaterAnalysisSaveUseCase saveAll;

    private final Window window = javax.swing.SwingUtilities.getWindowAncestor(this);

    public WaterAnalysisScreen() {
        initComponents();
        init();
    }

    private void init(){
        buttonSearch.addActionListener(it -> search());
        buttonAdd.addActionListener(it -> showDialog());

        table.setAutoCreateRowSorter(true);
        table.setModel(new WaterAnalysisTableModel());
    }

    private void search(){
        List<WaterAnalysis> itens = list.execute();
        val modelItens = itens.stream()
            .map(WaterAnalysis::getAnalysis)
            .flatMap(Collection::stream)
            .map(WaterAnalysisTableContent::new)
            .toList();
        WaterAnalysisTableModel model = (WaterAnalysisTableModel) table.getModel();
        model.setList(modelItens);
        table.setModel(model);
    }

    private void showDialog(){
        CreateWaterAnalysisDialog dialog = new CreateWaterAnalysisDialog(window, (it) -> {
            saveAll.execute(List.of(it));
            Toast.show(this, "Salvo com sucesso!");
            search();
        });
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        panel1 = new JPanel();
        buttonAdd = new JButton();
        scrollPane1 = new JScrollPane();
        table = new JTable();
        panel2 = new JPanel();
        buttonSearch = new JButton();

        //======== this ========
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));

            //---- buttonAdd ----
            buttonAdd.setText("Adicionar");
            panel1.add(buttonAdd);
        }
        add(panel1, BorderLayout.NORTH);

        //======== scrollPane1 ========
        {

            //---- table ----
            table.setShowHorizontalLines(true);
            table.setShowVerticalLines(true);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scrollPane1.setViewportView(table);
        }
        add(scrollPane1, BorderLayout.CENTER);

        //======== panel2 ========
        {
            panel2.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));

            //---- buttonSearch ----
            buttonSearch.setText("Consultar");
            panel2.add(buttonSearch);
        }
        add(panel2, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner non-commercial license
    private JPanel panel1;
    private JButton buttonAdd;
    private JScrollPane scrollPane1;
    private JTable table;
    private JPanel panel2;
    private JButton buttonSearch;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
