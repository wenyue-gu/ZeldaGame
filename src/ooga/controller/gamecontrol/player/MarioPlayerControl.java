package ooga.controller.gamecontrol.player;

import ooga.controller.gamecontrol.JumpableControl;
import ooga.controller.gamecontrol.MovableControll1D;
import ooga.controller.gamecontrol.PlayerControlInterface;
import javafx.scene.input.KeyCode;
import ooga.model.characters.MarioCharacter;
import ooga.model.characters.MarioPlayer;
import ooga.model.interfaces.movables.Movable1D;

import java.util.Map;

public class MarioPlayerControl implements PlayerControlInterface, MovableControll1D, JumpableControl {

  private MarioPlayer myPlayer;
  private Map<String, KeyCode> myKeyCodeMap;

  public MarioPlayerControl(){

  }

  @Override
  public void setMyPlayer(Movable1D myPlayer) {
    this.myPlayer = (MarioPlayer)myPlayer;
  }

  @Override
  public void setKeyCodeMap(Map<String, KeyCode> map) {
    myKeyCodeMap = map;
  }

  @Override
  public void keyInput(KeyCode key) {
    if(key==myKeyCodeMap.get("left")) left(1);
    else if (key==myKeyCodeMap.get("right")) right(1);
    else if (key==myKeyCodeMap.get("up")) jump();
  }


  @Override
  public void jump() {
    myPlayer.jump();
  }

  @Override
  public void left(double deltaX) {
    myPlayer.moveInX(-1*deltaX);
  }

  @Override
  public void right(double deltaX) {
    myPlayer.moveInX(deltaX);
  }
}
