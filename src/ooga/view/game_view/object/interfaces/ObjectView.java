package ooga.view.game_view.object.interfaces;

import ooga.view.game_view.animation.interfaces.StateAnimation;
import java.util.Map;

public interface ObjectView {

  void getView(); // the returned datatype depends on the 2D/3D

  void update();

  void addStateAnimation(int ID, StateAnimation animation);

  void getStateAnimationMap(Map<Integer, StateAnimation> animationStateMap);

  void setObjectState(int ID);

  int getAnimatingState();

}
