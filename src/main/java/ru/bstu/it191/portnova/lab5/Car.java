package ru.bstu.it191.portnova.lab5;

import java.util.jar.Attributes;

public class Car {
    int id;
    protected String model;
    protected String color;
    protected int number;
    protected String name;
    protected String surname;

    public Car(){

    };
    public Car(int Id, String Model, String Color, int Number, String Name, String Surname){
        this.id = Id;
        this.model = Model;
        this.color = Color;
        this.number = Number;
        this.name = Name;
        this.surname = Surname;
    }
    public Car(String Model, String Color, int Number, String Name, String Surname){
        this.model = Model;
        this.color = Color;
        this.number = Number;
        this.name = Name;
        this.surname = Surname;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public int getId() {
        return id;
    }
    public String getModel() {
        return model;
    }
    public String getColor() {
        return color;
    }
    public int getNumber() {
        return number;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    @Override
    public String toString(){
        return "Id: " + getId() + "\n" +
                "model: " + getModel() + "\n" +
                "color: " + getColor() + "\n" +
                "number: " + getNumber() + "\n" +
                "owner name: " + getName() + "\n" +
                "owner surname: " + getSurname() + "\n";
    }
}

