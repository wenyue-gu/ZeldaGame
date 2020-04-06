package ooga.controller.gamecontrol.player;

import ooga.controller.gamecontrol.MovableControll2D;
import ooga.controller.gamecontrol.PlayerControlInterface;
import javafx.scene.input.KeyCode;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.interfaces.movables.Movable1D;

public class ZeldaPlayerControl implements PlayerControlInterface, MovableControll2D {

  private ZeldaCharacter myPlayer;

  public ZeldaPlayerControl(){

  }

  @Override
  public void setMyPlayer(Movable1D myPlayer) {
    this.myPlayer = (ZeldaCharacter)myPlayer;
  }

  @Override
  public void keyInput(KeyCode key) {
    switch (key){
      case LEFT: left(1); break;
      case RIGHT: right(1); break;
      case UP: up(1); break;
      case DOWN: down(1); break;
    }
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
