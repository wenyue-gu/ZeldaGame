package ooga.data;

import com.google.gson.GsonBuilder;
import ooga.model.characters.MarioCharacter;
import ooga.model.characters.ZeldaCharacter;
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
    private List<Map<String, GameMapGraph>> gameMapList;
    private Map<String, Map<String, String>> imageMap;
    private List<MarioCharacter> marioCharacterList;
    private List<PlayerStatus> playerList;
    private List<ZeldaCharacter> zeldaCharacterList;
    private Map<String, Map<String, String>> textMap;
    private com.google.gson.Gson gson;

    private Map<Object, String> fieldToPathMap;

    public GameObjectConfiguration() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls(); //ensure gson storing null values.
        gsonBuilder.registerTypeAdapter(Cell.class, new InterfaceAdapter());
        gson = gsonBuilder.create();//3 lines above are the same as DataStorer

        gameInfoList = new ArrayList<>();
        gameMapList = new ArrayList<>();
        imageMap = new HashMap<>();
        marioCharacterList = new ArrayList<>();
        playerList = new ArrayList<>();
        zeldaCharacterList = new ArrayList<>();
        textMap = new HashMap<>();

        fieldToPathMap = new HashMap<>();

//        for (String i: fieldToPathMap.values()) {
//            loadFilesUnderDirectory(fieldToPathMap.get(i), i.getClass());
//        }
        loadFilesUnderDirectory(GameInfoPath, GameInfo.class);
        loadFilesUnderDirectory(GameMapPath, GameMapGraph.class);
        loadFilesUnderDirectory(imageMapPath, imageMap.getClass());
        loadFilesUnderDirectory(marioCharacterPath, MarioCharacter.class);
        loadFilesUnderDirectory(playerPath, PlayerStatus.class);
        loadFilesUnderDirectory(zeldaCharacterPath, ZeldaCharacter.class);
        loadFilesUnderDirectory(textPath, textMap.getClass());

        fieldToPathMap.put(gameInfoList, GameInfoPath);
        fieldToPathMap.put(gameMapList, GameMapPath);
        fieldToPathMap.put(imageMap, imageMapPath);
        fieldToPathMap.put(marioCharacterList, marioCharacterPath);
        fieldToPathMap.put(playerList, playerPath);
        fieldToPathMap.put(zeldaCharacterList, zeldaCharacterPath);
        fieldToPathMap.put(textMap, textPath);
    }


    /**
     * two problems regarding centralized storing:
     * 1. Need to store the relative information for storing ---- overly complicated
     * 2. Cache problem for data synchronization.
     */
    private void storeGameInfoList() {
        for (Map.Entry<Object, String> i : fieldToPathMap.entrySet()) {
            String path = i.getValue();
            String[] folderName = path.split("/");
            switch (folderName[1]) {
                case "GameInfo":
                    for (GameInfo j : gameInfoList) {
                        writeObjectTOJson(j, path+"Game" + j.getGameType() +"level"+j.getLevelNum()+".json");
                    }
                    break;
                case "GameMap":
                    for (Map<String, GameMapGraph> j : gameMapList) {
                        for (String k : j.keySet()) {
                            writeObjectTOJson(j.get(k), path + );
                        }
                    }
//                    Map<String, GameMapGraph> tempMap = new HashMap<>();
//                    tempMap.put(child.getName(), loadJson(myDirectoryPath + child.getName(), classType));
//                    gameMapList.add(tempMap);
                    break;
                case "Image":
//                    imageMap.put(child.getName(), loadJson(myDirectoryPath + child.getName(), classType));
                    break;
                case "MarioCharacter":
//                    marioCharacterList.add(loadJson(myDirectoryPath + child.getName(), classType));
                    break;
                case "Player":
//                    playerList.add(loadJson(myDirectoryPath + child.getName(), classType));
                    break;
                case "ZeldaCharacter":
//                    zeldaCharacterList.add(loadJson(myDirectoryPath + child.getName(), classType));
                    break;
                case "Text":
//                    textMap.put(child.getName(), loadJson(myDirectoryPath + child.getName(), classType));
                    break;
            }
        }
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
                        gameInfoList.add(loadJson(myDirectoryPath + child.getName(), classType));
                        break;
                    case "GameMap":
                        Map<String, GameMapGraph> tempMap = new HashMap<>();
                        tempMap.put(child.getName(), loadJson(myDirectoryPath + child.getName(), classType));
                        gameMapList.add(tempMap);
                        break;
                    case "Image":
                        imageMap.put(child.getName(), loadJson(myDirectoryPath + child.getName(), classType));
                        break;
                    case "MarioCharacter":
                        marioCharacterList.add(loadJson(myDirectoryPath + child.getName(), classType));
                        break;
                    case "Player":
                        playerList.add(loadJson(myDirectoryPath + child.getName(), classType));
                        break;
                    case "ZeldaCharacter":
                        zeldaCharacterList.add(loadJson(myDirectoryPath + child.getName(), classType));
                        break;
                    case "Text":
                        textMap.put(child.getName(), loadJson(myDirectoryPath + child.getName(), classType));
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

    public Map<String, Map<String, String>> getTextMap() {
        return textMap;
    }

    public void setTextMap(Map<String, Map<String, String>> textMap) {
        this.textMap = textMap;
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


    private void writeObjectTOJson(Object object, String filePath) {
        try {
            FileWriter Writer1 = new FileWriter(filePath);
            gson.toJson(object, Writer1);
            Writer1.flush();
            Writer1.close();
        } catch (IOException e) {
            e.printStackTrace();
            //throw appropriate Exceptions
        }
    }
}
