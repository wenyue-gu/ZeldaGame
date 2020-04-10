package ooga.view.engine.utils;

public class TextMapReader {
  private String path;
  private String separator = ",";
  private int mapWidth;
  private int mapHeight;
  private int titlePixel;
  private int paletteWidth;
  private int paletteHeight;
  private int[][] mapCells;
  private int numWalls;
  private int[] walls;

  public TextMapReader(String path){

    String mapText = FileUtils.loadAsString(path, "");
    String[] mapContent = mapText.split(separator);

    int idx = 0;

    mapHeight = Integer.parseInt(mapContent[idx++]);
    mapWidth = Integer.parseInt(mapContent[idx++]);
    titlePixel = Integer.parseInt(mapContent[idx++]);
    numWalls = Integer.parseInt(mapContent[idx++]);
    paletteHeight = Integer.parseInt(mapContent[idx++]);
    paletteWidth = Integer.parseInt(mapContent[idx++]);

    walls = new int[numWalls];

    for (int i=0; i<numWalls; i++){
      int pixel_x = Integer.parseInt(mapContent[idx++]);
      int pixel_y = Integer.parseInt(mapContent[idx++]);
      walls[i] = pixel_x*paletteWidth + pixel_y - 1;
    }

    mapCells = new int[mapHeight][mapWidth];
    int x=0, y=0;

    for (int i=idx; i<mapContent.length; i++){
      mapCells[x][y++] = Integer.parseInt(mapContent[i]);
      x = y == mapWidth ? x+1:x;
      y = y == mapWidth ? 0:y;
    }

  }

  public int getMapWidth() {
    return mapWidth;
  }

  public int getMapHeight(){
    return mapHeight;
  }

  public int getTitlePixel(){
    return titlePixel;
  }

  public int getMapCell(int x, int y){
    return mapCells[x][y];
  }

  public int getPaletteWidth(){return paletteWidth;}

  public int getPaletteHeight(){return paletteHeight;}

  public boolean isMapCellWalkable(int x, int y){
    for (int wall : walls) {
      if (wall == mapCells[x][y]) {
        return false;
      }
    }
    return true;
  }
}
