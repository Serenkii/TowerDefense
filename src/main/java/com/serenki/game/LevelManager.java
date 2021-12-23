package com.serenki.game;

import com.serenki.game.enemies.Enemy;
import com.serenki.game.enemies.Soldier;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LevelManager {

    private Game game;

    private Queue<Level> levels;
    private int level;

    public LevelManager(@NotNull final Game game) {
        this.game = game;
        this.levels = new ConcurrentLinkedQueue<>();
        this.level = 0;
        loadLevels();
    }

    public void update() {
        if (this.levels.isEmpty() || !this.levels.peek().isRunning())
            return;
        levels.peek().update();
        if (!levels.peek().isRunning()) {
            levels.poll();
        }
    }

    public boolean startNextLevel() {
        if (this.levels.isEmpty() || this.levels.peek().isRunning())
            return false;

        this.levels.peek().start();
        this.level++;
        return true;
    }

    public int getLevel() {
        return level;
    }




    private void loadLevels() {
        Level level1 = new Level(game);
        for (int i = 0; i < 10; i++) {
            level1.addEnemy(new Soldier(new Vector(Math.random() * 15, -0.5), game.getPathfinding()), 1d);
        }
        levels.add(level1);

        Level level2 = new Level(game);
        for (int i = 0; i < 15; i++) {
            level2.addEnemy(new Soldier(new Vector(-0.5, Math.random() * 15), game.getPathfinding()), 0.5d);
        }
        levels.add(level2);

        Level level3 = new Level(game);
        for (int i = 0; i < 20; i++) {
            if (Math.random() < 0.5)
                level3.addEnemy(new Soldier(new Vector(Math.random() * 15, -0.5), game.getPathfinding()), Math.random() * 1d);
            else
                level3.addEnemy(new Soldier(new Vector(-0.5, Math.random() * 15), game.getPathfinding()), Math.random() * 1d);
        }
        levels.add(level3);
    }






    private class Level {

        private final Game game;

        private boolean running;

        private Queue<Enemy> enemyQueue;
        private Queue<Integer> delay;

        private int framesSinceLastSpawn;

        public Level(@NotNull final Game game) {
            this.game = game;
            enemyQueue = new ConcurrentLinkedQueue<>();
            delay = new ConcurrentLinkedQueue<>();
            running = false;
            framesSinceLastSpawn = 0;
        }

        public void addEnemy(@NotNull Enemy enemy, @NotNull int delayInFrames) {
            enemyQueue.add(enemy);
            delay.add(delayInFrames);
        }

        public void addEnemy(@NotNull Enemy enemy, @NotNull double delayInSeconds) {
            enemyQueue.add(enemy);
            delay.add((int) Math.round(delayInSeconds * Game.FRAME_RATE));
        }

        public void start() {
            running = true;
        }

        public boolean update() {
            if (this.running == false)
                return false;

            while (!enemyQueue.isEmpty() && framesSinceLastSpawn >= delay.peek()) {
                summonEnemy();
            }

            if (enemyQueue.isEmpty())
                this.running = false;

            framesSinceLastSpawn++;
            return true;
        }

        public boolean isRunning() {
            return running;
        }

        /**
         * Summons the first enemy in the queue
         */
        private void summonEnemy() {
            game.getEnemiesManager().add(enemyQueue.poll());
            delay.poll();
            framesSinceLastSpawn = 0;
        }
    }
}
