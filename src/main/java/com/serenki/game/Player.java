package com.serenki.game;

import com.serenki.game.towers.*;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Player {

    private Game game;

    private int money;
    private int healthPoints;
    @Nullable private Tower towerToPlace;

    private int mouseX;
    private int mouseY;

    private final TowersManager towersManager;

    private Canvas canvas;

    public Player (@NotNull final Game game) {
        this.money = 100;
        this.healthPoints = 50;
        this.game = game;
        this.towersManager = game.getTowersManager();
        this.canvas = game.getCanvas();

        this.canvas.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case ESCAPE -> {
                        towerToPlace = null;
                        towersManager.unselectAll();
                    }
                    case DIGIT1 -> towerToPlace = new Barrier();
                    case DIGIT2 -> towerToPlace = new QuadrupleShooter(game.getEnemiesManager(), game.getProjectilesManager());
                    case DIGIT3 -> towerToPlace = new Flamethrower(game.getEnemiesManager(), game.getProjectilesManager());
                    case DIGIT4 -> towerToPlace = new Sniper(game.getEnemiesManager(), game.getProjectilesManager());
                    case DIGIT5 -> towerToPlace = new Cannon(game.getEnemiesManager(), game.getProjectilesManager());

                    case F1 ->  game.getLevelManager().startNextLevel();
                }
            }
        });

        this.canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseX = (int) mouseEvent.getX();
                mouseY = (int) mouseEvent.getY();
            }
        });

        this.canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if (towerToPlace == null) {
                    trySelectingTower(mouseEvent);
                    return;
                }
                tryPlacingTower(mouseEvent);
            }
        });

        this.towerToPlace = null;
    }

    private void tryPlacingTower(MouseEvent mouseEvent) {
        if (money < towerToPlace.getCost()) {
            return;
        }
        if(towersManager.placeTower(towerToPlace, Battlefield.getCoordinateFromPixPos(new GridCoordinate((int) mouseEvent.getX(), (int) mouseEvent.getY()))) == true) {
            changeMoneyBy(-towerToPlace.getCost());
        }
        towerToPlace = null;
    }

    private void trySelectingTower(MouseEvent mouseEvent) {
        towersManager.selectTower(Battlefield.getCoordinateFromPixPos(new GridCoordinate((int) mouseEvent.getX(), (int) mouseEvent.getY())));
    }

    public int getMoney() {
        return money;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * Changes the amount of money the player has.
     * @param value If value is greater than zero it adds the amount, less than zero the amount is subtracted.
     */
    public void changeMoneyBy(int value) {
        this.money += value;
    }

    public void changeHealthPointsBy(int value) {
        this.healthPoints += value;
    }

    public void setTowerToPlace(Tower towerToPlace) {
        this.towerToPlace = towerToPlace;
        this.towerToPlace.select();
        this.towersManager.unselectAll();
    }

    public void renderTower() {
        if (towerToPlace == null) {
            return;
        }
        towerToPlace.setCoordinate(Battlefield.getCoordinateFromPixPos(new GridCoordinate(this.mouseX, this.mouseY)));
        towerToPlace.renderShadow(canvas.getGraphicsContext2D());
        towerToPlace.renderRangeIndication(canvas.getGraphicsContext2D());
    }
}
