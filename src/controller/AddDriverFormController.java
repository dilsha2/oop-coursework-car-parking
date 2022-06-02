package controller;

import com.jfoenix.controls.JFXTextField;
import db.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Driver;
import util.closeWindow;

import java.io.IOException;
import java.net.URL;

public class AddDriverFormController implements closeWindow {
    public AnchorPane driverContext;
    public JFXTextField txtDriverName;
    public JFXTextField txtNic;
    public JFXTextField txtDriverLicense;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;

    public void CancelAddDriver(ActionEvent actionEvent) throws IOException {

        CloseWindowUi(driverContext);

        URL resource = getClass().getResource("../view/ManagementForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1= new Stage();
        stage1.setScene(scene);
        stage1.centerOnScreen();
        stage1.show();
    }

    private void ClearFields() {
        txtDriverName.clear();
        txtNic.clear();
        txtDriverLicense.clear();
        txtAddress.clear();
        txtContact.clear();
    }

    public void addDriverOnAction(ActionEvent actionEvent) {
        Database.driverTable.add(new Driver(txtDriverName.getText(),txtNic.getText(),
                txtDriverLicense.getText(),txtAddress.getText(),txtContact.getText()));
        ClearFields();
    }

    @Override
    public void CloseWindowUi(AnchorPane x) throws IOException {
        Stage stage= (Stage) x.getScene().getWindow();
        stage.close();

    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) driverContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/onDeliveryTableForm.fxml"))));
    }
}
