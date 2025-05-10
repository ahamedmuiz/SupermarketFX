package lk.ijse.finalproject.supermarketfx.supermarketfx.Model;

import lk.ijse.finalproject.supermarketfx.supermarketfx.db.DBConnection;
import lk.ijse.finalproject.supermarketfx.supermarketfx.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {

    public String getNextId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String lastId = resultSet.getString("customer_id"); // safer than index-based
            String numericPart = lastId.substring(1); // Skip the 'C' prefix
            int idNumber = Integer.parseInt(numericPart);
            int nextIdNumber = idNumber + 1;
            return String.format("C%03d", nextIdNumber); // Format like C001, C002, ...
        } else {
            return "C001"; // First customer
        }
    }

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO customer VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, customerDTO.getCustomerId());
        preparedStatement.setString(2, customerDTO.getCustomerName());
        preparedStatement.setString(3, customerDTO.getCustomerNic());
        preparedStatement.setString(4, customerDTO.getCustomerEmail());
        preparedStatement.setString(5, customerDTO.getCustomerPhone());

        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected > 0;
    }

    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<CustomerDTO> customerList = new ArrayList<>();
        while (resultSet.next()) {
            CustomerDTO customer = new CustomerDTO(
                    resultSet.getString("customer_id"),
                    resultSet.getString("name"),
                    resultSet.getString("nic"),
                    resultSet.getString("email"),
                    resultSet.getString("phone")
            );
            customerList.add(customer);
        }
        return customerList;
    }
}
