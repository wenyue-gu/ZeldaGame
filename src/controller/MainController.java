package controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import ooga.model.ZeldaCharacter;

import java.util.*;

public class MainController implements ZeldaController{
  private MapController myMapController;
  private PlayerController myPlayerController;
  //private MainView myMainView;
  private Map<ZeldaController, List<KeyCode>> keyMap = new HashMap<>();

  public MainController(){
    myMapController = new MapController();
    myPlayerController = new PlayerController(new ZeldaCharacter());
    //myMainView = new MainView();

    initializeMap();

  }

  @Override
  public void keyInput(KeyCode code) {
//    for(ZeldaController z:keyMap.keySet()){
//      if(keyMap.get(z).contains(code)) z.keyInput(code);
//    }

  }

  private void initializeMap(){

  }

//  public Scene getScene(){
//
//    Scene myScene = myMainView.getScene();
//    myScene.setOnKeyPressed(e -> keyInput(e.getCode().getName()));
//    return myScene;
//  }
}
