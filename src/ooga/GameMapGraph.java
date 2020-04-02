package ooga;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMapGraph {
    private Map<Grid, List<Grid>> adjacencyList;
    private int numOfEdge;
    private Grid startingGrid;

    public GameMapGraph() {
        //startingGrid = ...
        this.adjacencyList = new HashMap<>();
        addVertex(startingGrid);
    }
    private int numNode() {
        return adjacencyList.size();
    }

    public Grid getStartingGrid() {
        return startingGrid;
    }

    public void addEdge(Grid v, Grid w) {
        if (!hasVertex(v)) {
            addVertex(v);
        }
        if (!hasVertex(w)) {
            addVertex(w);
        }
        if (!hasEdge(v, w)) {
            numOfEdge++;
        }
        adjacencyList.get(v).add(w);
        adjacencyList.get(w).add(v);
    }

    /**
     * Adds vertex v to this graph (if it is not already a vertex).
     *
     * @param  v the vertex
     */
    public void addVertex(Grid v) {
        if (!hasVertex(v)) {
            adjacencyList.put(v, new ArrayList<>());
        }
    }


    public List getNeighbours(Grid v) {
        isVertex(v);
        return adjacencyList.get(v);
    }


    public boolean hasVertex(Grid v) {
        return adjacencyList.keySet().contains(v);
    }


    public boolean hasEdge(Grid v, Grid w) {
        isVertex(v);
        isVertex(w);
        return adjacencyList.get(v).contains(w);
    }

    private void isVertex(Grid v) {
        try{
            if (!adjacencyList.keySet().contains(v)) {
                throw new Exception("Grid " + /*v.getName()*/ "gridName" + "is not vertex");
            }
        } catch (Exception e) {
            /*terminate the program or try to throw the exception up*/
        }
    }


}
