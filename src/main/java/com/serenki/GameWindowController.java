package com.serenki;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameWindowController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}