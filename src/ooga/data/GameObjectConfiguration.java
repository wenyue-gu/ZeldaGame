package ooga.data;

import ooga.model.characters.MarioCharacter;
import ooga.model.characters.ZeldaCharacter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this is the man, the object storing EVERY piece of info!
 */
public class  GameObjectConfiguration {
    public static String GameInfoPath = "data/GameInfo/";
    public static String GameMapPath = "data/GameMap/";
    public static String imageMapPath = "data/Image/";
    public static String marioCharacterPath = "data/MarioCharacter/";
    public static String playerPath = "data/Player/";
    public static String zeldaCharacterPath = "data/ZeldaCharacter/";

    private List<GameInfo> gameInfoList;
    private List<Map<String, GameMapGraph>> gameMapList;
    private Map<String, Map<String, String>> imageMap;
    private List<MarioCharacter> marioCharacterList;
    private List<PlayerStatus> playerList;
    private List<ZeldaCharacter> zeldaCharacterList;
    private DataLoader dataLoader;

    public GameObjectConfiguration() {
        gameInfoList = new ArrayList<>();
        gameMapList = new ArrayList<>();
        imageMap = new HashMap<>();
        marioCharacterList = new ArrayList<>();
        playerList = new ArrayList<>();
        zeldaCharacterList = new ArrayList<>();
        dataLoader = new DataLoader();
        String a = imageMap.getClass().toString();
        loadFilesUnderDirectory(GameInfoPath, GameInfo.class);
        loadFilesUnderDirectory(GameMapPath, GameMapGraph.class);
        loadFilesUnderDirectory(imageMapPath, imageMap.getClass());
        loadFilesUnderDirectory(marioCharacterPath, MarioCharacter.class);
        loadFilesUnderDirectory(playerPath, PlayerStatus.class);
        loadFilesUnderDirectory(zeldaCharacterPath, ZeldaCharacter.class);
    }
    private void appendGameInfoList(String fileName, Class classtype) {
        gameInfoList.add(dataLoader.loadJson(fileName, classtype));
    }
    private void loadFilesUnderDirectory(String myDirectoryPath, Class classType) {
        File dir = new File(myDirectoryPath);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                // Do something with child
                String[] realFileName = myDirectoryPath.split("/");
                switch (realFileName[1]) {
                    case "GameInfo":
                        gameInfoList.add(dataLoader.loadJson(myDirectoryPath + child.getName(), classType));
                        break;
                    case "GameMap":
                        Map<String, GameMapGraph> tempMap = new HashMap<>();
                        tempMap.put(child.getName(), dataLoader.loadJson(myDirectoryPath + child.getName(), classType));
                        gameMapList.add(tempMap);
                        break;
                    case "Image":
                        imageMap.put(child.getName(), dataLoader.loadJson(myDirectoryPath + child.getName(), classType));
                        break;
                    case "MarioCharacter":
                        marioCharacterList.add(dataLoader.loadJson(myDirectoryPath + child.getName(), classType));
                        break;
                    case "Player":
                        playerList.add(dataLoader.loadJson(myDirectoryPath + child.getName(), classType));
                        break;
                    case "ZeldaCharacter":
                        zeldaCharacterList.add(dataLoader.loadJson(myDirectoryPath + child.getName(), classType));
                        break;
                }
            }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }
    }
    public List<GameInfo> getGameInfoList() {
        return gameInfoList;
    }

    public void setGameInfoList(List<GameInfo> gameInfoList) {
        this.gameInfoList = gameInfoList;
    }

    public List<Map<String, GameMapGraph>> getGameMapList() {
        return gameMapList;
    }

    public void setGameMapList(List<Map<String, GameMapGraph>> gameMapList) {
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
}
