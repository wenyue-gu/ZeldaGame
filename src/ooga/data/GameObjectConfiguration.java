package ooga.data;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ooga.model.characters.MarioCharacter;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.ImageCategory;
import ooga.model.enums.PlayerPara;
import ooga.model.enums.TextCategory;
import ooga.model.interfaces.gameMap.Cell;
import ooga.view.engine.graphics.animation.Animation2D;
import ooga.view.engine.io.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ooga.game.GameMain.HEIGHT;
import static ooga.game.GameMain.WIDTH;

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
  public static String animationPath = "data/Animation2D/";

  private List<GameInfo> gameInfoList;
  private Map<String, GameMapGraph> gameMapList;
  private Map<String, Map<String, String>> imageMap;
  private List<MarioCharacter> marioCharacterList;
  private List<PlayerStatus> playerList;
  private List<ZeldaCharacter> zeldaCharacterList;
  private Map<String, Map<String, String>> textMap; //Map<Category, Map<Keyword, Text>>
  private com.google.gson.Gson gsonLoad;
  private com.google.gson.Gson gsonStore;


  private Map<String, Animation2D> meleeRobotAnimations;
  private Map<String, Map<String, Animation2D>> animationMap;
  private List<Integer> currentPlayersID;
  private int currentPlayerID = 1;
  private int currentGameID = 1;


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
    gsonBuilder.registerTypeAdapter(Cell.class, new InterfaceAdapter("ooga.model.map.GameCell"));
    gsonLoad = gsonBuilder.create();//3 lines above are the same as DataStorer

    gameInfoList = new ArrayList<>();
    gameMapList = new HashMap<>();
    imageMap = new HashMap<>();
    marioCharacterList = new ArrayList<>();
    playerList = new ArrayList<>();
    zeldaCharacterList = new ArrayList<>();
    textMap = new HashMap<>();
    meleeRobotAnimations = new HashMap<>(); //delete after multiple agents occur
    animationMap = new HashMap<>();
    fieldToPathMap = new HashMap<>();



    loadGameEverything();
    fieldToPathMap.put(gameInfoList, GameInfoPath);
    fieldToPathMap.put(gameMapList, GameMapPath);
    fieldToPathMap.put(imageMap, imageMapPath);
    fieldToPathMap.put(marioCharacterList, marioCharacterPath);
    fieldToPathMap.put(playerList, playerPath);
    fieldToPathMap.put(zeldaCharacterList, zeldaCharacterPath);
    fieldToPathMap.put(textMap, textPath);
    fieldToPathMap.put(meleeRobotAnimations, animationPath);
    fieldToPathMap.put(animationMap, animationPath);  //multiple agents draft (1,4)
  }

  private void loadGameEverything() throws DataLoadingException {
    loadFilesUnderDirectory(GameInfoPath, GameInfo.class);
    loadFilesUnderDirectory(GameMapPath, GameMapGraph.class);
    loadFilesUnderDirectory(imageMapPath, imageMap.getClass());
    loadFilesUnderDirectory(marioCharacterPath, MarioCharacter.class);
    loadFilesUnderDirectory(playerPath, PlayerStatus.class);
    loadFilesUnderDirectory(zeldaCharacterPath, ZeldaCharacter.class);
    loadFilesUnderDirectory(textPath, textMap.getClass());
    loadFilesUnderDirectory(animationPath, meleeRobotAnimations.getClass());
    loadFilesUnderDirectory(animationPath, animationMap.getClass()); //multiple agents draft (2,4)
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
          case "Animation2D":
            Window window = new Window(WIDTH, HEIGHT, "Game");
            window.create();

            Type type = new TypeToken<Map<String, Animation2D>>(){}.getType();
            //delete after multiple agents occur
            meleeRobotAnimations = loadJson(animationPath + child.getName(), type);
            createTextureToAnimation(meleeRobotAnimations);

            
            //change to support multiple agents (3,4)
            Map<String, Animation2D> tempAgent = loadJson(animationPath + child.getName(), type);
            createTextureToAnimation(tempAgent);
            animationMap.put(child.getName(), tempAgent);
            window.destroy();
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

  private void createTextureToAnimation(Map<String, Animation2D> meleeRobotAnimations) {
    for (Animation2D i : meleeRobotAnimations.values()) {
      for (int j = 0; j < i.getFrameAmount(); j++) {
        i.getAnimatedFrames(j).createTexture();
      }
    }
  }



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
//          System.out.println("MarioCharacter storing is not supported");
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
        case "Animation2D":
          //delete after multiple agents
          writeObjectTOJson(meleeRobotAnimations, path + "MeleeRobotAnimations" + ".json");

          //use after using mulitple agents (4,4)
          for (String j : animationMap.keySet()) {
            writeObjectTOJson(animationMap.get(j), path + j);
          }
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
  public <clazz> clazz loadJson(String fileName, Type clazz) {
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

  public List<Integer> getCurrentPlayersID() {
    return currentPlayersID;
  }

  public int getCurrentPlayerID() {
    return currentPlayersID.get(0);
  }

  public int getCurrentGameID() {
    return currentGameID;
  }

  public void setCurrentPlayerAndGameID(int currentGameID, List<Integer> currentPlayersID) {
    this.currentPlayersID = currentPlayersID;
    this.currentGameID = currentGameID;
    List<PlayerStatus> tempPlayers = getPlayersWithID(currentPlayersID);
  }

  /**
   * retrieve player with specific ID. If Player doesn't exist, returns null. Please handle all the
   * situations where player doesn't exist by the caller.
   *
   * @param playerID
   * @return
   */
  public List<PlayerStatus> getPlayersWithID(List<Integer> playerID) {
    List<PlayerStatus> playerStatuses = new ArrayList<>();
    for (Integer id: playerID) {
      boolean contains = false;
      for (PlayerStatus i : playerList) {
        if (i.getPlayerID() == id) {
          playerStatuses.add(i);
          contains = true;
        }
      }

      if (!contains) {
        PlayerStatus temp = new PlayerStatus(currentGameID, id);
        temp.setPlayerParam(PlayerPara.Game, currentGameID);
        playerStatuses.add(temp);
        setPlayerWithID(id, temp);
      }
    }
    return playerStatuses;
  }

  public PlayerStatus getPlayerWithID(int playerID) {
    for (PlayerStatus i : playerList) {
      if (i.getPlayerID() == playerID) {
        return i;
      }
    }

      return null;
  }

  public List<PlayerStatus> getCurrentPlayers() {
      return getPlayersWithID(currentPlayersID);
  }

  public PlayerStatus getCurrentPlayer() {
    return getCurrentPlayers().get(0);
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

  public Map<String, Animation2D> getMeleeRobotAnimations() {
    return meleeRobotAnimations;
  }

  public void setMeleeRobotAnimations(Map<String, Animation2D> meleeRobot) {
    meleeRobotAnimations = meleeRobot;
  }

  public void setAnimationMap(String agent, Map<String, Animation2D> agentAnimation) {
    if (animationMap.containsKey(agent)) {
      animationMap.replace(agent, agentAnimation);
    } else {
      animationMap.put(agent, agentAnimation);
    }
  }

  public Map<String, Animation2D> getSpecificAgentAnimation(String agent) {
    return animationMap.get(agent);
  }
}