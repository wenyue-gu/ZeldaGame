package ooga.controller.gamecontrol.player;

import ooga.controller.gamecontrol.PlayerControlInterface;

import java.util.*;

public class PlayerControlFactory {


  private Map<Integer, PlayerControlInterface> controlMap = new HashMap<>();

  public PlayerControlFactory(){
    fillMap();
  }

  private void fillMap(){
    controlMap.put(1, new ZeldaPlayerControl());
    controlMap.put(0, new MarioPlayerControl());
  }

  public PlayerControlInterface selectControl(int type){
    if(!controlMap.containsKey(type)) return null; //throw exception
    return controlMap.get(type);
  }
}
