package ooga.model.interfaces.map;

public interface Grid {

  void setSize(int length, int width);

  int getCellState(int row, int col);

  void setCellState(int row, int col, int state);


}
