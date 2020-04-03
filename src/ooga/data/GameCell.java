package ooga.data;

import ooga.model.interfaces.gameMap.Cell;

import java.util.ArrayList;

public class GameCell implements Cell {
    private ArrayList<Cell> neighbours;
    private int ID;
    private int cellState;
    private int imageIndex;
    GameCell(int state, int imageIndex) {
        setState(state);
        setImage(imageIndex);
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

}
