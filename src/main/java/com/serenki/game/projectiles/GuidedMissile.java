package com.serenki.game.projectiles;

import com.serenki.game.Vector;
import com.serenki.game.enemies.Enemy;
import org.jetbrains.annotations.NotNull;

public class GuidedMissile extends Projectile {

    private Enemy target;
    private int damage;
    private boolean reachedTarget;

    /**
     *
     * @param target
     * @param speed       The distance per second the projectile moves.
     * @param pathToImage
     */
    public GuidedMissile(@NotNull Vector position, @NotNull Enemy target, int damage, double speed, @NotNull String pathToImage) {
        super(position, speed, pathToImage);
        this.target = target;
        this.reachedTarget = false;
        this.damage = damage;
    }

    @Override
    public void move() {
        if (atTarget()) {
            this.reachedTarget = true; //Ready to be deleted
            this.target.dealDamage(damage);
            return;
        }
        this.position = this.position.add(this.calculateVelocity());
    }

    public boolean hasReachedTarget() {
        return reachedTarget;
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
