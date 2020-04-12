package ooga.view.game_view.agent.interfaces;

import ooga.view.game_view.animation.interfaces.StateAnimation;

public class AbstractAgentView implements AgentView {

  @Override
  public void getView() {

  }

  @Override
  public void addStateAnimation(int ID, StateAnimation animation) {

  }

  @Override
  public void setAgentState(int ID) {

  }

  @Override
  public int getAnimatingState() {
    return 0;
  }
}
