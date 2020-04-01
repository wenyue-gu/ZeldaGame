package controller;


import javafx.scene.input.KeyCode;
import java.util.List;
import java.util.Map;


public class MainController{
  private MapController myMapController;
  private Map<ZeldaController, List<KeyCode>> keyMap;

  public MainController(){
    myMapController = new MapController();
    initializeMap();

  }

  public void handleKeyInput(KeyCode code){
    for(ZeldaController z:keyMap.keySet()){
      if(keyMap.get(z).contains(code)) z.keyInput(code.getName());
    }
  }

  private void initializeMap(){

  }
}
