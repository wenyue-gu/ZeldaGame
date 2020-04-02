package controller.player;

import controller.PlayerControlInterface;
import ooga.model.MarioCharacter;
import ooga.model.ZeldaCharacter;

import java.util.*;

public class PlayerControlFactory {

  //private Map<String, Player> playerMap = new HashMap<>();

  //TODO: maybe make it final? since it contains all possible characters supported by our game and shouldn't be able to change
  private Map<String, PlayerControlInterface> controlMap = new HashMap<>();

  public PlayerControlFactory(){
    fillMap();
  }

  private void fillMap(){
    controlMap.put("Zelda", new ZeldaPlayerControl(new ZeldaCharacter()));
    controlMap.put("Mario", new MarioPlayerControl(new MarioCharacter()));
  }

  public PlayerControlInterface selectControl(String type){
    if(!controlMap.containsKey(type)) return null; //throw exception
    return controlMap.get(type);
  }
}
