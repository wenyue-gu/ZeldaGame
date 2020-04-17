package ooga.data;

import com.google.gson.Gson;
import ooga.model.interfaces.gameMap.Cell;
import ooga.model.map.GameCell;
import ooga.view.engine.utils.cyberpunk2d.Text2DMapReader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static ooga.data.DataLoader.SubMapPerMap;

/**
 * this class will not be part of the final submission. It's purely used for generating data for the example file for the presentation only.
 */
public class ExampleDataGenerator {

    static Gson gson;
    public static void main(String[] args) {
        DataLoader a = new DataLoader();
        DataStorer b = new DataStorer();
        com.google.gson.GsonBuilder gsonBuilder = new com.google.gson.GsonBuilder();
        gsonBuilder.serializeNulls(); //ensure gson storing null values.
        gson = gsonBuilder.create();
        //generateLevelData();
        generateTheMapForFirstSprint();
    }
    private static void generateLevelData() {
        int levelNum = 1;
        int gameType = 1;
        List<Integer> npcID= new ArrayList<>();
        npcID.add(1);
        List<Integer> playerID = new ArrayList<>();
        playerID.add(1);
        Map<Integer, String> subMapID = new HashMap<>();
        Map<Integer, Map<Integer, String>> a = new HashMap<>();
        for (int i = 0; i  < SubMapPerMap; i++) {

            subMapID.put(i, UUID.randomUUID().toString());//file name is a random unique ID.
        }
        a.put(levelNum, subMapID);
        GameInfo gameInfo1 = new GameInfo(npcID, playerID, levelNum, a, gameType);
        writeObjectTOJson(gameInfo1, "data/GameInfo/Game"+gameType+"level"+levelNum+".json");
    }
    private static void writeObjectTOJson(Object object, String filePath) {
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
    protected static void generateTheMapForFirstSprint() {
        String mapPath = "/view/textures/2d/cyberpunk/map/map.txt";
        Text2DMapReader textMapReader = new Text2DMapReader(mapPath);
        int subMapHeight = textMapReader.getMapHeight();
        int subMapWidth = textMapReader.getMapWidth();
        List<Cell> cellList = new ArrayList<>();
        for (int i = 0; i < subMapHeight; i++) {
            for (int j = 0; j < subMapWidth; j++) {
                Cell tempCell = new GameCell();
                int imageID = textMapReader.getMapCell(i, j);
                int a = textMapReader.getMapCell(5, 6);
                tempCell.setImage(imageID);
                tempCell.setWalkable(textMapReader.isMapCellWalkable(i, j));
                cellList.add(tempCell);
            }
        }

        //store data
        DataStorer b = new DataStorer();
        System.out.println(cellList.size());
        b.storeSubMap(cellList, 1, 0, 1);
        DataLoader c = new DataLoader();
//        GameObjectConfiguration d = c.getGameObjectConfiguration();
        c.getGameObjectConfiguration().storeGameEverything();
    }
////        Gson gson = new Gson();
////        ObjectMapper mapper = new ObjectMapper();
////        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//
//        /**
//         * the following is testing the Game map loading
//         */
////        LinkedList<Cell> cellLinkedList = new LinkedList<>();
////        for (int i = 0; i < 20; i++) {
////            Cell newCell = new GameCell(1);
////            newCell.setState(1);
////            newCell.setImage(9);
////            cellLinkedList.add(newCell);
////        }
////
////
////        a.loadCell(1,1,1, 1);
////        b.storeMap(cellLinkedList, 1);
//        /**
//         * the following is generating character loading
//         *
//         */
//        ZeldaCharacter ZC = new ZeldaCharacter(1,2,3,4);
//        ZC.setFiringDirection(Direction.EAST);
//        b.storeCharacter(2, ZC);
//        a.loadCharacter(2, CharacterProperty.ATTACK);
//        /**
//         *
//         */
//        /**
//         * the following is testing image map loading/storing (incomplete)
//         */
//
////        constructExampleGameMap(gameMap);
////        try {
////            mapper.writeValue(new File("example Data2.json"), gameMap);
////        } catch (Exception e) {
////
////        }
//
////        Type type = new TypeToken<Map<String, String>>(){}.getType();
////        Map<String, String> myMap = gson.fromJson("{'k1':'apple','k2':'orange'}", type);
////        System.out.println(myMap);
////        ObjectMapper mapper = new ObjectMapper();
////
////        GameMapGraph gameMap = new GameMapGraph(10, 10);
////        constructExampleGameMap(gameMap);
////        try {
////            mapper.writeValue(new File("example Data2.json"), gameMap);
////            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(gameMap);
////            System.out.println(jsonString);
////
////            FileWriter Writer1 = new FileWriter("example Data");
////            String jsonString2 = gson.toJson(gameMap);
////            gson.toJson(gameMap, Writer1);
////            Writer1.flush();
////            Writer1.close();
////            FileWriter Writer2 = new FileWriter("cell Data");
////            String jsonString2 = gson.toJson(gameMap.getStartingCell());
////            Writer2.write(jsonString2);
////            gson.toJson(gameMap, Writer2);
////            Writer2.flush();
////            Writer2.close();
////            System.out.println(jsonString2);
//
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//    }
//    private static void constructExampleGameMap(GameMapGraph gameMapGraph) {
////        Cell previous = gameMapGraph.getStartingCell();
////        Cell current;
////        for (int i = 0; i < 10; i++) {
////            current = new GameCell(i, i);
////            gameMapGraph.addVertex(new GameCell(i, i));
////            gameMapGraph.addEdge(current, previous);
////            previous = current;
////        }
////        new GameMapGraph(10 , 10);
//    }
////
////    public static void main(String[] args) {
////        ObjectMapper mapper = new ObjectMapper();
////        GameMapGraph gameMap = new GameMapGraph();
////
////        try {
////            mapper.writeValue(new File("person.json"), gameMap);
////            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
////            System.out.println(jsonString);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//
////    public GameMapGraph storeLevel(double levelNumber) {
////
////    }

}
