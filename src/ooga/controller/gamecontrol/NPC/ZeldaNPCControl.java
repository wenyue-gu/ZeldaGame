package ooga.controller.gamecontrol.NPC;

import ooga.controller.gamecontrol.NPCControlInterface;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.interfaces.movables.Movable1D;

public class ZeldaNPCControl implements NPCControlInterface {

  private ZeldaCharacter myNPC;

  public ZeldaNPCControl(){

  }

  @Override
  public void setMyNPC(Movable1D NPC) {
    myNPC = (ZeldaCharacter)NPC;
  }

  @Override
  public void update() {

  }
}
