package com.serenki.game.projectiles;

import com.serenki.game.Game;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

//In theory, I could make one superclass with generics or something, but imo this is easier

public class ProjectilesManager {

    private Game game;

    private ArrayList<Projectile> projectiles;

    public ProjectilesManager(@NotNull final Game game) {
        projectiles = new ArrayList<>();
        this.game = game;
    }

    /**
     * Add a projectile to the ProjectilesManager and therefore the game (Battlefield).
     * @param projectile The projectile that should be added.
     */
    public void add(@NotNull Projectile projectile) {
        this.projectiles.add(projectile);
    }

    /**
     * Updates and renders every projectile.
     * @implSpec Should be called once per frame.
     */
    public void renderAndUpdate() {
        projectiles.removeIf(projectile -> projectile.hasReachedTarget());
        for (Projectile projectile : projectiles) {
            projectile.update();
            projectile.render(this.game.getGraphicsContext());
        }
    }

}
