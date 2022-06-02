package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.LoadUI;
import util.closeWindow;

import java.io.IOException;
import java.util.Optional;

import static db.Database.belowUp;

public class ManagementLoginFormController implements LoadUI, closeWindow {
    public JFXTextField txtUserName;
    public JFXPasswordField pwdPassword;
    public AnchorPane loginContext;

    int attempts=0;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        CloseWindowUi(loginContext);
        attempts++;
        if (attempts <= 3){
            if(txtUserName.getText().equals("Management") && pwdPassword.getText().equals("1234")){
                loadUit("inParkingTableForm");
            }else {
                new Alert(Alert.AlertType.WARNING,"Try Again").showAndWait();
            }

        }else {
            txtUserName.setEditable(false);
            pwdPassword.setEditable(false);
        }

    }

    private void loadUitwo(String inParking) throws IOException {
        Stage stage = (Stage) belowUp.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/inParkingTableForm.fxml"))));
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure...?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            Stage stage = (Stage) loginContext.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/parkingSystemForm.fxml"))));
        }
    }

    @Override
    public void loadUi(String location, String title) throws IOException {
        Stage stage = (Stage) belowUp.getScene().getWindow();
        stage.close();
    }

    @Override
    public void CloseWindowUi(AnchorPane x) throws IOException {
        Stage stage = (Stage) x.getScene().getWindow();
        stage.close();
    }
}
