module com.project.db {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;
    requires java.desktop;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.mail;
    //requires jasperreports.javaflow;
    requires jasperreports;

    requires com.oracle.database.jdbc;
    requires java.naming;



    //requires net.sf.jasperreports.engine;


    opens com.project.db to javafx.fxml;
    exports com.project.db;
}

