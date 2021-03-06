package com.serenki.game.projectiles;

import com.serenki.game.Vector;
import com.serenki.game.enemies.Enemy;
import org.jetbrains.annotations.NotNull;

public class GuidedMissile extends Projectile {

    private Enemy target;
    private int damage;


    /**
     *
     * @param target Position of the target enemy.
     * @param speed The distance per second the projectile moves.
     * @param pathToImage
     */
    public GuidedMissile(@NotNull Vector position, @NotNull Enemy target, int damage, double speed, boolean rotatableProjectile, @NotNull String pathToImage) {
        super(position, speed, rotatableProjectile, pathToImage);
        this.target = target;
        this.damage = damage;
    }

    /**
     * @implSpec Should be called once per frame by the super.update() function, so the GuidedMissile's position is updated.
     */
    @Override
    protected void move() {
        if (atTarget()) {
            this.reachedTarget(); //Ready to be deleted
            this.target.dealDamage(damage);
            return;
        }
        this.position = this.position.add(this.calculateVelocity());
        if (isRotatableProjectile())
            this.sprite.setRotationAngle(position.vectorTo(target.getPosition()));
    }


    /**
     * @implNote The destination is reached as soon as the distance to it is minimal.
     * @return True if the destination was reached, otherwise false.
     */
    private boolean atTarget() {
        if (this.position.distanceToSquared(target.getPosition()) < target.getHitBoxRadius() * target.getHitBoxRadius())
            return true;
        return false;
    }

    /**
     * Calculates the velocity of the airplane based on the target's position.
     */
    private Vector calculateVelocity() {
        return this.position.vectorTo(target.getPosition()).normalize().multiply(this.getSpeed());
    }
}
