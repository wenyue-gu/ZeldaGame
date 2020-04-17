package ooga.data;

import ooga.model.enums.Direction;
import ooga.model.interfaces.gameMap.Cell;
import ooga.model.map.GameCell;

import java.util.HashMap;
import java.util.Map;

public class GameMapGraph {
    private Cell[][] cellArray;
    private int level;
    private int gameID;
    private int subMapID;
    private Map<Direction, Integer> directionMap;
    public GameMapGraph() {
        directionMap = new HashMap<>();
        for (Direction i : Direction.values()) {
            directionMap.put(i, null);
        }
    }
    public GameMapGraph(int level, int subMapID, int row, int column, int GameID) {
        this();
        this.level = level;
        this.subMapID = subMapID;
        this.gameID = GameID;
        cellArray = new GameCell[row][column];
        for (int i = 0; i< row; i++) {
            for (int j = 0; j < column; j++) {
                cellArray[i][j] = new GameCell(i*(column-1) + j);
            }
        }
    }
    public GameMapGraph(int level, int ID, int row, int column, int GameID, Map<Direction, Integer> newDirections) {
        this(level, ID, row, column, GameID);
        addDirectionMap(newDirections);
    }
    public void setElement(int row, int column, Cell cell) {
        cellArray[row][column] = cell;
    }

    public Cell getElement(int row, int column) {
        return cellArray[row][column];
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setDirectionMap(Map<Direction, Integer> directionMap) {
        this.directionMap = directionMap;
    }
    public void addDirectionMap(Map<Direction, Integer> newDirections) {
        for (Direction i : newDirections.keySet()) {
            directionMap.replace(i, newDirections.get(i));
        }
    }
    public Map<Direction, Integer> getDirectionMap() {
        return directionMap;
    }

    public int getSubMapID() {
        return subMapID;
    }

    public void setSubMapID(int subMapID) {
        this.subMapID = subMapID;
    }
}
