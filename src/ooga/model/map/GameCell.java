package ooga.model.map;

import ooga.model.interfaces.gameMap.Cell;

import java.util.ArrayList;

public class  GameCell implements Cell {
    private ArrayList<Cell> neighbours;
    private int ID;
    private int cellState;
    private int imageIndex;
    private boolean walkable;

    public GameCell() {

    }
    public GameCell(int ID) {
        this.ID = ID;
    }
    @Override
    public void setState(int state) {
        cellState = state;
    }

    @Override
    public int getState() {
        return cellState;
    }

    @Override
    public int getImage() {
        return imageIndex;
    }

    @Override
    public void setImage(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    @Override
    public int getUniqueID() {
        return this.ID;
    }

    @Override
    public int setUniqueID() {
        return this.ID;
    }

    @Override
    public boolean isMapCellWalkable() {
        return walkable;
    }

    @Override
    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }
}
