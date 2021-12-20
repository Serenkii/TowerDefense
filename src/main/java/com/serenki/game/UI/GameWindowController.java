package com.serenki.game.UI;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameWindowController {

    @FXML
    private Canvas battlefieldCanvas;

    public GraphicsContext getGraphicsContextOfCanvas() {
        return battlefieldCanvas.getGraphicsContext2D();
    }

}
