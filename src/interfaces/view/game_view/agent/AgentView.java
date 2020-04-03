package interfaces.view.game_view.agent;

import interfaces.view.game_view.animation.StateAnimation;

public interface AgentView {

  void getView(); // the returned data type depends on the view

  void addStateAnimation(int ID, StateAnimation animation);

  void setAgentState(int ID);

  int getAnimatingState();

}
