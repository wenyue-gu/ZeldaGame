package ooga.view.game_view.agent;

import java.util.Map;
import ooga.view.game_view.animation.StateAnimation;

public class AbstractAgentController implements AgentController {

  @Override
  public AgentView getAgentView() {
    return null;
  }

  @Override
  public void update() {

  }

  @Override
  public void addStateAnimation(int ID, StateAnimation animation) {

  }

  @Override
  public void getStateAnimationMap(Map<Integer, StateAnimation> stateAnimationMap) {

  }

  @Override
  public void setAgentState(int ID) {

  }

  @Override
  public int getAnimatingState() {
    return 0;
  }
}
