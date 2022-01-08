package com.serenki.game.towers;

import com.serenki.game.Vector;
import com.serenki.game.enemies.EnemiesManager;
import com.serenki.game.projectiles.GuidedMissile;
import com.serenki.game.projectiles.ProjectilesManager;

public class QuadrupleShooter extends Tower {

    public QuadrupleShooter(EnemiesManager enemiesManager, ProjectilesManager projectilesManager) {
        super(200,0.8d, 3, enemiesManager, projectilesManager, "file:src/main/resources/com/serenki/art/towers/QuadrupleShooter.png");
    }

    /**
     * Creates projectiles that follow target.
     * @implNote Should be called in update().
     */
    @Override
    public void shoot() {
        updateCooldown();
        if (!isReady())
            return;
        if (getTarget() == null)
            return;
        this.projectilesManager.add(new GuidedMissile(this.position.add(new Vector(0.4, 0)), this.getTarget(),
                5, 4, false,"file:src/main/resources/com/serenki/art/projectiles/SpikeMissile.png"));
        this.projectilesManager.add(new GuidedMissile(this.position.add(new Vector(-0.4, 0)), this.getTarget(),
                5, 4, false,"file:src/main/resources/com/serenki/art/projectiles/SpikeMissile.png"));
        this.projectilesManager.add(new GuidedMissile(this.position.add(new Vector(0, 0.4)), this.getTarget(),
                5, 4, false,"file:src/main/resources/com/serenki/art/projectiles/SpikeMissile.png"));
        this.projectilesManager.add(new GuidedMissile(this.position.add(new Vector(0, -0.4)), this.getTarget(),
                5, 4, false,"file:src/main/resources/com/serenki/art/projectiles/SpikeMissile.png"));
        reload();
    }
}
