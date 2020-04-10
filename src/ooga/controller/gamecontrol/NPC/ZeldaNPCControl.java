package ooga.controller.gamecontrol.NPC;

import javafx.scene.input.KeyCode;
import ooga.controller.gamecontrol.MovableControll2D;
import ooga.controller.gamecontrol.NPCControlInterface;
import ooga.controller.gamecontrol.PlayerControlInterface;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.interfaces.movables.Movable1D;

import java.util.Map;

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
