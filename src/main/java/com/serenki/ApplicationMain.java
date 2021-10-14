package com.serenki;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("game-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);     //disable escape to leave fullscreen
                    //https://stackoverflow.com/questions/16713554/how-to-change-scene-when-in-fullscreen-in-javafx-and-avoid-press-esc-to-exit-fu
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}