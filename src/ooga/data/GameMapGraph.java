package ooga.data;

import ooga.model.interfaces.gameMap.Cell;
import ooga.model.map.GameCell;

public class GameMapGraph {
    private Cell[][] cellArray;
    private int level;
    private int gameID;
    public GameMapGraph() {

    }
    public GameMapGraph(int level, int ID, int row, int column) {
        this.level = level;
        this.gameID = ID;
        cellArray = new GameCell[row][column];
        for (int i = 0; i< row; i++) {
            for (int j = 0; j < column; j++) {
                cellArray[i][j] = new GameCell(i*(column-1) + j);
            }
        }
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
//    private Map<Cell, List<Cell>> adjacencyList;
//    private int numOfEdge;
//    private Cell startingCell;
//
//    public GameMapGraph() {
//        //assume starting cell for now
//        startingCell = new GameCell(0, 0);
//        this.adjacencyList = new HashMap<>();
//        addVertex(startingCell);
//    }
//    private int numNode() {
//        return adjacencyList.size();
//    }
//
//    public Cell getStartingCell() {
//        return startingCell;
//    }
//
//    public void addEdge(Cell v, Cell w) {
//        if (!hasVertex(v)) {
//            addVertex(v);
//        }
//        if (!hasVertex(w)) {
//            addVertex(w);
//        }
//        if (!hasEdge(v, w)) {
//            numOfEdge++;
//        }
//        adjacencyList.get(v).add(w);
//        adjacencyList.get(w).add(v);
//    }
//
//    /**
//     * Adds vertex v to this graph (if it is not already a vertex).
//     *
//     * @param  v the vertex
//     */
//    public void addVertex(Cell v) {
//        if (!hasVertex(v)) {
//            adjacencyList.put(v, new ArrayList<>());
//        }
//    }
//
//
//    public List getNeighbours(Cell v) {
//        isVertex(v);
//        return adjacencyList.get(v);
//    }
//
//
//    public boolean hasVertex(Cell v) {
//        return adjacencyList.keySet().contains(v);
//    }
//
//
//    public boolean hasEdge(Cell v, Cell w) {
//        isVertex(v);
//        isVertex(w);
//        return adjacencyList.get(v).contains(w);
//    }
//
//    private void isVertex(Cell v) {
//        try{
//            if (!adjacencyList.keySet().contains(v)) {
//                throw new Exception("Cell " + /*v.getName()*/ "CellName" + "is not vertex");
//            }
//        } catch (Exception e) {
//            /*terminate the program or try to throw the exception up*/
//        }
//    }


}
