package controller.player;

import controller.JumpableControl;
import controller.MovableControl1D;
import controller.PlayerControlInterfaceInterface;
import javafx.scene.input.KeyCode;
import ooga.model.MarioCharacter;

public class MarioPlayerControl implements PlayerControlInterfaceInterface, MovableControl1D, JumpableControl {

  private MarioCharacter myPlayer;

  public MarioPlayerControl(MarioCharacter character){
    myPlayer = character;
  }

  @Override
  public void keyInput(KeyCode key) {
    switch (key){
        case LEFT: moveInX(-1); break;
        case RIGHT: moveInX(1); break;
        case UP: jump(); break;
    }
  }

  @Override
  public void update() {

  }

  @Override
  public void moveInX(double deltaX) {
    myPlayer.moveInX(deltaX);
  }

  @Override
  public void jump() {
    myPlayer.jump();
  }
}
