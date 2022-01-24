/*
 * Course: CS1021
 * Winter 2021-2022
 * Homework 6 - Login
 * Name: Ian Czerkis
 * Created: 23 Jan 2022
 */

package czerkisi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Program module that validates input when creating a new account for a system
 */
public class Validator extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Validator.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
