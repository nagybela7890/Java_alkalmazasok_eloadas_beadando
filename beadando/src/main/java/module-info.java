module beadando {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.naming;
    requires java.sql;
    requires org.hibernate.orm.core;
    opens beadando to javafx.fxml;
    exports beadando;
}
