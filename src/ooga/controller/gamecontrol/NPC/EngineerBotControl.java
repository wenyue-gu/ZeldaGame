package ooga.controller.gamecontrol.NPC;

import java.beans.PropertyChangeListener;
import ooga.controller.gamecontrol.NPCControlInterface;
import ooga.model.enums.backend.MovingState;

public class EngineerBotControl extends NPCSubControl implements NPCControlInterface, PropertyChangeListener {

//  @Override
//  public void attack() {
//    switch (new Random().nextInt(4)) {
//      case 0:
//        myNPC.setState(MovingState.ATTACK);
//        break;
//      case 1:
//        myNPC.setState(MovingState.SUMMON_BIGBOY);
//        break;
//      case 2:
//        myNPC.setState(MovingState.SUMMON_TURRET);
//        break;
//      case 3:
//        myNPC.setState(MovingState.SUMMON_WATCHER);
//        break;
//    }
//  }

  @Override
  protected boolean isAttacking() {
    return myNPC.getState() == MovingState.ATTACK ||
        myNPC.getState() == MovingState.SUMMON_BIGBOY ||
        myNPC.getState() == MovingState.SUMMON_TURRET ||
        myNPC.getState() == MovingState.SUMMON_WATCHER;
  }
}
