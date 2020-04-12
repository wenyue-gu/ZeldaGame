package ooga.data;


import com.google.gson.*;
import ooga.model.map.GameCell;

import java.lang.reflect.Type;

/**
 * Modified From https://technology.finra.org/code/serialize-deserialize-interfaces-in-java.html
 * @author Guangyu Feng
 */


public class InterfaceAdapter implements JsonSerializer, JsonDeserializer{

    private static final String CLASSNAME = "CLASSNAME";
    private static final String DATA = "DATA";

    public Object deserialize(JsonElement jsonElement, Type type,
                         JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        //JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
//        String className = prim.getAsString();
        String className = "ooga.model.map.GameCell";
        Class klass = getObjectClass(className);
        GameCell a = jsonDeserializationContext.deserialize(jsonObject, klass);
        return a;
    }
    public JsonElement serialize(Object jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(CLASSNAME, jsonElement.getClass().getName());
        jsonObject.add(DATA, jsonSerializationContext.serialize(jsonElement));
        return jsonObject;
    }
    /****** Helper method to get the className of the object to be deserialized *****/
    public Class getObjectClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            throw new JsonParseException(e.getMessage());
        }
    }
}

//import com.google.gson.InstanceCreator;
//import ooga.model.interfaces.gameMap.Cell;
//
//import java.lang.reflect.Type;
//
//public class InterfaceAdapter implements InstanceCreator<Cell> {
//    private int ID;
//    InterfaceAdapter(int ID) {
//        this.ID = ID;
//    }
//    @Override
//    public Cell createInstance(Type type) {
//        Cell gameCell = new GameCell(ID);
//        return gameCell;
//    }
//}