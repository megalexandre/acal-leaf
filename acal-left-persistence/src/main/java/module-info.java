module acal.left.persistence {
    requires acal.left.shared;
    requires acal.left.core;
    requires jakarta.persistence;
    requires spring.context;
    requires spring.data.jpa;
    requires spring.data.commons;

    exports acal.com.acal_left.resouces.repository.repository.impl;
    exports acal.com.acal_left.resouces.repository.repository.jpa;
}

