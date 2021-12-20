package com.serenki.game.towers;

import com.serenki.game.GridCoordinate;
import com.serenki.game.Vector;
import com.serenki.game.enemies.EnemiesManager;
import com.serenki.game.projectiles.GuidedMissile;
import com.serenki.game.projectiles.ProjectilesManager;

public class QuadrupleShooter extends Tower {

    @Deprecated
    public QuadrupleShooter(GridCoordinate coordinate, EnemiesManager enemiesManager, ProjectilesManager projectilesManager) {
        super(coordinate, 0.8d, 3, enemiesManager, projectilesManager, "file:src/main/resources/com/serenki/art/towers/QuadrupleShooter.png");
    }

    public QuadrupleShooter(EnemiesManager enemiesManager, ProjectilesManager projectilesManager) {
        super(250,0.8d, 3, enemiesManager, projectilesManager, "file:src/main/resources/com/serenki/art/towers/QuadrupleShooter.png");
    }

    @Override
    public void shoot() {
        updateCooldown();
        if (!isReady())
            return;
        if (getTarget() == null)
            return;
        this.projectilesManager.add(new GuidedMissile(this.position.add(new Vector(0.4, 0)), this.getTarget(),
                5, 4, "file:src/main/resources/com/serenki/art/projectiles/SpikeMissile.png"));
        this.projectilesManager.add(new GuidedMissile(this.position.add(new Vector(-0.4, 0)), this.getTarget(),
                5, 4, "file:src/main/resources/com/serenki/art/projectiles/SpikeMissile.png"));
        this.projectilesManager.add(new GuidedMissile(this.position.add(new Vector(0, 0.4)), this.getTarget(),
                5, 4, "file:src/main/resources/com/serenki/art/projectiles/SpikeMissile.png"));
        this.projectilesManager.add(new GuidedMissile(this.position.add(new Vector(0, -0.4)), this.getTarget(),
                5, 4, "file:src/main/resources/com/serenki/art/projectiles/SpikeMissile.png"));
        reload();
    }
}
