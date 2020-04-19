package ooga.data;

import com.google.gson.GsonBuilder;
import ooga.model.characters.MarioCharacter;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.ImageCategory;
import ooga.model.enums.PlayerPara;
import ooga.model.enums.TextCategory;
import ooga.model.interfaces.gameMap.Cell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this is the man, the object storing EVERY piece of info!
 */
public class GameObjectConfiguration {

  public static String GameInfoPath = "data/GameInfo/";
  public static String GameMapPath = "data/GameMap/";
  public static String imageMapPath = "data/Image/";
  public static String marioCharacterPath = "data/MarioCharacter/";
  public static String playerPath = "data/Player/";
  public static String zeldaCharacterPath = "data/ZeldaCharacter/";
  public static String textPath = "data/Text/";

  private List<GameInfo> gameInfoList;
  private Map<String, GameMapGraph> gameMapList;
  private Map<String, Map<String, String>> imageMap;
  private List<MarioCharacter> marioCharacterList;
  private List<PlayerStatus> playerList;
  private List<ZeldaCharacter> zeldaCharacterList;
  private Map<String, Map<String, String>> textMap; //Map<Category, Map<Keyword, Text>>
  private com.google.gson.Gson gsonLoad;
  private com.google.gson.Gson gsonStore;
  private int currentPlayerID;
  private int currentGameID;

  private Map<Object, String> fieldToPathMap;
  private static GameObjectConfiguration gameObjectConfiguration;

  /**
   * Static 'instance' method
   */
  public static GameObjectConfiguration getInstance() throws DataLoadingException {
    if (gameObjectConfiguration == null) {
      gameObjectConfiguration = new GameObjectConfiguration();
    }
    return gameObjectConfiguration;
  }

  private GameObjectConfiguration() throws DataLoadingException {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.serializeNulls(); //ensure gson storing null values.
    gsonStore = gsonBuilder.create();
    gsonBuilder.registerTypeAdapter(Cell.class, new InterfaceAdapter());
    gsonLoad = gsonBuilder.create();//3 lines above are the same as DataStorer

    gameInfoList = new ArrayList<>();
    gameMapList = new HashMap<>();
    imageMap = new HashMap<>();
    marioCharacterList = new ArrayList<>();
    playerList = new ArrayList<>();
    zeldaCharacterList = new ArrayList<>();
    textMap = new HashMap<>();

    fieldToPathMap = new HashMap<>();

//        for (String i: fieldToPathMap.values()) {
//            loadFilesUnderDirectory(fieldToPathMap.get(i), i.getClass());
//        }

    loadGameEverything();
    fieldToPathMap.put(gameInfoList, GameInfoPath);
    fieldToPathMap.put(gameMapList, GameMapPath);
    fieldToPathMap.put(imageMap, imageMapPath);
    fieldToPathMap.put(marioCharacterList, marioCharacterPath);
    fieldToPathMap.put(playerList, playerPath);
    fieldToPathMap.put(zeldaCharacterList, zeldaCharacterPath);
    fieldToPathMap.put(textMap, textPath);

  }

  private void loadGameEverything() throws DataLoadingException {
    loadFilesUnderDirectory(GameInfoPath, GameInfo.class);
    loadFilesUnderDirectory(GameMapPath, GameMapGraph.class);
    loadFilesUnderDirectory(imageMapPath, imageMap.getClass());
    loadFilesUnderDirectory(marioCharacterPath, MarioCharacter.class);
    loadFilesUnderDirectory(playerPath, PlayerStatus.class);
    loadFilesUnderDirectory(zeldaCharacterPath, ZeldaCharacter.class);
    loadFilesUnderDirectory(textPath, textMap.getClass());
  }

  private void loadFilesUnderDirectory(String myDirectoryPath, Class<?> classType)
      throws DataLoadingException {
    File dir = new File(myDirectoryPath);
    File[] directoryListing = dir.listFiles();
    if (directoryListing != null) {
      for (File child : directoryListing) {
        // Do something with child
        //TODO: possibly with a Reflection
        String[] realFileName = myDirectoryPath.split("/");
        switch (realFileName[1]) {
          case "GameInfo":
            gameInfoList.add(loadJson(myDirectoryPath + child.getName(), classType));
            break;
          case "GameMap":
            gameMapList
                .put(child.getName(),
                    loadJson(myDirectoryPath + child.getName(), classType));
            break;
          case "Image":
            imageMap.put(child.getName(),
                loadJson(myDirectoryPath + child.getName(), classType));
            break;
          case "MarioCharacter":
            marioCharacterList
                .add(loadJson(myDirectoryPath + child.getName(), classType));
            break;
          case "Player":
            playerList.add(loadJson(myDirectoryPath + child.getName(), classType));
            break;
          case "ZeldaCharacter":
            zeldaCharacterList
                .add(loadJson(myDirectoryPath + child.getName(), classType));
            break;
          case "Text":
            textMap.put(child.getName(),
                loadJson(myDirectoryPath + child.getName(), classType));
            break;
          default:
            throw new DataLoadingException(
                "Cannot recognize configuration file name " + child.getPath()
                    + " or file is not in the correct format.");

        }
      }
    } else {
      // Handle the case where dir is not really a directory.
      // Checking dir.isDirectory() above would not be sufficient
      // to avoid race conditions with another process that deletes
      // directories.
    }
  }


  /**
   * two problems regarding centralized storing: 1. Need to store the relative information for
   * storing ---- overly complicated 2. Cache problem for data synchronization.
   */
  public void storeGameEverything() {
    for (Map.Entry<Object, String> i : fieldToPathMap.entrySet()) {
      String path = i.getValue();
      String[] folderName = path.split("/");
      switch (folderName[1]) {
        case "GameInfo":
          for (GameInfo j : gameInfoList) {
            writeObjectTOJson(j,
                path + "Game" + j.getGameType() + "level" + j.getLevelNum() + ".json");
          }
          break;
        case "GameMap":
          for (String j : gameMapList.keySet()) {
            writeObjectTOJson(gameMapList.get(j), path + j);
          }

          break;
        case "Image":
          for (String j : imageMap.keySet()) {
            writeObjectTOJson(imageMap.get(j), path + j);
          }
          break;
        case "MarioCharacter":
          System.out.println("MarioCharacter storing is not supported");
//                    marioCharacterList.add(loadJson(myDirectoryPath + child.getName(), classType));
          break;
        case "Player":
          for (PlayerStatus j : playerList) {
            writeObjectTOJson(j, path + "player" + j.getPlayerID() + ".json");
          }
          break;
        case "ZeldaCharacter":
          for (ZeldaCharacter j : zeldaCharacterList) {
            writeObjectTOJson(j, path + "CharacterData" + j.getId() + ".json");
          }
          break;
        case "Text":
          for (String j : textMap.keySet()) {
            writeObjectTOJson(textMap.get(j), path + j);
          }
          break;
      }
    }
  }


  public List<GameInfo> getGameInfoList() {
    return gameInfoList;
  }

  public void setGameInfoList(List<GameInfo> gameInfoList) {
    this.gameInfoList = gameInfoList;
  }

  public Map<String, GameMapGraph> getGameMapList() {
    return gameMapList;
  }

  public void setGameMapList(Map<String, GameMapGraph> gameMapList) {
    this.gameMapList = gameMapList;
  }

  public Map<String, Map<String, String>> getImageMap() {
    return imageMap;
  }

  public void setImageMap(Map<String, Map<String, String>> imageMap) {
    this.imageMap = imageMap;
  }

  public List<MarioCharacter> getMarioCharacterList() {
    return marioCharacterList;
  }

  public void setMarioCharacterList(List<MarioCharacter> marioCharacterList) {
    this.marioCharacterList = marioCharacterList;
  }

  public List<PlayerStatus> getPlayerList() {
    return playerList;
  }

  public void setPlayerList(List<PlayerStatus> playerList) {
    this.playerList = playerList;
  }

  public List<ZeldaCharacter> getZeldaCharacterList() {
    return zeldaCharacterList;
  }

  public void setZeldaCharacterList(List<ZeldaCharacter> zeldaCharacterList) {
    this.zeldaCharacterList = zeldaCharacterList;
  }

  public Map<String, Map<String, String>> getTextMap() {
    return textMap;
  }

  public void setTextMap(Map<String, Map<String, String>> textMap) {
    this.textMap = textMap;
  }

  public <clazz> clazz loadJson(String fileName, Class clazz) {
    try {
      Reader reader = Files.newBufferedReader(Paths.get(fileName));
      return (clazz) gsonLoad.fromJson(reader, clazz);
    } catch (IOException e) {
      System.out.println("file at " + fileName + "hasn't been created.");
    }
    return null;
  }


  private void writeObjectTOJson(Object object, String filePath) {
    try {
      FileWriter Writer1 = new FileWriter(filePath);
      gsonStore.toJson(object, Writer1);
      Writer1.flush();
      Writer1.close();
    } catch (IOException e) {
      e.printStackTrace();
      //throw appropriate Exceptions
    }
  }

  public void setImageMap(Map<String, String> newImageMap, ImageCategory imageCategory) {
    String newKey = imageCategory.toString();
    if (imageMap.containsKey(newKey)) {
      imageMap.replace(newKey, newImageMap);
    } else {
      imageMap.put(newKey, newImageMap);
    }

  }

  public int getCurrentPlayerID() {
    return currentPlayerID;
  }


  public int getCurrentGameID() {
    return currentGameID;
  }

  public void setCurrentPlayerAndGameID(int currentGameID, int currentPlayerID) {
    this.currentPlayerID = currentPlayerID;
    this.currentGameID = currentGameID;
    PlayerStatus tempPlayer = getPlayerWithID(currentGameID);
    if (tempPlayer == null) {
      tempPlayer = new PlayerStatus(currentGameID, currentPlayerID);
    }
    tempPlayer.setPlayerParam(PlayerPara.Game, currentGameID);
    setPlayerWithID(currentGameID, tempPlayer);
  }

  /**
   * retrieve player with specific ID. If Player doesn't exist, returns null. Please handle all the
   * situations where player doesn't exist by the caller.
   *
   * @param playerID
   * @return
   */
  public PlayerStatus getPlayerWithID(int playerID) {
    for (PlayerStatus i : playerList) {
      if (i.getPlayerID() == playerID) {
        return i;
      }
    }
    return null;
  }

  public PlayerStatus getCurrentPlayer() {
      return getPlayerWithID(currentPlayerID);
  }

  /**
   * this method handles adding players.
   *
   * @param playerID
   * @param player
   */
  public void setPlayerWithID(int playerID, PlayerStatus player) {
    List<PlayerStatus> tempList = new ArrayList<>();
    for (PlayerStatus i : gameObjectConfiguration.getPlayerList()) {
      if (i.getPlayerID() != playerID) {
        tempList.add(i);
      }
    }
    tempList.add(player);
    playerList = tempList;
  }

  public void setTextMap(String text, String keyword, TextCategory category) {
    Map<String, String> tempTextMap = textMap.get(category);
    if (tempTextMap == null) {
      System.out.println("category not found (330 config)");
    }
    if (tempTextMap.keySet().contains(keyword)) {
      tempTextMap.replace(keyword, text);
    } else {
      tempTextMap.put(keyword, text);
    }
  }

  public GameInfo getCurrentGameInfo() {
    return getGameInfo(currentGameID, getCurrentPlayer().getPlayerParam(PlayerPara.CURRENT_LEVEL));
  }

  public GameInfo getGameInfo(int level, int id) {
    for (GameInfo i : gameInfoList) {
      if (i.getGameType() == id && i.getLevelNum() == level) {
        return i;
      }
    }
    return null;
  }
}
