package controller;

import java.util.*;

public class PlayerController implements ZeldaController{
  private playerView myPlayerView;
  private playerModel myPlayerModel;
  private Map<String, String> keyMap;

  public PlayerController (){
    myPlayerView = new playerView();
    myPlayerModel = new playerModel();
    keyMap = new HashMap<>();
  }

  public void keyInput(String key){

  }


}
