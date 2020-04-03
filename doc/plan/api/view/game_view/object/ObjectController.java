package ooga.view.game_view.object;

import ooga.view.game_view.animation.StateAnimation;
import java.util.Map;

public interface ObjectController {

  ObjectView getObjectView();

  void update();

  void addStateAnimation(int ID, StateAnimation animation);

  void getStateAnimationMap(Map<Integer, StateAnimation> animationStateMap);

  void setObjectState(int ID);

  int getAnimatingState();

}
