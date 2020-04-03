package controller.player;

import controller.PlayerControlInterfaceInterface;
import ooga.model.MarioCharacter;
import ooga.model.ZeldaCharacter;

import java.util.*;

public class PlayerControlFactory {

  //private Map<String, Player> playerMap = new HashMap<>();

  //TODO: maybe make it final? since it contains all possible characters supported by our game and shouldn't be able to change
  private Map<String, PlayerControlInterfaceInterface> controlMap = new HashMap<>();

  public PlayerControlFactory(){
    fillMap();
  }

  private void fillMap(){
    controlMap.put("Zelda", new ZeldaPlayerControl());
    controlMap.put("Mario", new MarioPlayerControl());
  }

  public PlayerControlInterfaceInterface selectControl(String type){
    if(!controlMap.containsKey(type)) return null; //throw exception
    return controlMap.get(type);
  }
}
