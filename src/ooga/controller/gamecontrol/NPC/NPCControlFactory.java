package ooga.controller.gamecontrol.NPC;

import ooga.controller.gamecontrol.NPCControlInterface;

import java.util.HashMap;
import java.util.Map;

public class NPCControlFactory {


  private Map<Integer, NPCControlInterface> controlMap = new HashMap<>();

  public NPCControlFactory(){
    fillMap();
  }

  private void fillMap(){
    controlMap.put(2, new SoldierControl());
    controlMap.put(1, new BigBoyControl());
//    controlMap.put(0, new MarioNPCControl());
  }

  public NPCControlInterface selectControl(int type){
    if(!controlMap.containsKey(type)) return null; //throw exception
    return controlMap.get(type);
  }
}
