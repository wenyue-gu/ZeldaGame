package ooga.view.game_view.agent.interfaces;

import ooga.view.game_view.animation.StateAnimation;

public interface AgentView {

  void getView(); // the returned data type depends on the view

  void addStateAnimation(int ID, StateAnimation animation);

  void setAgentState(int ID);

  int getAnimatingState();

}
