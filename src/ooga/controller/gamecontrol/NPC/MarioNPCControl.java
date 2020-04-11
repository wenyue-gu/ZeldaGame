package ooga.controller.gamecontrol.NPC;

import ooga.controller.gamecontrol.NPCControlInterface;
import ooga.model.characters.MarioCharacter;
import ooga.model.interfaces.movables.Movable1D;

public class MarioNPCControl implements NPCControlInterface {
  private MarioCharacter myNPC;

  public MarioNPCControl(){

  }



  @Override
  public void update() {

  }

  @Override
  public void setMyNPC(Movable1D NPC) {
    myNPC = (MarioCharacter)NPC;
  }
}
