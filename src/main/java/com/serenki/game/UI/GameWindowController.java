package com.serenki.game.UI;

import com.serenki.game.Game;
import com.serenki.game.towers.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public class GameWindowController {

    private Game game;


    @FXML
    private Canvas battlefieldCanvas;

    @FXML
    private Label healthDisplay;

    @FXML
    private Label moneyDisplay;

    @FXML
    private Label levelDisplay;

    @FXML
    private Label nextLevelButton;


    public void updateDisplays() {
        moneyDisplay.setText(this.game.getPlayer().getMoney() + "$");
        healthDisplay.setText(this.game.getPlayer().getHealthPoints() + "♥");

        if (this.game.getLevelManager().getLevel() == this.game.getLevelManager().getNumberTotalLevels())
            levelDisplay.setText("endless Mode");
        else
            levelDisplay.setText("Level " + this.game.getLevelManager().getLevel());

        if (game.getLevelManager().nextLevelAvailable())
            nextLevelButton.setText("next Level");
        else
            nextLevelButton.setText("");

        if(this.game.getPlayer().getHealthPoints() <= 0)
            healthDisplay.setTextFill(Color.RED);
        else
            healthDisplay.setTextFill(Color.BLACK);
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

    public void startNextLevel(MouseEvent mouseEvent) {
        game.getLevelManager().startNextLevel();
    }
}
