package com.serenki.game.enemies;

import com.serenki.game.Vector;
import javafx.scene.canvas.GraphicsContext;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

//In theory, I could make one superclass with generics or something, but imo this is easier

public class EnemiesManager {

    private GraphicsContext graphicsContext;

    private ArrayList<Enemy> enemies;

    public EnemiesManager(GraphicsContext graphicsContext) {
        enemies = new ArrayList<>();
        this.graphicsContext = graphicsContext;
    }

    public Enemy getOneEnemyInArea(Vector position, double radius) {
        for (Enemy enemy : enemies) {
            if (position.distanceToSquared(enemy.getPosition()) <= radius * radius)
                return enemy;
        }
        return null;
    }

    public ArrayList<Enemy> getEnemiesInArea(Vector position, double radius) {
        ArrayList<Enemy> e = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (position.distanceToSquared(enemy.getPosition()) <= radius * radius) {
                e.add(enemy);
            }
        }
        return e;
    }

    public void add(@NotNull Enemy enemy) {
        this.enemies.add(enemy);
    }

    public Enemy get(int i) {
        return enemies.get(i);
    }

    /**
     * Deletes all dead enemies, then updates and renders each one.
     */
    public void renderAndUpdate() {
        enemies.removeIf(enemy -> !enemy.isAlive());        //some weird shit I don't understand (Predicates and stuff?)
        for (Enemy enemy : enemies) {
            enemy.update();
            enemy.render(this.graphicsContext);
        }
    }


}
