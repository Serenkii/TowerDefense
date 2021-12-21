package com.serenki.game.pathfinding;

import com.serenki.game.Battlefield;
import com.serenki.game.GridCoordinate;
import com.serenki.game.Vector;
import com.serenki.game.towers.TowersManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


// +--------------------+-------------------+--------------------+--------------------+-----+---------------------+--------------------+-----------------------+
// | Canvas             | Column 1          | 2                  | 3                  | ... | 14                  | 15                 | OUTSIDE OF THE CANVAS |
// +--------------------+-------------------+--------------------+--------------------+-----+---------------------+--------------------+-----------------------+
// | Row 1              | EDGE (0,0)        | EDGE (1,0)         | EDGE (2,0)         | ... | EDGE (13,0)         | EDGE (14,0)        | NOT_VISIBLE(15,0)     |
// +--------------------+-------------------+--------------------+--------------------+-----+---------------------+--------------------+-----------------------+
// | 2                  | EDGE (0,1)        | INNER_SQUARE(1,1)  | INNER_SQUARE(2,1)  | ... | INNER_SQUARE(13,1)  | EDGE (14,1)        | NOT_VISIBLE(15,1)     |
// +--------------------+-------------------+--------------------+--------------------+-----+---------------------+--------------------+-----------------------+
// | 3                  | EDGE (0,2)        | INNER_SQUARE(1,2)  | INNER_SQUARE(2,2)  | ... | INNER_SQUARE(13,2)  | EDGE (14,2)        | NOT_VISIBLE(15,2)     |
// +--------------------+-------------------+--------------------+--------------------+-----+---------------------+--------------------+-----------------------+
// | ...                | ...               | ...                | ...                | ... | ...                 | ...                | ...                   |
// +--------------------+-------------------+--------------------+--------------------+-----+---------------------+--------------------+-----------------------+
// | 14                 | EDGE (0,13)       | INNER_SQUARE(1,13) | INNER_SQUARE(2,13) | ... | INNER_SQUARE(13,13) | EDGE (14,13)       | NOT_VISIBLE(15,13)    |
// +--------------------+-------------------+--------------------+--------------------+-----+---------------------+--------------------+-----------------------+
// | 15                 | EDGE (0,14)       | EDGE (1,14)        | EDGE (2,14)        | ... | EDGE (13,14)        | EDGE (14,14)       | NOT_VISIBLE(15,14)    |
// +--------------------+-------------------+--------------------+--------------------+-----+---------------------+--------------------+-----------------------+
// | OUTSIDE THE CANVAS | NOT_VISIBLE(0,15) | NOT_VISIBLE(1,15)  | NOT_VISIBLE(2,15)  | ... | NOT_VISIBLE(13,15)  | NOT_VISIBLE(14,15) | NOT_VISIBLE(15,15)    |
// +--------------------+-------------------+--------------------+--------------------+-----+---------------------+--------------------+-----------------------+






public class Grid {

    private Node[][] grid;


    public Grid() {
        this.grid = new Node[Battlefield.GRID_SIZE][Battlefield.GRID_SIZE];
        initializeGrid();
    }

    private void initializeGrid() {
        initializeGridEdge();
        initializeInnerGrid();
    }
    private void initializeGridEdge() {
        for (int x = 0; x < Battlefield.GRID_SIZE + 1; x++) {
            this.grid[x][0] = new Node(new GridCoordinate(x, 0));       //Row 1
            this.grid[x][Battlefield.GRID_SIZE - 1] = new Node(new GridCoordinate(x, Battlefield.GRID_SIZE - 1));    //Row 15
            this.grid[x][Battlefield.GRID_SIZE] = new Node(new GridCoordinate(x, Battlefield.GRID_SIZE));               //Row "OUTSIDE THE CANVAS"
        }
        for (int y = 1; y < Battlefield.GRID_SIZE - 1; y++) {
            this.grid[0][y] = new Node((new GridCoordinate(0, y)));     //Column 1
            this.grid[Battlefield.GRID_SIZE - 1][y] = new Node(new GridCoordinate(Battlefield.GRID_SIZE - 1, y));    //Column 15
            this.grid[Battlefield.GRID_SIZE][y] = new Node(new GridCoordinate(Battlefield.GRID_SIZE, y));               //Column "OUTSIDE THE CANVAS"
        }
    }
    private void initializeInnerGrid() {
        for (int x = 0 + Battlefield.EDGE_SIZE; x < Battlefield.GRID_SIZE - Battlefield.EDGE_SIZE; x++) {
            for (int y = 0 + Battlefield.EDGE_SIZE; y < Battlefield.GRID_SIZE - Battlefield.EDGE_SIZE; y++) {
                this.grid[x][y] = new Node(true, new GridCoordinate(x, y));
            }
        }
    }

    /*
     *      ┌────┐
     *      │case│
     *      │4   │
     * ┌────┼────┼────┐
     * │case│THIS│case│
     * │2   │NODE│1   │
     * └────┼────┼────┘
     *      │case│
     *      │3   │
     *      └────┘
     */
    public ArrayList<Node> getNeighbours(Node node) {
        ArrayList<Node> neighbours = new ArrayList<Node>();

        int checkX = node.getGridX() + 1;   //case 1
        if (checkX <= Battlefield.GRID_SIZE)
            neighbours.add(grid[checkX][node.getGridY()]);

        checkX = node.getGridX() - 1;       //case 2
        if (checkX >= 0)
            neighbours.add(grid[checkX][node.getGridY()]);

        int checkY = node.getGridY() + 1;   //case 3
        if (checkY <= Battlefield.GRID_SIZE)
            neighbours.add(grid[node.getGridX()][checkY]);

        checkY = node.getGridY() - 1;       //case 4
        if (checkY >= 0)
            neighbours.add(grid[node.getGridX()][checkY]);

        return neighbours;
    }

    /**
     *
     * @param position
     * @return The node of this position is in. If the position is not in the node, it returns the closest node.
     */
    public Node getNodeFromPosition(@NotNull final Vector position) {
        int x, y;
        x = (int) position.getX();
        y = (int) position.getY();
        if (position.getX() < 0)
            x = 0;
        else if (position.getX() > Battlefield.GRID_SIZE + 1)        //+1 because node outside the canvas
            x = Battlefield.GRID_SIZE + 1;
        if (position.getY() < 0)
            y = 0;
        else if (position.getY() > Battlefield.GRID_SIZE + 1)        //+1 because node outside the canvas
            y = Battlefield.GRID_SIZE + 1;
        return grid[x][y];
    }

    /**
     * Updates the Grid for new towers, the grid does not update for somehow removed towers.
     * @param towersManager
     */
    public void updateForNewTowers(@NotNull final TowersManager towersManager) {
        for (int i = 0; i < towersManager.towerCount(); i++) {
            GridCoordinate coordinate = towersManager.get(i).getCoordinate();
            this.grid[coordinate.getX()][coordinate.getY()] = new Node(false, coordinate);
        }
    }

    /**
     * Updates the Grid completely, therefore also taking into account e.g. removed towers.
     * @param towersManager
     */
    public void updateWholeGrid(@NotNull final TowersManager towersManager) {
        this.initializeInnerGrid();
        this.updateForNewTowers(towersManager);
    }
}
