package lk.ijse.finalproject.supermarketfx.supermarketfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardController {



    @FXML
    private AnchorPane ancMainContainer;

    @FXML
    private AnchorPane ancPane;

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {

    navigateTo("/View/Customer.fxml");

    }

    @FXML
    void btnItemOnAction(ActionEvent event) {
        navigateTo("/View/Item.fxml");

    }

    @FXML

    void btnOrderOnAction(ActionEvent event) {
        navigateTo("/View/Order.fxml");
    }

    @FXML

    void btnTestOnAction(ActionEvent event) {
        navigateTo("/View/Test.fxml");
    }



    private void navigateTo(String path) {

        try {


            ancMainContainer.getChildren().clear();

            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancMainContainer.getChildren().add(parent);

        } catch (IOException e) {
            new Alert( Alert.AlertType.ERROR,"Page not found");
            e.printStackTrace();
        }

    }

}

