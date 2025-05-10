module lk.ijse.finalproject.supermarketfx.supermarketfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;


    opens lk.ijse.finalproject.supermarketfx.supermarketfx.Controller to javafx.fxml;
    opens lk.ijse.finalproject.supermarketfx.supermarketfx.tm to javafx.base;
    exports lk.ijse.finalproject.supermarketfx.supermarketfx;
}