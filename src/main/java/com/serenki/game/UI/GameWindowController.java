package com.serenki.game.UI;

import com.serenki.game.Game;
import com.serenki.game.towers.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;

public class GameWindowController {

    private Game game;


    @FXML
    private Canvas battlefieldCanvas;

    @FXML
    private Label healthDisplay;

    @FXML
    private Label moneyDisplay;

    public void updateDisplays() {
        moneyDisplay.setText(this.game.getPlayer().getMoney() + "$");
        healthDisplay.setText(this.game.getPlayer().getHealthPoints() + "♥");
    }

    public GraphicsContext getGraphicsContextOfCanvas() {
        return battlefieldCanvas.getGraphicsContext2D();
    }

    public void initializeManually(@NotNull final Game game) {
        this.game = game;
    }

    @FXML
    void pickUpBarrier(MouseEvent event) {
        game.getPlayer().setTowerToPlace(new Barrier());
    }

    @FXML
    void pickUpCannon(MouseEvent event) {
        game.getPlayer().setTowerToPlace(new Cannon(this.game.getEnemiesManager(), this.game.getProjectilesManager()));
    }

    @FXML
    void pickUpQuadrupleShooter(MouseEvent event) {
        game.getPlayer().setTowerToPlace(new QuadrupleShooter(this.game.getEnemiesManager(), this.game.getProjectilesManager()));
    }

    public void pickUpFlameThrower(MouseEvent mouseEvent) {
        game.getPlayer().setTowerToPlace(new Flamethrower(this.game.getEnemiesManager(), this.game.getProjectilesManager()));
    }

    public void pickUpSniper(MouseEvent mouseEvent) {
        game.getPlayer().setTowerToPlace(new Sniper(this.game.getEnemiesManager(), this.game.getProjectilesManager()));
    }
}
