package com.serenki.game.towers;

import com.serenki.game.GridCoordinate;
import com.serenki.game.Vector;
import com.serenki.game.enemies.EnemiesManager;
import com.serenki.game.projectiles.GuidedMissile;
import com.serenki.game.projectiles.ProjectilesManager;

public class FireCannon extends Tower{
    @Deprecated
    public FireCannon(GridCoordinate coordinate, EnemiesManager enemiesManager, ProjectilesManager projectilesManager) {
        super(coordinate, 2.0d, 2, enemiesManager, projectilesManager, "file:src/main/resources/com/serenki/art/towers/FireCannon.png");
    }

    public FireCannon(EnemiesManager enemiesManager, ProjectilesManager projectilesManager) {
        super(300,2.0d, 2, enemiesManager, projectilesManager, "file:src/main/resources/com/serenki/art/towers/FireCannon.png");
    }

    @Override
    public void shoot() {
        updateCooldown();
        if (!isReady())
            return;
        if (getTarget() == null)
            return;
        this.projectilesManager.add(new GuidedMissile(this.position.add(new Vector(0.35, 0)), this.getTarget(),
                80, 2, "file:src/main/resources/com/serenki/art/projectiles/FireMissile.png"));
        reload();
    }
}

/* public void shoot() {
        updateCooldown();
        if (!isReady())
            return;
        reload();
        if (target != null) {
            System.out.println(this + ": SHOOT");
        }
    } */