module com.example.jsoupdiploma {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires org.apache.commons.csv;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;


    opens com.example.jsoupdiploma to javafx.fxml;
    exports com.example.jsoupdiploma;
}