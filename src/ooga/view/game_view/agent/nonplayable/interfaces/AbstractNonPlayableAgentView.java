package ooga.view.game_view.agent.nonplayable.interfaces;

import ooga.view.game_view.animation.StateAnimation;

public class AbstractNonPlayableAgentView implements NonPlayableAgentView {

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
