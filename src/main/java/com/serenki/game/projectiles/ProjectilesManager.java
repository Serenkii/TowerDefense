package com.serenki.game.projectiles;

import javafx.scene.canvas.GraphicsContext;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

//In theory, I could make one superclass with generics or something, but imo this is easier

public class ProjectilesManager {

    private GraphicsContext graphicsContext;

    private ArrayList<Projectile> projectiles;

    public ProjectilesManager(GraphicsContext graphicsContext) {
        projectiles = new ArrayList<>();
        this.graphicsContext = graphicsContext;
    }

    public void add(@NotNull Projectile projectile) {
        this.projectiles.add(projectile);
    }

    public Projectile get(int i) {
        return projectiles.get(i);
    }

    /**
     * Updates and renders every projectile
     */
    public void renderAndUpdate() {
        for (Projectile projectile : projectiles) {
            projectile.update();
            projectile.render(this.graphicsContext);
        }
    }

}
