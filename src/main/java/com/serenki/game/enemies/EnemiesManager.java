package com.serenki.game.enemies;

import com.serenki.game.Game;
import com.serenki.game.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

//In theory, I could make one superclass with generics or something, but imo this is easier

public class EnemiesManager {

    private Game game;

    private ArrayList<Enemy> enemies;

    public EnemiesManager(Game game) {
        enemies = new ArrayList<>();
        this.game = game;
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

    public void findNewPaths() {
        for (Enemy enemy : enemies) {
            enemy.findPath();
        }
    }

    public void add(@NotNull Enemy enemy) {
        this.enemies.add(enemy);
    }

    public Enemy get(int i) {
        return enemies.get(i);
    }

    /**
     * Takes care of all dead enemies and enemies that reached their target, then updates and renders the remaining ones.
     */
    public void renderAndUpdate() {             //https://stackoverflow.com/questions/9691328/removing-object-from-arraylist-in-for-each-loop
        for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext();) {
            Enemy e = iterator.next();
            if (!e.isAlive()) {
                this.game.getPlayer().changeMoneyBy(e.getMoneyValue());
                iterator.remove();
            }
            else if (e.reachedDestination()) {
                this.game.getPlayer().changeHealthPointsBy(-e.getDamageValue());
                iterator.remove();
            }
        }
        for (Enemy enemy : enemies) {
            enemy.update();
            enemy.render(this.game.getGraphicsContext());
        }
    }


}
