module com.carstore.carrent {
    requires javafx.controls;
    requires javafx.fxml;
    requires ormlite.jdbc;
    requires java.sql;
    requires bcrypt;

    opens com.carstore.carrent.Model.Car to ormlite.jdbc, javafx.base;
    opens com.carstore.carrent.Model.User to ormlite.jdbc;
    opens com.carstore.carrent.Model.Client to ormlite.jdbc;
    exports com.carstore.carrent.Model.Car to ormlite.jdbc;
    exports com.carstore.carrent.Model.User to ormlite.jdbc;
    exports com.carstore.carrent.Model.Client to ormlite.jdbc;
    opens com.carstore.carrent to javafx.fxml;
    exports com.carstore.carrent;
    opens com.carstore.carrent.Components to javafx.fxml;
    exports com.carstore.carrent.Components;
}