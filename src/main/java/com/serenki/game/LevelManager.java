package com.serenki.game;

import com.serenki.game.enemies.*;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LevelManager {

    private Game game;

    private Queue<Level> levels;
    private int level;

    private final int totalNumberOfLevels;

    public LevelManager(@NotNull final Game game) {
        this.game = game;
        this.levels = new ConcurrentLinkedQueue<>();
        this.level = 0;
        loadLevels();
        this.totalNumberOfLevels = levels.size();
    }

    public void update() {
        if (this.levels.isEmpty() || !this.levels.peek().isRunning())
            return;
        levels.peek().update();
        if (!levels.peek().isRunning()) {
            this.game.getPlayer().changeMoneyBy(levels.poll().getReward());
        }
    }

    public boolean nextLevelAvailable() {
        return !this.levels.isEmpty() && !this.levels.peek().isRunning();
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

    public int getNumberTotalLevels() {
        return totalNumberOfLevels;
    }


    private void loadLevels() {
        Level temp;

        temp = new Level(game, 200);
        for (int i = 0; i < 10; i++) {
            temp.addEnemy(new Soldier(new Vector(-0.5, 7.5), game.getPathfinding()), 1d);
        }
        levels.add(temp);

        temp = new Level(game, 100);
        for (int i = 0; i < 10; i++) {
            temp.addEnemy(new Soldier(new Vector(7.5, -0.5), game.getPathfinding()), 0.7d);
        }
        levels.add(temp);

        temp = new Level(game, 200);
        for (int i = 0; i < 10; i++) {
            temp.addEnemy(new Soldier(new Vector(7.5, -0.5), game.getPathfinding()), 0.7d);
            temp.addEnemy(new Soldier(new Vector(-0.5, 7.5), game.getPathfinding()), 0.7d);
        }
        levels.add(temp);

        temp = new Level(game, 50);
        for (int i = 0; i < 10; i++) {
            temp.addEnemy(new Soldier(new Vector(-0.5, 6.5), game.getPathfinding()), 1d);
            temp.addEnemy(new Soldier(new Vector(-0.5, 7.5), game.getPathfinding()), 0);
            temp.addEnemy(new Soldier(new Vector(-0.5, 8.5), game.getPathfinding()), 0);
        }
        temp.addEnemy(new MilitaryTruck(new Vector(-0.5, 7.5), game.getPathfinding()), 10d);
        levels.add(temp);

        temp = new Level(game, 50);
        for (int i = 0; i < 5; i++) {
            temp.addEnemy(new MilitaryTruck(new Vector(7.5, -0.5), game.getPathfinding()), 3d);
            temp.addEnemy(new MilitaryTruck(new Vector(-0.5, 7.5), game.getPathfinding()), 3d);
        }
        levels.add(temp);

        temp = new Level(game, 50);
        for (int i = 0; i < 10; i++) {
            temp.addEnemy(new MilitaryTruck(new Vector(7.5, -0.5), game.getPathfinding()), 2d);
            temp.addEnemy(new MilitaryTruck(new Vector(-0.5, 7.5), game.getPathfinding()), 0.1d);
        }
        levels.add(temp);

        temp = new Level(game, 200);
        for (int i = 0; i < 15; i++) {
            temp.addEnemy(new Soldier(new Vector(7.5, -0.5), game.getPathfinding()), 0.2);
            temp.addEnemy(new Soldier(new Vector(-0.5, 7.5), game.getPathfinding()), 0);
        }
        levels.add(temp);

        temp = new Level(game, 0);
        for (int i = 0; i < 5; i++) {
            temp.addEnemy(new Airplane(new Vector(Math.random() * 10 + 2.5, -0.5), game.getPathfinding()), 1d);
        }
        levels.add(temp);

        temp = new Level(game, 50);
        temp.addEnemy(new Airplane(new Vector(-20, 7), game.getPathfinding()), 0);
        temp.addEnemy(new Airplane(new Vector(-20, 8), game.getPathfinding()), 0);
        temp.addEnemy(new Airplane(new Vector(7, -20), game.getPathfinding()), 0);
        temp.addEnemy(new Airplane(new Vector(8, -20), game.getPathfinding()), 0);
        for (int i = 0; i < 10; i++) {
            temp.addEnemy(new Soldier(new Vector(7.5, -0.5), game.getPathfinding()), 0.2);
            temp.addEnemy(new Soldier(new Vector(-0.5, 7.5), game.getPathfinding()), 0);
        }
        levels.add(temp);

        temp = new Level(game, 50);
        temp.addEnemy(new MilitaryCar(new Vector(-0.5, 7.5), game.getPathfinding()), 2d);
        temp.addEnemy(new MilitaryCar(new Vector(-0.5, 5.5), game.getPathfinding()), 2d);
        temp.addEnemy(new MilitaryCar(new Vector(-0.5, 12.5), game.getPathfinding()), 5d);
        levels.add(temp);

        temp = new Level(game, 50);
        temp.addEnemy(new Airplane(new Vector(7.5, -0.5), game.getPathfinding()), 0);
        for (int i = 0; i < 5; i++) {
            temp.addEnemy(new MilitaryCar(new Vector(Math.random() * 10 + 2.5, -0.5), game.getPathfinding()), 7d);
            temp.addEnemy(new MilitaryTruck(new Vector(Math.random() * 10 + 2.5, -0.5), game.getPathfinding()), 2d);
        }
        levels.add(temp);

        temp = new Level(game, 0);
        temp.addEnemy(new Airplane(new Vector(7.5, -0.5), game.getPathfinding()), 0);
        for (int i = 0; i < 5; i++) {
            temp.addEnemy(new MilitaryCar(new Vector(Math.random() * 10 + 2.5, -0.5), game.getPathfinding()), 4d);
            temp.addEnemy(new MilitaryTruck(new Vector(Math.random() * 10 + 2.5, -0.5), game.getPathfinding()), 1d);
        }
        levels.add(temp);

        temp = new Level(game, 0);
        temp.addEnemy(new SlowTank(new Vector(-0.5, 7.5), game.getPathfinding()), 4d);
        temp.addEnemy(new FastTank(new Vector(-0.5, 6.5), game.getPathfinding()), 10d);
        temp.addEnemy(new FastTank(new Vector(-0.5, 8.5), game.getPathfinding()), 0d);
        levels.add(temp);

        temp = new Level(game, 0);
        temp.addEnemy(new Airplane(new Vector(-0.5, 7.5), game.getPathfinding()), 0);
        for (int i = 0; i < 5; i++) {
            temp.addEnemy(new MilitaryCar(new Vector(-0.5, Math.random() * 10 + 2.5), game.getPathfinding()), 4d);
            temp.addEnemy(new MilitaryTruck(new Vector(-0.5, Math.random() * 10 + 2.5), game.getPathfinding()), 1d);
        }
        temp.addEnemy(new SlowTank(new Vector(-0.5, 7.5), game.getPathfinding()), 15d);
        temp.addEnemy(new SlowTank(new Vector(-0.5, 7.5), game.getPathfinding()), 5d);
        levels.add(temp);

        temp = new Level(game, 0);
        temp.addEnemy(new Airplane(new Vector(7.5, -0.5), game.getPathfinding()), 0);
        for (int i = 0; i < 7; i++) {
            temp.addEnemy(new Airplane(new Vector(Math.random() * 10 + 2.5, -0.5), game.getPathfinding()), 3d);
            temp.addEnemy(new Airplane(new Vector(-0.5, Math.random() * 10 + 2.5), game.getPathfinding()), Math.random());
        }
        levels.add(temp);

        temp = new Level(game, 0);
        for (int i = 0; i < 10; i++) {
            temp.addEnemy(new Soldier(new Vector(-0.5, 7.5), game.getPathfinding()), 0.5d);
        }
        temp.addEnemy(new MilitaryTruck(new Vector(-0.5, 7.5), game.getPathfinding()), 5d);
        for (int i = 0; i < 9; i++) {
            temp.addEnemy(new MilitaryTruck(new Vector(-0.5, 7.5), game.getPathfinding()), 0.8d);
        }
        temp.addEnemy(new FastTank(new Vector(-0.5, 7.5), game.getPathfinding()), 5d);
        for (int i = 0; i < 9; i++) {
            temp.addEnemy(new FastTank(new Vector(-0.5, 7.5), game.getPathfinding()), 1d);
        }
        levels.add(temp);


        //Level 17
        temp = new Level(game, 0);
        for (int i = 0; i < 10; i++) {
            temp.addEnemy(new Soldier(new Vector(-0.5, 7.5), game.getPathfinding()), 0.5d);
        }
        temp.addEnemy(new MilitaryTruck(new Vector(-0.5, 7.5), game.getPathfinding()), 5d);
        for (int i = 0; i < 9; i++) {
            temp.addEnemy(new MilitaryTruck(new Vector(-0.5, 7.5), game.getPathfinding()), 0.8d);
        }
        temp.addEnemy(new FastTank(new Vector(-0.5, 7.5), game.getPathfinding()), 5d);
        for (int i = 0; i < 9; i++) {
            temp.addEnemy(new FastTank(new Vector(-0.5, 7.5), game.getPathfinding()), 1d);
        }
        temp.addEnemy(new Airplane(new Vector(-0.5, Math.round(Math.random() * 10) + 2.5), game.getPathfinding()), 10d);
        for (int i = 0; i < 9; i++) {
            temp.addEnemy(new Airplane(new Vector(-0.5, Math.round(Math.random() * 10) + 2.5), game.getPathfinding()), 0.5d);
        }
        levels.add(temp);


        temp = new Level(game, 100);
        for (int i = 0; i < 5; i++) {
            temp.addEnemy(new SlowTank(new Vector(7.5, -0.5), game.getPathfinding()), 10d);
            temp.addEnemy(new SlowTank(new Vector(6.5, -0.5), game.getPathfinding()), 0);
            temp.addEnemy(new SlowTank(new Vector(8.5, -0.5), game.getPathfinding()), 0);
        }
        levels.add(temp);

        temp = new Level(game, 100);
        for (int i = 0; i < 5; i++) {
            temp.addEnemy(new FastTank(new Vector(-0.5, 7.5), game.getPathfinding()), 11d);
            temp.addEnemy(new FastTank(new Vector(-0.5, 6.5), game.getPathfinding()), 0);
            temp.addEnemy(new FastTank(new Vector(-0.5, 8.5), game.getPathfinding()), 0);

            temp.addEnemy(new SlowTank(new Vector(7.5, -0.5), game.getPathfinding()), 6d);
            temp.addEnemy(new SlowTank(new Vector(6.5, -0.5), game.getPathfinding()), 0);
            temp.addEnemy(new SlowTank(new Vector(8.5, -0.5), game.getPathfinding()), 0);
        }
        levels.add(temp);

        //Level 20
        temp = new Level(game, 50);
        for (int i = 0; i < 30; i++) {
            temp.addEnemy(new Soldier(new Vector(Math.round(Math.random() * 10) + 2.5, -0.5), game.getPathfinding()),0.1);
            temp.addEnemy(new Soldier(new Vector(-0.5, Math.round(Math.random() * 10) + 2.5), game.getPathfinding()), 0);
        }
        levels.add(temp);

        temp = new Level(game, 50);
        for (int i = 0; i < 15; i++) {
            temp.addEnemy(new MilitaryTruck(new Vector(Math.round(Math.random() * 10) + 2.5, -0.5), game.getPathfinding()),0.3);
            temp.addEnemy(new MilitaryTruck(new Vector(-0.5, Math.round(Math.random() * 10) + 2.5), game.getPathfinding()), 0);
        }
        levels.add(temp);

        temp = new Level(game, 50);
        for (int i = 0; i < 10; i++) {
            temp.addEnemy(new SlowTank(new Vector(-0.5, Math.round(Math.random() * 10) + 2.5), game.getPathfinding()), 1d);
        }
        levels.add(temp);

        //Level 23
        temp = new Level(game, 50);
        for (int i = 0; i < 20; i++) {
            temp.addEnemy(new Soldier(new Vector(-0.5, 7.5), game.getPathfinding()), 0.3d);
        }
        temp.addEnemy(new MilitaryTruck(new Vector(-0.5, 7.5), game.getPathfinding()), 5d);
        for (int i = 0; i < 13; i++) {
            temp.addEnemy(new MilitaryTruck(new Vector(-0.5, 7.5), game.getPathfinding()), 0.7d);
        }
        temp.addEnemy(new FastTank(new Vector(-0.5, 7.5), game.getPathfinding()), 5d);
        for (int i = 0; i < 11; i++) {
            temp.addEnemy(new FastTank(new Vector(-0.5, 7.5), game.getPathfinding()), 1d);
        }
        temp.addEnemy(new Airplane(new Vector(-0.5, Math.round(Math.random() * 10) + 2.5), game.getPathfinding()), 10d);
        for (int i = 0; i < 13; i++) {
            temp.addEnemy(new Airplane(new Vector(-0.5, Math.round(Math.random() * 10) + 2.5), game.getPathfinding()), 0.5d);
        }
        temp.addEnemy(new SlowTank(new Vector(-0.5, 7.5), game.getPathfinding()), 5d);
        temp.addEnemy(new SlowTank(new Vector(-0.5, 8.5), game.getPathfinding()), 0);
        temp.addEnemy(new SlowTank(new Vector(-0.5, 6.5), game.getPathfinding()), 0);
        levels.add(temp);


        //Level 24
        temp = new Level(game, 500);
        temp.addEnemy(new SlowTank(new Vector(-0.5, 7.5), game.getPathfinding()), 0);
        levels.add(temp);

        //LEVEL 25
        temp = new Level(game, 500);
        temp.addEnemy(new Zeppelin(new Vector(-5, 7.5), game.getPathfinding()), 0);
        levels.add(temp);

        temp = new Level(game, 0);
        for (int i = 0; i < 20; i++) {
            temp.addEnemy(new Soldier(new Vector(Math.round(Math.random() * 8) + 3.5, -0.5), game.getPathfinding()), 0.2d);
        }
        temp.addEnemy(new SlowTank(new Vector(Math.round(Math.random() * 8) + 3.5, -0.5), game.getPathfinding()), 5d);
        for (int i = 0; i < 10; i++) {
            temp.addEnemy(new SlowTank(new Vector(Math.round(Math.random() * 8) + 3.5, -0.5), game.getPathfinding()), 0.5d);
        }
        temp.addEnemy(new Airplane(new Vector(Math.round(Math.random() * 8) + 3.5, -0.5), game.getPathfinding()), 13d);
        for (int i = 0; i < 10; i++) {
            temp.addEnemy(new Airplane(new Vector(Math.round(Math.random() * 8) + 3.5, -0.5), game.getPathfinding()), 0.2d);
        }
        temp.addEnemy(new SlowTank(new Vector(Math.round(Math.random() * 8) + 3.5, -0.5), game.getPathfinding()), 8d);
        for (int i = 0; i < 10; i++) {
            temp.addEnemy(new SlowTank(new Vector(Math.round(Math.random() * 8) + 3.5, -0.5), game.getPathfinding()), 0.5d);
        }
        temp.addEnemy(new FastTank(new Vector(Math.round(Math.random() * 8) + 3.5, -0.5), game.getPathfinding()), 9d);
        for (int i = 0; i < 10; i++) {
            temp.addEnemy(new FastTank(new Vector(Math.round(Math.random() * 8) + 3.5, -0.5), game.getPathfinding()), 0.5d);
        }
        temp.addEnemy(new MilitaryTruck(new Vector(Math.round(Math.random() * 8) + 3.5, -0.5), game.getPathfinding()), 8d);
        for (int i = 0; i < 10; i++) {
            temp.addEnemy(new MilitaryTruck(new Vector(Math.round(Math.random() * 8) + 3.5, -0.5), game.getPathfinding()), 0.5d);
        }
        levels.add(temp);

        //Level 27
        temp = new Level(game, 0);
        temp.addEnemy(new Zeppelin(new Vector(-3, 7.5), game.getPathfinding()), 3d);
        temp.addEnemy(new Zeppelin(new Vector(-3, 6.5), game.getPathfinding()), 0.5);
        temp.addEnemy(new Zeppelin(new Vector(-3, 8.5), game.getPathfinding()), 0);
        levels.add(temp);

        temp = new Level(game, 100);
        for (int i = 0; i < 20; i++) {
            temp.addEnemy(new SlowTank(new Vector(-0.5, 7.5), game.getPathfinding()), 1d);
        }
        temp.addEnemy(new Zeppelin(new Vector(-1, 7.5), game.getPathfinding()), 5d);
        levels.add(temp);

        temp = new Level(game, 100);
        for (int i = 0; i < 30; i++) {
            temp.addEnemy(new FastTank(new Vector(-0.5, 7.5), game.getPathfinding()), 0.7d);
        }
        temp.addEnemy(new Zeppelin(new Vector(6.5, -1), game.getPathfinding()), 5d);
        temp.addEnemy(new Zeppelin(new Vector(8.5, -1), game.getPathfinding()), 2d);
        levels.add(temp);

        temp = new Level(game, 100);
        for (int i = 0; i < 6; i++) {
            temp.addEnemy(new Zeppelin(new Vector(Math.round(Math.random() * 8) + 3.5, -0.5), game.getPathfinding()), 8d);
            temp.addEnemy(new Zeppelin(new Vector(-0.5, Math.round(Math.random() * 8) + 3.5), game.getPathfinding()), 8d);
        }
        levels.add(temp);

        levels.add(new InfiniteLevel(game));
    }




    private class Level {

        protected final Game game;

        protected boolean running;

        protected Queue<Enemy> enemyQueue;
        protected Queue<Integer> delay;

        protected int framesSinceLastSpawn;

        protected int reward;

        public Level(@NotNull final Game game, int reward) {
            this.game = game;
            this.reward = reward;
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

        public int getReward() {
            return reward;
        }

        /**
         * Summons the first enemy in the queue
         */
        protected void summonEnemy() {
            enemyQueue.peek().findPath();       //Before being spawned, the entity should now an up to date path
            game.getEnemiesManager().add(enemyQueue.poll());
            delay.poll();
            framesSinceLastSpawn = 0;
        }
    }

    private class InfiniteLevel extends Level {
        public InfiniteLevel(@NotNull Game game) {
            super(game, 0);
        }

        public void start() {
            addCompletelyRandomEnemy();
            running = true;
        }

        @Override
        public boolean update() {
            while (!enemyQueue.isEmpty() && framesSinceLastSpawn >= delay.peek()) {
                addCompletelyRandomEnemy();
                summonEnemy();
            }
            framesSinceLastSpawn++;
            return true;
        }

        private void addCompletelyRandomEnemy() {
            Random rnd = new Random();
            int r = rnd.nextInt(100);
            if (r < 19) {
                if (rnd.nextBoolean())
                    addEnemy(new Soldier(new Vector(-0.5, rnd.nextDouble(15d)), game.getPathfinding()), 0.2d);
                else
                    addEnemy(new Soldier(new Vector(rnd.nextDouble(15d), -0.5), game.getPathfinding()), 0.2d);
            }
            else if (r < 20) {
                if (rnd.nextBoolean())
                    addEnemy(new Zeppelin(new Vector(-0.5, rnd.nextDouble(15d)), game.getPathfinding()), 5d);
                else
                    addEnemy(new Zeppelin(new Vector(rnd.nextDouble(15d), -0.5), game.getPathfinding()), 5d);
            }
            else if (r < 36) {
                if (rnd.nextBoolean())
                    addEnemy(new SlowTank(new Vector(-0.5, rnd.nextDouble(15d)), game.getPathfinding()), 0.5d);
                else
                    addEnemy(new SlowTank(new Vector(rnd.nextDouble(15d), -0.5), game.getPathfinding()), 0.5d);
            }
            else if (r < 52) {
                if (rnd.nextBoolean())
                    addEnemy(new FastTank(new Vector(-0.5, rnd.nextDouble(15d)), game.getPathfinding()), 0.5d);
                else
                    addEnemy(new FastTank(new Vector(rnd.nextDouble(15d), -0.5), game.getPathfinding()), 0.5d);
            }
            else if (r < 68) {
                if (rnd.nextBoolean())
                    addEnemy(new MilitaryCar(new Vector(-0.5, rnd.nextDouble(15d)), game.getPathfinding()), 0.5d);
                else
                    addEnemy(new MilitaryCar(new Vector(rnd.nextDouble(15d), -0.5), game.getPathfinding()), 0.5d);
            }
            else if (r < 84) {
                if (rnd.nextBoolean())
                    addEnemy(new MilitaryTruck(new Vector(-0.5, rnd.nextDouble(15d)), game.getPathfinding()), 0.5d);
                else
                    addEnemy(new MilitaryTruck(new Vector(rnd.nextDouble(15d), -0.5), game.getPathfinding()), 0.5d);
            }
            else {
                if (rnd.nextBoolean())
                    addEnemy(new Airplane(new Vector(-0.5, rnd.nextDouble(15d)), game.getPathfinding()), 8d);
                else
                    addEnemy(new Airplane(new Vector(rnd.nextDouble(15d), -0.5), game.getPathfinding()), 8d);
            }
        }

    }
}
