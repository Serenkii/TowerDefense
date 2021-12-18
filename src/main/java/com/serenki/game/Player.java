package com.serenki.game;

import com.serenki.game.towers.Tower;
import com.serenki.game.towers.TowersManager;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.Nullable;

public class Player {

    private int money;
    @Nullable private Tower towerToPlace;

    private int mouseX;
    private int mouseY;

    private final TowersManager towersManager;

    private Canvas canvas;

    public Player (Canvas canvas, TowersManager towersManager) {
        this.money = 999999;
        this.towersManager = towersManager;
        this.canvas = canvas;
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
                //System.out.println("Clicked x: " + mouseEvent.getX());
                //System.out.println("Clicked y: " + mouseEvent.getY());
                if (towerToPlace == null) {
                    return;
                }
                if (money < towerToPlace.getCost()) {
                    return;
                }
                if(towersManager.placeTower(towerToPlace, Battlefield.getCoordinateFromPixPos(new GridCoordinate((int) mouseEvent.getX(), (int) mouseEvent.getY()))) == false) {
                    return;
                }
                changeMoneyBy(towerToPlace.getCost());
                towerToPlace = null;
            }
        });
        this.towerToPlace = null;
    }

    /**
     * Changes the amount of money the player has.
     * @param value If value is greater than zero it adds the amount, less than zero the amount is subtracted.
     */
    public void changeMoneyBy(int value) {
        this.money += value;
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
        towerToPlace.renderTransparently(canvas.getGraphicsContext2D());
        towerToPlace.renderRangeIndication(canvas.getGraphicsContext2D());
    }
}