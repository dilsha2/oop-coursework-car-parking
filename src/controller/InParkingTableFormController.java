package controller;

import com.jfoenix.controls.JFXComboBox;
import db.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.parking;
import util.LoadUI;
import util.closeWindow;
import view.tm.parkTM;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class InParkingTableFormController  implements  LoadUI,closeWindow {
    public TableView<parkTM> tblInParking;
    public AnchorPane inParkContext;
    public JFXComboBox cmbInPark;
    public TableColumn colVehicleNumber;
    public TableColumn colParkingSlot;
    public TableColumn colVehicleType;
    public TableColumn colParkingTime;


    public void initialize() throws IOException {

        colVehicleNumber.setCellValueFactory(new PropertyValueFactory("VehicleNumber"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory("VehicleType"));
        colParkingSlot.setCellValueFactory(new PropertyValueFactory("Slot"));
        colParkingTime.setCellValueFactory(new PropertyValueFactory("ParkedTime"));

        cmbInPark.getItems().add("In Parking");
        cmbInPark.getItems().add("On Delivery");

        cmbInPark.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String text = String.valueOf(newValue);
            if (text.equals("On Delivery")) {

                Stage stage1 = (Stage) inParkContext.getScene().getWindow();
                try {
                    stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/onDeliveryTableForm.fxml"))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        loadAllData();
    }
    private void loadAllData () {
        ObservableList obList = FXCollections.observableArrayList();
        for (parking p: Database.parkingTable) {
            parkTM tm= new parkTM(p.getVehicleNumber(),p.getVehicleType(), p.getSlot(),p.getParkedTime());
            obList.add(tm);
        }
        tblInParking.setItems(obList);
    }

    public void addVehicleOnAction (ActionEvent actionEvent) throws IOException {
        CloseWindowUi(inParkContext);

        loadUi("addVehicleForm","ADD VEHICLE");
    }

    public void addDriverOnAction (ActionEvent actionEvent) throws IOException {
        CloseWindowUi(inParkContext);

        loadUi("AddDriverForm","ADD DRIVER");
    }

    public void logingOutOnAction (ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure You want Logout?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            Stage stage = (Stage) inParkContext.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/parkingSystemForm.fxml"))));
        } else {
        }
    }

    @Override
    public void loadUi (String location,String title) throws IOException {
        URL resource = getClass().getResource("../view/" + location + ".fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1 = new Stage();
        stage1.setTitle(title);
        stage1.setScene(scene);
        stage1.centerOnScreen();
        stage1.show();
    }


    @Override
    public void CloseWindowUi (AnchorPane x) throws IOException {
        Stage stage= (Stage)x.getScene().getWindow();
        stage.close();
    }

}
