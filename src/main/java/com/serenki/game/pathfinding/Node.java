package com.serenki.game.pathfinding;

import com.serenki.game.GridCoordinate;
import com.serenki.game.Vector;

public class Node {

    private int edgeWeight;
    private boolean walkable;
    private Vector position;

    private int gridX;
    private int gridY;

    private int gCost;
    private int hCost;
    private Node parent;

    /**
     * A node for an inner square of the Battlefield.
     * @param walkable
     * @param position
     */
    public Node(boolean walkable, GridCoordinate position) {
        this.walkable = walkable;
        this.position = new Vector(position.getX() + 0.5, position.getY() + 0.5);
        this.gridX = position.getX();
        this.gridY = position.getY();
        this.edgeWeight = 0;
    }

    /**
     * A node for the edge of the Battlefield.
     * @param position
     */
    public Node(GridCoordinate position) {
        this.walkable = true;
        this.edgeWeight = 500;
        this.position = new Vector(position.getX() + 0.5, position.getY() + 0.5);
        this.gridX = position.getX();
        this.gridY = position.getY();
    }

    public void setGCost(int gCost) {
        this.gCost = gCost;
    }

    public void setHCost(int hCost) {
        this.hCost = hCost;
    }

    public int fCost() {
        return gCost() + hCost();
    }

    public int gCost() {
        return gCost + edgeWeight;
    }

    public int hCost() {
        return hCost + edgeWeight;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Vector getPosition() {
        return position;
    }
}
