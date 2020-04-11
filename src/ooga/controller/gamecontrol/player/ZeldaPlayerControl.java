package ooga.controller.gamecontrol.player;

import ooga.controller.gamecontrol.playerInterface.AttackerControl;
import ooga.controller.gamecontrol.playerInterface.MovableControll2D;
import ooga.controller.gamecontrol.PlayerControlInterface;
import javafx.scene.input.KeyCode;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.enums.Direction;
import ooga.model.enums.MovingState;
import ooga.model.interfaces.movables.Movable1D;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ZeldaPlayerControl implements PlayerControlInterface, MovableControll2D, AttackerControl, PropertyChangeListener {

  public static final String STATE = "state";
  public static final String DIRECTION = "direction";

  private ZeldaPlayer myPlayer;
  private Map<KeyCode, String> myKeyCodeMap = new HashMap<>();
  private int myID;

  public ZeldaPlayerControl(){
    myKeyCodeMap.put(KeyCode.LEFT, "left");
    myKeyCodeMap.put(KeyCode.RIGHT, "right");
    myKeyCodeMap.put(KeyCode.UP, "up");
    myKeyCodeMap.put(KeyCode.DOWN, "down");
    myKeyCodeMap.put(KeyCode.Q, "attack0");
    myKeyCodeMap.put(KeyCode.W, "attack1");
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
  public void setID() {
    myID = myPlayer.getId();
  }

  @Override
  public int getID() {
    return myID;
  }

  @Override
  public void keyInput(KeyCode key) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    if(myKeyCodeMap.get(key)==null) return;
    this.getClass().getDeclaredMethod(myKeyCodeMap.get(key)).invoke(this);

  }


  @Override
  public void up() {
    myPlayer.setState(MovingState.WALK);
    myPlayer.setDirection(Direction.NORTH);
  }

  @Override
  public void down() {
    myPlayer.setState(MovingState.WALK);
    myPlayer.setDirection(Direction.SOUTH);
  }

  @Override
  public void left() {
    myPlayer.setState(MovingState.WALK);
    myPlayer.setDirection(Direction.WEST);
  }

  @Override
  public void right() {
    myPlayer.setState(MovingState.WALK);
    myPlayer.setDirection(Direction.EAST);
  }

  @Override
  public void attack0() {
    myPlayer.setState(MovingState.ATTACK);
    myPlayer.setAttack(0);
  }

  @Override
  public void attack1() {
    myPlayer.setState(MovingState.ATTACK);
    myPlayer.setAttack(1);

  }

  @Override
  public void attack2() {
    myPlayer.setState(MovingState.ATTACK);
    myPlayer.setAttack(2);

  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    String s = evt.getPropertyName();
    switch(s){
      case STATE:
        break;
      case DIRECTION:
        break;
    }
  }
}
