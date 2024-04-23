package com.example.csit228_f1_v2;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.csit228_f1_v2.HelloApplication.*;

public class HomeController {

    public ToggleButton tbNight;

    public Label lblName;

    public Label lblNew;

    public Label lblConfirm;
    public TextField tfName;
    public PasswordField pfNew;
    public PasswordField pfConfirm;
    public Label lblGreet;
    public Label lblGrtName;
    private String loggedInUsername;


    public void onNightModeClick() {
        if (tbNight.isSelected()) {
            // Set background color to black and change text to "DAY"
            Pane parentPane = (Pane) tbNight.getParent();
            parentPane.setStyle("-fx-background-color: BLACK");
            tbNight.setText("DAY");

            // Set text color to white for labels
            setLabelsTextColor(Color.WHITE);
        } else {
            // Set background color to white and change text to "NIGHT"
            Pane parentPane = (Pane) tbNight.getParent();
            parentPane.setStyle("-fx-background-color: WHITE");
            tbNight.setText("NIGHT");

            // Set text color to black for labels
            setLabelsTextColor(Color.BLACK);
        }
    }

    private void setLabelsTextColor(Color color) {
        lblName.setTextFill(color);
        lblNew.setTextFill(color);
        lblConfirm.setTextFill(color);
        lblGrtName.setTextFill(color);
        lblGreet.setTextFill(color);
    }

    public void onSaveClick(ActionEvent actionEvent) {
        // Call the method to update user information


    }

    public void onDeleteClick(ActionEvent actionEvent) {
        // You can implement deletion functionality here
        // For example, prompt the user for confirmation before deleting
    }

    private void updateUserAndPassword(String username) {
        // Implement your logic to update user information here
        String newName = tfName.getText(); // Assuming tfName holds the new name
        String newPassword = pfNew.getText();
        String confirmNewPassword = pfConfirm.getText();

        // Perform validation checks before updating
        if (newPassword.equals(confirmNewPassword)) {
            // Passwords match, proceed with update
            try {
                // Establish connection to the database and update user information
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                String sql = "UPDATE account SET password = ?, name = ? WHERE username = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, newPassword);
                statement.setString(2, newName);
                statement.setString(3, username);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    // Update successful
                    System.out.println("User information updated successfully.");
                } else {
                    // No rows were updated, handle accordingly
                    System.out.println("Failed to update user information.");
                }
                // Close resources
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Passwords don't match, notify the user
            System.out.println("Passwords do not match. Please re-enter.");
        }
    }
    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
    }


}

