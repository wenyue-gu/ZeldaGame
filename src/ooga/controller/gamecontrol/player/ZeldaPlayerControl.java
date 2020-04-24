package ooga.controller.gamecontrol.player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;
import ooga.controller.gamecontrol.PlayerControlInterface;
import ooga.controller.gamecontrol.playerInterface.AttackerControl;
import ooga.controller.gamecontrol.playerInterface.MovableControll2D;
import ooga.game.GameZelda2DSingle;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.enums.backend.Direction;
import ooga.model.enums.backend.MovingState;
import ooga.model.interfaces.movables.Movable1D;

public class ZeldaPlayerControl implements PlayerControlInterface, MovableControll2D,
    AttackerControl, PropertyChangeListener {

  public static final String PROPERTY_STATE = "state";
  public static final String PROPERTY_MOVING_DIRECTION = "direction";

  private ZeldaPlayer myPlayer;
  //private Player2DView playerView;
  private GameZelda2DSingle myView;
  private Map<KeyCode, String> myKeyCodeMap = new HashMap<>();
  private Map<Integer, String> myGLFWMap = new HashMap<>();
  private int myID;


  public ZeldaPlayerControl() {
//    //keycode map hard code
//    myKeyCodeMap.put(KeyCode.LEFT, "left");
//    myKeyCodeMap.put(KeyCode.RIGHT, "right");
//    myKeyCodeMap.put(KeyCode.UP, "up");
//    myKeyCodeMap.put(KeyCode.DOWN, "down");
//    myKeyCodeMap.put(KeyCode.Q, "attack0");
//    myKeyCodeMap.put(KeyCode.W, "attack1");
//
//    //glfw map hard code
//    myGLFWMap.put(GLFW.GLFW_KEY_LEFT, "left");
//    myGLFWMap.put(GLFW.GLFW_KEY_RIGHT, "right");
//    myGLFWMap.put(GLFW.GLFW_KEY_UP, "up");
//    myGLFWMap.put(GLFW.GLFW_KEY_DOWN, "down");
//    myGLFWMap.put(GLFW.GLFW_KEY_Q, "attack0");
//    myGLFWMap.put(GLFW.GLFW_KEY_W, "attack1");

  }

  @Override
  public void setMyPlayer(Movable1D myPlayer) {
    this.myPlayer = (ZeldaPlayer) myPlayer;
    this.myPlayer.addListener(this);
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
  public void keyReleased() {
    myPlayer.setState(MovingState.IDLE);
  }

  @Override
  public void keyInput(KeyCode key)
          throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    if (myKeyCodeMap.get(key) == null) {
      return;
    }
    this.getClass().getDeclaredMethod(myKeyCodeMap.get(key)).invoke(this);

  }


  @Override
  public void up() {
    myPlayer.setState(MovingState.SPRINT);
    myPlayer.setDirection(Direction.N);
  }

  @Override
  public void down() {
    myPlayer.setState(MovingState.SPRINT);
    myPlayer.setDirection(Direction.S);
  }

  @Override
  public void left() {
//    myPlayer.setState(MovingState.SPRINT);
//    myPlayer.setDirection(Direction.W);
  }

  @Override
  public void right() {
    myPlayer.setState(MovingState.SPRINT);
    myPlayer.setDirection(Direction.E);
  }

  @Override
  public void attack0() {
    myPlayer.setState(MovingState.ATTACK1);
    myPlayer.setAttack(0);
  }

  @Override
  public void attack1() {
    myPlayer.setState(MovingState.ATTACK1);
    myPlayer.setAttack(1);

  }

  @Override
  public void attack2() {
    myPlayer.setState(MovingState.ATTACK3);
    myPlayer.setAttack(2);

  }


  public void sprintSE(){
    myPlayer.setState(MovingState.SPRINT);
    myPlayer.setDirection(Direction.SE);
  }

  public void death(){
    myPlayer.setState(MovingState.DEATH);
    myPlayer.setDirection(Direction.E);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    String s = evt.getPropertyName();
    System.out.println(s);
    switch (s) {
      case PROPERTY_STATE:
      case PROPERTY_MOVING_DIRECTION:
        myView.updatePlayer(myID, myPlayer.getDirection().toString(), myPlayer.getState().toString());
    }
  }

  @Override
  public void updateKey() {
    try {
      for (int i : myGLFWMap.keySet()) {
        if (myView.getView().isKeyDown(i)){
          this.getClass().getDeclaredMethod(myGLFWMap.get(i)).invoke(this);
          System.out.println(myPlayer.getDirection().toString());
          System.out.println(myPlayer.getState().toString());
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("key map fault");
    }
  }

  @Override
  public void setView(GameZelda2DSingle view) {
    myView = view;
  }

  @Override
  public void setNewKeyMap(Map<Integer, String> map) {
    if(map!=null) myGLFWMap = map;
  }

  @Override
  public Movable1D getPlayer() {
    return myPlayer;
  }

  @Override
  public boolean checkScore(int score) {
    return score<=myPlayer.getScore();
  }

  public Map<KeyCode, String> getKeyCodeMap() {
    return myKeyCodeMap;
  }

  public Map<Integer, String> getKeyMap() {
    return myGLFWMap;
  }
}
