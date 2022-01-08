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
     * @param targetPos Position of the destination/target.
     * @param speed The distance per second the projectile moves.
     * @param pathToImage
     */
    public Missile(@NotNull Vector position, @NotNull Vector targetPos, double speed, boolean rotatableProjectile, @NotNull String pathToImage) {
        super(position, speed, rotatableProjectile, pathToImage);
        this.targetPosition = targetPos;
        this.calculateVelocity();
        if (isRotatableProjectile())
            this.sprite.setRotationAngle(velocity);
    }

    /**
     * Calculates the velocity of the airplane based on the target's position.
     * @implNote Only needs to be called once when created.
     */
    private void calculateVelocity() {
        this.velocity = this.position.vectorTo(targetPosition).normalize().multiply(super.getSpeed());
    }

    /**
     * @implSpec Should be called once per frame by the super.update() function, so the GuidedMissile's position is updated.
     */
    @Override
    protected void move() {
        if(atTarget()) {
            this.velocity = new Vector(0,0);
        }
        this.position = this.position.add(velocity);
    }

    /**
     * @implNote The destination is reached as soon as the distance to it is minimal.
     * @return True if the destination was reached, otherwise false.
     */
    private boolean atTarget() {
        if (this.position.distanceToSquared(targetPosition) < 0.01)
            return true;
        return false;
    }
}
