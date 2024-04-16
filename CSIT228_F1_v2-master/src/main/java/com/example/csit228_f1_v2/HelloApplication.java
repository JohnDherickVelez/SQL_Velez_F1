package com.example.csit228_f1_v2;

import com.example.csit228_f1_v2.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);

        Text txtWelcome = new Text("Welcome to CIT");
        txtWelcome.setFont(Font.font("Chiller", FontWeight.EXTRA_BOLD, 69));
        txtWelcome.setFill(Color.RED);
        txtWelcome.setTextAlignment(TextAlignment.CENTER);
        grid.add(txtWelcome, 0, 0, 3, 1);

        TextField tfUsername = new TextField();
        tfUsername.setFont(Font.font(30));
        grid.add(new Text("Username:"), 0, 1);
        grid.add(tfUsername, 1, 1);

        PasswordField pfPassword = new PasswordField();
        pfPassword.setFont(Font.font(30));
        grid.add(new Text("Password:"), 0, 2);
        grid.add(pfPassword, 1, 2);

        Button btnLogin = new Button("Log In");
        btnLogin.setFont(Font.font(40));
        grid.add(btnLogin, 0, 3, 2, 1);

        UserDAO userDAO = new UserDAO();

        btnLogin.setOnAction(event -> {
            String username = tfUsername.getText();
            String password = pfPassword.getText();

            if (username.isEmpty() || password.isEmpty()) {
                System.out.println("Please enter username and password.");
                return;
            }

            boolean authenticated = userDAO.authenticateUser(username, password);
            if (authenticated) {
                System.out.println("Login successful!");
                // Redirect to homepage or next screen
            } else {
                System.out.println("Login failed. Invalid username or password.");
            }
        });

        Scene scene = new Scene(grid, 700, 500, Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
