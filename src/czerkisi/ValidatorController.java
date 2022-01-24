/*
 * Course: CS1021
 * Winter 2021-2022
 * Homework 6 - Login
 * Name: Ian Czerkis
 * Created: 23 Jan 2022
 */

package czerkisi;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Create New Login scene
 */
public class ValidatorController implements Initializable {
    private Database database;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button addButton;

    /**
     * Initializes variables before displaying scene
     *
     * Instantiates a new Database object and assigns listeners to the text property
     * of the email and password TextFields
     *
     * @param location unused
     * @param resources unused
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        database = new Database();
        emailField.textProperty().addListener((obs, oldText, newText) -> update());
        passwordField.textProperty().addListener((obs, oldText, newText) -> update());
    }

    /**
     * Handler for the Add Button, attempts to add the contents of the form to the database
     */
    @FXML
    private void addLogin() {
        Entry e = new Entry(emailField.getText(), passwordField.getText());
        try {
            database.add(e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Your account has been created");
            alert.setContentText("Thanks for creating an account");
            alert.showAndWait();
        } catch (Database.MalformedEmailException exception){
            error("Invalid Email Entry", "Your email " +
                    "does not match the correct email format");
        } catch (Database.WeakPasswordException exception){
            error("Weak Password", "Your password must be at least 8 characters long, " +
                    "contain 1 upper and 1 lower case letter, contain 1 digit and " +
                    "1 special character");
        } catch (Database.DuplicateEntryException exception){
            error("Duplicate Entry", "This email has already been entered");
        }
    }

    private void error(String header, String context){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.showAndWait();
    }

    /**
     * Handler for the Cancel Button. Clears all the TextFields and disables the Add Button
     */
    @FXML
    private void cancel() {
        emailField.clear();
        passwordField.clear();
        update();
    }

    /**
     * Handler for the email and password fields. If both fields are not empty, the Add Button is
     * enabled. If either or both fields are empty, the Add Button is disabled
     */
    @FXML
    private void update() {
        addButton.setDisable(passwordField.getText().isEmpty() || emailField.getText().isEmpty());
    }
}
