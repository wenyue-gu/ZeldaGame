package ooga.view.game_view.agent.nonplayable;

import java.util.Map;
import ooga.view.game_view.animation.StateAnimation;
import ooga.view.game_view.object.ObjectView;
import ooga.view.game_view.object.non_interactive.NonInteractiveObjectController;

public class AbstractNonPlayableAgentController implements NonInteractiveObjectController {

  @Override
  public ObjectView getObjectView() {
    return null;
  }

  @Override
  public void update() {

  }

  @Override
  public void addStateAnimation(int ID, StateAnimation animation) {

  }

  @Override
  public void getStateAnimationMap(Map<Integer, StateAnimation> animationStateMap) {

  }

  @Override
  public void setObjectState(int ID) {

  }

  @Override
  public int getAnimatingState() {
    return 0;
  }
}
