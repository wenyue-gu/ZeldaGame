package controller.player;

import controller.MovableControl1D;
import controller.MovableControl2D;
import controller.PlayerControlInterface;
import javafx.scene.input.KeyCode;
import ooga.model.ZeldaCharacter;

public class ZeldaPlayerControl implements PlayerControlInterface, MovableControl2D, MovableControl1D {

  private ZeldaCharacter myPlayer;

  public ZeldaPlayerControl(ZeldaCharacter character){
    myPlayer = character;
  }

  @Override
  public void keyInput(KeyCode key) {
    switch (key){
      case LEFT: moveInX(-1); break;
      case RIGHT: moveInX(1); break;
      case UP: moveInY(1); break;
      case DOWN: moveInY(-1); break;
    }
  }

  @Override
  public void moveInX(double deltaX) {
    myPlayer.moveInX(deltaX);
  }

  @Override
  public void moveInY(double deltaY) {
    myPlayer.moveInY(deltaY);
  }
}
