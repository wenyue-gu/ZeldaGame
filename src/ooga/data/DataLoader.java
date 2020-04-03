package ooga;

import java.util.Map;
import javafx.scene.image.Image;

public class DataLoader implements ooga.data.DataLoaderAPI {

  @Override
  public int loadCell(int row, int col) {
    return 0;
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
  public Image loadImage(String keyword, String category) {
    return null;
  }

  @Override
  public Integer loadInteger(String keyword, String category) {
    return null;
  }
}
