package com.serenki;

import com.serenki.game.Game;
import com.serenki.game.UI.GameWindowController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationMain extends Application {

    private Game game;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMain.class.getResource("game-window.fxml"));

        Pane root = fxmlLoader.load();

        Scene scene = new Scene(root, 1920, 1080);

        GameWindowController gameWindowController = fxmlLoader.getController();

        stage.setTitle("Serenki's Tower Defense Game");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.getIcons().add(new Image("file:src/main/resources/com/serenki/art/towers/QuadrupleShooter.png"));
        stage.show();
        AnimationTimer gameloop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                game.update();
            }
        };



        game = new Game(gameWindowController, gameloop);            //TODO Refactor at some point so Game just extends AnimationTimer

        gameloop.start();

    }

    public static void main(String[] args) {
        launch();
    }
}

/*
https://youtu.be/Pkjdl5X0ylc?t=107
 */
