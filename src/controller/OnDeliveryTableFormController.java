package controller;

import com.jfoenix.controls.JFXComboBox;
import db.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Dilivery;
import util.LoadUI;
import util.closeWindow;
import view.tm.DeliverTM;

import java.io.IOException;
import java.net.URL;

public class OnDeliveryTableFormController implements LoadUI, closeWindow {
    public AnchorPane onDeliveryContext;
    public JFXComboBox cmbOnDelivery;
    public TableView <DeliverTM>tblOnDelivery;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colDriverName;
    public TableColumn colLeftTime;

    public void initialize() {

        colVehicleNumber.setCellValueFactory(new PropertyValueFactory("vehicleNo"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        colDriverName.setCellValueFactory(new PropertyValueFactory("driverName"));
        colLeftTime.setCellValueFactory(new PropertyValueFactory("leftTime"));


        cmbOnDelivery.getItems().add("In Parking");
        cmbOnDelivery.getItems().add("On Delivery");

        cmbOnDelivery.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String text = String.valueOf(newValue);
            if (text.equals("In Parking")) {

                Stage stage1 = (Stage) onDeliveryContext.getScene().getWindow();
                try {
                    stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/inParkingTableForm.fxml"))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        loadAllData();
    }

    private void loadAllData() {
        ObservableList obList = FXCollections.observableArrayList();
        for (Dilivery d: Database.diliveryTable) {


            DeliverTM tm= new DeliverTM(d.getVehicleNo(),d.getVehicleType(),d.getDriverName(),d.getLeftTime());
            obList.add(tm);

        }
        tblOnDelivery.setItems(obList);
    }


    public void addVehicleOnAction(ActionEvent actionEvent) throws IOException {
        CloseWindowUi(onDeliveryContext);
        loadUi("addVehicleForm","ADD VEHICLE");
    }

    public void addDriverOnAction(ActionEvent actionEvent) throws IOException {
        CloseWindowUi(onDeliveryContext);
        loadUi("AddDriverForm","ADD DRIVER");
    }

    @Override
    public void loadUi(String location,String title) throws IOException {
        URL resource = getClass().getResource("../view/"+location+".fxml");
        Parent load =FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1= new Stage();
        stage1.setScene(scene);
        stage1.setTitle(title);
        stage1.centerOnScreen();
        stage1.show();

    }

    @Override
    public void CloseWindowUi(AnchorPane x) throws IOException {
        Stage stage= (Stage)x.getScene().getWindow();
        stage.close();

    }
}
