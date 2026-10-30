package com.uji;

import com.uji.view.MainView;
import com.uji.controller.MainController;
import com.uji.model.models.SongModel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage){
        SongModel songModel = new SongModel();

        MainView mainView = new MainView();

        MainController mainController = new MainController(mainView, songModel);

        BorderPane rootLayout = mainView.getRootLayout();

        Scene scene = new Scene(rootLayout, 1200, 800);

        primaryStage.setTitle("Recomendador de Canciones");
        primaryStage.setMinWidth(950);
        primaryStage.setMinHeight(720);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
