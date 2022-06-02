package controller;
import db.*;
import javafx.util.Duration;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Dilivery;
import model.Driver;
import model.Vehicle;
import model.parking;
import util.LoadUI;
import util.vehiclePark;
import view.tm.DriverTM;
import view.tm.VehicleTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

public class ParkingSystemFormController extends parking implements vehiclePark, LoadUI {
    public AnchorPane parkingSystemContext;
    public JFXComboBox cmbVehicleNumber;
    public JFXTextField txtVehicleType;
    public JFXComboBox cmbDriver;
    public JFXButton btnParkVehicle;
    public JFXButton btnOnDeliveryShift;
    public Label lblSlotNumber;
    public Label setTimeDatelbl;
    public JFXButton btnManagement;
    private SingleSelectionModel<VehicleTM> colVehicleNumber;
    Vehicle selectedVehicle;

    boolean b=false;
    boolean delivery=false;
    
    public void initialize(){
        try {

            lblSlotNumber.setText("15");
            loadHomeDate();
            DateTime();

            cmbVehicleNumber.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                VehicleType(newValue);


            });

        }catch (Exception e){

        }
    }

    private void parkingSlot() {
        switch (txtVehicleType.getText()){
            case "Van" : {
                setslot("Van");
            }break;
            case "Cargo Lorry" : {
                setslot("Cargo Lorry");
            }break;
            case "Bus" : {
                setslot("Bus");
            }break;
        }

    }
    private void setslot(String vehicletype) {
        for (int i=0; i<Database.slotTable.size(); i++){
            for (int j=0; j<Database.slotTable.size(); j++){
                if (vehicletype.equals(Database.slotTable.get(i).getVehicleType()) && Database.slotTable.get(i).getStatus().equals("notUse")) {
                    lblSlotNumber.setText(Database.slotTable.get(i).getSlot());
                    return;
                }
            }
        }
        clearslotlbl();
    }

    private void clearslotlbl() {
        for (int i=0; i<Database.parkingTable.size(); i++){
            if (Database.parkingTable.get(i).getVehicleNumber().equals(cmbVehicleNumber.getValue())){
                lblSlotNumber .setText(" ");
            }
        }
    }

    private void setnotuse(String slotnmbr) {
        for (int i=0; i<Database.slotTable.size(); i++){
            if (Database.slotTable.get(i).getSlot().equals(slotnmbr)){
                Database.slotTable.get(i).setStatus("notUse");
            }
        }
    }

    private void VehicleType(Object newValue) {
        String a= String.valueOf(newValue);
        for (int i = 0; i< Database.vehicleTable.size(); i++){
            String number=Database.vehicleTable.get(i).getVehicleNo();
            if (a.equals(number)){

                txtVehicleType.setText(Database.vehicleTable.get(i).getVehicleType());
                String type=txtVehicleType.getText();
            }
        }
        parkingSlot();
    }


    private void DateTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            LocalDate currentDate = LocalDate.now();
            setTimeDatelbl.setText(currentDate.getYear()+"-"+currentDate.getMonthValue()+"-"+currentDate.getDayOfMonth()+
                    "    "+ currentTime.getHour() + ":" + currentTime.getMinute() + ":"+ currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void loadHomeDate() {
        ObservableList<VehicleTM> observableList= FXCollections.observableArrayList();
        for (Vehicle v:Database.vehicleTable){
            VehicleTM tm =new VehicleTM(v.getVehicleNo());
            observableList.add(tm);
        }

        ObservableList<DriverTM>observableList1=FXCollections.observableArrayList();
        for (Driver d: Database.driverTable){
            DriverTM dr=new DriverTM(d.getName());
            observableList1.add(dr);
        }

        cmbVehicleNumber.setItems(observableList);
        cmbDriver.setItems(observableList1);
    }

    public void clearFields() {
        txtVehicleType.clear();
        cmbVehicleNumber.getSelectionModel().clearSelection();
        cmbDriver.getSelectionModel().clearSelection();
    }

    public void btnParksVehicleOnAction(ActionEvent actionEvent) throws IOException {
        for(int k=0;k<Database.slotTable.size();k++){
            if( lblSlotNumber.getText().equals(Database.slotTable.get(k).getSlot())){
                Database.slotTable.get(k).setStatus("Use");
            }
        }

        park();
    }

    private void park() {
        cmbVehicleNumber.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println();
            btnParkVehicle.setDisable(false);
            String temp=String.valueOf(newValue);
            for (int i = 0; i< Database.parkingTable.size(); i++){
                b= Database.parkingTable.get(i).getVehicleNumber().contains(temp);
                if(b==true){
                    btnParkVehicle.setDisable(true);
                    //  btnPark.setDisable(b);
                }
            }
        });
        if(b==false){
            if(cmbVehicleNumber.getValue()!=null){
                String vehicleNumber= (String.valueOf(cmbVehicleNumber.getValue())) ;
                parking p=new parking(String.valueOf(cmbVehicleNumber.getValue()),txtVehicleType.getText(),Integer.parseInt(lblSlotNumber.getText()),setTimeDatelbl.getText());
                Database.parkingTable.add(p);

                for(int i = 0; i< Database.diliveryTable.size(); i++){
                    if(Database.diliveryTable.get(i).getVehicleNo().contains(vehicleNumber)){
                        Database.diliveryTable.remove(i);
                    }
                }
            }
        }
        clearFields();
    }

    public void btnOnDeliveryOnAction(ActionEvent actionEvent) {

       try{
            delivery();
            for(int k=0;k<Database.parkingTable.size();k++){
                if(cmbVehicleNumber.getValue().equals(Database.parkingTable.get(k).getVehicleNumber())){
                   // System.out.println(String.valueOf(Database.parkingTable.get(k).getSlot()));
                    setnotuse(String.valueOf(Database.parkingTable.get(k).getSlot()));
                }
            }

       }catch(Throwable e){
          // System.out.println(e);
        }

    }

    private void delivery() {
        cmbVehicleNumber.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String temp2=String.valueOf(newValue);
            btnOnDeliveryShift.setDisable(false);

            for (int i=0;i<Database.diliveryTable.size();i++){
                delivery= Database.diliveryTable.get(i).getVehicleNo().contains(temp2);

                if(delivery==false){
                    btnOnDeliveryShift.setDisable(true);
                    // btnPark.setVisible(false);
                }
            }
        });

        if(delivery==false){

            if(cmbVehicleNumber.getValue()!=null && cmbDriver.getValue()!=null){
                String data= (String.valueOf(cmbVehicleNumber.getValue())) ;
                for(int i=0;i<Database.parkingTable.size();i++){
                    if(Database.parkingTable.get(i).getVehicleNumber().contains(data)){
                        Database.parkingTable.remove(i);
                    }
                }
                Dilivery d=new Dilivery(String.valueOf(cmbVehicleNumber.getValue()),txtVehicleType.getText(),String.valueOf(cmbDriver.getValue()),setTimeDatelbl.getText());
                Database.diliveryTable.add(d);
            }
        }
        clearFields();
    }


    public void btnManagementLoginOnAction(ActionEvent actionEvent) throws IOException {
        Database.belowUp = parkingSystemContext;

        loadUi("managementLoginForm", "Log in");
    }

    @Override
    public void loadUi(String location, String title) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public void CloseWindowUi(AnchorPane x) throws IOException {
        Stage stage = (Stage) x.getScene().getWindow();
        stage.close();
    }
}
