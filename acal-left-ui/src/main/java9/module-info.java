module acal.left.ui {
    // Internal module dependencies
    requires acal.left.core;
    requires acal.left.shared;

    // Java Desktop modules
    requires java.desktop;
    requires java.sql;

    // Spring Context
    requires spring.context;
    requires spring.beans;
    requires spring.core;

    // Lombok and Kotlin
    requires static lombok;
    requires kotlin.stdlib;
    requires org.jspecify;
    requires swingx.all;
    requires spring.boot;
    requires miglayout-swing;

    // Export UI packages
    exports acal.com.acal_left.ui;
    exports acal.com.acal_left.ui.boostrap;
    exports acal.com.acal_left.ui.converter;
    exports acal.com.acal_left.ui.event;
    exports acal.com.acal_left.ui.filter;
    exports acal.com.acal_left.ui.model;
    exports acal.com.acal_left.ui.report;
    exports acal.com.acal_left.ui.routes;
    exports acal.com.acal_left.ui.screen;

    // Open packages for Spring reflection
    opens acal.com.acal_left.ui to spring.core, spring.beans, spring.context;
    opens acal.com.acal_left.ui.boostrap to spring.core, spring.beans, spring.context;
    opens acal.com.acal_left.ui.screen to spring.core, spring.beans, spring.context;
}

