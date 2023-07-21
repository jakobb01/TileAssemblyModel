module com.jakobbeber.tam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.common;
    //requires mpi;

    opens com.jakobbeber.tam to javafx.fxml;
    exports com.jakobbeber.tam;
}