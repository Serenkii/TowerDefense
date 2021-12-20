package com.serenki.game.UI;

import com.serenki.game.Player;
import com.serenki.game.enemies.EnemiesManager;
import com.serenki.game.projectiles.ProjectilesManager;
import com.serenki.game.towers.Barrier;
import com.serenki.game.towers.FireCannon;
import com.serenki.game.towers.QuadrupleShooter;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class GameWindowController {

    private Player player;
    private EnemiesManager enemiesManager;
    private ProjectilesManager projectilesManager;


    @FXML
    private Canvas battlefieldCanvas;

    @FXML
    private Label healthDisplay;

    @FXML
    private Label moneyDisplay;

    public void updateDisplays() {
        moneyDisplay.setText(this.player.getMoney() + "$");
        healthDisplay.setText(this.player.getHealthPoints() + "♥");
    }

    public GraphicsContext getGraphicsContextOfCanvas() {
        return battlefieldCanvas.getGraphicsContext2D();
    }

    public void initializeManually(Player player, EnemiesManager enemiesManager, ProjectilesManager projectilesManager) {
        this.player = player;
        this.enemiesManager = enemiesManager;
        this.projectilesManager = projectilesManager;
    }

    @FXML
    void pickUpBarrier(MouseEvent event) {
        player.setTowerToPlace(new Barrier());
    }

    @FXML
    void pickUpCannon(MouseEvent event) {
        player.setTowerToPlace(new FireCannon(this.enemiesManager, this.projectilesManager));
    }

    @FXML
    void pickUpQuadrupleShooter(MouseEvent event) {
        player.setTowerToPlace(new QuadrupleShooter(this.enemiesManager, this.projectilesManager));
    }

}
