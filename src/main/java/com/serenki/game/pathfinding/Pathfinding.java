package com.serenki.game.pathfinding;

import com.serenki.game.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Pathfinding {

    private Grid grid;

    public Pathfinding(@NotNull final Grid grid) {
        this.grid = grid;
    }

    public void findPath(@NotNull final Vector startPosition, @NotNull final Vector targetPosition) {
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
                retracePath(startNode, targetNode);
                return;
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
    }

    public void retracePath(Node startNode, Node endNode) {
        ArrayList<Node> path = new ArrayList<Node>();
        Node currentNode = endNode;

        while (currentNode != startNode) {          //!= instead of !equals() should work because they should be at the same position in memory
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }

        Collections.reverse(path);
    }

    public int getDistance(Node node1, Node node2) {
        return Math.abs(node1.getGridX() - node2.getGridX()) + Math.abs(node1.getGridY() - node2.getGridY());
        //More complex version for diagonal moves https://youtu.be/mZfyt03LDH4?t=960
    }

}

//https://youtu.be/mZfyt03LDH4