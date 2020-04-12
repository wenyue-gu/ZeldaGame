package ooga.view.game_view.agent.interfaces;

import ooga.view.game_view.animation.interfaces.StateAnimation;
import java.util.Map;

public interface AgentController {

  AgentView getAgentView(); // the returned data type depends on the 2D/3D.

  void update();

  void addStateAnimation(int ID, StateAnimation animation);

  void getStateAnimationMap(Map<Integer, StateAnimation> stateAnimationMap);

  void setAgentState(int ID);

  int getAnimatingState();

}
