package acal.com.acal_left.ui.flatlaf.screen.category.model;

import lombok.Getter;

@Getter
public enum CategoryColumns {
    CATEGORY("Categoria"),
    NAME("Nome"),
    WATER("Valor Água"),
    PARTNER("Valor Sócio"),
    TOTAL("Total");

    private final String name;
    CategoryColumns(String name) { this.name = name; }

}