module acal.left.app {
    requires acal.left.core;
    requires acal.left.shared;

    // Database
    requires java.sql;


    // FlatLaf for UI Look and Feel
    requires com.formdev.flatlaf;

    // Lombok (optional - for compile-time processing)
    requires static lombok;

    // Kotlin standard library
    requires kotlin.stdlib;

    // Opens packages for Spring's reflection and component scanning
    opens acal.com.acal_left to spring.core, spring.beans, spring.context;

    // Exports the main application package
    exports acal.com.acal_left;
}

