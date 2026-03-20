package acal.com.acal_left.ui.flatlaf.screen.water.model;

import lombok.Getter;

import static java.util.Arrays.stream;

@Getter
public enum WaterAnalysisColumns {

    PERIOD("Referência:"),
    TYPE("Parâmetro:"),
    REQUIRED("Exigido:"),
    ANALYZED("Analisado:"),
    CONFORMITY("Conforme:")
    ;

    private final String name;
    WaterAnalysisColumns(String name) { this.name = name; }

    public static String[] getLabels() {
        return stream(WaterAnalysisColumns.values())
                .map(WaterAnalysisColumns::getName)
                .toArray(String[]::new);
    }
}