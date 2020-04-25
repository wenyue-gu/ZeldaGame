package ooga.controller.gamecontrol.NPC;

import static ooga.controller.gamecontrol.player.ZeldaPlayerControl.PROPERTY_MOVING_DIRECTION;
import static ooga.controller.gamecontrol.player.ZeldaPlayerControl.PROPERTY_STATE;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import ooga.controller.gamecontrol.NPCControlInterface;
import ooga.game.GameZelda2DSingle;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.backend.Direction;
import ooga.model.enums.backend.MovingState;
import ooga.model.interfaces.movables.Movable1D;

public class BigBoyControl implements NPCControlInterface, PropertyChangeListener {

  private ZeldaCharacter myNPC;
  private int myID;
  private GameZelda2DSingle myView;
  private int attackCounter = 0;
  private MovingState lastState = MovingState.IDLE;

  public BigBoyControl(){
  }

  public void setMyNPC(Movable1D myNPC) {
    this.myNPC = (ZeldaCharacter) myNPC;
    this.myNPC.addListener(this);
  }

  @Override
  public void setID() {
    myID = myNPC.getId();
  }

  public int getID() {
    return myID;
  }

  @Override
  public void attack() {
    myNPC.setState(MovingState.ATTACK);
  }

  public void setView(GameZelda2DSingle view) {
    myView = view;
  }

  public Movable1D getNPC() {
    return myNPC;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    String s = evt.getPropertyName();
    switch (s) {
      case PROPERTY_STATE:
      case PROPERTY_MOVING_DIRECTION:
//        System.out.println("notified");
        myView
            .updateCharacter(myID, myNPC.getDirection().toString(),
                myNPC.getState().toString(), (myNPC.getState() == MovingState.ATTACK));
    }
  }

  public void death() {
    myNPC.setState(MovingState.DEATH);
    myNPC.setDirection(Direction.E);
  }

  @Override
  public void update() {
    if (myNPC.getState() == MovingState.ATTACK && attackCounter > 500) {
      myNPC.setState(MovingState.IDLE);
      attackCounter = 0;
    } else {
      attackCounter ++;
    }
  }
}
