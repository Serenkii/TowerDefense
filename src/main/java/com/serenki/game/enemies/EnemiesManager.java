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

    /**
     * Determines one enemy that is in the specified circular area.
     * @param position The center of the circle-area.
     * @param radius The radius of the circle-area.
     * @return Null if no enemy in the specified area was found, otherwise the first enemy found in the ArrayList.
     */
    public Enemy getOneEnemyInArea(Vector position, double radius) {
        for (Enemy enemy : enemies) {
            if (position.distanceToSquared(enemy.getPosition()) <= radius * radius)
                return enemy;
        }
        return null;
    }

    /**
     * Determines all enemies in the specified circular area.
     * @param position The center of the circle-area.
     * @param radius The radius of the circle-area.
     * @return An ArrayList containing all enemies found in the specified area. The ArrayList is empty there are no enemies in the specified area.
     */
    public ArrayList<Enemy> getEnemiesInArea(Vector position, double radius) {
        ArrayList<Enemy> e = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (position.distanceToSquared(enemy.getPosition()) <= radius * radius) {
                e.add(enemy);
            }
        }
        return e;
    }

    /**
     * Forces all enemies to find new paths. This should be called if something on the Battlefield is changed, e.g. a Tower was placed.
     */
    public void findNewPaths() {
        for (Enemy enemy : enemies) {
            enemy.findPath();
        }
    }

    /**
     * Add an enemy to the EnemiesManager and therefore the game (Battlefield).
     * @param enemy The enemy that should be added.
     */
    public void add(@NotNull Enemy enemy) {
        this.enemies.add(enemy);
    }


    /**
     * Takes care of all dead enemies and enemies that reached their target, then updates and renders the remaining ones.
     * @implSpec Should be called once a frame.
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
