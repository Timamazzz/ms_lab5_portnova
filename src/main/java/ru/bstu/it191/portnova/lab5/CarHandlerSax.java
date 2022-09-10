package ru.bstu.it191.portnova.lab5;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

public class CarHandlerSax extends DefaultHandler{
    List<Car> result;
    Car currentCar;
    private final StringBuilder currentValue = new StringBuilder();

    @Override
    public void startDocument() {
        result = new ArrayList<>();
    }

    public List<Car> getResult() {
        return result;
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) {

        currentValue.setLength(0);
        if (qName.equalsIgnoreCase("Car")) {
            currentCar = new Car();
            String id = attributes.getValue("id");
            currentCar.setId(Integer.parseInt(id));
        }
    }

    public void endElement(String uri,
                           String localName,
                           String qName) {
        switch (qName){
            case "model":
                currentCar.setModel(currentValue.toString());
                break;
            case "color":
                currentCar.setColor(currentValue.toString());
                break;
            case "number":
                currentCar.setNumber(Integer.parseInt(currentValue.toString()));
                break;
            case "name":
                currentCar.setName(currentValue.toString());
                break;
            case "surname":
                currentCar.setSurname(currentValue.toString());
                break;
            case "Car":
                result.add(currentCar);
                break;
        }
    }

    public void characters(char[] ch, int start, int length) {
        currentValue.append(ch, start, length);
    }
}
