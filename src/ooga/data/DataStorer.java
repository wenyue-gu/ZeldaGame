package ooga.data;

import javafx.scene.input.KeyCode;
import ooga.model.characters.UnchangableCharacter;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.ImageCategory;
import ooga.model.gameElements.WeaponBase;
import ooga.model.interfaces.Inventory;
import ooga.model.interfaces.gameMap.Cell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//import ooga.model.gameElements.Weapon;

public class  DataStorer implements DataStorerAPI {
    public static final int numFilesPerLevel = 1;
    public static final int subMapRowNum = 22;//from frontend
    public static final int subMapColNum = 34;//from frontend
    public static final String mapKeyword =  "MapOfLevel";
    public static final String characterKeyword =  "CharacterData";
    public static final String gameMapAddressPrefix = "data/GameMap/";
    private Map<String, String> generalLevelFile;
    private com.google.gson.Gson gson;
    private DataLoader dataLoader; //for just tentative measure.

    public DataStorer() {
        com.google.gson.GsonBuilder gsonBuilder = new com.google.gson.GsonBuilder();
        gsonBuilder.serializeNulls(); //ensure gson storing null values.
        gson = gsonBuilder.create();
        dataLoader = new DataLoader();

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
    public void initializePlayerStatus(int playerID) {
        PlayerStatus playerStatus = new PlayerStatus(playerID);
        String filePath = "data/Player/player" + playerID +".json";
        if (fileExist(filePath)) {
            //todo: throw an error.
            System.out.println("player has already been initialized");
        } else {
            writeObjectTOJson(playerStatus, filePath);
        }
    }

    @Override
    public void StoreText(String text, String keyword, String category) {

    }

    @Override
    public void storeCharacter(int ID, UnchangableCharacter character) {

    }

    @Override
    public void storeWeapons(int ID, WeaponBase weapon) {
        
    }


    //@Override
    public void storeCharacter(int characterID, ZeldaCharacter character) {
        writeObjectTOJson(character, "data/ZeldaCharacter/" + characterKeyword + characterID + ".json");
    }



    @Override
    public void StoreInventory(Inventory inventory) {

    }

    @Override
    public void storeKeyCode(Map<KeyCode, String> keyCodeMap, int playerID) {
        PlayerStatus player;
        String FilePath = "data/Player/player" + playerID + ".json";
        if (fileExist(FilePath)) {
            player = dataLoader.loadJson(FilePath, PlayerStatus.class);
        } else {
            player = new PlayerStatus(playerID);
        }
        player.setKeyCodeMap(keyCodeMap);
        writeObjectTOJson(player, FilePath);
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
        Map<String, String> imageMap = new HashMap<>();
        String filePath = "data/Image/" + imageCategory.toString();
        if (fileExist(filePath)) {
            imageMap = dataLoader.loadJson(filePath, imageMap.getClass());
        }
        boolean a = imageMap.containsKey(String.valueOf(ImageID));
        if (!imageMap.containsKey(String.valueOf(ImageID))) {
            imageMap.put(String.valueOf(ImageID), imagePath);
        } else {
            imageMap.replace(String.valueOf(ImageID), imagePath);
        }
        writeObjectTOJson(imageMap, filePath);
    }

    @Override
    public void storeInteger(String keyword, String category, int value) {

    }

    @Override
    public void updateParamSetting(Map<String, Integer> playerPreference, int category) {

    }

    @Override
    public void storeSubMap(Collection<Cell> map, int level, int subMapID) {
        if (map.size() != subMapRowNum * subMapColNum) {
            System.out.println("map stored didn't fit in dimension");
            //throw an exception
        }
        GameMapGraph mapGraph = new GameMapGraph(level, subMapID, subMapRowNum, subMapColNum);
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
        GameInfo currentGameInfo = dataLoader.loadGameInfo(level, 1);
         String subMapFileName = currentGameInfo.getSubMapInfo().get(level).get(subMapID);
        writeObjectTOJson(mapGraph, gameMapAddressPrefix + subMapFileName);

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
