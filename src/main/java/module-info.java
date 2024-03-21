module com.fsb.networked {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	requires javafx.graphics;
    requires javafx.media;
    requires org.json;
    requires org.apache.pdfbox;
    requires org.apache.fontbox;

    opens com.fsb.networked to javafx.fxml;
    exports com.fsb.networked;
    exports com.fsb.networked.controllers;
    opens com.fsb.networked.controllers to javafx.fxml;
}
