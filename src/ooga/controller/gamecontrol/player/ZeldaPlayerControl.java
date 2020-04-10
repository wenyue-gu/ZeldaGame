package ooga.controller.gamecontrol.player;

import ooga.controller.gamecontrol.MovableControll2D;
import ooga.controller.gamecontrol.PlayerControlInterface;
import javafx.scene.input.KeyCode;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.interfaces.movables.Movable1D;

import java.util.Map;

public class ZeldaPlayerControl implements PlayerControlInterface, MovableControll2D {

  private ZeldaCharacter myPlayer;
  private Map<String, KeyCode> myKeyCodeMap;

  public ZeldaPlayerControl(){

  }

  @Override
  public void setMyPlayer(Movable1D myPlayer) {
    this.myPlayer = (ZeldaCharacter)myPlayer;
  }

  @Override
  public void setKeyCodeMap(Map<String, KeyCode> map) {
    myKeyCodeMap = map;
  }

  @Override
  public void keyInput(KeyCode key) {
    if(key==myKeyCodeMap.get("left")) left(1);
    else if (key==myKeyCodeMap.get("right")) right(1);
    else if (key==myKeyCodeMap.get("up")) up(1);
    else if (key==myKeyCodeMap.get("down")) down(1);
  }

  @Override
  public void left(double deltaX) {
    myPlayer.moveInX(-1*deltaX);
  }

  @Override
  public void right(double deltaX) {
    myPlayer.moveInX(deltaX);
  }

  @Override
  public void up(double deltaY) {
    myPlayer.moveInY(deltaY);
  }

  @Override
  public void down(double deltaY) {
    myPlayer.moveInX(-1*deltaY);
  }
}
