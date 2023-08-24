module com.example.jsoupdiploma {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires org.apache.commons.csv;


    opens com.example.jsoupdiploma to javafx.fxml;
    exports com.example.jsoupdiploma;
}