module com.fsb.networked {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	requires javafx.graphics;
	
	opens com.fsb.networked to javafx.fxml;
    exports com.fsb.networked;
}
