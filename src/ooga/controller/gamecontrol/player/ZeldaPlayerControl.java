package ooga.controller.gamecontrol.player;

import ooga.controller.gamecontrol.MovableControll2D;
import ooga.controller.gamecontrol.PlayerControlInterface;
import javafx.scene.input.KeyCode;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.enums.Direction;
import ooga.model.enums.MovingState;
import ooga.model.interfaces.movables.Movable1D;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ZeldaPlayerControl implements PlayerControlInterface, MovableControll2D {

  private ZeldaPlayer myPlayer;
  private Map<KeyCode, String> myKeyCodeMap;
  private int myID;

  public ZeldaPlayerControl(){

  }

  @Override
  public void setMyPlayer(Movable1D myPlayer) {
    this.myPlayer = (ZeldaPlayer)myPlayer;
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
  public void up() {
    myPlayer.setState(MovingState.RUN);
    myPlayer.setDirection(Direction.NORTH);
  }

  @Override
  public void down() {
    myPlayer.setState(MovingState.RUN);
    myPlayer.setDirection(Direction.SOUTH);
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
}
