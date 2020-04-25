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

public class SoldierControl implements NPCControlInterface, PropertyChangeListener {

  private ZeldaCharacter myNPC;
  private int myID;
  private GameZelda2DSingle myView;

  public SoldierControl(){
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
        myView
            .updateCharacter(myID, myNPC.getDirection().toString(),
                myNPC.getState().toString(), (myNPC.getState() == MovingState.ATTACK1));
    }
  }

  public void death() {
    myNPC.setState(MovingState.DEATH);
    myNPC.setDirection(Direction.E);
  }

  @Override
  public void update() {
    myNPC.setState(MovingState.ATTACK);
  }
}
