package acal.com.acal_left.ui.event;

import lombok.Getter;

public enum Screen {
    CATEGORY("Categoria"),
    PARTNER("Sócio"),
    REGISTER("Lançamento"),
    ADDRESS("Endereço"),
    LINK("Ligações"),
    INVOICE("Faturas")
    ;

    @Getter
    private final String title;

    Screen(String title) {
        this.title = title; }

}
