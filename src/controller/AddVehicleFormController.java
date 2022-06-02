package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Vehicle;
import util.closeWindow;

import java.io.IOException;

public class AddVehicleFormController implements closeWindow {
    public AnchorPane vehicleContext;
    public JFXTextField txtVehicleNumber;
    public JFXTextField txtWeights;
    public JFXTextField txtPassengers;
    public JFXComboBox cmbVehicleNumber;

    public void initialize(){

        loadType();
    }

    private void loadType() {

        cmbVehicleNumber.getItems().add("Bus");
        cmbVehicleNumber.getItems().add("Van");
        cmbVehicleNumber.getItems().add("Cargo Lorry");
    }


    public void addVehicleOnAction(ActionEvent actionEvent) {
        Database.vehicleTable.add
                (new Vehicle(String.valueOf(txtVehicleNumber.getText()), (String) cmbVehicleNumber.getValue(),Double.parseDouble(txtWeights.getText()),
                        Integer.parseInt(txtPassengers.getText())));

        clearFields();
    }

    private void clearFields() {
        txtVehicleNumber.clear();
        txtWeights.clear();
        txtPassengers.clear();
        cmbVehicleNumber.getSelectionModel().clearSelection();
    }

    @Override
    public void CloseWindowUi(AnchorPane x) throws IOException {
        Stage stage= (Stage) x.getScene().getWindow();
        stage.close();

    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) vehicleContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/inParkingTableForm.fxml"))));
    }
}
