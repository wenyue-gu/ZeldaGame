package ooga.data;

import javafx.scene.image.Image;
import ooga.model.characters.UnchangableCharacter;
import ooga.model.gameElements.Weapon;
import ooga.model.interfaces.Inventory;
import ooga.model.interfaces.gameMap.Cell;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataStorer implements DataStorerAPI {
    public static final int numFilesPerLevel = 1;
    public static final int mapRowNum = 10;//from frontend
    public static final int mapColNum = 10;//from frontend
    public static final String mapKeyword =  "MapOfLevel";
    private Map<String, String> generalLevelFile;
    private com.google.gson.Gson gson;

    public DataStorer() {
        com.google.gson.GsonBuilder gsonBuilder = new com.google.gson.GsonBuilder();
        gsonBuilder.serializeNulls(); //ensure gson storing null values.
        gson = gsonBuilder.create();

        //delete in the future and move into property files.
        generalLevelFile = new HashMap<>();
        generalLevelFile.put("fileName", "level");
        generalLevelFile.put("map", "MapOfLevel");
    }
    @Override
    public void StoreText(String text, String keyword, String category) {

    }


    @Override
    public void StoreCharacter(int ID, UnchangableCharacter character) {

    }

    @Override
    public void storeWeapons(int ID, Weapon weapon) {

    }

    @Override
    public void StoreInventory(Inventory inventory) {

    }

    /**
     * Slow if serialize every time?
     * @param image
     * @param ImageID
     * @param category
     */
    @Override
    public void storeImage(Image image, int ImageID, String category) {
        Map<Integer, Image> imageMap = new HashMap<>();
        imageMap.put(ImageID, image);
        writeObjectTOJson(imageMap, category);
    }

    @Override
    public void storeInteger(String keyword, String category, int value) {

    }

    @Override
    public void updateParamSetting(Map<String, Integer> playerPreference, int category) {

    }

    @Override
    public void storeMap(Collection<Cell> map, int level) {
        if (map.size() > mapRowNum * mapColNum) {
            System.out.println("map stored didn't fit in dimension");
            //throw an exception
        }
        GameMapGraph mapGraph = new GameMapGraph(level, mapRowNum, mapColNum);
        int i = 0;
        for (Cell cell: map) {
            mapGraph.setElement(i/mapColNum, i%mapRowNum, cell.getState(), cell.getImage() );
            i++;
        }
        writeObjectTOJson(mapGraph, mapKeyword + String.valueOf(level)+".json");

    }

    private void writeObjectTOJson(Object object, String fileName) {
        try {
            FileWriter Writer1 = new FileWriter(fileName);
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
