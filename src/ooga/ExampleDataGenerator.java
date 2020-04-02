package ooga;

import com.google.gson.Gson;
import ooga.model.interfaces.gameMap.Cell;

import java.io.FileWriter;

public class ExampleDataGenerator {
    public static void main(String[] args) {
        Gson gson = new Gson();
        GameMapGraph gameMap = new GameMapGraph();
        constructExampleGameMap(gameMap);
        try {
            FileWriter Writer = new FileWriter("example Data");
            String jsonString = gson.toJson(gameMap);
            gson.toJson(gameMap, Writer);
            Writer.flush();
            Writer.close();
            System.out.println(jsonString);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static void constructExampleGameMap(GameMapGraph gameMapGraph) {
        Cell previous = gameMapGraph.getStartingCell();
        Cell current;
        for (int i = 0; i < 10; i++) {
            current = new GameCell(i, i);
            gameMapGraph.addVertex(new GameCell(i, i));
            gameMapGraph.addEdge(current, previous);
            previous = current;
        }
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
