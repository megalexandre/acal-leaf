module acal.left.persistence {
    requires acal.left.shared;
    requires acal.left.core;
    requires static lombok;

    requires jakarta.persistence;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.data.jpa;
    requires spring.data.commons;
    requires spring.tx;

    exports acal.com.acal_left.resouces.repository.repository.impl;
    exports acal.com.acal_left.resouces.repository.repository.jpa;

    opens acal.com.acal_left.resouces.repository.model;
    opens acal.com.acal_left.resouces.repository.repository.impl;
    opens acal.com.acal_left.resouces.repository.repository.jpa;
}

