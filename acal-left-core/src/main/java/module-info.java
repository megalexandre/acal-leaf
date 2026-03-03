module acal.left.core {
    requires acal.left.shared;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.core;
    requires spring.context;
    requires spring.beans;

    requires static lombok;
    requires kotlin.stdlib;

    exports acal.com.acal_left.core.model;
    exports acal.com.acal_left.core.repository;
    exports acal.com.acal_left.core.usecase;
    exports acal.com.acal_left.core.usecase.category;
    exports acal.com.acal_left.core.usecase.address;
    exports acal.com.acal_left.core.usecase.invoice;
    exports acal.com.acal_left.core.usecase.login;
}

