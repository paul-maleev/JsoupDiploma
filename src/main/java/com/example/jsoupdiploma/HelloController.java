package com.example.jsoupdiploma;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @FXML
    private Button parseDataButton;

    @FXML
    private Button saveDataButton;


    @FXML
    private TableView<ObservableCountry> countriesTableView;

    ObservableList<ObservableCountry> countries = FXCollections.observableArrayList();

    @FXML
    protected void onSaveDataButtonClick() {
        System.out.println("onSaveDataButtonClick");
        //File dest = fileChooser.showSaveDialog();

        FileChooser fileChooser = new FileChooser();

        Stage stage = (Stage)saveDataButton.getScene().getWindow();

        FileChooser.ExtensionFilter fileExtensions =
                new FileChooser.ExtensionFilter(
                        "Text file", "*.txt");
        fileChooser.getExtensionFilters().add(fileExtensions);
        fileExtensions =
                new FileChooser.ExtensionFilter(
                        "CSV file", "*.csv");
        fileChooser.getExtensionFilters().add(fileExtensions);

        fileExtensions =
                new FileChooser.ExtensionFilter(
                        "Excel file", "*.xls");
        fileChooser.getExtensionFilters().add(fileExtensions);

        File selectedFile = fileChooser.showSaveDialog(stage);

        //System.out.println(selectedFile.getAbsolutePath());
        String extension = "";

        int i = (selectedFile!=null)? selectedFile.getName().lastIndexOf('.'):0;
        if (i > 0) {
            extension = selectedFile.getName().substring(i+1);
        }

        if(extension.equals("txt")) {
            saveAsTxtFile(selectedFile);
        }
        if(extension.equals("csv")) {
            saveAsCsvFile(selectedFile);
        }
        if(extension.equals("xls")) {
            saveAsExcelFile(selectedFile);
        }



    }


    @FXML
    protected void onparseDataButtonClick() {

        System.out.println("onparseDataButtonClick()");
        downloadData();
        initTable();

    }

    public void downloadData() {
        Document doc;
        try {
            doc = Jsoup.connect("https://www.scrapethissite.com/pages/simple/")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Elements listNews = doc.select("div.col-md-4.country");

        for (Element element : listNews) {
            ObservableCountry observableCountry = new ObservableCountry(new SimpleStringProperty(element.select("h3.country-name").text()),
                    new SimpleStringProperty(element.select("span.country-capital").text()),
                    new SimpleIntegerProperty(Integer.parseInt(element.select("span.country-population").text())),
                    new SimpleDoubleProperty(Double.parseDouble(element.select("span.country-area").text())));
            countries.add(observableCountry);

        }

        /*

        for(int i=0;i<countries.size();i++) {
            ObservableCountry c = countries.get(i);
            System.out.println(c.toString());
        }

         */
    }

    public void initTable() {
        countriesTableView.getColumns().clear();
        TableColumn<ObservableCountry, String> columnName = new TableColumn<>("Name");
        columnName.setCellValueFactory(new PropertyValueFactory<ObservableCountry, String>("name"));
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<ObservableCountry, String> columnCapital = new TableColumn<>("Capital");
        columnCapital.setCellValueFactory(new PropertyValueFactory<>("capital"));
        columnCapital.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<ObservableCountry, Integer> columnPopulation = new TableColumn<>("Population");
        columnPopulation.setCellValueFactory(new PropertyValueFactory<>("population"));
        //columnPopulation.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<ObservableCountry, Double> columnArea = new TableColumn<>("Area");
        columnArea.setCellValueFactory(new PropertyValueFactory<>("area"));
        //columnArea.setCellFactory(TextFieldTableCell.forTableColumn());

        countriesTableView.getColumns().add(columnName);
        countriesTableView.getColumns().add(columnCapital);
        countriesTableView.getColumns().add(columnPopulation);
        countriesTableView.getColumns().add(columnArea);
        countriesTableView.setItems(countries);
    }

    private void saveAsTxtFile(File file) {
        System.out.println(file.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<countries.size();i++) {
            ObservableCountry c = countries.get(i);
            sb.append(c.getName()+" "+c.getCapital()+" "+c.getPopulation()+" "+c.getArea()+"\n");
        }

        //System.out.println(sb.toString());
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveAsCsvFile(File file) {
        System.out.println("saveAsCsvFile!");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL
                    .withHeader("Name", "Capital", "Population", "Area"));
            for(int i=0;i<countries.size();i++) {
                ObservableCountry c = countries.get(i);
                csvPrinter.printRecord(c.getName(), c.getCapital(), c.getPopulation(), c.getArea());
            }
            csvPrinter.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void saveAsExcelFile(File file) {
        System.out.println("saveAsExcelFile!");
    }



}