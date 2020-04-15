package ooga.data;

import javafx.scene.input.KeyCode;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.CharacterProperty;
import ooga.model.enums.Direction;
import ooga.model.enums.ImageCategory;
import ooga.model.interfaces.gameMap.Cell;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ooga.data.DataStorer.characterKeyword;
import static ooga.model.map.GameGridInMap.ID_NOT_DEFINED;

public class DataLoader implements ooga.data.DataLoaderAPI {
  public static final String DATA_Directory = "data/";
  public static final String Image_Keyword = "image";
  public static final String Text_Keyword = "text";
  public static final String GAME_Keyword = "Game";
  public static final String LEVEL_Keyword = "level";
  public static final String JsonPostFix = ".json";
  public static final int gameID = 1;//the belonging of Game ID is a problem. Where should it get from?
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
  public int loadGameParam(GamePara para) {
    GameInfo gameInfo = loadGameParamPrep();
    int level = gameInfo.getLevelNum();
    switch (para) {
      case GRID_NUM:
        return gameInfo.getSubMapInfo().get(level).size();
      case LEVEL_NUM:
        return level;
      case NPC_NUM:
        return gameInfo.getNPC_ID().size();
      case GAME_TYPE:
        return gameInfo.getGameType();
      case PLAYER_NUM:
        return gameInfo.getPlayer_ID().size();
      case INIT_POS_X:
        return gameInfo.getInitialPosition()[0];
      case INIT_POS_Y:
        return gameInfo.getInitialPosition()[1];
    }
    return 0;
  }
  private GameInfo loadGameParamPrep() {
    GameInfo gameInfo = loadGameParamPrep();
    PlayerStatus currentPlayerStatus = loadJson("data/Player/player1.json", PlayerStatus.class);
    int level = currentPlayerStatus.getLevel();
    return loadGameInfo(level,gameID);
  }
  @Override
  public List<Direction> loadAvailableDirection(GamePara para) {
    GameInfo gameInfo = loadGameParamPrep();
    return gameInfo.getAvailableAttackDirections();
  }

  @Override
  public void setGame(int GameID) {

  }

  @Override
  public int getGameType() {
    return 0;
  }

  @Override
  public Cell loadCell(int row, int col, int subMapID, int level) {
    return loadMap(level, subMapID).getElement(row, col);
  }

  @Override
  public int getNextSubMapID(Direction direction, int current) {
    return ID_NOT_DEFINED;
  }
  //todo: upgrade it from whole map to submap.
  private GameMapGraph loadMap(int level, int subMapID) {

    GameMapGraph map = new GameMapGraph();

    GameInfo gameInfo = loadGameInfo(level, gameID);

    try {
      //two readings: 1. read level files 2. read subMap file.
      //Map<String, String> levelInfoMap = loadLevelInfo(GameName, level);
      //loadJson(GAME_Keyword + String.valueOf(level), );
      map = loadJson("data/GameMap/"+ gameInfo.getSubMapInfo().get(level).get(subMapID), map.getClass());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }

  public GameInfo loadGameInfo(int level, int gameID) {
    return ( loadJson("data/GameInfo/"+ GAME_Keyword + gameID + LEVEL_Keyword + level + JsonPostFix , GameInfo.class));
  }
  @Override
  public String loadText(String keyword, String category) {
    Map<String, String> text = new HashMap<>();
    text = loadJson(Text_Keyword + category, text.getClass());
    return text.get(keyword);
  }

  @Override
  public int loadCharacter(int ID, CharacterProperty property) {
    ZeldaCharacter zeldaCharacter = new ZeldaCharacter(1,2);

    zeldaCharacter =  loadJson("data/ZeldaCharacter/" + characterKeyword + ID + ".json", zeldaCharacter.getClass());
    try {
      Method methodcall = zeldaCharacter.getClass().getDeclaredMethod("get" + property.toString().substring(0,1)+ property.toString().substring(1));
      int a = (int) methodcall.invoke(zeldaCharacter);
      return (int) methodcall.invoke(zeldaCharacter);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
    }


    return 0;
  }

  @Override
  public int loadWeapon(int ID, int property) {
    return 0;
  }

  @Override
  public int currentLevel() {
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
  public Map<KeyCode, String> loadKeyCode(int playerID) {
    String filePath = "data/Player/player" + playerID + ".json";
    Map<KeyCode, String> tempMap = new HashMap<>();
    PlayerStatus player =  loadJson(filePath, PlayerStatus.class);
    return player.getKeyCodeMap();
  }

  /**
   * in Json, <int, String> always returns <Stirng, String>
   * @param imageID
   * @param category
   * @return
   */
  @Override
  public String loadImagePath(int imageID, ImageCategory category) {
    Map<String, String> imagePath = new HashMap<>();
    imagePath = loadJson("data/Image/" + category.toString(), imagePath.getClass());
    return imagePath.get(String.valueOf(imageID));
  }

  public <clazz> clazz loadJson(String fileName, Class clazz) {
    try {
      Reader reader = Files.newBufferedReader(Paths.get(fileName));
      return (clazz) gson.fromJson(reader, clazz);
    } catch (IOException e) {
      System.out.println("file at " + fileName + "hasn't been created.");
    }
    return null;
  }

  //todo: finish the method
  public String loadSetting(int property) {
    return "";
  }
  @Override
  public Integer loadInteger(String keyword, String category) {
    return null;
  }


}
