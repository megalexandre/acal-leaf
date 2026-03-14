package acal.com.acal_left.ui.event;

import lombok.Getter;

public enum Screen {
    CATEGORY("Categorias"),
    PARTNER("Sócios"),
    REGISTER("Lançamentos"),
    ADDRESS("Endereços"),
    LINK("Ligações"),
    INVOICE("Faturas"),
    CHARGE("Cobranças")
    ;

    @Getter
    private final String title;

    Screen(String title) {
        this.title = title; }

}
