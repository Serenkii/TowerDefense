package com.serenki.game.towers;

import com.serenki.game.Vector;
import com.serenki.game.enemies.EnemiesManager;
import com.serenki.game.projectiles.GuidedMissile;
import com.serenki.game.projectiles.ProjectilesManager;

public class Cannon extends Tower {
    public Cannon(EnemiesManager enemiesManager, ProjectilesManager projectilesManager) {
        super(500, 2.0d, 3d, enemiesManager, projectilesManager, "file:src/main/resources/com/serenki/art/towers/Cannon.png");
    }

    /**
     * Creates projectile that follows target.
     * @implNote Should be called in update().
     */
    @Override
    public void shoot() {
        updateCooldown();
        if (!isReady())
            return;
        if (getTarget() == null)
            return;
        this.projectilesManager.add(new GuidedMissile(this.position.add(new Vector(0.4, -0.4)), this.getTarget(),
                200, 4, false, "file:src/main/resources/com/serenki/art/projectiles/FireMissile.png"));
        reload();
    }
}
