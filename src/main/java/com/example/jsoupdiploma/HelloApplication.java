package com.example.jsoupdiploma;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static HelloApplication applicationInstance;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("https://www.scrapethissite.com/pages/simple/");
        stage.setScene(scene);
        stage.show();

    }

    public static HelloApplication getApplicationInstance() {
        return applicationInstance;
    }

    @Override
    public void init() {
        applicationInstance = this;
    }

    public static void main(String[] args) {
        launch();
    }
}