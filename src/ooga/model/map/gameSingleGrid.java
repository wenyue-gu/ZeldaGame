package ooga.model.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ooga.data.DataLoaderAPI;
import ooga.model.enums.Direction;
import ooga.model.interfaces.gameMap.SingleGrid;

public class gameSingleGrid implements SingleGrid {
  private int width;
  private int length;

  private List<List<gameCell>> grid;
  private DataLoaderAPI loader;

  public gameSingleGrid(DataLoaderAPI loader) {
    grid = new ArrayList<>();
    this.loader = loader;
  }

  @Override
  public void setSize(int length, int width) {
    this.length = length;
    this.width = width;
  }

  @Override
  public int getLength() {
    return length;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public void loadGrid(int id, int level) {
    for (int r = 0; r < length; r++) {
      List<gameCell> currentRow = new ArrayList<>();
      for (int c = 0; c < width; c++) {
        currentRow.add((gameCell) loader.loadCell(r, c, id, level));
      }
      grid.add(currentRow);
    }
  }

  @Override
  public int getCellState(int row, int col) {
    return grid.get(row).get(col).getState();
  }

  @Override
  public void setCellState(int row, int col, int state) {
    grid.get(row).get(col).setState(state);
  }

  // TODO: implement this if needed
  @Override
  public boolean isGateCell(int row, int col, Direction direction) {
    return false;
  }

  @Override
  public List<List<?>> getGrid() {
    return Collections.unmodifiableList(grid);
  }
}
