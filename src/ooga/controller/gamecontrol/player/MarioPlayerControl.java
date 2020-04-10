package ooga.controller.gamecontrol.player;

import ooga.controller.gamecontrol.playerInterface.AttackerControl;
import ooga.controller.gamecontrol.playerInterface.JumpableControl;
import ooga.controller.gamecontrol.playerInterface.MovableControll1D;
import ooga.controller.gamecontrol.PlayerControlInterface;
import javafx.scene.input.KeyCode;
import ooga.model.characters.MarioPlayer;
import ooga.model.enums.Direction;
import ooga.model.enums.MovingState;
import ooga.model.interfaces.movables.Movable1D;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class MarioPlayerControl implements PlayerControlInterface, MovableControll1D, JumpableControl, AttackerControl {

  private MarioPlayer myPlayer;
  private Map<KeyCode, String> myKeyCodeMap;
  private int myID;

  public MarioPlayerControl(){

  }

  @Override
  public void setMyPlayer(Movable1D myPlayer) {
    this.myPlayer = (MarioPlayer)myPlayer;
  }

  @Override
  public void setKeyCodeMap(Map<KeyCode, String> map) {
    myKeyCodeMap = map;
  }

  @Override
  public void setID(int id) {
    myID = id;
  }

  @Override
  public void keyInput(KeyCode key) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    this.getClass().getDeclaredMethod(myKeyCodeMap.get(key)).invoke(this);
  }


  @Override
  public void jump() {
    myPlayer.setState(MovingState.JUMP);
    myPlayer.setDirection(Direction.UP);
  }

  @Override
  public void left() {
    myPlayer.setState(MovingState.RUN);
    myPlayer.setDirection(Direction.WEST);
  }

  @Override
  public void right() {
    myPlayer.setState(MovingState.RUN);
    myPlayer.setDirection(Direction.EAST);
  }

  @Override
  public void attack1() {
    myPlayer.setState(MovingState.MELEE_ATTACK);

  }

  @Override
  public void attack2() {
    myPlayer.setState(MovingState.MELEE_ATTACK);
  }

  @Override
  public void attack3() {
    myPlayer.setState(MovingState.MELEE_ATTACK);
  }
}
