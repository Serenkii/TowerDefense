package com.serenki.game.towers;

public class Barrier extends Tower{


    public Barrier() {
        super(50, 0, -0.5, null, null, "file:src/main/resources/com/serenki/art/towers/Rock.png");
    }

    /**
     * @implNote The barrier should not do anything, that why each frame nothing is executed.
     */
    @Override
    public void update() {
        //be
    }

    /**
     * @implNote The barrier should not do anything, that why each frame nothing is executed.
     */
    @Override
    public void shoot() {

    }
}
