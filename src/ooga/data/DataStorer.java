package ooga.data;

import javafx.scene.input.KeyCode;
import ooga.model.characters.UnchangableCharacter;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.ImageCategory;
import ooga.model.enums.PlayerPara;
import ooga.model.enums.TextCategory;
import ooga.model.gameElements.WeaponBase;
import ooga.model.interfaces.Inventory;
import ooga.model.interfaces.gameMap.Cell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static ooga.data.DataLoader.SubMapPerMap;

//import ooga.model.gameElements.Weapon;

public class DataStorer implements DataStorerAPI {
    public static final int numFilesPerLevel = 1;
    public static final int subMapRowNum = 22;//from frontend
    public static final int subMapColNum = 34;//from frontend
    public static final String mapKeyword =  "MapOfLevel";
    public static final String characterKeyword =  "CharacterData";
    public static final String gameMapAddressPrefix = "data/GameMap/";
    private Map<String, String> generalLevelFile;
    private com.google.gson.Gson gson;
    private DataLoader dataLoader; //for just tentative measure.
    private GameObjectConfiguration gameObjectConfiguration;

    public DataStorer() {
        com.google.gson.GsonBuilder gsonBuilder = new com.google.gson.GsonBuilder();
        gsonBuilder.serializeNulls(); //ensure gson storing null values.
        gson = gsonBuilder.create();
        dataLoader = new DataLoader();
        gameObjectConfiguration = dataLoader.getGameObjectConfiguration();

        //delete in the future and move into property files.
        generalLevelFile = new HashMap<>();
        generalLevelFile.put("fileName", "level");
        generalLevelFile.put("map", "MapOfLevel");
    }

    @Override
    public int getGameType() {
        return 0;
    }

    @Override
    public void setGame(int GameID) {

    }

    //todo: test not done
    @Override
    public void StoreText(String text, String keyword, TextCategory category) {
        gameObjectConfiguration.setTextMap(text, keyword, category);
    }

    @Override
    public void storeCharacter(int ID, UnchangableCharacter character) {

    }

    @Override
    public void storeWeapons(int ID, WeaponBase weapon) {
        
    }


    //@Override
    public void storeCharacter(int characterID, ZeldaCharacter character) {

        character.setId(characterID);
        List<ZeldaCharacter> tempCharList = new ArrayList<>();
        for (ZeldaCharacter i : gameObjectConfiguration.getZeldaCharacterList()) {
            if (i.getId() != characterID) {
                tempCharList.add(i);
            }
        }
        tempCharList.add(character);
        gameObjectConfiguration.setZeldaCharacterList(tempCharList);
//        writeObjectTOJson(character, "data/ZeldaCharacter/" + characterKeyword + characterID + ".json");
    }



    @Override
    public void StoreInventory(Inventory inventory) {

    }
    @Override
    public void storePlayerParamToCurrentPlayer(PlayerPara para, int value) {
        int playerID = gameObjectConfiguration.getCurrentPlayer();
        setPlayerParam(para, value, playerID);
    }
    @Override
    public void setPlayerParam(PlayerPara para, int value, int playerID) {
        PlayerStatus tempPlayer = gameObjectConfiguration.getPlayerWithID(playerID);
        if (tempPlayer == null) {
            System.out.println("Player not created(storer 114)");
            //todo: throw errors.
        }
        tempPlayer.setPlayerParam(para, value);
    }
    @Override
    public void addPlayer(int playerID) {
        gameObjectConfiguration.setPlayerWithID(playerID, new PlayerStatus(playerID));
    }
    @Override
    public void storeKeyCode(Map<KeyCode, String> keyCodeMap, int playerID) {
//        boolean playerExist = false;
//        List<PlayerStatus> tempList = new ArrayList<>();
//        for (PlayerStatus i : gameObjectConfiguration.getPlayerList()) {
//            if (i.getPlayerID() != playerID) {
//                tempList.add(i);
//            } else {
//                playerExist = true;
//                i.setKeyCodeMap(keyCodeMap);
//                tempList.add(i);
//            }
//
//        }
//        if (!playerExist) {
//            PlayerStatus tempPlayer = new PlayerStatus(playerID);
//            tempPlayer.setKeyCodeMap(keyCodeMap);
//            tempList.add(tempPlayer);
//        }
//        gameObjectConfiguration.setPlayerList(tempList);
        PlayerStatus tempPlayer = gameObjectConfiguration.getPlayerWithID(playerID);
        if (tempPlayer != null) {
            tempPlayer.setKeyCodeMap(keyCodeMap);
            gameObjectConfiguration.setPlayerWithID(playerID, tempPlayer);
        } else {
            System.out.println("player not found in Storer 144");
            //todo: throw playerNotFound error

        }

    }

    private boolean fileExist(String filePath) {
        File tmpDir = new File(filePath);
        return tmpDir.exists();
    }

    /**
     * Slow if serialize every time?
     * @param imagePath
     * @param ImageID
     * @param
     */
    @Override
    //todo: finish testing
    public void storeImage(String imagePath, int ImageID, ImageCategory imageCategory) {
        String imageIDString = String.valueOf(ImageID);
        Map<String, String> imageMap = gameObjectConfiguration.getImageMap().get(imageCategory);

        if (imageMap != null) {
            if (!imageMap.containsKey(imageIDString)) {
                imageMap.put(imageIDString, imagePath);
            } else {
                imageMap.replace(imageIDString, imagePath);
            }
        } else {
            imageMap = new HashMap<>();
            imageMap.put(imageIDString, imagePath);
        }
        gameObjectConfiguration.setImageMap(imageMap, imageCategory);

//        writeObjectTOJson(imageMap, filePath);
    }

    /**
     * level = current level; subMapID = next available ID;
     * @param map
     * @param level
     */
    @Override
    //todo: testing is not done.
    public void storeSubMapWithSubmapIDRandom(Collection<Cell> map, int level) {
        int subMapID = nextAvailableID(level);
        storeSubMapForCurrentGame(map, level, subMapID);
    }
    @Override
    public void storeSubMapForCurrentGame(Collection<Cell> map, int level, int subMapID) {
        storeSubMap( map, level, subMapID, gameObjectConfiguration.getCurrentGameID());
    }
    @Override
    public void storeSubMap(Collection<Cell> map, int level, int subMapID, int gameID) {
        if (map.size() != subMapRowNum * subMapColNum) {
            System.out.println("map stored didn't fit in dimension");
            //throw an exception
        }

        GameMapGraph mapGraph = new GameMapGraph(level, subMapID, subMapRowNum, subMapColNum, gameID);
        int i = 0;
        for (Cell cell: map) {
            mapGraph.setElement(i/ subMapColNum, i%subMapRowNum, cell);
            i++;
        }
        /**
         * How storer knows the name of the game map file being stored is challenging.
         * Storer and loader are therefore not independent.
         *
         */
        GameInfo currentGameInfo = dataLoader.loadGameInfo(level, gameObjectConfiguration.getCurrentGameID());
        String subMapFileName = currentGameInfo.getSubMapInfo().get(level).get(subMapID) + ".json";
        Map<String, GameMapGraph> currentGameMapList =  gameObjectConfiguration.getGameMapList();
        if (currentGameMapList.keySet().contains(subMapFileName)) {
            currentGameMapList.replace(subMapFileName, mapGraph);
        } else  {
            currentGameMapList.put(subMapFileName, mapGraph);
        }
        gameObjectConfiguration.setGameMapList(currentGameMapList);
//         writeObjectTOJson(mapGraph, gameMapAddressPrefix + subMapFileName);

    }

    private int nextAvailableID(int level) {
        Map<String, GameMapGraph> currentGameMapList =  gameObjectConfiguration.getGameMapList();
        int i = 0;
        boolean flag = false;
        while (i < SubMapPerMap) {
            for (GameMapGraph j :currentGameMapList.values()) {
                if (j.getSubMapID() == i) {
                    flag = true;
                }
            }
            if (flag) {
                i++;
                flag = false;
            } else {
                break;
            }
        }
        if (i >= SubMapPerMap) {
            System.out.println("not more empty submap to add to! Please use storeSubMap(Collection<Cell> map, int level, int subMapID) method");
            //todo: throw errors.
        }

        return i;

    }

    private void writeObjectTOJson(Object object, String filePath) {
        try {
            FileWriter Writer1 = new FileWriter(filePath);
            String jsonString2 = gson.toJson(object);
            gson.toJson(object, Writer1);
            Writer1.flush();
            Writer1.close();
        } catch (IOException e) {
            e.printStackTrace();
            //throw appropriate Exceptions
        }

    }
    /**
     * It will create a Json file holding a menu telling the program where to get data of that specific level.
     * @param levelNumber the number of the level we add
     */
    @Override
    public void addLevel(int levelNumber) {

        for (int i = 0; i < numFilesPerLevel; i++) {
//            String jsonString = gson.toJson(person);
        }
    }



}
