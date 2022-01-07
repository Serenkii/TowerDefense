package com.serenki.game.pathfinding;

import com.serenki.game.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class Pathfinding {

    private Grid grid;

    public Pathfinding(@NotNull final Grid grid) {
        this.grid = grid;
    }

    public Grid getGrid() {
        return grid;
    }

    public Stack<Node> findPath(@NotNull final Vector startPosition, @NotNull final Vector targetPosition) {
        Node startNode = grid.getNodeFromPosition(startPosition);
        Node targetNode = grid.getNodeFromPosition(targetPosition);

        ArrayList<Node> openSet = new ArrayList<Node>();
        HashSet<Node> closedSet = new HashSet<Node>();
        openSet.add(startNode);

        while (openSet.size() > 0) {
            Node currentNode = openSet.get(0);
            for (int i = 1; i < openSet.size(); i++) {
                if (openSet.get(i).fCost() < currentNode.fCost() || (openSet.get(i).fCost() == currentNode.fCost() && openSet.get(i).hCost() < currentNode.hCost())) {
                    currentNode = openSet.get(i);
                }
            }

            openSet.remove(currentNode);
            closedSet.add(currentNode);

            if (currentNode == targetNode) {
                return retracePath(startNode, targetNode);
            }

            for (Node neighbour : grid.getNeighbours(currentNode)) {
                if (!neighbour.isWalkable() || closedSet.contains(neighbour)) {
                    continue;
                }

                int newMovementCostToNeighbour = currentNode.gCost() + getDistance(currentNode, neighbour);
                if (newMovementCostToNeighbour < neighbour.gCost() || !openSet.contains(neighbour)) {
                    neighbour.setGCost(newMovementCostToNeighbour);
                    neighbour.setHCost(getDistance(neighbour, targetNode));
                    neighbour.setParent(currentNode);

                    if (!openSet.contains(neighbour))
                        openSet.add(neighbour);

                }
            }
        }

        return null;
    }

    private Stack<Node> retracePath(Node startNode, Node endNode) {
        Stack<Node> path = new Stack<Node>();
        Node currentNode = endNode;

        while (currentNode != startNode) {          //!= instead of !equals() works because reference is compared
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }

        return path;
    }

    /**
     * Calculates the distance between two nodes, not being able to go in diagonal lines.
     * @param node1 The first node.
     * @param node2 The second node.
     * @return The distance between these two nodes, rounded to an integer.
     */
    private int getDistance(Node node1, Node node2) {
        return Math.abs(node1.getGridX() - node2.getGridX()) + Math.abs(node1.getGridY() - node2.getGridY());
        //More complex version for diagonal moves https://youtu.be/mZfyt03LDH4?t=960
    }

}

//https://youtu.be/mZfyt03LDH4