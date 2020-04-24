package ooga.controller.gamecontrol.NPC;

import javafx.scene.input.KeyCode;
import ooga.controller.gamecontrol.NPCControlInterface;
import ooga.controller.gamecontrol.PlayerControlInterface;
import ooga.model.interfaces.movables.Movable1D;

import java.util.Map;

public class MainNPCControl implements NPCControlInterface {
  private NPCControlInterface myNPCControl;
  private NPCControlFactory myNPCControlFactory;

  public MainNPCControl(){
    myNPCControlFactory = new NPCControlFactory();
  }

  public  void setControl(int gameType){
    myNPCControl= myNPCControlFactory.selectControl(gameType);
  }


  @Override
  public void update() {
    myNPCControl.update();
  }

  @Override
  public void setMyNPC(Movable1D NPC) {
    myNPCControl.setMyNPC(NPC);
  }
}