package com.serenki.game.towers;

import com.serenki.game.Vector;
import com.serenki.game.enemies.EnemiesManager;
import com.serenki.game.projectiles.GuidedMissile;
import com.serenki.game.projectiles.ProjectilesManager;

public class Sniper extends Tower{

    public Sniper(EnemiesManager enemiesManager, ProjectilesManager projectilesManager) {
        super(500, 5.0, 9, enemiesManager, projectilesManager, "file:src/main/resources/com/serenki/art/towers/Sniper.png");
    }

    @Override
    public void shoot() {
        updateCooldown();
        if (!isReady())
            return;
        if (getTarget() == null)
            return;
        this.projectilesManager.add(new GuidedMissile(this.position.add(new Vector(0.35, 0)), this.getTarget(),
                200, 8, "file:src/main/resources/com/serenki/art/projectiles/SniperMissile.png"));
        reload();
    }
}
