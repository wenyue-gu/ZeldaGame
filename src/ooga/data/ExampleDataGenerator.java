package ooga.data;

import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.CharacterProperty;
import ooga.model.enums.Direction;

public class ExampleDataGenerator {
    public static void main(String[] args) {
        DataLoader a = new DataLoader();
        DataStorer b = new DataStorer();
//        Gson gson = new Gson();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        /**
         * the following is testing the Game map loading
         */
//        LinkedList<Cell> cellLinkedList = new LinkedList<>();
//        for (int i = 0; i < 20; i++) {
//            Cell newCell = new GameCell(1);
//            newCell.setState(1);
//            newCell.setImage(9);
//            cellLinkedList.add(newCell);
//        }
//
//
//        a.loadCell(1,1,1, 1);
//        b.storeMap(cellLinkedList, 1);
        /**
         * the following is generating character loading
         *
         */
        ZeldaCharacter ZC = new ZeldaCharacter(1,2,3,4);
        ZC.setFiringDirection(Direction.EAST);
        b.storeCharacter(2, ZC);
        a.loadCharacter(2, CharacterProperty.ATTACK);
        /**
         * the following is testing image map loading/storing (incomplete)
         */

//        constructExampleGameMap(gameMap);
//        try {
//            mapper.writeValue(new File("example Data2.json"), gameMap);
//        } catch (Exception e) {
//
//        }

//        Type type = new TypeToken<Map<String, String>>(){}.getType();
//        Map<String, String> myMap = gson.fromJson("{'k1':'apple','k2':'orange'}", type);
//        System.out.println(myMap);
//        ObjectMapper mapper = new ObjectMapper();
//
//        GameMapGraph gameMap = new GameMapGraph(10, 10);
//        constructExampleGameMap(gameMap);
//        try {
//            mapper.writeValue(new File("example Data2.json"), gameMap);
//            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(gameMap);
//            System.out.println(jsonString);
//
//            FileWriter Writer1 = new FileWriter("example Data");
//            String jsonString2 = gson.toJson(gameMap);
//            gson.toJson(gameMap, Writer1);
//            Writer1.flush();
//            Writer1.close();
//            FileWriter Writer2 = new FileWriter("cell Data");
//            String jsonString2 = gson.toJson(gameMap.getStartingCell());
//            Writer2.write(jsonString2);
//            gson.toJson(gameMap, Writer2);
//            Writer2.flush();
//            Writer2.close();
//            System.out.println(jsonString2);

//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
    private static void constructExampleGameMap(GameMapGraph gameMapGraph) {
//        Cell previous = gameMapGraph.getStartingCell();
//        Cell current;
//        for (int i = 0; i < 10; i++) {
//            current = new GameCell(i, i);
//            gameMapGraph.addVertex(new GameCell(i, i));
//            gameMapGraph.addEdge(current, previous);
//            previous = current;
//        }
//        new GameMapGraph(10 , 10);
    }
//
//    public static void main(String[] args) {
//        ObjectMapper mapper = new ObjectMapper();
//        GameMapGraph gameMap = new GameMapGraph();
//
//        try {
//            mapper.writeValue(new File("person.json"), gameMap);
//            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
//            System.out.println(jsonString);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public GameMapGraph storeLevel(double levelNumber) {
//
//    }

}
