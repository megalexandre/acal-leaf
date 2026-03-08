module acal.left.app {
    requires acal.left.core;
    requires acal.left.shared;
    requires acal.left.persistence;
    requires acal.left.ui;

    // FlatLaf for UI Look and Feel
    requires com.formdev.flatlaf;

    // Lombok (optional - for compile-time processing)
    requires static lombok;

    // Kotlin standard library
    requires kotlin.stdlib;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    requires org.hibernate.validator;

    // Opens packages for Spring's reflection and component scanning
    opens acal.com.acal_left to spring.core, spring.beans, spring.context;

    // Exports the main application package
    exports acal.com.acal_left;
}

