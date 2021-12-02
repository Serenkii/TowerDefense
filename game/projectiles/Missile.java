package com.serenki.game.projectiles;

import com.serenki.game.Vector;
import jdk.jfr.Experimental;
import org.jetbrains.annotations.NotNull;

/**
 * @apiNote I do not know what to do with this yet.
 */
@Experimental
public class Missile extends Projectile {

    Vector targetPosition;
    Vector velocity;

    /**
     * @param targetPos
     * @param speed       The distance per second the projectile moves.
     * @param pathToImage
     */
    public Missile(@NotNull Vector position, @NotNull Vector targetPos, double speed, @NotNull String pathToImage) {
        super(position, speed, pathToImage);
        this.targetPosition = targetPos;
        this.calculateVelocity();
    }

    private void calculateVelocity() {
        this.velocity = this.position.vectorTo(targetPosition).normalize().multiply(super.getSpeed());
    }

    @Override
    public void move() {
        if(atTarget()) {
            this.velocity = new Vector(0,0);
        }
        this.position = this.position.add(velocity);
    }

    private boolean atTarget() {
        if (this.position.distanceToSquared(targetPosition) < 0.01)
            return true;
        return false;
    }
}
