package ooga.view.game_view.animation.interfaces;

import ooga.view.engine.graphics.animation.Animation2D;

abstract public class AnimationDict {

  public AnimationDict(){}

  abstract public void setInUseAnimation (String direction, String action);

  //abstract public Animation2D getAnimation();
}
