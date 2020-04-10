package ooga.controller.gamecontrol.NPC;

import javafx.scene.input.KeyCode;
import ooga.controller.ZeldaControlInterface;
import ooga.controller.gamecontrol.JumpableControl;
import ooga.controller.gamecontrol.MovableControll1D;
import ooga.controller.gamecontrol.NPCControlInterface;
import ooga.controller.gamecontrol.PlayerControlInterface;
import ooga.model.characters.MarioCharacter;
import ooga.model.interfaces.movables.Movable1D;

import java.util.Map;

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
