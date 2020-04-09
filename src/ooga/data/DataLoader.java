package ooga.data;

import javafx.scene.image.Image;
import ooga.model.interfaces.gameMap.Cell;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static ooga.data.DataStorer.mapKeyword;

public class DataLoader implements ooga.data.DataLoaderAPI {
  private int currentLevel;
  private com.google.gson.Gson gson;
  public DataLoader() {
    com.google.gson.GsonBuilder gsonBuilder = new com.google.gson.GsonBuilder();
    gsonBuilder.serializeNulls(); //ensure gson storing null values.
    gsonBuilder.registerTypeAdapter(Cell.class, new InterfaceAdapter());
    gson = gsonBuilder.create();//3 lines above are the same as DataStorer
  }
  public void setCurrentLevel(int currentLevel) {
    this.currentLevel = currentLevel;
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  @Override
  public Cell loadCell(int row, int col, int level) {
    return loadMap(level).getElement(row, col);
  }

  private GameMapGraph loadMap(int level) {
    // create a reader
    GameMapGraph map = null;
    try {
      Reader reader = Files.newBufferedReader(Paths.get(mapKeyword+ String.valueOf(level) + ".json"));
      map = gson.fromJson(reader,GameMapGraph.class);
      System.out.println(map);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }
  @Override
  public String loadText(String keyword, String category) {
    return null;
  }

  @Override
  public int loadCharacter(int ID, int property) {
    return 0;
  }

  @Override
  public int loadWeapon(int ID, int property) {
    return 0;
  }

  @Override
  public Object loadInventoryElement(int ID) {
    return null;
  }

  @Override
  public Map<String, Integer> loadInternalStorage(String category) {
    return null;
  }

  @Override
  public Image loadImage(int imageID, String category) {
    return null;
  }
  private Object loadMap(Class clazz, String fileName) {
    Object map = null;
    try {

      Reader reader = Files.newBufferedReader(Paths.get(fileName + ".json"));
      map = gson.fromJson(reader,clazz);
      System.out.println(map);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;

  }


  @Override
  public Integer loadInteger(String keyword, String category) {
    return null;
  }


}
