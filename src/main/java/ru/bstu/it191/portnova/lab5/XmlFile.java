package ru.bstu.it191.portnova.lab5;

import javafx.scene.control.Alert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class XmlFile {
    Properties properties = new Properties();
    protected List<Car> cars;
    XmlFile(){
        try {
            properties.load(new FileInputStream("config.properties"));
            read();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    public void read(){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            CarHandlerSax handler = new CarHandlerSax();
            saxParser.parse(properties.getProperty("pathToXml"), handler);
            cars = handler.getResult();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    public void addCar(Car car) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.parse(properties.getProperty("pathToXml"));

            Element rootElement = document.getDocumentElement();
            int lastId = Integer.parseInt(rootElement.getAttribute("lastId"));
            lastId++;

            Element carElement = document.createElement("Car");
            carElement.setAttribute("id", String.valueOf(lastId));

            Element modelElement = document.createElement("model");
            modelElement.setTextContent(car.getModel());
            carElement.appendChild(modelElement);

            Element colorElement = document.createElement("color");
            colorElement.setTextContent(car.getColor());
            carElement.appendChild(colorElement);

            Element numberElement = document.createElement("number");
            numberElement.setTextContent(String.valueOf(car.getNumber()));
            carElement.appendChild(numberElement);

            Element nameElement = document.createElement("name");
            nameElement.setTextContent(car.getName());
            carElement.appendChild(nameElement);

            Element surnameElement = document.createElement("surname");
            surnameElement.setTextContent(car.getSurname());
            carElement.appendChild(surnameElement);

            rootElement.appendChild(carElement);
            rootElement.setAttribute("lastId", String.valueOf(lastId));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(properties.getProperty("pathToXml")));

            read();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    public Element findCar(Document document, int id) {
        NodeList cars = document.getElementsByTagName("Car");
        Element currentCar = null;
        int i = 0;

        while(i < cars.getLength() && currentCar == null){
            Node node = cars.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                if(String.valueOf(id).equals(element.getAttribute("id"))){
                    currentCar = element;
                }
            }
            i++;
        }
        return currentCar;
    }
    public void redactCar(Car car) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.parse(properties.getProperty("pathToXml"));

            Element currentCar = findCar(document, car.getId());

            if(currentCar != null){
                NodeList children = currentCar.getChildNodes();

                for (int j=0; j < children.getLength(); j++){
                    Node node = children.item(j);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        switch (element.getTagName()){
                            case "model":
                                currentCar.setTextContent(car.getModel());
                                break;
                            case "color":
                                currentCar.setTextContent(car.getColor());
                                break;
                            case "number":
                                currentCar.setTextContent(String.valueOf(car.getNumber()));
                                break;
                            case "name":
                                currentCar.setTextContent(car.getName());
                                break;
                            case "surname":
                                currentCar.setTextContent(car.getSurname());
                                break;
                        }
                    }
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(new DOMSource(document), new StreamResult(properties.getProperty("pathToXml")));

                read();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


    }
    public void deleteCar(Car car) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.parse(properties.getProperty("pathToXml"));
            Element rootElement = document.getDocumentElement();

            Element currentCar = findCar(document, car.getId());

            if(currentCar != null){
                rootElement.removeChild(currentCar);
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(new DOMSource(document), new StreamResult(properties.getProperty("pathToXml")));

                read();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Not found car");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    public boolean isExists(Car car){
        boolean isExists = false;
        int count = 0;
        while(count< cars.size() && !isExists){
            if(cars.get(count).getId() == car.getId()) isExists = true;
            count++;
        }
        return isExists;
    }
    public List<Car> getCars(){
        return cars;
    }
}
