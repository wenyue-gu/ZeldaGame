package ooga.view.game_view.object.interfaces;

import java.util.Map;
import ooga.view.game_view.animation.interfaces.StateAnimation;

public class AbstractObjectView implements ObjectView {

  @Override
  public void getView() {

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
