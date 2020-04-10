package ooga.view.engine.utils;

public class TextMapReader {
  private String path;
  private String separator = ",";
  private int mapWidth;
  private int mapHeight;
  private int titlePixel;
  private int[][] mapCells;

  public TextMapReader(String path){

    String mapText = FileUtils.loadAsString(path, "");
    String mapContent[] = mapText.split(separator);

    mapHeight = Integer.parseInt(mapContent[0]);
    mapWidth = Integer.parseInt(mapContent[1]);
    titlePixel = Integer.parseInt(mapContent[2]);

    mapCells = new int[mapHeight][mapWidth];
    int x=0, y=0;

    for (int i=3; i<mapContent.length; i++){
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
}
