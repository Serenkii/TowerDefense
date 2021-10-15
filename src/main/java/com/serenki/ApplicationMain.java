package com.serenki;

import com.serenki.game.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class ApplicationMain extends Application {

    private Game game;

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 1920, 1080);
        stage.setScene(scene);

        Canvas canvas = new Canvas(1080, 1080);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        root.setLeft(canvas);

        game = new Game(graphicsContext);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();

        game.update();
    }

    public static void main(String[] args) {
        launch();
    }
}

/*
https://youtu.be/Pkjdl5X0ylc?t=107
 */


/*
 * 2021 10 15 18:55         To be honest, I think I don't know enough about javafx etc to be using fxml, so fuck it
 FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("game-window.fxml"));
 Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
 stage.setTitle("Hello!");
 stage.setScene(scene);
 stage.setFullScreen(true);
 stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);     //disable escape to leave fullscreen
 //https://stackoverflow.com/questions/16713554/how-to-change-scene-when-in-fullscreen-in-javafx-and-avoid-press-esc-to-exit-fu
 stage.show();
 */