package ru.bstu.it191.portnova.lab5;

import javafx.collections.FXCollections;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HelloController {
    public Label idLabel;
    public TextField modelTextField;
    public TextField colorTextField;
    public TextField numberTextField;
    public TextField nameTextField;
    public TextField surnameTextField;
    public Button addButton;
    public Button redactButton;
    public Button deleteButton;
    public ListView<Car> listView;
    public Button modeButton;
    public Button filterButton;
    public Button convertButton;
    XmlFile file = new XmlFile();
    String mode = "xml";

    public void initialize() {
        try {
            setDisableButtons(true);

            listView.setItems(FXCollections.observableList(file.getCars()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    public void changeModeClick() {
        if(Objects.equals(mode, "xml")){
        }
        else {
            listView.setItems(FXCollections.observableList(file.getCars()));
            mode = "xml";

            modeButton.setText("Xml mode");
            convertButton.setText("Write all to the database");
            setDisableButtons(true);
        }
    }
    public void setDisableButtons(boolean is) {
        deleteButton.setDisable(is);
        redactButton.setDisable(is);
    }
    public void onCarClick() {
        try {
            Car car = listView.getSelectionModel().getSelectedItem();

            idLabel.setText("Car id:"+car.getId());
            modelTextField.setText(car.getModel());
            colorTextField.setText(car.getColor());
            numberTextField.setText(String.valueOf(car.getNumber()));
            nameTextField.setText(car.getName());
            surnameTextField.setText(car.getSurname());
            setDisableButtons(false);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    public void onAddButtonClick() {
        Car car = new Car(modelTextField.getText().trim(),
                colorTextField.getText().trim(),
                Integer.parseInt(numberTextField.getText().trim()),
                nameTextField.getText().trim(),
                surnameTextField.getText().trim());

        if(Objects.equals(mode, "xml")){
            file.addCar(car);
            listView.setItems(FXCollections.observableList(file.getCars()));
        }
        else {

        }
    }
    public void redactButtonClick() {
        if(!listView.getSelectionModel().isEmpty()){
            Car car = new Car(listView.getSelectionModel().getSelectedItem().id,
                    modelTextField.getText().trim(),
                    colorTextField.getText().trim(),
                    Integer.parseInt(numberTextField.getText().trim()),
                    nameTextField.getText().trim(),
                    surnameTextField.getText().trim());

            if(Objects.equals(mode, "xml")){
                file.redactCar(car);
                listView.setItems(FXCollections.observableList(file.getCars()));
            }
            else {
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Enter a car");
            alert.showAndWait();
            setDisableButtons(true);
        }

    }
    public void deleteButtonClick() {
        if(!listView.getSelectionModel().isEmpty()) {
            Car car = listView.getSelectionModel().getSelectedItem();
            if(Objects.equals(mode, "xml")){
                file.deleteCar(car);
                listView.setItems(FXCollections.observableList(file.getCars()));
            }
            else {
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Enter a country");
            alert.showAndWait();
            setDisableButtons(true);
        }
    }

    public void filterButtonClick() {
    }

    public void ConvertButtonClick() {
    }

}