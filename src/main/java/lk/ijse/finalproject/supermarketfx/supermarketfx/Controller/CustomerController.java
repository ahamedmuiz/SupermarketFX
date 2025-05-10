package lk.ijse.finalproject.supermarketfx.supermarketfx.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.finalproject.supermarketfx.supermarketfx.Model.CustomerModel;
import lk.ijse.finalproject.supermarketfx.supermarketfx.dto.CustomerDTO;
import lk.ijse.finalproject.supermarketfx.supermarketfx.tm.CustomerTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CustomerController implements Initializable {

    private final CustomerModel customerModel = new CustomerModel();

    @FXML
    private TextField txtCusEmail;

    @FXML
    private TextField txtCusId;

    @FXML
    private TextField txtCusName;

    @FXML
    private TextField txtCusNic;

    @FXML
    private TextField txtCusPhone;

    @FXML
    private TableView<CustomerTM> fullTable;

    @FXML
    private TableColumn<CustomerTM, String> tblCusId;

    @FXML
    private TableColumn<CustomerTM, String> tblCusName;

    @FXML
    private TableColumn<CustomerTM, String> tblCusNic;

    @FXML
    private TableColumn<CustomerTM, String> tblCusEmail;

    @FXML
    private TableColumn<CustomerTM, String> tblCusPhone;

    @FXML
    void btnCustomerSaveOnAction(ActionEvent event) {
        String customerId = txtCusId.getText();
        String customerName = txtCusName.getText();
        String customerNic = txtCusNic.getText();
        String customerEmail = txtCusEmail.getText();
        String customerPhone = txtCusPhone.getText();

//        1. Using Pattern Object java.util.regex
//        String namePattern = "^[A-Za-z ]+$";
//        Pattern compiledNamePattern = Pattern.compile(namePattern);
//        boolean isValueName = compiledNamePattern.matcher(customerName).matches();
//        System.out.println(customerName + " is valid:(1) " + isValueName);


//        2. Using String Class matches() method

        String namePattern = "^[A-Za-z ]+$";
        boolean matches = customerName.matches(namePattern);
        System.out.println(customerName + " matches " + matches);




        CustomerDTO dto = new CustomerDTO(customerId, customerName, customerNic, customerEmail, customerPhone);

        try {
            boolean isSave = customerModel.saveCustomer(dto);
            if (isSave) {
                new Alert(Alert.AlertType.INFORMATION, "Customer saved successfully").show();
                clearFields();
                loadNextId();
                loadTableData(); // Refresh the table after save
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save customer").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving customer").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblCusId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tblCusName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblCusNic.setCellValueFactory(new PropertyValueFactory<>("Nic"));
        tblCusEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        tblCusPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));

        try {
            loadNextId();
            loadTableData();
        } catch (Exception e) {
            throw new RuntimeException("Initialization error: " + e.getMessage(), e);
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = customerModel.getNextId();
        txtCusId.setText(nextId);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {

        //        1. Long code

//        try {
//            ArrayList<CustomerDTO> customerDTOArrayList = customerModel.getAllCustomer();
//            ObservableList<CustomerTM> list = FXCollections.observableArrayList();
//
//            for (CustomerDTO customerDTO : customerDTOArrayList) {
//                CustomerTM customerTM = new CustomerTM(
//                        customerDTO.getCustomerId(),
//                        customerDTO.getCustomerName(),
//                        customerDTO.getCustomerNic(),
//                        customerDTO.getCustomerEmail(),
//                        customerDTO.getCustomerPhone()
//                );
//                list.add(customerTM);
//            }
//
//            fullTable.setItems(list);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "Failed to load customer data").show();
//        }


//        2. Short code

fullTable.setItems(FXCollections.observableArrayList(
        customerModel.getAllCustomer().stream().map(customerDTO ->
                new CustomerTM(
                        customerDTO.getCustomerId(),
                        customerDTO.getCustomerName(),
                        customerDTO.getCustomerNic(),
                        customerDTO.getCustomerEmail(),
                        customerDTO.getCustomerPhone()
                )).toList()
));


    }

    private void clearFields() {
        txtCusName.clear();
        txtCusNic.clear();
        txtCusEmail.clear();
        txtCusPhone.clear();
    }
}
