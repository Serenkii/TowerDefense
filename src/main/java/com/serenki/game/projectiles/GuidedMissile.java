package com.serenki.game.projectiles;

import com.serenki.game.Vector;
import com.serenki.game.enemies.Enemy;
import org.jetbrains.annotations.NotNull;

public class GuidedMissile extends Projectile {

    private Enemy target;

    /**
     *
     * @param target
     * @param speed       The distance per second the projectile moves.
     * @param pathToImage
     */
    public GuidedMissile(@NotNull Vector position, @NotNull Enemy target, double speed, @NotNull String pathToImage) {
        super(position, speed, pathToImage);
        this.target = target;
    }

    @Override
    public void move() {
        if (atTarget()) {
            //do something
        }
        this.position = this.position.add(this.calculateVelocity());
    }

    private boolean atTarget() {
        if (this.position.distanceToSquared(target.getPosition()) < 0.01)
            return true;
        return false;
    }

    private Vector calculateVelocity() {
        return this.position.vectorTo(target.getPosition()).normalize().multiply(this.getSpeed());
    }
}
