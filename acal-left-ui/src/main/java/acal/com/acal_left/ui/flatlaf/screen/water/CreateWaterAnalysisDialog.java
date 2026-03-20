package acal.com.acal_left.ui.flatlaf.screen.water;

import acal.com.acal_left.core.model.WaterAnalysis;
import acal.com.acal_left.shared.model.WaterParameterType;
import acal.com.acal_left.ui.flatlaf.screen.water.model.CreateWaterAnalysisForm;
import acal.com.acal_left.ui.flatlaf.screen.water.model.CreateWaterAnalysisItemForm;
import acal.com.acal_left.ui.flatlaf.utils.SwingValidator;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CreateWaterAnalysisDialog extends JDialog {
    private final List<WaterAnalysisItemInputRow> itemRows = new ArrayList<>();

    private final Consumer<WaterAnalysis> onSuccess;

    public CreateWaterAnalysisDialog(Window owner, Consumer<WaterAnalysis> onSuccess) {
        super(owner);
        this.onSuccess = onSuccess;
        initComponents();
        init();
    }

    private void init(){
        createMask(formattedPeriod, "##/####");
        cancelButton.addActionListener(e -> dispose());
        okButton.addActionListener(e -> saveWaterAnalysis());

        panelContent.setLayout(new GridLayout(0, 4, 10, 5));
        panelContent.add(new javax.swing.JLabel("<html><b>Parametro</b></html>"));
        panelContent.add(new javax.swing.JLabel("<html><b>Exigido</b></html>"));
        panelContent.add(new javax.swing.JLabel("<html><b>Analisado</b></html>"));
        panelContent.add(new javax.swing.JLabel("<html><b>Conformidade</b></html>"));

        itemRows.clear();
        for (WaterParameterType type : WaterParameterType.values()) {
            JTextField requiredField = new javax.swing.JTextField("");
            JTextField analyzedField = new javax.swing.JTextField("");
            JTextField conformityField = new javax.swing.JTextField("");

            panelContent.add(new javax.swing.JLabel(type.getDescription()));
            panelContent.add(requiredField);
            panelContent.add(analyzedField);
            panelContent.add(conformityField);

            itemRows.add(new WaterAnalysisItemInputRow(type, requiredField, analyzedField, conformityField));
        }
        panelContent.revalidate();
    }

    private void saveWaterAnalysis(){
        List<CreateWaterAnalysisItemForm> itemForms = itemRows.stream()
            .map(row -> CreateWaterAnalysisItemForm.builder()
                .type(row.type)
                .required(normalize(row.requiredField.getText()))
                .analyzed(normalize(row.analyzedField.getText()))
                .conformity(normalize(row.conformityField.getText()))
                .build())
            .toList();

        for (int i = 0; i < itemForms.size(); i++) {
            CreateWaterAnalysisItemForm itemForm = itemForms.get(i);
            WaterAnalysisItemInputRow row = itemRows.get(i);

            boolean validItem = SwingValidator.validate(this, itemForm, Map.of(
                "required", (JComponent) row.requiredField,
                "analyzed", (JComponent) row.analyzedField,
                "conformity", (JComponent) row.conformityField
            ));

            if (!validItem) {
                return;
            }
        }

        LocalDate period = getPeriod();
        List<CreateWaterAnalysisItemForm> analysis = itemForms.stream()
            .map(it -> CreateWaterAnalysisItemForm.builder()
                .type(it.getType())
                .required(it.getRequired())
                .analyzed(it.getAnalyzed())
                .conformity(it.getConformity())
                .build())
            .toList();

        CreateWaterAnalysisForm form = CreateWaterAnalysisForm.builder()
            .analysis(analysis)
            .period(period)
            .build();

        boolean validForm = SwingValidator.validate(this, form, Map.of(
            "analysis", panelContent,
            "period", panelContent
        ));

        if (!validForm) {
            return;
        }

        onSuccess.accept(form.toWaterAnalysis());
    }

    private LocalDate getPeriod() {
        String texto = formattedPeriod.getText().trim();

        if (texto.contains("_") || texto.length() < 7) {
            return null;
        }

        try {
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("MM/yyyy");
            YearMonth mesAno = YearMonth.parse(texto, parser);
            return mesAno.atDay(1);

        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static String normalize(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    private static class WaterAnalysisItemInputRow {
        private final WaterParameterType type;
        private final JTextField requiredField;
        private final JTextField analyzedField;
        private final JTextField conformityField;

        private WaterAnalysisItemInputRow(
            WaterParameterType type,
            JTextField requiredField,
            JTextField analyzedField,
            JTextField conformityField
        ) {
            this.type = type;
            this.requiredField = requiredField;
            this.analyzedField = analyzedField;
            this.conformityField = conformityField;
        }
    }

    private void createMask(JFormattedTextField field, String mask){
        try{
            MaskFormatter item = new MaskFormatter(mask);
            item.setPlaceholderCharacter('_');
            item.install(field);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner non-commercial license
        dialogPane = new JPanel();
        panel1 = new JPanel();
        label4 = new JLabel();
        formattedPeriod = new JFormattedTextField();
        panelContent = new JPanel();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== panel1 ========
            {
                panel1.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- label4 ----
                label4.setText("Periodo:");
                panel1.add(label4);
                panel1.add(formattedPeriod);
            }
            dialogPane.add(panel1, BorderLayout.NORTH);

            //======== panelContent ========
            {
                panelContent.setLayout(new BorderLayout());
            }
            dialogPane.add(panelContent, BorderLayout.CENTER);

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
    // Generated using JFormDesigner non-commercial license
    private JPanel dialogPane;
    private JPanel panel1;
    private JLabel label4;
    private JFormattedTextField formattedPeriod;
    private JPanel panelContent;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
